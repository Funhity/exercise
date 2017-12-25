/**
 * @项目名称:
 * @文件名称: RmsRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.service.RmsRoleService;
import com.huaxia.rms.core.model.RmsRole;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("roleService")
public class RmsRoleServiceImpl extends BaseServiceImpl implements RmsRoleService {
    private Logger logger = LoggerFactory.getLogger(RmsRoleServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsRole> mBaseDao;

    @Override
    public Long deleteRmsRoleById(Long id) {
        return mBaseDao.delete("rmsrole.delete", id);
    }

    @Override
    @MethodLog(remark = "新增角色")
    public Long createRmsRole(RmsRole record) {
        return mBaseDao.create("rmsrole.insert", record);
    }

    @Override
    @MethodLog(remark = "更新角色")
    public Long updateRmsRole(RmsRole record) {
        return mBaseDao.update("rmsrole.update", record);
    }

    @Override
    public List<RmsRole> queryRmsRoleList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsrole.selectAll", map);
    }

    @Override
    public PageInfo<RmsRole> findRmsRolePage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsrole.selectAll", pageNum, pageSize, map);
    }


    @Override
    public RmsRole findById(int id) {
        RmsRole role = (RmsRole)mBaseDao.findObjectByTemplate("rmsrole.selectById", id);
        return role;
    }

    @Override
    public RmsRole findByCode(String code) {
        RmsRole role = (RmsRole)mBaseDao.findObjectByTemplate("rmsrole.selectByCode", code);
        return role;
    }

    @Override
    public List getRoleidList(int userId) {

        List ids = new ArrayList();
        List<RmsRole> roles = getRoleList(userId);
        if(roles != null && roles.size() > 0) {
            for (RmsRole role : roles) {
                ids.add(role.getId());
            }
        }
        return ids;
    }

    @Override
//    @MethodLog(remark = "查询用户角色列表")
    public List<RmsRole> getRoleList(int userId){
        String key = RedisConstant.ROLE_PRE + userId;
        List<RmsRole> roles = redisDao.getList(key, RmsRole.class);
        if(roles == null){
            roles = (List<RmsRole>)mBaseDao.findListByTemplate("rmsrole.selectUserRoleByUid", userId);
            logger.info("【获取用户"+userId+"的所有角色列表】" + roles);
            if(roles != null && roles.size() > 0) {
                redisDao.add(key, roles);
            } else {
                return null;
            }
        }
        return roles;
    }

    @Override
    //@MethodLog(remark = "查询角色列表")
    public List<RmsRole> queryRoleList(Map map) {
        return mBaseDao.findListByTemplate("rmsrole.selectRoleList", map);
    }

    @Override
    public int queryRoleListCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsrole.selectRoleListCount", map)));
        return userCount;
    }

    @Override
    public Map checkCodeUnique(String code) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            RmsRole role = findByCode(code);
            if(role == null) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
            }
            return map;
        } catch (Exception ex) {
            logger.info(ex.getMessage().toString());
            map.put("valid", false);
            return map;
        }
    }

    @Override
    public List<TreeNode> getTreeData() {

        // 获取数据
        String key = RedisConstant.ROLE_PRE + "tree";
        List<TreeNode> tnlist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            tnlist = JSON.parseArray(tnStr, TreeNode.class);
        }
        if (tnlist != null) {
            return tnlist;
        } else {
            //List<RmsRole> roles = queryRmsRoleList(null);//获取所有的角色列表包括上级目录
            List<RmsRole> roles = queryRmsRoleParentDirectoryList();//只获取角色的父级目录
            //List<RmsOrganization> orgs = queryRmsOrganizationList();
            Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
            for (RmsRole role : roles) {
                TreeNode node = new TreeNode();
                node.setText(role.getName());
                node.setCode(role.getCode());
                node.setParentCode(role.getParentCode());
//                node.setParentName(role.getParentName());
//                node.setLayer(String.valueOf(role.getSysCode()));
                nodelist.put(node.getCode(), node);
            }
            logger.info("----------nodelist: " + nodelist.size());
            // 构造树形结构
            tnlist = TreeUtil.getNodeList(nodelist);
//            logger.info("----------tnlist2: " + tnlist);
            redisDao.save(key, tnlist);
            return tnlist;
        }
    }

//    @Override
//    public Result getRoleNames(String code) {
//        Map nameMap = redisDao.get(RedisConstant.ROLE_PRE + code, Map.class);
//        //logger.info("---------getOrgNames.nameMap: " + nameMap);
//        if(nameMap == null) {
//            nameMap = new HashMap();
//            RmsRole role = findByCode(code);
//
//            String name = role.getName();
//            //logger.info("---------getOrgNames.name: " + name);
//            /**
//             * 存在上级机构的组织，
//             * 再获取到上级机构，存入redis
//             */
////            String parentName = "";
//            if(StringUtils.isNotBlank(role.getParentCode())){
//                Map result  = (Map)getRoleNames(role.getParentCode()).getData();
//                String parentName = result.get("parentName")+"->"+ role.getName();
//                logger.info("---------getRoleNames.parentName: " + parentName);
//                nameMap.put("name", role.getName());
//                nameMap.put("parentName", parentName);
//                redisDao.save(RedisConstant.ROLE_PRE + code, nameMap);
//                return new Result(true, nameMap,"获取成功");
//            } else {
//                nameMap.put("name", role.getName());
//                nameMap.put("parentName", role.getName());
//                redisDao.save(RedisConstant.ROLE_PRE + code, nameMap);
//                return new Result(true, nameMap,"获取成功");
//            }
//        } else {
//            return new Result(true, nameMap,"获取成功");
//        }
//    }


    @Override
    public List<RmsRole> queryRmsRoleParentDirectoryList() {
        //TODO
        return mBaseDao.findListByTemplate("rmsrole.selectParentDirectoryList", null);
    }

    @Override
    public String findMaxCodeByParentCode(String code) {
        String maxCode = (String) mBaseDao.findObjectByTemplate("rmsrole.selectMaxCodeByParentCode", code);
        return maxCode;
    }

    /**
     * 判断部门下是否有员工
     * @return
     */
    public boolean referByRole(String code) {

        Map map = (Map)mBaseDao.findObjectByTemplate("rmsrole.referByRole", code);
        logger.info("----------map: " + map);

        if(map != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<RmsRole> findAllRoleList() {
        return mBaseDao.findListByTemplate("rmsrole.selectAll", null);
    }

    @Override
    //@MethodLog(remark = "查询角色列表")
    public List<RmsRole> selectRoleListByUid(Map map) {
        return mBaseDao.findListByTemplate("rmsrole.selectRoleListByUid", map);
    }

    @Override
    public int selectRoleListByUidCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsrole.selectRoleListByUidCount", map)));
        return userCount;
    }

}