/**
 * @项目名称:
 * @文件名称: HelpCenterTypeService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.HelpCenterType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: HelpCenterTypeService
 * @Description: 帮助类型
 * @author xiaogui
 */
public interface HelpCenterTypeService {
    /**
     * 查询类型
     * @param id
     * @return
     */
    HelpCenterType getHelpCenterTypeById(Long id);

    /**
     * 删除类型
     * @param id
     * @return
     */
    Long deleteHelpCenterTypeById(Long id);

    /**
     * 新增类型
     * @param record
     * @return
     */
    Long createHelpCenterType(HelpCenterType record);

    /**
     * 更新类型
     * @param record
     * @return
     */
    Long updateHelpCenterType(HelpCenterType record);

    /**
     * 查询类型列表
     * @param record
     * @return
     */
    List<HelpCenterType> queryHelpCenterTypeList(HelpCenterType record);

    /**
     * 查询类型分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<HelpCenterType> findHelpCenterTypePage(int pageNum, int pageSize, HelpCenterType record);

    /**
     * sort和type是否存在
     * @param record
     * @return 0不存在
     */
    Integer countBySort(HelpCenterType record);

    /**
     * 存在重复,低位调到高位
     * 高位后的序号+1
     * @param record
     * @return
     */
    Long updateSortByLowToHigh(HelpCenterType record);

    /**
     * 存在重复,高位调到低位
     * 低位与高位之间的序号+1
     * @param record
     * @return
     */
    Long updateSortByHighToLow(HelpCenterType record);
}