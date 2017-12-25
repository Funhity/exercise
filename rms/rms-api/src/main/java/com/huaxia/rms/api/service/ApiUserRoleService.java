/**
 * @项目名称:
 * @文件名称: RmsUserRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.UserRo;
import com.huaxia.rms.api.model.UserRoleRo;

import java.util.List;
import java.util.Map;

/**
 * RmsUserRoleService接口
 */
public interface ApiUserRoleService {

    /**
     * 获取角色或角色用户的ID列表
     * 根据用户ID查找角色ID列表 map.put("user_id", xxx)
     * 根据角色ID查找用户ID列表 map.put("role_id", xxx)
     * 分页 map.put("start", xxx)
     *      map.put("limit", xxx)
     * @param map
     * @return
     */
    List<UserRoleRo> queryUserRoleList(Map<String, Object> map);

    /**
     * 获取角色或角色用户的数量
     * queryUserRoleList 方法使用
     * map内容与qqueryUserRoleList方法中map的值一致（除分页部分）
     * @param map
     * @return
     */
    int queryUserRoleListCount(Map<String, Object> map);


    /**
     * 获取角色或角色用户的ID列表(无需分页)
     * 根据用户ID查找角色ID列表 map.put("user_id", xxx)
     * 根据角色ID查找用户ID列表 map.put("role_id", xxx)
     * @param map
     * @return
     */
    List<UserRoleRo> queryUserRoleByParam(Map<String, Object> map);

}