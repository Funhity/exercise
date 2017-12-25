/**
 * @项目名称:
 * @文件名称: NoticeService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.Notice;
import chunxian.admin.service.NoticeService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title: NoticeServiceImpl
 * @Description: NoticeServiceImpl
 * @author
 */
@Service
public class NoticeServiceImpl implements NoticeService {
	@Resource(name = "mBaseDao")
	private MBaseDao<Notice> mBaseDao;

	@Override
	public Long deleteNoticeById(Long id, Date date) {
		return mBaseDao.delete("notice.delete", id);
	}

	@Override
	public Long createNotice(Notice record) {
		return mBaseDao.create("notice.insert", record);
	}

	@Override
	public Long updateNotice(Notice record) {
		return mBaseDao.update("notice.update", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> queryNoticeList(Map<String, Object> condition) {
		return mBaseDao.findListByTemplate("notice.selectByCondition", condition);
	}

	@Override
	public PageInfo<Notice> findNoticePage(int pageNum, int pageSize, Map<String, Object> condition) {
		return mBaseDao.findByQuery("notice.selectByCondition", pageNum, pageSize, condition);
	}

	@Override
	public Notice findNoticeById(Long id) {
		return (Notice) mBaseDao.findObjectByTemplate("notice.selectById", id);
	}
}