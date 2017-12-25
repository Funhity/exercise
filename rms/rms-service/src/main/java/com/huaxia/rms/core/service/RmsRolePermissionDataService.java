/**
 * @项目名称:
 * @文件名称: RmsRolePermissionDataService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.pojo.Result;

import java.util.List;
import java.util.Map;

/**
 * RmsRolePermissionDataService接口
 */
public interface RmsRolePermissionDataService extends BaseService {
    Long deleteRmsRolePermissionDataById(Long id);
    Long createRmsRolePermissionData(RmsRolePermissionData record);
    Long updateRmsRolePermissionData(RmsRolePermissionData record);
    List<RmsRolePermissionData> queryRmsRolePermissionDataList(Map<String, Object> map);
    PageInfo<RmsRolePermissionData> findRmsRolePermissionDataPage(int pageNum, int pageSize, Map<String, Object> map);

//    List<RmsRolePermissionData> queryRmsRolePermissionDataList(Map<String, Object> map);

    RmsRolePermissionData findById(int id);

    /**
     * 批量保存数据权限
     * @param functionFilterList 数据权限列表
     * @return 执行结果
     */
    void saveBatchRolePermissionData(List<RmsRolePermissionData> functionFilterList);

    /**
     * 更改权限后，把绑定相关角色的权限缓存清除
     * @param roleId
     */
    void deleteAuthInRedis(int roleId);

    void deleteByRoleId(Map map);

    List<RmsRolePermissionData> findPermissionDatalist(Map map);

    int findPermissionDatalistCount(Map map);

}