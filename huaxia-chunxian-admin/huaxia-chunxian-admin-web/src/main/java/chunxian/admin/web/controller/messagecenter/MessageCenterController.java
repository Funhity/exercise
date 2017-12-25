/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: MessageCenterController 版本号：1.0
 * @创建日期: 2017年12月14日 8:49
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.messagecenter;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.NoticeSendType;
import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.AccountInfo;
import chunxian.admin.model.MessageInfo;
import chunxian.admin.model.Notice;
import chunxian.admin.service.AccountInfoService;
import chunxian.admin.utils.FastJsonUtil;
import chunxian.admin.model.UserMessage;
import chunxian.admin.service.MessageInfoService;
import chunxian.admin.service.NoticeService;
import chunxian.admin.service.UserMessageService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * 说明: 消息中心，包括系统公告和个人消息
 * @author xiaogui
 * @version
 */
@Controller
@RequestMapping(value = "messagecenter")
public class MessageCenterController {
    public static final String ERROR_MESSAGE = "传入参数不对";
    private Logger logger = LoggerFactory.getLogger(MessageCenterController.class);

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    MessageInfoService messageInfoService;

    @Autowired
    UserMessageService userMessageService;

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "main", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String base() {
        return "messagecenter/messageCenter";
    }

    @RequestMapping(value = "addMessage", method = RequestMethod.GET)
    @Login(AuthenType.json)
    public String addMessage(HttpServletRequest request) {
        String messageId = request.getParameter("messageId");
        if(StringUtils.isNotBlank(messageId)) {
            Map<String, Object> queryMap = new HashMap<>(2);
            queryMap.put("id", messageId);
            List<UserMessage> userMessageList = userMessageService.queryUserMessageList(queryMap);
            if(userMessageList != null && !userMessageList.isEmpty()) {
                UserMessage userMessage = userMessageList.get(0);
                request.setAttribute("userMessage", userMessage);
            }
        }

       return "messagecenter/addMessage";
    }

    @RequestMapping(value = "messageinfos", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addMessageInfo(HttpServletRequest request, @RequestBody MessageInfo record) {
        logger.debug("start process request addMessageInfo with record {}", record);
        if (record == null) {
            throw new BaseException(ERROR_MESSAGE);
        }
        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        record.setCreateUser(userName);
        record.setCreateTime(new Date());
        messageInfoService.createMessageInfo(record);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "messageinfos/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateMessageInfo(HttpServletRequest request, @RequestBody MessageInfo record,
                                    @PathVariable Long id) {
        logger.debug("start process request updateMessageInfo by id {} record", id, record);
        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        record.setUpdateUser(userName);
        record.setUpdateTime(new Date());
        Long num = messageInfoService.updateMessageInfo(record);
        // 更新不成功
        if (num < 1) {
            logger.error("update updateMessageInfo {} by id {} failed", record, id);
            throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
        }

        return MapUtils.EMPTY_MAP;
    }



    @RequestMapping(value = "usermessages", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<UserMessage> listUserMessage(@RequestParam Map<String, Object> params) {

        logger.debug("start process request listUserMessage with params {}", params);
        int pageNum = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNum = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        return userMessageService.findUserMessagePage(pageNum, pageSize, params);
    }

    @RequestMapping(value = "receivers", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<AccountInfo> listReceiver(@RequestParam Map<String, Object> params) {
        logger.debug("start process request listReceiver with params {}", params);
        int pageNum = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNum = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        String json = FastJsonUtil.collectToString(params);
        AccountInfo record = FastJsonUtil.toBean(json, AccountInfo.class);

        return accountInfoService.findReceiverPage(pageNum, pageSize, record);
    }

    @RequestMapping(value = "notices", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addNotice(HttpServletRequest request, @RequestBody Notice record) {
        logger.debug("start process request addNotice with record {}", record);
        if (record == null) {
            throw new BaseException(ERROR_MESSAGE);
        }

        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        record.setCreateUser(userName);
        record.setCreateTime(new Date());
        noticeService.createNotice(record);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "notices/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateNotice(HttpServletRequest request, @RequestBody Notice record, @PathVariable Long id) {
        logger.debug("start process request updateNotice by id {} record", id, record);
        Date nowDate = Calendar.getInstance().getTime();
        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        record.setUpdateUser(userName);
        record.setUpdateTime(nowDate);
        if(NoticeSendType.WAITING_SEND.getValue().equals(record.getSendType())){
        	record.setSendTime(nowDate);
        }
        Long num = noticeService.updateNotice(record);
        // 更新不成功
        if (num < 1) {
            logger.error("update updateNotice {} by id {} failed", record, id);
            throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
        }

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "notices/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @Login(AuthenType.json)
    public Object deleteNotice(@PathVariable Long id) {
        logger.debug("start process request deleteNotice by id {}", id);
        if (id == null || id < 1) {
            logger.error("invaild param id {}", id);
            throw new BaseException(ERROR_MESSAGE);
        }
        Long num = 0L;
        Notice deleteRecord = noticeService.findNoticeById(id);
        if(deleteRecord != null){
        	Date nowDate = Calendar.getInstance().getTime();
        	deleteRecord.setUpdateTime(nowDate);
        	deleteRecord.setDeleteTime(nowDate);
        	num = noticeService.updateNotice(deleteRecord);
        }
        // 更新不成功
        if (num < 1) {
            logger.error("update deleteNotice {} by id {} failed", deleteRecord, id);
            throw new BaseException(ResponseCode.ERROR.getValue(), "删除失败");
        }
        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "notices", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<Notice> listNotice(HttpServletResponse response, @RequestParam Map<String, Object> params) {
        logger.debug("start process request listNotice with params {}", params);

        response.setContentType("application/json; charset=utf-8");
        int pageNum = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNum = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        return noticeService.findNoticePage(pageNum, pageSize, params);
    }
}
