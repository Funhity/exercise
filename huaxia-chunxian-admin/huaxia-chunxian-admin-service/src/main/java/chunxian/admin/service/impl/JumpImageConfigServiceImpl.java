/**
 * @项目名称:
 * @文件名称: JumpImageConfigService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.JumpImageConfig;
import chunxian.admin.service.JumpImageConfigService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: JumpImageConfigServiceImpl
 * @Description: JumpImageConfigServiceImpl
 * @author
 */
@Service
public class JumpImageConfigServiceImpl implements JumpImageConfigService {
	@Resource(name = "mBaseDao")
	private MBaseDao<JumpImageConfig> mBaseDao;

	@Override
	public Long deleteJumpImageConfigById(Long id, Date date) {
		Map<String, Object> map = new HashMap(2);
		map.put("deleteTime", date);
		map.put("id", id);
		return mBaseDao.update("jumpimageconfig.softDelete", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long createJumpImageConfig(JumpImageConfig record) {
		JumpImageConfig sortRecord = new JumpImageConfig();
		sortRecord.setSort(record.getSort());
		sortRecord.setType(record.getType());
		Integer count = countBySort(sortRecord);
		if(count > 0) {
			//sort存在,低位到高位规则，存在sort及之后+1
			updateSortByLowToHigh(sortRecord);
		}

		return mBaseDao.create("jumpimageconfig.insert", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long updateJumpImageConfig(JumpImageConfig record) {
		//查询原有的sort
		JumpImageConfig oldRecord = getJumpImageConfigById(record.getId());
		Integer oldSort = oldRecord.getSort();

		JumpImageConfig sortRecord = new JumpImageConfig();
		sortRecord.setSort(record.getSort());
		sortRecord.setType(record.getType());
		Integer count = countBySort(sortRecord);
		if(count > 0) {
			JumpImageConfig hlRecord = new JumpImageConfig();
			hlRecord.setSort(oldSort);
			hlRecord.setToSort(record.getSort());
			hlRecord.setType(record.getType());
			if(oldSort < record.getSort()) {
				//sort低位到高位规则，存在sort及之后+1
				updateSortByLowToHigh(hlRecord);
			} else {
				//高位到低位规则,低位到高位之间+1
				updateSortByHighToLow(hlRecord);
			}
		}

		return mBaseDao.update("jumpimageconfig.update", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JumpImageConfig> queryJumpImageConfigList(JumpImageConfig record) {
		return mBaseDao.findListByTemplate("jumpimageconfig.selectByCondition", record);
	}

	@Override
	public PageInfo<JumpImageConfig> findJumpImageConfigPage(int pageNum, int pageSize, JumpImageConfig record) {
		return mBaseDao.findByQuery("jumpimageconfig.selectByCondition", pageNum, pageSize, record);
	}

	@Override
	public JumpImageConfig getJumpImageConfigById(Long id) {
		return (JumpImageConfig) mBaseDao.findObjectByTemplate("jumpimageconfig.selectById", id);
	}

	@Override
	public Integer countBySort(JumpImageConfig record) {
		return (Integer) mBaseDao.findObjectByTemplate("jumpimageconfig.countBySort", record);
	}

	@Override
	public Long updateSortByLowToHigh(JumpImageConfig record) {
		return mBaseDao.update("jumpimageconfig.updateSortByLowToHigh", record);
	}

	@Override
	public Long updateSortByHighToLow(JumpImageConfig record) {
		return mBaseDao.update("jumpimageconfig.updateSortByHighToLow", record);
	}
}