/**
 * @项目名称:
 * @文件名称: RmsLabelService 版本号：1.0
 * @创建日期: 2017/12/20
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsLabel;

import java.util.List;
import java.util.Map;

/**
 * RmsLabelService接口
 */
public interface RmsLabelService {
    Long deleteRmsLabelById(Long id);
    Long createRmsLabel(RmsLabel record);
    Long updateRmsLabel(RmsLabel record);
    List<RmsLabel> queryRmsLabelList(Map<String, Object> map);
    PageInfo<RmsLabel> findRmsLabelPage(int pageNum, int pageSize, Map<String, Object> map);

    List<RmsLabel> queryLabelList(Map<String, Object> map);
    int queryLabelListCount(Map map);

    String getNamesByIds(String ids);
}