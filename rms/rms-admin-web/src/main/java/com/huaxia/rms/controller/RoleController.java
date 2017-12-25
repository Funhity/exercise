package com.huaxia.rms.controller;

import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsRole;
import com.huaxia.rms.core.service.RmsRoleService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RmsRoleService roleService;

    /**
     * 编号唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkCodeUnique")
    @ResponseBody
    public Map checkNameUnique(String code, String id) {
        logger.info("-------id: " + id);
        Map map = new HashMap();
        /**
         * 存在ID标准更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
            map = roleService.checkCodeUnique(code);
        } else {
            map.put("valid", true);
        }
        logger.info("------map:" + map);
        return map;
    }

    /**
     * 角色列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list.jhtml")
    @Login(AuthenType.page)
    private String list(HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        request.setAttribute("userId", authorization.getId());
        return "base/auth/role_list";
    }

    /**
     * 角色列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list2.jhtml")
    @Login(AuthenType.page)
    private String list2() {

        return "base/auth/role_list_2";
    }

    /**
     * getTreeData 构造bootstrap-treeview格式数据
     *
     * @return
     */
    @RequestMapping(value = "/treeData.do", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> getTreeData() {
        logger.info("---------构造bootstrap-treeview格式数据-----------");
        List<TreeNode> list = roleService.getTreeData();
        logger.info("-----------role.list: " + list);
        return list;

    }

    /**
     * 角色编辑
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    @Login(AuthenType.page)
    private String eidt(String id, String parentcode, HttpServletRequest request) {
        /**
         * 编辑时ID不为空
         * 新增时，id为空，获取父级信息，带入页面
         */
        boolean isRoot = false;
        boolean isGroup = false;
        /**
         * 编辑时，判断是否是角色组，如果是角色组，则隐藏页面角色redio
         */
        if(StringUtils.isNotBlank(id)) {

            String group = request.getParameter("isGroup");
            if(StringUtils.isNotBlank(group) && "true".equals(group)) {
                RmsRole role = roleService.findByCode(id);
                if(role != null) {
                    id = String.valueOf(role.getId());
                    isGroup = true;
                }
            }

            request.setAttribute("id", id);

        } else {
            /**
             * 新增时，
             * 1、判断新增的是否是跟目录(/role/edit?isroot=true)，如果是根目录，隐藏页面选择角色目录的div，非根目录不隐藏
             * 2、隐藏页面角色的redio，只显示角色组redio
             * 3、自动带入code，按顺序排，取最大值+ 1
             */
            String maxCode = "";

            String group = request.getParameter("isGroup");
            if(StringUtils.isNotBlank(group)) {
                isGroup = true;
            }

            String root = request.getParameter("isroot");
            if(StringUtils.isNotBlank(root) && "true".equals(root)) {
                maxCode = roleService.findMaxCodeByParentCode("");
                isRoot = true;
            } else {
                /**默认管理员组*/
                if(StringUtils.isBlank(parentcode)) {
                    parentcode = "100";
                }

                maxCode = roleService.findMaxCodeByParentCode(parentcode);
                /**maxCode为空，表示该角色组下没有角色*/
                if(StringUtils.isBlank(maxCode)) {
                    maxCode = parentcode + "100";
                }
                RmsRole role = roleService.findByCode(parentcode);
                request.setAttribute("role", role);//新增时传入父级code

            }
            request.setAttribute("maxCode", Long.parseLong(maxCode) + 1);

        }

        request.setAttribute("isGroup", isGroup);
        request.setAttribute("isRoot", isRoot);

        return "base/auth/role_edit";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public RmsRole get(String id) {
        RmsRole role = new RmsRole();
        if(StringUtils.isNotBlank(id)) {
            role = roleService.findById(Integer.parseInt(id));
        }
        return role;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result save(RmsRole role, HttpServletRequest request) {


        Authorization authorization = WebUtils.getSessionUser(request);
        try {
            if (StringUtils.isBlank(String.valueOf(role.getId())) || "null".equals(String.valueOf(role.getId()))) {
                role.setCreateUser(authorization.getUsername());//session中获取
                roleService.createRmsRole(role);
            } else {

                RmsRole oldRole = roleService.findByCode(role.getCode());
                /**
                 * TODO
                 * 如果角色组变更
                 * 则角色组中的角色权限需要重置，或者不能变更
                 */
                if(!role.getParentCode().equals(oldRole.getParentCode())) {
                    /**判断员角色组中角色是否具有权限，如果具备则不能变更或清空原有权限*/

                    //return new Result(false, "角色组");
                }

                /** 新修改的角色信息复制到oldRole里面*/
                BeanUtils.copyProperties(role,oldRole);

                role.setUpdateTime(new Date());//删除状态
                role.setUpdateUser(authorization.getUsername());//session中获取
                roleService.updateRmsRole(role);
            }
            /**
             * 新增或更新角色后，刷新缓存
             *
             */
//            String key = RedisConstant.ROLE_PRE + "tree";
//            roleService.deleteCacheByKey(key);
            roleService.deleteCacheByKeys(RedisConstant.ROLE_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "系统异常，请稍后再试");
        }

        return new Result(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result delete(@PathVariable("id") String id, HttpServletRequest request) {

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsRole role = new RmsRole();
            /**
             * 如果删除的是角色组
             * 判断角色组下是否关联了角色，如果关联则无法删除
             */
            //boolean isRoot = false;
            String group = request.getParameter("isGroup");
            if(StringUtils.isNotBlank(group) && "true".equals(group)) {
                if(roleService.referByRole(id)) {
                    return new Result(false,"该角色组被其他角色引用，不可删除");
                }
                //isRoot = true;
                role = roleService.findByCode(id);
            } else {
                role = roleService.findById(Integer.parseInt(id));
            }

            role.setDeleteTime(new Date());
            role.setDeleteUser(authorization.getUsername());//session中获取
            role.setDeleteMark(0);
            roleService.updateRmsRole(role);

            /**
             * 删除角色后，刷新缓存
             */
//            String key = RedisConstant.ROLE_PRE + "tree";
//            roleService.deleteCacheByKey(key);
            roleService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            //request.setAttribute("isRoot", isRoot);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "系统异常，删除失败");
        }

        return new Result(true);

    }

    @RequestMapping(value="getAll",method = RequestMethod.POST)
    @ResponseBody
    public List<RmsRole> getAll(){
        return roleService.findAllRoleList();
    }


}
