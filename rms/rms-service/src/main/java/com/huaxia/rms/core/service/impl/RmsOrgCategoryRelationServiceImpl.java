/**
 * @项目名称:
 * @文件名称: RmsOrgCategoryRelationService 版本号：1.0
 * @创建日期: 2017/12/6
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.service.RmsOrgCategoryRelationService;
import com.huaxia.rms.core.model.RmsOrgCategoryRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("orgCategoryRelationService")
public class RmsOrgCategoryRelationServiceImpl extends BaseServiceImpl implements RmsOrgCategoryRelationService {
    private Logger logger = LoggerFactory.getLogger(RmsOrgCategoryRelationServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsOrgCategoryRelation> mBaseDao;

    @Override
    public Long deleteRmsOrgCategoryRelationById(Long id) {
        return mBaseDao.delete("rmsorgcategoryrelation.delete", id);
    }

    @Override
    public Long createRmsOrgCategoryRelation(RmsOrgCategoryRelation record) {
        return mBaseDao.create("rmsorgcategoryrelation.insert", record);
    }

    @Override
    public Long updateRmsOrgCategoryRelation(RmsOrgCategoryRelation record) {
        return mBaseDao.update("rmsorgcategoryrelation.update", record);
    }

    @Override
    public PageInfo<RmsOrgCategoryRelation> findRmsOrgCategoryRelationPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsorgcategoryrelation.selectAll", pageNum, pageSize, map);
    }

    @Override
    public void saveBatchCategoryRelation(List<RmsOrgCategoryRelation> categoryList) {
        mBaseDao.create("rmsorgcategoryrelation.addTrainRecordBatch", categoryList);
    }

    @Override
    public void deleteCategoryRelationByOrgcode(Map map) {
        mBaseDao.create("rmsorgcategoryrelation.deleteOrgCategoryRelation", map);
    }

    @Override
    public List<RmsOrgCategoryRelation> queryOrgCategoryRelationList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsorgcategoryrelation.selectByParam", map);
    }

}