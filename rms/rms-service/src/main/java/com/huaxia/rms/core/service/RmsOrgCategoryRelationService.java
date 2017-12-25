/**
 * @项目名称:
 * @文件名称: RmsOrgCategoryRelationService 版本号：1.0
 * @创建日期: 2017/12/6
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsOrgCategoryRelation;

import java.util.List;
import java.util.Map;

/**
 * RmsOrgCategoryRelationService接口
 */
public interface RmsOrgCategoryRelationService extends BaseService {
    Long deleteRmsOrgCategoryRelationById(Long id);
    Long createRmsOrgCategoryRelation(RmsOrgCategoryRelation record);
    Long updateRmsOrgCategoryRelation(RmsOrgCategoryRelation record);
    PageInfo<RmsOrgCategoryRelation> findRmsOrgCategoryRelationPage(int pageNum, int pageSize, Map<String, Object> map);

    /**
     * 批量保存数据
     * @param categoryList 机构类别列表
     * @return 执行结果
     */
    void saveBatchCategoryRelation(List<RmsOrgCategoryRelation> categoryList);

    void deleteCategoryRelationByOrgcode(Map map);

    List<RmsOrgCategoryRelation> queryOrgCategoryRelationList(Map<String, Object> map);
}