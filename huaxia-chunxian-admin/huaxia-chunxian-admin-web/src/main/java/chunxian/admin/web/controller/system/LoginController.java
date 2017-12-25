/**
 * @项目名称:
 * @文件名称: LoginController 版本号：1.0
 * @创建日期: 2017-12-07 16:40:12
 * <p>
 * Copyrights © 2017 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.system;

import chunxian.admin.constant.AdminConstant;
import com.huaxia.common.core.common.BaseController;
import com.huaxia.rms.api.service.ApiResourceService;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.CookieUtils;
import com.huaxia.sso.utils.SessionUtils;
import com.huaxia.sso.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: LoginController
 * @Description: LoginController
 * @author
 */
@Controller
@RequestMapping(value = "main")
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${admin.url.prefix}")
    private String adminUrlPrefix;
    
    @Value("${sso.server.logout.url}")
    private String logouUrl;

    @Resource
    private ApiResourceService apiResourceService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String login(HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        logger.info("---------authorization: {}", authorization);

        if(authorization !=null) {
            int uid = authorization.getId();
            String menus = apiResourceService.getSourceTreeByUid(uid);
            String username = authorization.getUsername();
            request.setAttribute(AdminConstant.RMS_USERNAME, username);
            request.setAttribute(AdminConstant.ADMINLTE_MENU_JSON, menus);
        }
        return "/layouts/default";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String baseRetrun() {
        return "/index/index";
    }
    
    

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 统一登录退出接口
         * 链接中存在cas_logout=true表示用户退出
         */
        String outUrl = logouUrl;

        String contextPath = adminUrlPrefix;

        logger.debug("-----------用户退出，清除session和cookie-----------");

        CookieUtils.removeAllCookie(request, response);
        SessionUtils.removeLoginSession(request, response);

        String logoutUrl = outUrl + "?redirect="  + contextPath + request.getContextPath() +  "/main/login";

        logger.debug("-----------退出登录---------");
        return "redirect:" + logoutUrl;
    }

}