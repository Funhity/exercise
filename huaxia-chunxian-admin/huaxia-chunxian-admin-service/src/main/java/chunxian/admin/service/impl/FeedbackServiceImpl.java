/**
 * @项目名称:
 * @文件名称: FeedbackServiceImpl 版本号：1.0
 * @创建日期: 2017-12-12 14:25:52
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.Feedback;
import chunxian.admin.service.FeedbackService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: FeedbackServiceImpl
 * @Description: FeedbackServiceImpl
 * @author
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource(name="mBaseDao")
    private MBaseDao<Feedback> mBaseDao;

    @Override
    public Feedback getFeedbackById(Long id) {
        return (Feedback) mBaseDao.findObjectByTemplate("feedback.selectById", id);
    }

    @Override
    public Long deleteFeedbackById(Long id) {
        return mBaseDao.delete("feedback.delete", id);
    }

    @Override
    public Long createFeedback(Feedback record) {
        return mBaseDao.create("feedback.insert", record);
    }

    @Override
    public Long updateFeedback(Feedback record) {
        return mBaseDao.update("feedback.update", record);
    }

    @Override
    public List<Feedback> queryFeedbackList(Feedback record) {
        return mBaseDao.findListByTemplate("feedback.selectByCondition", record);
    }

    @Override
    public PageInfo<Feedback> findFeedbackPage(int pageNum, int pageSize, Feedback record) {
        return mBaseDao.findByQuery("feedback.selectByCondition", pageNum, pageSize, record);
    }
}