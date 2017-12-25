/**
 * @项目名称:
 * @文件名称: FeedbackTypeService 版本号：1.0
 * @创建日期: 2017-12-12 14:25:06
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.FeedbackType;
import java.util.List;

/**
 * @Title: 用户反馈类型
 * @Description: 用户反馈类型
 * @author
 */
public interface FeedbackTypeService {
    /**
     * 根据ID查反馈类型
     * @param id
     * @return FeedbackType
     */
    FeedbackType getFeedbackTypeById(Long id);

    /**
     * 删除反馈类型
     * @param id
     * @return
     */
    Long deleteFeedbackTypeById(Long id);

    /**
     * 新增反馈类型
     * @param record
     * @return
     */
    Long createFeedbackType(FeedbackType record);

    /**
     * 更新反馈类型
     * @param record
     * @return
     */
    Long updateFeedbackType(FeedbackType record);

    /**
     * 反馈类型列表
     * @param record
     * @return
     */
    List<FeedbackType> queryFeedbackTypeList(FeedbackType record);

    /**
     * 反馈类型分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<FeedbackType> findFeedbackTypePage(int pageNum, int pageSize, FeedbackType record);
}