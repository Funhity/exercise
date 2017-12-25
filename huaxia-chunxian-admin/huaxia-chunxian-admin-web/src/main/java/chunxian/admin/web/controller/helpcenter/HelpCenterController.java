/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: HelpCenterController 版本号：1.0
 * @创建日期: 2017年12月13日 23:50
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.helpcenter;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.HelpCenterQa;
import chunxian.admin.model.HelpCenterType;
import chunxian.admin.service.HelpCenterQaService;
import chunxian.admin.service.HelpCenterTypeService;
import chunxian.admin.utils.FastJsonUtil;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 说明: 帮助中心
 * @author xiaogui
 * @version 1.0
 */
@Controller
@RequestMapping(value = "helpcenter")
public class HelpCenterController {
    public static final String ERRPOR_MESSAGE = "传入参数不对";

    @Autowired
    HelpCenterQaService helpCenterQaService;

    @Autowired
    HelpCenterTypeService helpCenterTypeService;

    @RequestMapping(value = "type", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String helpCenterType() {
        return "helpcenter/helpCenterType";
    }

    @RequestMapping(value = "qa", method = RequestMethod.GET)
    @Login(AuthenType.json)
    public String helpCenterQa(HttpServletRequest request) {
        String typeId = request.getParameter("typeId");
        if(StringUtils.isNotBlank(typeId)) {
            request.setAttribute("typeId", typeId);
        }
        return "helpcenter/helpCenterQa";
    }

    @RequestMapping(value = "types", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<HelpCenterType> listType(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        String json = FastJsonUtil.collectToString(params);
        HelpCenterType helpCenterType = FastJsonUtil.toBean(json, HelpCenterType.class);

        return helpCenterTypeService.findHelpCenterTypePage(pageNo, pageSize, helpCenterType);
    }

    @RequestMapping(value = "types", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addType(@RequestBody HelpCenterType helpCenterType, HttpServletRequest request) {
        if (helpCenterType == null) {
            throw new BaseException(ERRPOR_MESSAGE);
        }

        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        helpCenterType.setCreateUser(userName);
        helpCenterType.setCreateTime(new Date());
        helpCenterTypeService.createHelpCenterType(helpCenterType);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "types/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateType(@RequestBody HelpCenterType helpCenterType, @PathVariable Long id, HttpServletRequest request) {
        if (helpCenterTypeService.getHelpCenterTypeById(id) == null) {
            throw new BaseException("更新失败，id" + id + "的数据不存在");
        }

        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        helpCenterType.setUpdateUser(userName);
        helpCenterType.setUpdateTime(new Date());
        helpCenterType.setId(id);
        Long num = helpCenterTypeService.updateHelpCenterType(helpCenterType);
        //更新不成功
        if (num < 1) {
            throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
        }

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "types/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @Login(AuthenType.json)
    public Object deleteType(@PathVariable Long id) {
        if (id == null || id < 1) {
            throw new BaseException(ERRPOR_MESSAGE);
        }

        helpCenterTypeService.deleteHelpCenterTypeById(id);

        return MapUtils.EMPTY_MAP;
    }


    @RequestMapping(value = "qas", method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<HelpCenterQa> listQa(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }

        String json = FastJsonUtil.collectToString(params);
        HelpCenterQa helpCenterQa = FastJsonUtil.toBean(json, HelpCenterQa.class);

        return helpCenterQaService.findHelpCenterQaPage(pageNo, pageSize, helpCenterQa);
    }

    @RequestMapping(value = "qas", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Object addQa(@RequestBody HelpCenterQa helpCenterQa, HttpServletRequest request) {
        if (helpCenterQa == null) {
            throw new BaseException(ERRPOR_MESSAGE);
        }

        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        helpCenterQa.setCreateUser(userName);
        helpCenterQa.setCreateTime(new Date());
        helpCenterQaService.createHelpCenterQa(helpCenterQa);

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "qas/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseBody
    @Login(AuthenType.json)
    public Object updateQa(@RequestBody HelpCenterQa helpCenterQa, @PathVariable Long id, HttpServletRequest request) {
        if (helpCenterQaService.getHelpCenterQaById(id) == null) {
            throw new BaseException("更新失败，id" + id + "的数据不存在");
        }

        String userName = AdminConstant.SYSTEM_USER;
        if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
            userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
        }
        helpCenterQa.setUpdateUser(userName);
        helpCenterQa.setUpdateTime(new Date());
        helpCenterQa.setId(id);
        Long num = helpCenterQaService.updateHelpCenterQa(helpCenterQa);
        //更新不成功
        if (num < 1) {
            throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
        }

        return MapUtils.EMPTY_MAP;
    }

    @RequestMapping(value = "qas/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @Login(AuthenType.json)
    public Object deleteQa(@PathVariable Long id) {
        if (id == null || id < 1) {
            throw new BaseException(ERRPOR_MESSAGE);
        }

        helpCenterQaService.deleteHelpCenterQaById(id);

        return MapUtils.EMPTY_MAP;
    }
}
