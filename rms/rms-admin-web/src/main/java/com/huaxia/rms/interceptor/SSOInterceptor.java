package com.huaxia.rms.interceptor;

import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.WebUtility;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆过滤器
 * 如果用户登陆，则将用户放入shiro中管理
 */
public class SSOInterceptor  extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(SSOInterceptor.class);

    @Value("${sso.server.login.url}")
    private String ssoLoginUrl;

    @Value("${sso.sysid}")
    private String sysid;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("----------------sso用户权限过滤器---------------");
        Authorization authorization = WebUtils.getSessionUser(request);
        if(authorization != null) {
            String username = authorization.getUsername();

            Subject subject = SecurityUtils.getSubject() ;
            UsernamePasswordToken token = new UsernamePasswordToken(authorization.getUsername(), "") ;
            subject.login(token);
            return true;
        }


//        String reqUrl = request.getScheme() + "://" + request.getServerName() + ":"
//                + request.getServerPort() + request.getContextPath() + request.getRequestURI();
//        response.sendRedirect(ssoLoginUrl + "?redirect=" + reqUrl);

        //String redirUrl = WebUtility.generatorRequestSsoURL(request, Configuration.getProperty("sso.server.login.url") , Configuration.getProperty("sysid"));
        String redirUrl = WebUtility.generatorRequestSsoURL(request, ssoLoginUrl , sysid);

        //logger.info("【PAGE页重定向】:" + redirUrl);
        response.sendRedirect(redirUrl);
        return false;

    }
}
