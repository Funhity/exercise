/**
* @项目名称:
* @文件名称: FeedbackController 版本号：1.0
* @创建日期: 2017-12-12 14:25:52
* <p>
* Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
* <p>
* 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
*/
package chunxian.admin.web.controller.feedback;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.Feedback;
import chunxian.admin.model.FeedbackType;
import chunxian.admin.service.FeedbackService;
import chunxian.admin.service.FeedbackTypeService;
import chunxian.admin.utils.FastJsonUtil;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**   
 * @Title: 用户反馈内容
 * @Description: 用户反馈内容
 * @author
 */
@Controller
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    
    @Autowired
    FeedbackTypeService feedbacktypeService;

    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String base() {
    	FeedbackType searchRecord = new FeedbackType();
    	searchRecord.setVisible(1);
        return "feedback/feedback";
    }
    
    @RequestMapping(value = "feedbackType", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public List<FeedbackType> feedbackType() {
    	FeedbackType searchRecord = new FeedbackType();
    	searchRecord.setVisible(1);
        return feedbacktypeService.queryFeedbackTypeList(searchRecord);
    }

    @RequestMapping(value = "feedbacks", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<Feedback> listFeedback(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        String json = FastJsonUtil.collectToString(params);
        Feedback feedback = FastJsonUtil.toBean(json, Feedback.class);

        return feedbackService.findFeedbackPage(pageNo, pageSize, feedback);
    }

    @RequestMapping(value = "feedbacks", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addFeedback(@RequestBody Feedback feedback) {
        if (feedback == null) {
            throw new BaseException("传入参数不对");
        }

        feedbackService.createFeedback(feedback);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "feedbacks/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateFeedback(@RequestBody Feedback feedback, @PathVariable Long id) {
         if (feedbackService.getFeedbackById(id) == null) {
              throw new BaseException("更新失败，id" + id + "的数据不存在");
         }

         feedback.setId(id);
         Long num = feedbackService.updateFeedback(feedback);
        //更新不成功
         if (num < 1) {
             throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
         }

         return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "feedbacks/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @Login(AuthenType.json)
    public Object deleteFeedback(@PathVariable Long id) {
        if (id == null || id < 1) {
            throw new BaseException("传入参数不对");
        }

        feedbackService.deleteFeedbackById(id);

        return MapUtils.EMPTY_MAP;
    }

}
