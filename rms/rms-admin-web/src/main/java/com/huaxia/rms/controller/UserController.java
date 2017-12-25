package com.huaxia.rms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.rms.annotation.RefreshCSRFToken;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.*;
import com.huaxia.rms.core.service.*;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.utils.HanyuPinyinHelper;
import com.huaxia.rms.utils.Md5PasswordEncoder;
import com.huaxia.rms.annotation.VerifyCSRFToken;
import com.huaxia.rms.pojo.PageInfo;

import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liuzhilai on 2017/10/18.
 * 用戶管理控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RmsUserService userService;

    @Resource
    private RmsRoleService roleService;

    @Resource
    private RmsUserRoleService userRoleService;

    @Resource
    private RmsLabelService labelService;

    @Resource
    private RmsUserLabelService userLabelService;

    @Resource
    private RmsOrganizationService organizationService;

    @VerifyCSRFToken
    @RequestMapping(method = RequestMethod.POST, value = "/saveLabel.do")
    @ResponseBody
    @Login(AuthenType.json)
    private Map saveUserLabel(String labelName, HttpServletRequest request) {

        Map map = new HashMap();
        try {
            Authorization authorization = WebUtils.getSessionUser(request);
            if (StringUtils.isNotBlank(labelName)) {
                //判断此name是否已经存在
                RmsLabel label = userService.findLabelByName(labelName);
                if(label != null) {
                    map.put("isSucc", "0");
                    map.put("message", "该标签已存在");
                    return map;
                }
                label = new RmsLabel();
                label.setCode(String.valueOf(System.currentTimeMillis()));
                label.setName(labelName);
                label.setCreateUser(authorization.getUsername());
                userService.createRmsLabel(label);
                map.put("isSucc", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSucc", "0");
            map.put("message", "系统异常，请稍后再试");
        }

        return map;
    }

    @Value("${user.initPassword}")
    private String initPassword;

    @RequestMapping(value = "/label/{multiple}/{ids}/{callback}", method = RequestMethod.GET)
    public String selectUserLabelPage(@PathVariable("multiple") String multiple,
                                 @PathVariable("ids") String ids,
                                 @PathVariable("callback") String callback, HttpServletRequest request) {

        request.setAttribute("multiple", multiple);
        request.setAttribute("ids", ids);
        request.setAttribute("callback",callback);
        return "base/user/user_label_select";
    }

    @RequestMapping(value = "/select/{multiple}/{ids}/{callback}", method = RequestMethod.GET)
    public String selectUserPage(@PathVariable("multiple") String multiple,
                                 @PathVariable("ids") String ids,
                                 @PathVariable("callback") String callback, HttpServletRequest request) {

        request.setAttribute("multiple", multiple);
        request.setAttribute("ids", ids);
        request.setAttribute("callback",callback);
        return "base/user/user_select_list";
    }

    @RequestMapping(value = "/names.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getNamesByIds(String ids) {
        Map<String,String> map=new HashMap<>();
        String names = userService.getUserNamesByUserIds(ids);
        map.put("name",names);
        return map;

    }

    @RequestMapping(value = "/label/names.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getNamesByLabelIds(String ids) {
        Map<String,String> map = new HashMap<>();
        String names = labelService.getNamesByIds(ids);
        map.put("name",names);
        return map;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/get")
    @ResponseBody
    private RmsUser getUser(String id, String code) {
        RmsUser user = null;
        if(StringUtils.isNotBlank(id)) {
            user = userService.findOneById(Integer.parseInt(id));
        } else if(StringUtils.isNotBlank(code)) {
            user = userService.findOneByCode(code);
        }

        /**
         * 获取用户角色ID
         */
        List<RmsRole> roles = roleService.getRoleList(user.getId());
        if(roles != null && roles.size() > 0) {
            StringBuilder roleIds = new StringBuilder();
            for (RmsRole role: roles) {
                roleIds.append(role.getId()).append(",");
            }

            user.setRoles(roleIds.deleteCharAt(roleIds.length() - 1).toString());
        }

        /**
         * 获取用户标签信息
         */
        List<RmsLabel> labels = userService.findLabelByUcode(user.getId());
        if(labels != null && labels.size() > 0) {
            StringBuilder labelIds = new StringBuilder();
            for (RmsLabel label : labels ) {
                labelIds.append(label.getId()).append(",");
            }
            user.setLabelIds(labelIds.deleteCharAt(labelIds.length() - 1).toString());
        }


//        if(user != null) {
//            String plannerCode = user.getFinancialPlanner();
//            if(StringUtils.isNotBlank(plannerCode)) {
//                RmsUser planner = userService.findOneByCode(plannerCode);
//                user.setPlanner(planner);
//            }
//
//        }

        return user;
    }

    @VerifyCSRFToken
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    @Login(AuthenType.json)
    private Result saveUser(@RequestBody RmsUser user, HttpServletRequest request) {

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            //新增操作
            if (StringUtils.isBlank(String.valueOf(user.getId())) || "null".equals(String.valueOf(user.getId()))) {
                //设置初始密码
                String passwd = initPassword;
                if(StringUtils.isNotBlank(user.getPassword())) {
                    passwd = user.getPassword();
                }
//                user.setPassword(EncryptUtil.getPassword(passwd, user.getName()));
                user.setPassword(Md5PasswordEncoder.encodeSaltPassword(passwd, user.getName()));

                //设置用户的部门全称：总部->信息技术部->系统运维处
                String orgCode = user.getOrgCode();
                if(StringUtils.isNotBlank(orgCode)) {
                    Result result = organizationService.getOrgNames(orgCode);
                    Map<String, String> orgNameMap = (Map<String, String>)result.getData();
                    String orgFullName = orgNameMap.get("parentName");
                    user.setOrgName(orgFullName);
                }

                userService.createRmsUser(user);
                //新增后返回ID
                logger.info("************userId: " + user.getId());

                /**
                 * 用户信息明细
                 */
//                RmsUserDetail userDetail = new RmsUserDetail();
                RmsUserDetail userDetail = user.getUserDetail();
                if(userDetail == null) {
                    userDetail = new RmsUserDetail();
                }
                userDetail.setUserId(user.getId());
                userDetail.setCreateUser(authorization.getUsername());
                userService.createRmsUserDetail(userDetail);

                /**
                 * 新增用户角色内容
                 */
                logger.info("---------user.roles: " + user.getRoles());
                if(StringUtils.isNotBlank(user.getRoles())) {
                    String roleIds[] = user.getRoles().split(",");

                    List<RmsUserRole> urs = new ArrayList<RmsUserRole>();
                    for (String roleid : roleIds) {
                        RmsUserRole ur = new RmsUserRole();
                        ur.setUserId(user.getId());
                        ur.setRoleId(Integer.parseInt(roleid));
                        ur.setCreateUser(authorization.getUsername());//session中获取
                        urs.add(ur);
                    }
                    logger.info("--------新增的角色信息: " + urs);
                    userRoleService.batchSave(urs);
                }

                /**
                 * 新增用户标签
                 */
                logger.info("---------user.getLabelIds: " + user.getLabelIds());
                if(StringUtils.isNotBlank(user.getLabelIds())) {
                    String labelIds[] = user.getLabelIds().split(",");

                    List<RmsUserLabel> urs = new ArrayList<RmsUserLabel>();
                    for (String labelid : labelIds) {
                        RmsUserLabel ul = new RmsUserLabel();
                        ul.setUserId(user.getId());
                        ul.setLabelId(Integer.parseInt(labelid));
                        ul.setCreatedUser(authorization.getUsername());//session中获取
                        urs.add(ul);
                    }
                    logger.info("--------新增的用户标签信息: " + urs);
                    userLabelService.saveBatchUserLabel(urs);
                }

//            //头像和用户管理
//            userService.updateUserAvatar(user, request.getRealPath("/"));

            //更新操作
            } else {
                RmsUser oldUser = userService.findOneById(user.getId());

                //--------------------------------------记录用户修改信息 begin---------------------------------------------
                /**
                 * 记录用户更新日志
                 */
                RmsUserUpdateRecord record = new RmsUserUpdateRecord();
                BeanUtils.copyProperties(oldUser,record);
                BeanUtils.copyProperties(oldUser.getUserDetail(),record);
                //获取old user的角色信息
                List<RmsRole> roles = roleService.getRoleList(oldUser.getId());
                if(roles != null & roles.size() > 0) {
                    StringBuilder roleNames = new StringBuilder();
                    for (RmsRole role: roles) {
                        roleNames.append(role.getName()).append(",");
                    }
                    if(roleNames.length() > 0) {
                        record.setRoleNames(roleNames.deleteCharAt(roleNames.length() - 1).toString());
                    }
                }
                //获取old user的理财师信息
                if(oldUser.getFinancialPlanner() > 0) {
                    RmsUser planner = userService.findOneById(oldUser.getFinancialPlanner());
                    record.setFplannerName(planner.getRealname());
                }

                //--------------------------------------记录用户修改信息 end---------------------------------------------

                //设置用户的部门全称：总部->信息技术部->系统运维处
                if(StringUtils.isNotBlank(user.getOrgCode()) && !user.getOrgCode().equals(oldUser.getOrgCode())) {
                    String orgCode = user.getOrgCode();
                    if(StringUtils.isNotBlank(orgCode)) {
                        Result result = organizationService.getOrgNames(orgCode);
                        Map<String, String> orgNameMap = (Map<String, String>)result.getData();
                        String orgFullName = orgNameMap.get("parentName");
                        user.setOrgName(orgFullName);
                    }
                }

                /**
                 * 新修改的用户信息复制到oldUser里面
                 */
                BeanUtils.copyProperties(user,oldUser);
                /**
                 * 更新操作的时候
                 * 如果用户没有设置密码，则原始密码不变
                 */
                if(StringUtils.isNotBlank(user.getPassword())) {
                    logger.info("------更新用戶密碼： " + user.getPassword());
                    //oldUser.setPassword(EncryptUtil.getPassword(user.getPassword(), user.getName()));
                    oldUser.setPassword(Md5PasswordEncoder.encodeSaltPassword(user.getPassword(), user.getName()));
                }

                RmsUserDetail userDetail = user.getUserDetail();
                if(userDetail == null) {
                    userDetail = userService.findOneByUserId(user.getId());
                }
                if(userDetail != null) {
                    userDetail.setUserId(user.getId());
                    userDetail.setUpdateTime(new Date());//删除状态
                    userDetail.setUpdateUser(authorization.getUsername());//session中获取
                    userDetail.setUpdateUserid(authorization.getId());//session中获取
                    userService.updateRmsUserDetail(userDetail);
                }
                userService.updateRmsUser(oldUser);

                /**
                 * 更新用户角色内容
                 * 先删除老的角色信息，
                 * 然后新增新角色信息
                 */
                logger.info("---------user.roles: " + user.getRoles());
                if(StringUtils.isNotBlank(user.getRoles())) {
                    //删除老的角色信息
                    Map map = new HashMap();
                    map.put("userId", user.getId());
                    map.put("username", authorization.getUsername());//session中获取
                    userRoleService.deleteUserRoleByUid(map);

                    //新增新的角色信息
                    String roleIds[] = user.getRoles().split(",");
                    List<RmsUserRole> urs = new ArrayList<RmsUserRole>();
                    for (String roleid : roleIds) {
                        RmsUserRole ur = new RmsUserRole();
                        ur.setUserId(user.getId());
                        ur.setRoleId(Integer.parseInt(roleid));
                        ur.setCreateUser(authorization.getUsername());//session中获取
                        urs.add(ur);
                    }
                    logger.info("--------更新的角色信息: " + urs);
                    userRoleService.batchSave(urs);

                }

                /**
                 * 更新用户标签内容
                 * 先删除老的标签信息，
                 * 然后新增新标签信息
                 */
                logger.info("---------user.getLabelIds: " + user.getLabelIds());
                if(StringUtils.isNotBlank(user.getLabelIds())) {
                    //删除老的角色信息
                    Map map = new HashMap();
                    map.put("userId", user.getId());
                    map.put("username", authorization.getUsername());//session中获取
                    userLabelService.deleteUserLabelByUid(map);

                    //新增新的角色信息
                    String labelIds[] = user.getLabelIds().split(",");
                    List<RmsUserLabel> urs = new ArrayList<RmsUserLabel>();
                    for (String labelId : labelIds) {
                        RmsUserLabel ul = new RmsUserLabel();
                        ul.setUserId(user.getId());
                        ul.setLabelId(Integer.parseInt(labelId));
                        ul.setCreatedUser(authorization.getUsername());//session中获取
                        urs.add(ul);
                    }
                    logger.info("--------更新的标签信息: " + urs);
                    userLabelService.saveBatchUserLabel(urs);

                }

                //新人用户修改记录表
                userService.createRmsUserRecord(record);

            }

            /**
             * 清除角色缓存
             */
            //清除redis缓存
//            userRoleService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            userRoleService.deleteCacheByKeys(RedisConstant.ROLE_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result(true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    @ResponseBody
    @Login(AuthenType.json)
    private Result deleteUser(@PathVariable("id") String id, HttpServletRequest request) {

        logger.info("-----------删除用户操作------------");
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsUser user = userService.findOneById(Integer.parseInt(id));
            user.setEnabled(0);
            userService.updateRmsUser(user);

            RmsUserDetail userDetail = userService.findOneByUserId(Integer.parseInt(id));
            if(userDetail != null) {
                userDetail.setDeleteMark(0);//删除状态
                userDetail.setDeleteTime(new Date());
                userDetail.setDeleteUser(authorization.getUsername());//session中获取
                userService.updateRmsUserDetail(userDetail);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result(true);
    }

    /**
     * loadData
     *
     * @param pInfo
     * @param conditions
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/loadData.do")
    @ResponseBody
    public Map<String, Object> loadData(String pInfo, String conditions) {

        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo pageInfo = JSON.parseObject(pInfo, PageInfo.class);

        Map queryMap = JSON.parseObject(conditions, Map.class);

       int userCount = userService.findUserCount(queryMap);
        pageInfo.setCount(userCount);
        map.put("pageInfo", pageInfo);
        //setFirstResult((page.getPageNum() - 1) * page.getPageSize()
        queryMap.put("start", (pageInfo.getPageNum() - 1) * pageInfo.getPageSize());
        queryMap.put("limit", pageInfo.getPageSize());
        map.put("data", userService.queryUserListByDeptCode(queryMap));

        return map;
    }

    /**
     * tab方式curd demo
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tab/list")
    private String tablist() {

        return "base/user/user_tab_list";
    }


    /**
     * 新的页面打开 curd demo
     */
//    @MethodLog(modelName = "/user/page/list")
    @RequestMapping(method = RequestMethod.GET, value = "/page/list")
    @Login(AuthenType.page)
    private String pagelist(String id, String orgcode, HttpServletRequest request) {

        request.setAttribute("selectId", id);
        request.setAttribute("orgcode", orgcode);
        return "base/user/user_page_list";
    }

    /**
     * 用户编辑 new page
     *
     * @return
     */
    @RefreshCSRFToken
//    @MethodLog(modelName = "/user/page/edit")
    @RequestMapping(method = RequestMethod.GET, value = "/page/edit")
    @Login(AuthenType.page)
    private String pageEdit(String id, String orgcode, HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        request.setAttribute("currid", authorization.getId());

        request.setAttribute("id", id);
        request.setAttribute("orgcode", orgcode);
        return "base/user/user_page_edit";
    }


    /**
     * 用户详细信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @Login(AuthenType.page)
    private String detail(String id, HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
//        request.setAttribute("currid", authorization.getId());

//        RmsUser user = userService.findOneById(Integer.parseInt(id));
//        request.setAttribute("user", user);
        request.setAttribute("id", id);
        return "base/user/user_page_detail";
    }


    /**
     * 用户详细信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/compar")
//    @Login(AuthenType.page)
    private String compar(String rid, String uid, HttpServletRequest request) {

        logger.info("-----------rid-uid: " + rid +"-"+uid);
        Authorization authorization = WebUtils.getSessionUser(request);


        RmsUserUpdateRecord record = userService.findRecordById(Integer.parseInt(rid));
        //更新人信息
        if(record != null) {
            int updateUserid = record.getUpdateUserid();
            RmsUser updateUser = userService.findOneById(updateUserid);
            request.setAttribute("updateUser", updateUser);
        }

        RmsUser user = userService.findOneById(Integer.parseInt(uid));
        //获取角色信息
        /**
         * 获取用户角色ID
         */
        List<RmsRole> roles = roleService.getRoleList(Integer.parseInt(uid));
        if(roles != null && roles.size() > 0) {
            StringBuilder roleNames = new StringBuilder();
            for (RmsRole role: roles) {
                roleNames.append(role.getName()).append(",");
            }

            user.setRoleNames(roleNames.deleteCharAt(roleNames.length() - 1).toString());
        }

        //获取上级理财师信息
        if(user.getFinancialPlanner() > 0) {
            RmsUser planner = userService.findOneById(user.getFinancialPlanner());
            user.setFinancialPlannerName(planner.getRealname());
        }

        request.setAttribute("record", record);
        request.setAttribute("user", user);
//        request.setAttribute("id", id);
        return "base/user/user_update_compar";
    }

    /**
     * 用户头像上传 avatar
     */
    @RequestMapping(method = RequestMethod.GET, value = "/avatar")
    private String avatar(String userId, HttpServletRequest request) {
        request.setAttribute("userId", userId);
        return "base/user/user_avatar";
    }

//    @RequestMapping(value = "/names", method = RequestMethod.POST)
//    @ResponseBody
//    public Map getNamesByIds(String ids) {
//        Map<String,String> map=new HashMap<>();
//        String names=userService.getUserNamesByUserIds(ids);
//        map.put("name",names);
//        return map;
//
//    }

    /**
     * 编号唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkCodeUnique.do")
    @ResponseBody
    public Map checkCodeUnique(String code, String id) {
        Map map = new HashMap();
        /**
         * 存在ID标准更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
//            map = userService.checkCodeUnique(code);
            Map paramMap = new HashMap();
            paramMap.put("code", code);
            map = userService.checkParamUnique(paramMap);

        } else {
            map.put("valid", true);
        }
        return map;
    }
    /**
     * 编号唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkNameUnique.do")
    @ResponseBody
    public Map checkNameUnique(String name, String id) {
        Map map = new HashMap();
        /**
         * 存在ID标准更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
            Map paramMap = new HashMap();
            paramMap.put("name", name);
            map = userService.checkParamUnique(paramMap);
        } else {
            map.put("valid", true);
        }
        return map;
    }
    /**
     * 编号唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkMobileUnique.do")
    @ResponseBody
    public Map checkMobileUnique(String mobile, String id) {
        Map map = new HashMap();
        /**
         * 存在ID标准更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
            Map paramMap = new HashMap();
            paramMap.put("mobile", mobile);
            map = userService.checkParamUnique(paramMap);
        } else {
            map.put("valid", true);
        }
        return map;
    }


    @RequestMapping(value = "/getMaxCode", method = RequestMethod.POST)
    @ResponseBody
    public String getMaxCode(HttpServletRequest request) {

        String maxCode = userService.findMaxCode("5");
        logger.info("--------maxCode: " + maxCode);
        return maxCode;

        //return rmsOrganizationService.findMaxCodeByParentCode(code);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/select.do")
    //@Login(AuthenType.page)
    @ResponseBody
    private String userlist(String uname, HttpServletRequest request) {

        logger.info("----------uname: " + uname);

        Map < String , Object > map1 = new HashMap< String , Object>();
        map1.put("id", 1);
        map1.put("text", "随风");

        Map < String , Object > map2 = new HashMap< String , Object>();
        map2.put("id", 2);
        map2.put("text", "刘志来");

        List list = new ArrayList();
        list.add(map1);
        list.add(map2);

        Map < String , Object > map = new HashMap< String , Object>();
        map.put("items", list);
        String str = JSONObject.toJSONString(map);
        System.out.println("---------------str: " + str);
        //String retStr = "[{id:'00002',text:'随风'},{id:'00001',text:'刘志来'}]";

        return str;
    }

    @RequestMapping(value="getAll.do",method = RequestMethod.POST)
    @ResponseBody
    public List<RmsUser> getAll(){
        List<RmsUser> allUser = userService.queryRmsUserList(null);
        return allUser;
    }

    /**
     * 根据用户输入的姓名，获取用户名
     * 如果用户名重复，则在其后加编号
     * @param realname
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getName.do")
    @ResponseBody
    private String getName(String realname) {

        String name = "";
        try {
            name = HanyuPinyinHelper.toHanyuPinyin(realname);

            Map paramMap = new HashMap();
            paramMap.put("name", name);
            //获取同名的个数
            int count = userService.queryUserCountByName(paramMap);
//        int count = 2;
            if(count > 0 && count < 10) {
                name += ("0" + count);
            } else if(count >= 10){
                name += count;
            }
            logger.info("---------name: " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;

    }

}
