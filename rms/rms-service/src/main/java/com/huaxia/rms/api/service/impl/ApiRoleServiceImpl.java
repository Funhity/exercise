/**
 * @项目名称:
 * @文件名称: RmsRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.api.model.RoleRo;
import com.huaxia.rms.api.service.ApiRoleService;

import com.huaxia.rms.core.model.RmsRole;
import com.huaxia.rms.core.service.RmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("apiRoleService")
public class ApiRoleServiceImpl extends ApiBaseServiceImpl implements ApiRoleService {
    private Logger logger = LoggerFactory.getLogger(ApiRoleServiceImpl.class);

    @Resource
    private RmsRoleService roleService;

    @Override
    public RoleRo findById(int id) {
        RoleRo roleRo = null;
        try {
            RmsRole role = roleService.findById(id);
            if(role != null) {
                roleRo = new RoleRo();
                BeanUtils.copyProperties(role, roleRo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return roleRo;
    }

    @Override
    public RoleRo findByCode(String code) {
        RoleRo roleRo = null;
        try {
            RmsRole role = roleService.findByCode(code);
            if(role != null) {
                roleRo = new RoleRo();
                BeanUtils.copyProperties(role, roleRo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return roleRo;
    }


    @Override
    public List<RoleRo> getRoleListByUid(int userId){

      List<RoleRo> roleRoList = null;
        try {

            String key = RedisConstant.ROLE_PRE + userId;
            List<RmsRole> roles = redisDao.getList(key, RmsRole.class);
            if(roles == null){
                roles = roleService.getRoleList(userId);
                if(roles != null && roles.size() > 0) {
                    redisDao.add(key,roles);

                    roleRoList = new ArrayList<RoleRo>();
                    BeanUtils.copyProperties(roles, roleRoList);
                }
            }
        }catch(Exception e){
            return null;
        }
        return roleRoList;
    }

//    @Override
//    public List<TreeNode> getTreeData() {
//
//        // 获取数据
//        String key = RedisConstant.ROLE_PRE + "tree";
//        List<TreeNode> tnlist = null;
//        String tnStr = redisDao.get(key);
//        logger.info("-------tnStr: " + tnStr);
//        if (!StringUtils.isEmpty(key)) {
//            tnlist = JSON.parseArray(tnStr, TreeNode.class);
//            logger.info("-------tnlist1: " + tnlist);
//        }
//        if (tnlist != null) {
//            return tnlist;
//        } else {
//            //List<RmsRole> roles = queryRmsRoleList(null);//获取所有的角色列表包括上级目录
//            List<RoleRo> roles = queryRmsRoleParentDirectoryList();//只获取角色的父级目录
//            //List<RmsOrganization> orgs = queryRmsOrganizationList();
//            logger.info("----------roles: " + roles);
//            Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
//            for (RoleRo role : roles) {
//                TreeNode node = new TreeNode();
//                node.setText(role.getName());
//                node.setCode(role.getCode());
//                node.setParentCode(role.getParentCode());
//                nodelist.put(node.getCode(), node);
//            }
//            logger.info("----------nodelist: " + nodelist);
//            // 构造树形结构
//            tnlist = TreeUtil.getNodeList(nodelist);
//            logger.info("----------tnlist2: " + tnlist);
//            redisDao.save(key, tnlist);
//            return tnlist;
//        }
//    }

//    @Override
//    public List<RoleRo> queryRmsRoleParentDirectoryList() {
//        //TODO
//        return mBaseDao.findListByTemplate("rmsrole.selectParentDirectoryList", null);
//    }



}