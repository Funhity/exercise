/**
 * @项目名称:
 * @文件名称: RmsUserBlacklistService 版本号：1.0
 * @创建日期: 2017/11/15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsUserBlacklist;

import java.util.List;
import java.util.Map;

/**
 * RmsUserBlacklistService接口
 */
public interface RmsUserBlacklistService {
    Long deleteRmsUserBlacklistById(Long id);
    Long createRmsUserBlacklist(RmsUserBlacklist record);
    Long updateRmsUserBlacklist(RmsUserBlacklist record);
    PageInfo<RmsUserBlacklist> findRmsUserBlacklistPage(int pageNum, int pageSize, Map<String, Object> map);

    List<RmsUserBlacklist> queryUserBlackList(Map<String, Object> map);
    int queryUserBlackListCount(Map map);

    RmsUserBlacklist findOneById(String id);

    List<RmsUserBlacklist> queryAllBlackList();
}