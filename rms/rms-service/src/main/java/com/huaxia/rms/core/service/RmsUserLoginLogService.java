/**
 * @项目名称:
 * @文件名称: RmsUserLoginLogService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsUserLoginLog;

import java.util.List;
import java.util.Map;

/**
 * RmsUserLoginLogService接口
 */
public interface RmsUserLoginLogService {
    Long deleteRmsUserLoginLogById(Long id);
    Long createRmsUserLoginLog(RmsUserLoginLog record);
    Long updateRmsUserLoginLog(RmsUserLoginLog record);
    List<RmsUserLoginLog> queryRmsUserLoginLogList(Map<String, Object> map);
    PageInfo<RmsUserLoginLog> findRmsUserLoginLogPage(int pageNum, int pageSize, Map<String, Object> map);
}