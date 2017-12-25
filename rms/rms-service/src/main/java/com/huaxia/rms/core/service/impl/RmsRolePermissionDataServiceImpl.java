/**
 * @项目名称:
 * @文件名称: RmsRolePermissionDataService 版本号：1.0
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
import com.huaxia.rms.core.model.RmsUserRole;
import com.huaxia.rms.core.service.RmsRolePermissionDataService;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.core.service.RmsUserRoleService;
import com.huaxia.rms.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("rolePermissionServiceData")
public class RmsRolePermissionDataServiceImpl extends BaseServiceImpl implements RmsRolePermissionDataService {
    private Logger logger = LoggerFactory.getLogger(RmsRolePermissionDataServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsRolePermissionData> mBaseDao;

    @Resource
    private RmsUserRoleService userRoleService;

    @Override
    public Long deleteRmsRolePermissionDataById(Long id) {
        return mBaseDao.delete("rmsrolepermissiondata.delete", id);
    }

    @Override
    @MethodLog(remark = "新增权限数据")
    public Long createRmsRolePermissionData(RmsRolePermissionData record) {
        return mBaseDao.create("rmsrolepermissiondata.insert", record);
    }

    @Override
    @MethodLog(remark = "更新权限数据")
    public Long updateRmsRolePermissionData(RmsRolePermissionData record) {
        return mBaseDao.update("rmsrolepermissiondata.update", record);
    }

    @Override
    @MethodLog(remark = "查询权限数据")
    public List<RmsRolePermissionData> queryRmsRolePermissionDataList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsrolepermissiondata.selectRolePermissionDataByParam", map);
    }

    @Override
    public PageInfo<RmsRolePermissionData> findRmsRolePermissionDataPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsrolepermissiondata.selectAll", pageNum, pageSize, map);
    }

    @Override
    public RmsRolePermissionData findById(int id) {
        return (RmsRolePermissionData)mBaseDao.findObjectByTemplate("rmsrolepermissiondata.selectById", id);
    }

    @Override
    @MethodLog(remark = "批量保存权限数据")
    public void saveBatchRolePermissionData(List<RmsRolePermissionData> permissionDataList) {

//        Map<String,Object> params = new HashMap<>();
//        params.put("roleId",roleId);
//        params.put("resourceId",resourceId);
//        List<RmsRolePermissionData> list = queryRmsRolePermissionDataList(params);
//        if (list != null && list.size() > 0) {
//            RmsRolePermissionData rolePermissionData = list.get(0);
//            rolePermissionData.setDeleteMark(0);
//            rolePermissionData.setDeleteTime(new Date());
//            rolePermissionData.setDeleteUser("");//seesion中获取
//            updateRmsRolePermissionData(rolePermissionData);
//            //-----------update redis-----------
//            this.deleteAuthInRedis(roleId);
//            //----------------------------------
//        }

        mBaseDao.create("rmsrolepermissiondata.addTrainRecordBatch", permissionDataList);

    }

    @Override
    public void deleteAuthInRedis(int roleId) {
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",roleId);
        List<RmsUserRole> userRoles = userRoleService.queryUserRoleByParam(params);

        for (RmsUserRole userRole : userRoles) {
            String userId= String.valueOf(userRole.getUserId());
            redisDao.delete(userId);
            redisDao.delete(RedisConstant.ROLE_PRE + userId);
            redisDao.delete(RedisConstant.PERMISSION_PRE + userId);
        }
    }

    @Override
    public void deleteByRoleId(Map map) {
        mBaseDao.update("rmsrolepermissiondata.deleteRolePermissionData", map);
    }

    @Override
    @MethodLog(remark = "查询权限数据")
    public List<RmsRolePermissionData> findPermissionDatalist(Map map) {
        return mBaseDao.findListByTemplate("rmsrolepermissiondata.selectPermissionDatalist", map);

    }

    @Override
    public int findPermissionDatalistCount(Map map) {

        Object ojb = mBaseDao.findObjectByTemplate("rmsrolepermissiondata.selectPermissionDatalistCount", map);
        System.out.println("--------------ojb: " + ojb);
        int count = 0 ;
        if(ojb != null) {
            count = Integer.parseInt(String.valueOf(ojb));
        }
        return count;
    }

}