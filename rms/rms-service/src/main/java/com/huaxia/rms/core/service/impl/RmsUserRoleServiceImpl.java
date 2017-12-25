/**
 * @项目名称:
 * @文件名称: RmsUserRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.service.RmsUserRoleService;
import com.huaxia.rms.core.model.RmsUserRole;
import com.huaxia.rms.core.service.RmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userRoleService")
public class RmsUserRoleServiceImpl extends BaseServiceImpl implements RmsUserRoleService {
    private Logger logger = LoggerFactory.getLogger(RmsUserRoleServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserRole> mBaseDao;

    @Resource
    private RmsUserService userService;

    @Override
    public Long deleteRmsUserRoleById(Long id) {
        return mBaseDao.delete("rmsuserrole.delete", id);
    }

    @Override
    @MethodLog(remark = "新增用户角色")
    public Long createRmsUserRole(RmsUserRole record) {
        return mBaseDao.create("rmsuserrole.insert", record);
    }

    @Override
    @MethodLog(remark = "更新用户角色")
    public Long updateRmsUserRole(RmsUserRole record) {
        return mBaseDao.update("rmsuserrole.update", record);
    }

        @Override
    public PageInfo<RmsUserRole> findRmsUserRolePage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserrole.selectAll", pageNum, pageSize, map);
    }

    @Override
    @MethodLog(remark = "查询用户角色列表")
    public List<RmsUserRole> queryUserRoleList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuserrole.selectUserRoleList", map);
    }

    @Override
    public int queryUserRoleListCount(Map<String, Object> map) {
        Object ojb = mBaseDao.findObjectByTemplate("rmsuserrole.selectUserRoleListCount", map);
        System.out.println("--------------ojb: " + ojb);
        int userCount = 0 ;
        if(ojb != null) {
            userCount = Integer.parseInt(String.valueOf(ojb));
        }
        return userCount;
    }

    @Override
    public RmsUserRole findById(int id) {
        return (RmsUserRole)mBaseDao.findListByTemplate("rmsuserrole.selectById", id);
    }

    @Override
    public void deleteUserRole(Map map) {
        mBaseDao.update("rmsuserrole.deleteUserRole", map);
    }

    @Override
    public List<RmsUser> queryUnselectedUser(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmsuserrole.selectUnselectedUser", map);
    }

    @Override
    public int queryUnselectedUserCount(Map<String, Object> map) {

        Object ojb = mBaseDao.findObjectByTemplate("rmsuserrole.selectUnselectedUserCount", map);
        System.out.println("--------------ojb: " + ojb);
        int userCount = 0 ;
        if(ojb != null) {
            userCount = Integer.parseInt(String.valueOf(ojb));
        }
        return userCount;
    }

    @Override
    public void deleteAuthInRedis(String userId){
        userService.deleteAuthInRedis(userId);
    }

    @Override
    @MethodLog(remark = "批量增加用户角色")
    public void batchSave(List<RmsUserRole> userRoles) {
        mBaseDao.create("rmsuserrole.addTrainRecordBatch", userRoles);
    }

    @Override
    public List<RmsUserRole> queryUserRoleByParam(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmsuserrole.selectUserRoleByParam", map);
    }

    @Override
    public void deleteUserRoleByUid(Map map) {
        mBaseDao.update("rmsuserrole.deleteUserRoleByUid", map);
    }

}