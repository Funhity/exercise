/**
 * @项目名称:
 * @文件名称: UserMessageServiceImpl 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserMessage;
import chunxian.admin.service.UserMessageService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Title: UserMessageServiceImpl
 * @Description: UserMessageServiceImpl
 * @author
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
	@Resource(name = "mBaseDao")
	private MBaseDao<UserMessage> mBaseDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> queryUserMessageList(Map<String, Object> condition) {
		return mBaseDao.findListByTemplate("usermessage.selectByCondition", condition);
	}

	@Override
	public PageInfo<UserMessage> findUserMessagePage(int pageNum, int pageSize, Map<String, Object> condition) {
		return mBaseDao.findByQuery("usermessage.selectByCondition", pageNum, pageSize, condition);
	}

}
