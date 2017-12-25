/**
 * @项目名称:
 * @文件名称: FeedbackTypeServiceImpl 版本号：1.0
 * @创建日期: 2017-12-12 14:25:06
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.FeedbackType;
import chunxian.admin.service.FeedbackTypeService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: FeedbackTypeServiceImpl
 * @Description: FeedbackTypeServiceImpl
 * @author
 */
@Service
public class FeedbackTypeServiceImpl implements FeedbackTypeService {
    @Resource(name="mBaseDao")
    private MBaseDao<FeedbackType> mBaseDao;

    @Override
    public FeedbackType getFeedbackTypeById(Long id) {
        return (FeedbackType) mBaseDao.findObjectByTemplate("feedbacktype.selectById", id);
    }

    @Override
    public Long deleteFeedbackTypeById(Long id) {
        return mBaseDao.delete("feedbacktype.delete", id);
    }

    @Override
    public Long createFeedbackType(FeedbackType record) {
        return mBaseDao.create("feedbacktype.insert", record);
    }

    @Override
    public Long updateFeedbackType(FeedbackType record) {
        return mBaseDao.update("feedbacktype.update", record);
    }

    @Override
    public List<FeedbackType> queryFeedbackTypeList(FeedbackType record) {
        return mBaseDao.findListByTemplate("feedbacktype.selectByCondition", record);
    }

    @Override
    public PageInfo<FeedbackType> findFeedbackTypePage(int pageNum, int pageSize, FeedbackType record) {
        return mBaseDao.findByQuery("feedbacktype.selectByCondition", pageNum, pageSize, record);
    }
}