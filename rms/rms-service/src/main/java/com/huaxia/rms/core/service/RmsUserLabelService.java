/**
 * @项目名称:
 * @文件名称: RmsUserLabelService 版本号：1.0
 * @创建日期: 2017/12/20
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsUserLabel;

import java.util.List;
import java.util.Map;

/**
 * RmsUserLabelService接口
 */
public interface RmsUserLabelService {
    Long deleteRmsUserLabelById(Long id);
    Long createRmsUserLabel(RmsUserLabel record);
    Long updateRmsUserLabel(RmsUserLabel record);
    List<RmsUserLabel> queryRmsUserLabelList(Map<String, Object> map);
    PageInfo<RmsUserLabel> findRmsUserLabelPage(int pageNum, int pageSize, Map<String, Object> map);

    /**
     * 批量保存数据
     * @param list 标签列表
     * @return 执行结果
     */
    void saveBatchUserLabel(List<RmsUserLabel> list);

    void deleteUserLabelByUid(Map map);
}