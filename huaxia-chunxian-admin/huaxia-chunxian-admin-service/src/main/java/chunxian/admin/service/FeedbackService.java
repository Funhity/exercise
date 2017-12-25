/**
 * @项目名称:
 * @文件名称: FeedbackService 版本号：1.0
 * @创建日期: 2017-12-12 14:25:52
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.Feedback;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: 用户反馈内容
 * @Description: 用户反馈内容
 * @author
 */
public interface FeedbackService {
    /**
     * 查询反馈
     * @param id
     * @return
     */
    Feedback getFeedbackById(Long id);

    /**
     * 删除反馈
     * @param id
     * @return
     */
    Long deleteFeedbackById(Long id);

    /**
     * 新增反馈
     * @param record
     * @return
     */
    Long createFeedback(Feedback record);

    /**
     * 更新反馈
     * @param record
     * @return
     */
    Long updateFeedback(Feedback record);

    /**
     * 查询反馈列表
     * @param record
     * @return
     */
    List<Feedback> queryFeedbackList(Feedback record);

    /**
     * 查询反馈分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<Feedback> findFeedbackPage(int pageNum, int pageSize, Feedback record);
}