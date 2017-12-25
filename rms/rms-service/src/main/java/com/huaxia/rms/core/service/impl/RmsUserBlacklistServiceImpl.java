/**
 * @项目名称:
 * @文件名称: RmsUserBlacklistService 版本号：1.0
 * @创建日期: 2017/11/15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.core.service.RmsUserBlacklistService;
import com.huaxia.rms.core.model.RmsUserBlacklist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blacklistService")
public class RmsUserBlacklistServiceImpl implements RmsUserBlacklistService {
    private Logger logger = LoggerFactory.getLogger(RmsUserBlacklistServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUserBlacklist> mBaseDao;

    @Override
    @MethodLog(remark = "删除黑名单用户")
    public Long deleteRmsUserBlacklistById(Long id) {
        return mBaseDao.delete("rmsuserblacklist.delete", id);
    }

    @Override
    @MethodLog(remark = "新建黑名单用户")
    public Long createRmsUserBlacklist(RmsUserBlacklist record) {
        return mBaseDao.create("rmsuserblacklist.insert", record);
    }

    @Override
    @MethodLog(remark = "更新黑名单用户")
    public Long updateRmsUserBlacklist(RmsUserBlacklist record) {
        return mBaseDao.update("rmsuserblacklist.update", record);
    }

    @Override
    public PageInfo<RmsUserBlacklist> findRmsUserBlacklistPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuserblacklist.selectAll", pageNum, pageSize, map);
    }

    @Override
    //@MethodLog(remark = "查询黑名单列表")
    public List<RmsUserBlacklist> queryUserBlackList(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmsuserblacklist.selectUserBlacklist", map);
    }

    @Override
    public int queryUserBlackListCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuserblacklist.selectUserBlacklistCount", map)));
        return userCount;
    }

    @Override
    public RmsUserBlacklist findOneById(String id) {
        return (RmsUserBlacklist)mBaseDao.findObjectByTemplate("rmsuserblacklist.selectById", id);
    }


    @Override
    public List<RmsUserBlacklist> queryAllBlackList() {
        return mBaseDao.findListByTemplate("rmsuserblacklist.selectAll", null);
    }
}