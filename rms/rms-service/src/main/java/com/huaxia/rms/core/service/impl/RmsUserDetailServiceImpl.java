/**
 * @项目名称:
 * @文件名称: RmsUserDetailService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.service.RmsUserDetailService;
import com.huaxia.rms.core.model.RmsUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RmsUserDetailServiceImpl implements RmsUserDetailService {
    private Logger logger = LoggerFactory.getLogger(RmsUserDetailServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserDetail> mBaseDao;

    @Override
    public Long deleteRmsUserDetailById(Long id) {
        return mBaseDao.delete("rmsuserdetail.delete", id);
    }

    @Override
    public Long createRmsUserDetail(RmsUserDetail record) {
        return mBaseDao.create("rmsuserdetail.insert", record);
    }

    @Override
    public Long updateRmsUserDetail(RmsUserDetail record) {
        return mBaseDao.update("rmsuserdetail.update", record);
    }

    @Override
    public List<RmsUserDetail> queryRmsUserDetailList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuserdetail.selectAll", map);
    }

    @Override
    public PageInfo<RmsUserDetail> findRmsUserDetailPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserdetail.selectAll", pageNum, pageSize, map);
    }
}