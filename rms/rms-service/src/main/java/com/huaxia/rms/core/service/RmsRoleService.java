/**
 * @项目名称:
 * @文件名称: RmsRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsRole;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RmsRoleService接口
 */
public interface RmsRoleService extends BaseService {
    Long deleteRmsRoleById(Long id);
    Long createRmsRole(RmsRole record);
    Long updateRmsRole(RmsRole record);
    List<RmsRole> queryRmsRoleList(Map<String, Object> map);
    PageInfo<RmsRole> findRmsRolePage(int pageNum, int pageSize, Map<String, Object> map);

    RmsRole findById(int id);
    RmsRole findByCode(String code);

    /**
     * 根据登录名，获取角色集合
     * @param userId 用户id
     * @return 角色编码集合
     */
    List getRoleidList(int userId);

    List<RmsRole> getRoleList(int userId);

    //List<RmsRole> selectUserRoleByUid(String userId);

    List<RmsRole> queryRoleList(Map map);

    int queryRoleListCount(Map map);

    Map checkCodeUnique(String code);

    List<TreeNode> getTreeData();

//    Result getRoleNames(String code);
    List<RmsRole> queryRmsRoleParentDirectoryList();

    String findMaxCodeByParentCode(String code);

    boolean referByRole(String code);

    List<RmsRole> findAllRoleList();

    List<RmsRole> selectRoleListByUid(Map map);

    int selectRoleListByUidCount(Map map);

}