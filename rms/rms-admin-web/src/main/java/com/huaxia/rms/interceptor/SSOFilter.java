package com.huaxia.rms.interceptor;

import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.utils.Authorization;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆过滤器
 * 如果用户登陆，则将放入shiro中管理
 */
public class SSOFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(SSOFilter.class);

//    @Value("${sso.server.login.url}")
    private String ssoLoginUrl;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("----------------sso用户权限验证---------------");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Authorization authorization = WebUtils.getSessionUser(httpServletRequest);
        if(authorization != null) {
            String username = authorization.getUsername();

            /**
             * 将用户放入shiro中管理
             */
            Subject subject = SecurityUtils.getSubject() ;
            UsernamePasswordToken token = new UsernamePasswordToken(authorization.getUsername(), "") ;
            subject.login(token);
            return;
        }


        String reqUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":"
                + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getRequestURI();

//        Object username = httpServletRequest.getSession().getAttribute("username");
//        if (username != null) {
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//            return;
//        }
//        String sid = httpServletRequest.getParameter("SHAREJSESSIONID");
//        if (StringUtils.isNotEmpty(sid)) {
//            Cookie cookie = new Cookie("SHAREJSESSIONID", sid);
//            cookie.setPath("/");
//            httpServletResponse.addCookie(cookie);
//            String html = "<html><head><script type=\"text/javascript\">location.href='" + reqUrl + "'</script></head><body></body></html>";
//            byte[] bytes = html.getBytes();
//            httpServletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
//            httpServletResponse.getOutputStream().write(bytes);
//            httpServletResponse.getOutputStream().flush();
//            httpServletResponse.getOutputStream().close();
//            return;
//        }

        httpServletResponse.sendRedirect(ssoLoginUrl + "?redirect=" + reqUrl);
    }


    public void destroy() {

    }

    public String getSsoLoginUrl() {
        return ssoLoginUrl;
    }

    public void setSsoLoginUrl(String ssoLoginUrl) {
        this.ssoLoginUrl = ssoLoginUrl;
    }
}

