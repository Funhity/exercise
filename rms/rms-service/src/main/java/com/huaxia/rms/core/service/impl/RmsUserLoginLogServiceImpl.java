/**
 * @项目名称:
 * @文件名称: RmsUserLoginLogService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.core.service.RmsUserLoginLogService;
import com.huaxia.rms.core.model.RmsUserLoginLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RmsUserLoginLogServiceImpl implements RmsUserLoginLogService {
    private Logger logger = LoggerFactory.getLogger(RmsUserLoginLogServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserLoginLog> mBaseDao;

    @Override
    public Long deleteRmsUserLoginLogById(Long id) {
        return mBaseDao.delete("rmsuserloginlog.delete", id);
    }

    @Override
    @MethodLog(remark = "新增用户登陆信息")
    public Long createRmsUserLoginLog(RmsUserLoginLog record) {
        return mBaseDao.create("rmsuserloginlog.insert", record);
    }

    @Override
    @MethodLog(remark = "更新用户登陆信息")
    public Long updateRmsUserLoginLog(RmsUserLoginLog record) {
        return mBaseDao.update("rmsuserloginlog.update", record);
    }

    @Override
    @MethodLog(remark = "查询用户登陆信息")
    public List<RmsUserLoginLog> queryRmsUserLoginLogList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuserloginlog.selectAll", map);
    }

    @Override
    public PageInfo<RmsUserLoginLog> findRmsUserLoginLogPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserloginlog.selectAll", pageNum, pageSize, map);
    }
}