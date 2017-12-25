/**
 * @项目名称:
 * @文件名称: HelpCenterQaService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.HelpCenterQa;

import java.util.List;


/**
 * @Title: HelpCenterQaService
 * @Description: 热点问题服务
 * @author xiaogui
 */
public interface HelpCenterQaService {
    /**
     * 获取热点问题
     * @param id
     * @return
     */
    HelpCenterQa getHelpCenterQaById(Long id);

    /**
     * 删除热点问题
     * @param id
     * @return
     */
    Long deleteHelpCenterQaById(Long id);

    /**
     * 新建热点问题
     * @param record
     * @return
     */
    Long createHelpCenterQa(HelpCenterQa record);

    /**
     * 更新热点问题
     * @param record
     * @return
     */
    Long updateHelpCenterQa(HelpCenterQa record);

    /**
     * 查询热点问题列表
     * @param record
     * @return
     */
    List<HelpCenterQa> queryHelpCenterQaList(HelpCenterQa record);

    /**
     * 查询热点问题分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<HelpCenterQa> findHelpCenterQaPage(int pageNum, int pageSize, HelpCenterQa record);

    /**
     * sort和type是否存在
     * @param record
     * @return 0不存在
     */
    Integer countBySort(HelpCenterQa record);

    /**
     * 存在重复,低位调到高位
     * 高位后的序号+1
     * @param record
     * @return
     */
    Long updateSortByLowToHigh(HelpCenterQa record);

    /**
     * 存在重复,高位调到低位
     * 低位与高位之间的序号+1
     * @param record
     * @return
     */
    Long updateSortByHighToLow(HelpCenterQa record);
}