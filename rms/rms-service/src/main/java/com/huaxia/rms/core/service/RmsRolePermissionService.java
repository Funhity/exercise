/**
 * @项目名称:
 * @文件名称: RmsRolePermissionService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.pojo.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RmsRolePermissionService接口
 */
public interface RmsRolePermissionService extends BaseService {
    Long deleteRmsRolePermissionById(Long id);
    Long createRmsRolePermission(RmsRolePermission record);
    Long updateRmsRolePermission(RmsRolePermission record);
    List<RmsRolePermission> queryRmsRolePermissionList(Map<String, Object> map);
    PageInfo<RmsRolePermission> findRmsRolePermissionPage(int pageNum, int pageSize, Map<String, Object> map);



    List<RmsRolePermission> queryRolePermissionList(Map<String, Object> map);
    int queryRolePermissionListCount(Map<String, Object> map);

    RmsRolePermission findById(int id);

    /**
     * 删除角色功能绑定
     * @param id 功能角色id
     * @return
     */
    Result deleteRolePermission(int id);

//    /**
//     * 保存角色授权
//     * @param rfobj 角色功能对象
//     * @return
//     */
//    Result saveRolePermission(RmsRolePermission rfobj);

    /**
     * 获取角色功能绑定
     * @param roleId 角色ID
     * @param resourceId 功能ID
     * @return 获取的角色功能实体
     */
    RmsRolePermission getRolePermission(int roleId, int resourceId);

    /**
     * 批量保存角色功能绑定
     * @param roleId 角色ID
     * @param rolePermissionList 角色功能列表
     * @return 执行结果
     */
//    Result saveBatchRolePermission(String roleId, List<RmsRolePermission> roleFunctionList);
    void saveBatchRolePermission(List<RmsRolePermission> rolePermissionList);



    /**
     * 更改权限后，把绑定相关角色的权限缓存清除
     * @param roleId
     */
    void deleteAuthInRedis(int roleId);

    void deleteByRoleId(Map map);

    List<Map<String, Object>> findRolePermissionlistBySelected(Map map);

    int findRolePermissionlistBySelectedCount(Map map);

    List<RmsRolePermission> getRolePermissionByRoleCodes(List<Integer> roleids);

}