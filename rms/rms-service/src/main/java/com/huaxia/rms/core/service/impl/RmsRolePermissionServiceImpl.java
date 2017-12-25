/**
 * @项目名称:
 * @文件名称: RmsRolePermissionService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.core.model.RmsUserRole;
import com.huaxia.rms.core.service.RmsRolePermissionService;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.service.RmsUserRoleService;
import com.huaxia.rms.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("rolePermissionService")
public class RmsRolePermissionServiceImpl extends BaseServiceImpl implements RmsRolePermissionService {
    private Logger logger = LoggerFactory.getLogger(RmsRolePermissionServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsRolePermission> mBaseDao;

    @Resource
    private RmsUserRoleService userRoleService;

    @Override
    public Long deleteRmsRolePermissionById(Long id) {
        return mBaseDao.delete("rmsrolepermission.delete", id);
    }

    @Override
    @MethodLog(remark = "新增角色权限")
    public Long createRmsRolePermission(RmsRolePermission record) {
        return mBaseDao.create("rmsrolepermission.insert", record);
    }

    @Override
    @MethodLog(remark = "更新角色权限")
    public Long updateRmsRolePermission(RmsRolePermission record) {
        return mBaseDao.update("rmsrolepermission.update", record);
    }

    @Override
    @MethodLog(remark = "查询角色权限")
    public List<RmsRolePermission> queryRmsRolePermissionList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsrolepermission.selectRolePermissionByParam", map);
    }

    @Override
    public PageInfo<RmsRolePermission> findRmsRolePermissionPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsrolepermission.selectAll", pageNum, pageSize, map);
    }

    @Override
    @MethodLog(remark = "查询角色权限列表")
    public List<RmsRolePermission> queryRolePermissionList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsrolepermission.selectRolePermissionList", map);
    }

    @Override
    public int queryRolePermissionListCount(Map<String, Object> map) {
        Object ojb = mBaseDao.findObjectByTemplate("rmsrolepermission.selectRolePermissionListCount", map);
        System.out.println("--------------ojb: " + ojb);
        int userCount = 0 ;
        if(ojb != null) {
            userCount = Integer.parseInt(String.valueOf(ojb));
        }
        return userCount;
    }


    @Override
    public RmsRolePermission findById(int id) {
        RmsRolePermission role = (RmsRolePermission)mBaseDao.findObjectByTemplate("rmsrolepermission.selectById", id);
        return role;
    }

    @Override
    public Result deleteRolePermission(int id) {
        //delete functionfilter first
        RmsRolePermission rolePermission = findById(id);
        if(rolePermission != null) {
            rolePermission.setDeleteMark(0);
            rolePermission.setDeleteTime(new Date());
            rolePermission.setDeleteUser("");//seesion中获取
            updateRmsRolePermission(rolePermission);

            //-----------update redis-----------
            this.deleteAuthInRedis(rolePermission.getRoleId());
            //----------------------------------
        }

        return new Result();
    }

//    @Override
//    public Result saveRolePermission(RmsRolePermission rolePermission) {
//        if(rolePermission != null) {
//            deleteRolePermission(rolePermission.getId());
//
//        }
//        createRmsRolePermission(rolePermission);
//        //-----------update redis-----------
//        this.deleteAuthInRedis(rolePermission.getRoleId());
//        //----------------------------------
//        return saveBatchRolePermissionData(rolePermission.getRoleId(),rolePermission.getResourceId(), rolePermission.get());
//    }

    @Override
    public RmsRolePermission getRolePermission(int roleId, int resourceId) {

        RmsRolePermission rolePermission = new RmsRolePermission();
        if(roleId <= 0 || resourceId <= 0) {
            return null;
        }

        Map<String,Object> params = new HashMap<>();
        params.put("roleId",roleId);
        params.put("resourceId",resourceId);
        List<RmsRolePermission> list = queryRmsRolePermissionList(params);


        if (list != null && list.size() > 0) {
            rolePermission = list.get(0);
            //-----------update redis-----------
            this.deleteAuthInRedis(roleId);
            //----------------------------------
        }
        return rolePermission;
    }

    @Override
    public void saveBatchRolePermission(List<RmsRolePermission> rolePermissionList) {
        mBaseDao.create("rmsrolepermission.addTrainRecordBatch", rolePermissionList);
    }

//    @Override
//    public Result saveBatchRolePermission(String roleId, List<RmsRolePermission> roleFunctionList) {
//        String hql="delete from RoleFunction where roleId='"+roleId+"'";
//        this.executeHql(hql);
//        this.batchSave(roleFunctionList);
//        //-----------update redis-----------
//        this.deleteAuthInRedis(roleId);
//        //----------------------------------
//        return new Result();
//    }



    @Override
    public void deleteAuthInRedis(int roleId) {
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",roleId);
        try {
            List<RmsUserRole> userRoles = userRoleService.queryUserRoleByParam(params);
            logger.info("---------userRoles: " + userRoles);
            logger.info("---------userRoles: " + userRoles.size());
            if(userRoles != null && userRoles.size() > 0) {
                for (RmsUserRole userRole : userRoles) {
                    String userId= String.valueOf(userRole.getUserId());
                    redisDao.delete(userId);
                    redisDao.delete(RedisConstant.ROLE_PRE + userId);
                    redisDao.delete(RedisConstant.PERMISSION_PRE + userId);
                    String key = RedisConstant.RES_PRE + "tree_" + userId;
                    Set<String> keys = redisDao.keys(key + "*");
                    redisDao.delete(keys);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByRoleId(Map map) {
        mBaseDao.update("rmsrolepermission.deleteRolePermission", map);
    }

    @Override
    public List<Map<String, Object>> findRolePermissionlistBySelected(Map map) {
        return (List<Map<String, Object>>)mBaseDao.findListByTemplate("rmsrolepermission.selectRolePermissionlistBySelected", map);
    }

    @Override
    public int findRolePermissionlistBySelectedCount(Map map) {

        Object ojb = mBaseDao.findObjectByTemplate("rmsrolepermission.selectRolePermissionlistBySelectedCount", map);
        System.out.println("--------------ojb: " + ojb);
        int userCount = 0 ;
        if(ojb != null) {
            userCount = Integer.parseInt(String.valueOf(ojb));
        }
        return userCount;
    }

    @Override
    public List<RmsRolePermission> getRolePermissionByRoleCodes(List<Integer> ids) {
        return mBaseDao.findListByTemplate("rmsrolepermission.selectRolePermissionByRoleCodes", ids);
    }
}