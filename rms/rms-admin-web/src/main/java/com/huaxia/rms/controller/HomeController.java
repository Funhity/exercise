package com.huaxia.rms.controller;

import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.service.RmsUserService;
import com.huaxia.sso.utils.CookieUtils;
import com.huaxia.sso.utils.SessionUtils;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.model.RmsSsoUser;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.service.UserService;
import com.huaxia.sso.utils.Authorization;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/")
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Value("${rms_url}")
    private String rms_url;

    @Value("${sso.server.logout.url}")
    private String logout_url;

    @Autowired
    private RmsUserService userService;

    @Resource
    private UserService ssoUserService;

    @RequestMapping("/index.jhtml")
    @Login(AuthenType.page)
    private String index(HttpServletRequest request) {

        Authorization authorization = WebUtils.getSessionUser(request);
        logger.info("---------authorization: " + authorization);

        RmsUser user = new RmsUser();
        if(authorization != null) {
            user = userService.findOneById(authorization.getId());

            /**
             * 用户登陆成功后，
             * 将用户放入shiro中管理
             */
            Subject subject = SecurityUtils.getSubject();
            logger.info("-------subject: " + subject);
            logger.info("-------subject.isAuthenticated: " + subject.isAuthenticated());
            if (!subject.isAuthenticated()) {
                //subject = SecurityUtils.getSubject() ;
                UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword()) ;
                subject.login(token);
            }

        }

//        String logoutUrl = logout_url + "?redirect=" + rms_url + "/index.jhtml";
//        request.setAttribute("logoutUrl", logoutUrl);

        request.setAttribute("user", user);

        return "homepage";
    }

    @RequestMapping("/welcome.jhtml")
    private String welcome(HttpServletRequest request) {

        Authorization authorization = WebUtils.getSessionUser(request);
        logger.info("---------authorization: " + authorization);

        RmsUser user = new RmsUser();
        if(authorization != null) {
            user = userService.findOneById(authorization.getId());
        }
        request.setAttribute("user", user);
        return "welcome";
    }

    @RequestMapping("/logout.jhtml")
    @Login(AuthenType.page)
    private ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

        /**
         * 统一登录退出接口
         * 链接中存在cas_logout=true表示用户退出
         */
        //String logout = request.getParameter("logout");
        String logoutUrl = "";
        try {
            logger.info("-----------用户退出，清除session和cookie-----------");
            Authorization authorization = WebUtils.getSessionUser(request);
            logger.info("---------authorization: " + authorization);

            if(authorization != null) {
                RmsUser user = userService.findOneById(authorization.getId());
                user.setUserOnline(0);
                userService.updateRmsUser(user);
//                request.setAttribute("user", user);
            }
            CookieUtils.removeAllCookie(request, response);
            SessionUtils.removeLoginSession(request, response);
            String redirect =  URLEncoder.encode((rms_url+ "/index.jhtml"), "UTF-8");
            logoutUrl = logout_url + "?redirect=" + redirect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("-----------退出登录---------");
        return new ModelAndView(new RedirectView(logoutUrl));
    }


    @RequestMapping("/test.jhtml")
    @ResponseBody
    //@Login(AuthenType.page)
    private String test(HttpServletRequest request) {

        logger.info("-----------测试调用dubbo服务接口------------");
        Authorization authorization = WebUtils.getSessionUser(request);
        logger.info("---------authorization: " + authorization);

        Authorization auth = ssoUserService.getAuthorizationByTgc(authorization.getTgc());

        RmsSsoUser ssoUser = ssoUserService.getSSOUserByUid(String.valueOf(authorization.getId()));

        return ssoUser.getName();
    }
}