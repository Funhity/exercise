/**
* @项目名称:
* @文件名称: FeedbackTypeController 版本号：1.0
* @创建日期: 2017-12-12 14:25:06
* <p>
* Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
* <p>
* 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
*/
package chunxian.admin.web.controller.feedback;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.FeedbackType;
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

import java.util.Map;

/**   
 * @Title: 用户反馈类型
 * @Description: 用户反馈类型
 * @author
 */
@Controller
@RequestMapping("")
public class FeedbackTypeController {
    @Autowired
    FeedbackTypeService feedbacktypeService;

    @RequestMapping(value = "feedbacktype", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String base() {
        return "/feedbacktype";
    }

    @RequestMapping(value = "feedbacktypes", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<FeedbackType> listFeedbackType(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        String json = FastJsonUtil.collectToString(params);
        FeedbackType feedbacktype = FastJsonUtil.toBean(json, FeedbackType.class);

        return feedbacktypeService.findFeedbackTypePage(pageNo, pageSize, feedbacktype);
    }

    @RequestMapping(value = "feedbacktypes", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addFeedbackType(@RequestBody FeedbackType feedbacktype) {
        if (feedbacktype == null) {
            throw new BaseException("传入参数不对");
        }

        feedbacktypeService.createFeedbackType(feedbacktype);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "feedbacktypes/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateFeedbackType(@RequestBody FeedbackType feedbacktype, @PathVariable Integer id) {
         if (feedbacktypeService.getFeedbackTypeById(Long.valueOf(id)) == null) {
              throw new BaseException("更新失败，id" + id + "的数据不存在");
         }

         feedbacktype.setId(id);
         Long num = feedbacktypeService.updateFeedbackType(feedbacktype);
        //更新不成功
         if (num < 1) {
             throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
         }

         return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "feedbacktypes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @Login(AuthenType.json)
    public Object deleteFeedbackType(@PathVariable Long id) {
        if (id == null || id < 1) {
            throw new BaseException("传入参数不对");
        }

        feedbacktypeService.deleteFeedbackTypeById(id);

        return MapUtils.EMPTY_MAP;
    }

}
