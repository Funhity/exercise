/**
 * @项目名称:
 * @文件名称: RmsUserLabelService 版本号：1.0
 * @创建日期: 2017/12/20
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.service.RmsUserLabelService;
import com.huaxia.rms.core.model.RmsUserLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userLabelService")
public class RmsUserLabelServiceImpl implements RmsUserLabelService {
    private Logger logger = LoggerFactory.getLogger(RmsUserLabelServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserLabel> mBaseDao;

    @Override
    public Long deleteRmsUserLabelById(Long id) {
        return mBaseDao.delete("rmsuserlabel.delete", id);
    }

    @Override
    public Long createRmsUserLabel(RmsUserLabel record) {
        return mBaseDao.create("rmsuserlabel.insert", record);
    }

    @Override
    public Long updateRmsUserLabel(RmsUserLabel record) {
        return mBaseDao.update("rmsuserlabel.update", record);
    }

    @Override
    public List<RmsUserLabel> queryRmsUserLabelList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuserlabel.selectAll", map);
    }

    @Override
    public PageInfo<RmsUserLabel> findRmsUserLabelPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserlabel.selectAll", pageNum, pageSize, map);
    }

    @Override
    public void saveBatchUserLabel(List<RmsUserLabel> list) {
        mBaseDao.create("rmsuserlabel.addTrainRecordBatch", list);
    }

    @Override
    public void deleteUserLabelByUid(Map map) {
        mBaseDao.create("rmsuserlabel.deleteUserLabel", map);
    }
}