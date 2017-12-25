/**
 * @项目名称:
 * @文件名称: RmsUserOperationLogService 版本号：1.0
 * @创建日期: 2017/11/1
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsUserOperationLog;

import java.util.List;
import java.util.Map;

/**
 * RmsUserOperationLogService接口
 */
public interface RmsUserOperationLogService {
    Long deleteRmsUserOperationLogById(Long id);
    Long createRmsUserOperationLog(RmsUserOperationLog record);
    Long updateRmsUserOperationLog(RmsUserOperationLog record);
    List<RmsUserOperationLog> queryRmsUserOperationLogList(Map<String, Object> map);
    PageInfo<RmsUserOperationLog> findRmsUserOperationLogPage(int pageNum, int pageSize, Map<String, Object> map);

//    List<RmsUserOperationLog> queryPageDataCount();
    List<RmsUserOperationLog> queryLogList(Map map);
    int queryLogListCount(Map map);

}