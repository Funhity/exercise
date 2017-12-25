/**
 * @项目名称:
 * @文件名称: JumpImageConfigService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;

import chunxian.admin.model.JumpImageConfig;

/**
 * @Title: JumpImageConfigService
 * @Description: 图片服务
 * @author
 */
public interface JumpImageConfigService {
	/**
	 * 删除图片
	 * @param id
	 * @param date
	 * @return
	 */
	Long deleteJumpImageConfigById(Long id, Date date);

	/**
	 * 新增图片
	 * @param record
	 * @return
	 */
	Long createJumpImageConfig(JumpImageConfig record);

	/**
	 * 更新图片
	 * @param record
	 * @return
	 */
	Long updateJumpImageConfig(JumpImageConfig record);

	/**
	 * 查询图片
	 * @param record
	 * @return
	 */
	List<JumpImageConfig> queryJumpImageConfigList(JumpImageConfig record);

	/**
	 * 查询图片
	 * @param pageNum
	 * @param pageSize
	 * @param record
	 * @return
	 */
	PageInfo<JumpImageConfig> findJumpImageConfigPage(int pageNum, int pageSize, JumpImageConfig record);

	/**
	 * 查询图片
	 * @param id
	 * @return
	 */
	JumpImageConfig getJumpImageConfigById(Long id);

	/**
	 * sort和type是否存在
	 * @param record
	 * @return 0不存在
	 */
	Integer countBySort(JumpImageConfig record);

	/**
	 * 存在重复,低位调到高位
	 * 高位后的序号+1
	 * @param record
	 * @return
	 */
	Long updateSortByLowToHigh(JumpImageConfig record);

	/**
	 * 存在重复,高位调到低位
	 * 低位与高位之间的序号+1
	 * @param record
	 * @return
	 */
	Long updateSortByHighToLow(JumpImageConfig record);
}