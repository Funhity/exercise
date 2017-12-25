/**
 * @项目名称:
 * @文件名称: RmsUserOperationLogService 版本号：1.0
 * @创建日期: 2017/11/1
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.service.RmsUserOperationLogService;
import com.huaxia.rms.core.model.RmsUserOperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("logService")
public class RmsUserOperationLogServiceImpl implements RmsUserOperationLogService {
    private Logger logger = LoggerFactory.getLogger(RmsUserOperationLogServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserOperationLog> mBaseDao;

    @Override
    public Long deleteRmsUserOperationLogById(Long id) {
        return mBaseDao.delete("rmsuseroperationlog.delete", id);
    }

    @Override
    public Long createRmsUserOperationLog(RmsUserOperationLog record) {
        return mBaseDao.create("rmsuseroperationlog.insert", record);
    }

    @Override
    public Long updateRmsUserOperationLog(RmsUserOperationLog record) {
        return mBaseDao.update("rmsuseroperationlog.update", record);
    }

    @Override
    @MethodLog(remark = "查询用户操作日志列表")
    public List<RmsUserOperationLog> queryRmsUserOperationLogList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuseroperationlog.selectAll", map);
    }

    @Override
    public PageInfo<RmsUserOperationLog> findRmsUserOperationLogPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuseroperationlog.selectAll", pageNum, pageSize, map);
    }


    @Override
    @MethodLog(remark = "查询用户操作日志列表")
    public List<RmsUserOperationLog> queryLogList(Map map) {
        return mBaseDao.findListByTemplate("rmsuseroperationlog.queryPageData", map);
    }

    @Override
    public int queryLogListCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuseroperationlog.queryPageDataCount", map)));
        return userCount;
    }

}