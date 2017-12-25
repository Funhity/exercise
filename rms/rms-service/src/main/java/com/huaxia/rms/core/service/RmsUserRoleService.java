/**
 * @项目名称:
 * @文件名称: RmsUserRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.model.RmsUserRole;

import java.util.List;
import java.util.Map;

/**
 * RmsUserRoleService接口
 */
public interface RmsUserRoleService extends BaseService {
    Long deleteRmsUserRoleById(Long id);
    Long createRmsUserRole(RmsUserRole record);
    Long updateRmsUserRole(RmsUserRole record);
    PageInfo<RmsUserRole> findRmsUserRolePage(int pageNum, int pageSize, Map<String, Object> map);

    List<RmsUserRole> queryUserRoleList(Map<String, Object> map);
    int queryUserRoleListCount(Map<String, Object> map);

    RmsUserRole findById(int id);

    void deleteUserRole(Map map);

    List<RmsUser> queryUnselectedUser(Map<String, Object> map);
    int queryUnselectedUserCount(Map<String, Object> map);

    void deleteAuthInRedis(String userId);

    void batchSave(List<RmsUserRole> userRoles);

    List<RmsUserRole> queryUserRoleByParam(Map<String, Object> map);

    void deleteUserRoleByUid(Map map);
}