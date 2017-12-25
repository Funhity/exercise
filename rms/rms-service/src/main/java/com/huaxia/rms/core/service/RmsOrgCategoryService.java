/**
 * @项目名称:
 * @文件名称: RmsOrgCategoryService 版本号：1.0
 * @创建日期: 2017/12/5
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsOrgCategory;
import com.huaxia.rms.pojo.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * RmsOrgCategoryService接口
 */
public interface RmsOrgCategoryService extends BaseService {
    Long deleteRmsOrgCategoryById(Long id);
    Long createRmsOrgCategory(RmsOrgCategory record);
    Long updateRmsOrgCategory(RmsOrgCategory record);
    List<RmsOrgCategory> queryRmsOrgCategoryList(Map<String, Object> map);
    PageInfo<RmsOrgCategory> findRmsOrgCategoryPage(int pageNum, int pageSize, Map<String, Object> map);

    List<RmsOrgCategory> queryCategoryList(Map<String, Object> map);
    int queryCategoryListCount(Map map);

    RmsOrgCategory findOneById(String id);
    RmsOrgCategory findOneByCode(String code);
    List<RmsOrgCategory> findListByCodes(String orgCode);
    List<RmsOrgCategory> queryAllCategoryList();

    List<TreeNode> getTreeData();
}