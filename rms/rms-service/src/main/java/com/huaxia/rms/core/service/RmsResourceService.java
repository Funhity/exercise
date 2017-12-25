/**
 * @项目名称:
 * @文件名称: RmsResourceService 版本号：1.0
 * @创建日期: 2017/10/25
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsResource;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RmsResourceService接口
 */
public interface RmsResourceService extends BaseService {
    Long deleteRmsResourceById(Long id);
    Long createRmsResource(RmsResource record);
    Long updateRmsResource(RmsResource record);
    List<RmsResource> queryRmsResourceList();
    PageInfo<RmsResource> findRmsResourcePage(int pageNum, int pageSize, Map<String, Object> map);


    RmsResource findOneByCode(String code);
    RmsResource findOneById(int id);

    Map checkUnique(String code);

    List<TreeNode> getTreeData(List<RmsResource> ress);

    String getSourceTreeByUid(int uid);

    String getSourceTreeByUidAndSyscode(int uid, String syscode);

    List<RmsResource> getSourceListByUidAndSyscode(int uid, String syscode);

    boolean referByUser(String code);

    String findMaxCodeByParentCode(String code);

    Result getResNames(String code);
    List<RmsResource> getResesByCode(String code);

    List<RmsResource> getResesByCodes(List ids);

    List<RmsResource> getResesByCodesAndSyscode(List ids, String syscode);

    Set<String> getResourceCodeSet(List<Integer> roleids, String userId);

    List<RmsResource> getAllResesByRoleCode(List<Integer> roleids, String userId);

}