/**
 * @项目名称:
 * @文件名称: RmsUserLockOutService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.service.RmsUserLockOutService;
import com.huaxia.rms.core.model.RmsUserLockOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RmsUserLockOutServiceImpl implements RmsUserLockOutService {
    private Logger logger = LoggerFactory.getLogger(RmsUserLockOutServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserLockOut> mBaseDao;

    @Override
    public Long deleteRmsUserLockOutById(Long id) {
        return mBaseDao.delete("rmsuserlockout.delete", id);
    }

    @Override
    public Long createRmsUserLockOut(RmsUserLockOut record) {
        return mBaseDao.create("rmsuserlockout.insert", record);
    }

    @Override
    public Long updateRmsUserLockOut(RmsUserLockOut record) {
        return mBaseDao.update("rmsuserlockout.update", record);
    }

    @Override
    public List<RmsUserLockOut> queryRmsUserLockOutList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuserlockout.selectAll", map);
    }

    @Override
    public PageInfo<RmsUserLockOut> findRmsUserLockOutPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserlockout.selectAll", pageNum, pageSize, map);
    }
}