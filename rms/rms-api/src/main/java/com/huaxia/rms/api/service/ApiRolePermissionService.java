/**
 * @项目名称:
 * @文件名称: RmsRolePermissionService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.RolePermissionRo;
import com.huaxia.rms.api.service.ApiBaseService;
import com.huaxia.rms.api.pojo.Result;

import java.util.List;
import java.util.Map;

/**
 * RmsRolePermissionService接口
 */
public interface ApiRolePermissionService extends ApiBaseService {

    /**
     * 获取资源或角色列表
     * 根据角色id获取资源列表 map.put("role_id", xxx)
     * 根据资源ID获取角色列表 map.put("resource_id", xxx)
     * 分页 map.put("start", xxx)
     *      map.put("limit", xxx)
     * @param map
     * @return
     */
    List<RolePermissionRo> queryRolePermissionList(Map<String, Object> map);

    /**
     * 取资源或角色列表的数量
     * 配合queryRolePermissionList 方法使用
     * map内容与queryRolePermissionList方法中map的值一致（除分页部分）
     * @param map
     * @return
     */
    int queryRolePermissionListCount(Map<String, Object> map);

    //RolePermissionRo findById(int id);

//    /**
//     * 获取角色功能绑定
//     * @param roleId 角色ID
//     * @param resourceId 功能ID
//     * @return 获取的角色功能实体
//     */
//    RolePermissionRo getRolePermission(int roleId, int resourceId);

}