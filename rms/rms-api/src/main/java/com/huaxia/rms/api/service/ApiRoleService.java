/**
 * @项目名称:
 * @文件名称: RmsRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.RoleRo;
import com.huaxia.rms.api.pojo.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * RmsRoleService接口
 */
public interface ApiRoleService extends ApiBaseService {

    /**
     * 根据角色id获取角色信息
     * @param id
     * @return
     */
    RoleRo findById(int id);

    /**
     * 根据角色code获取角色信息
     * @param code
     * @return
     */
    RoleRo findByCode(String code);

    /**
     * 根据用户id获取其所有角色的集合
     * @param userId
     * @return List<RoleRo>
     */
    List<RoleRo> getRoleListByUid(int userId);

//    /**
//     * 获取角色组的树状结构
//     * json格式
//     * @return
//     */
//    List<TreeNode> getTreeData();

//    /**
//     * 获取角色组列表，非角色列表
//     * @return
//     */
//    List<RoleRo> queryRmsRoleParentDirectoryList();

}