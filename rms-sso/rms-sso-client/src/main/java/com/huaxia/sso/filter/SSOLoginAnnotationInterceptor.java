package com.huaxia.sso.filter;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huaxia.sso.constants.Constants;
import com.huaxia.sso.service.UserService;
import com.huaxia.sso.utils.*;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * SSO外部权限拦截器
 * @author Liu zhilai by 20170925
 */
//@Component
public class SSOLoginAnnotationInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(SSOLoginAnnotationInterceptor.class);
//    private static Logger logger = Logger.getLogger(SSOLoginAnnotationInterceptor.class);

    @Resource
    private UserService ssoUserService;

    @Value("${sso.server.login.url}")
    private String loginUrl;

    @Value("${sso.sysid}")
    private String sysid;

    public UserService getSsoUserService() {
        return ssoUserService;
    }

    public void setSsoUserService(UserService ssoUserService) {
        this.ssoUserService = ssoUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("SSO filter RequestURL:"+ request.getRequestURL() +",QueryString:"+request.getQueryString());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Login login = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        if (null == login) {
            // 没有声明权限,放行
            logger.info("没有声明权限,放行");
            return true;
        }
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String url = requestURI.substring(contextPath.length());

        // 获取到的授权信息，从cookie里获取tgc
        Authorization authorization = ActionContext.getAuthorization();

        // 解决 Swfupload 上传无法 获取 Cookie 导致 重定向 到 SSO 登录页面 BUG.
        if (null == authorization && StringUtils.isNotBlank(request.getParameter("tgc"))) {
            authorization = new Authorization();
            authorization.setTgc(request.getParameter("tgc"));
            logger.info("有TGC，无用户信息，赋值TGC");
        }

        /**从redis中获取信息*/
        Authorization authorizationTgc = ActionContext.getAuthorization();
        if (null != authorization) {
            /** 从redis中获取tgc */
            String tgc = authorization.getTgc();
            logger.info("-----------用戶权限验证过滤器，调用dubbo服务获取Tgc 开始");
            authorizationTgc = ssoUserService.getAuthorizationByTgc(tgc);
            logger.info("-----------用戶权限验证过滤器，调用dubbo服务获取Tgc 结束，获取结果： " + authorizationTgc);
        }

        // 检测是否已登录
        if (null != authorization && (null != authorizationTgc
                && (0 == authorization.getId() || authorization.getId() == authorizationTgc.getId()))) {
            logger.info("已登录");
            authorization = authorizationTgc;
            //  解决 Swfupload 上传后续无法 获取 authorization
            if (StringUtils.isNotBlank(request.getParameter("tgc"))) {
                request.setAttribute(Constants.CAS_AUTHENTICATION_KEY, authorization);
            }

            logger.info("验证成功，用户已登录");
            return true;
        }

        /**
         * 通过ticket登陆
         * 1、通过ticket获取缓存中的TGC
         * 2、根据TGC获取用户的授权信息authorization
         * 3、将授权信息放入客户端cookie中
         */
        String ticket = request.getParameter(Constants.CAS_TICKET_KEY);
        if (StringUtils.isNotBlank(ticket)) {
            // 验证 Ticket是否有效
            // 还原 validationTicket方法。
            authorization = ssoUserService.getAuthorizationByTicket(ticket);
            if (null != authorization) {
                logger.info("通过Ticket，授权业务系统成功");
                // 清理 Ticket
                ssoUserService.removeAuthorizationByTicket(ticket);
                /**
                 * 根据ticket登陆，存入cookie
                 */
                ActionContext.setAuthorization(response, authorization);

                StringBuffer redirectURL = request.getRequestURL();
                String queryString = request.getQueryString();
                if(StringUtils.isNotBlank(request.getQueryString())) {
                    //替换退出标识
                    queryString = queryString.replaceAll("&?"+Constants.CAS_LOGOUT_KEY+"=[^=&]+", "");
                    if(queryString.startsWith("&")) {
                        queryString = queryString.substring(1, queryString.length());
                    }

                    if(StringUtils.isNotBlank(queryString)) {
                        redirectURL.append("?").append(queryString);
                    }
                }
                response.sendRedirect(redirectURL.toString());

                return false;
            }
            // 验证失败 继续 重定向到 SSO
            logger.info("Ticket验证失败,重定向到 SSO");
        }
        logger.info("验证失败，重定向到 SSO ");
        // 重定向到 SSO 去登录
        pageOrJson(request, response, login);
        return false;
    }

    /**
     * 权限失败重定向或json
     * @param request
     * @param response
     * @param login
     * @throws Exception
     */
    public void pageOrJson(HttpServletRequest request, HttpServletResponse response, Login login) throws Exception {
        if (login.value() == AuthenType.page) {
//            String redirUrl = WebUtility.generatorRequestSsoURL(request, Configuration.getProperty("sso.server.login.url") , Configuration.getProperty("sysid"));
            String redirUrl = WebUtility.generatorRequestSsoURL(request, loginUrl , sysid);
            //logger.info("【PAGE页重定向】:" + redirUrl);
            response.sendRedirect(redirUrl);
        } else if (login.value() == AuthenType.json) {
            String url  = loginUrl;
            String redir = "";
            String ret = request.getParameter("ret");
            if(ret != null && !"".equalsIgnoreCase(ret)) {
                redir = ret;
            } else {
                redir = request.getRequestURL().toString();
            }
            String redirectUrl = url+"?sysid=" + sysid + "&redirect="+redir;
            logger.info("***********json filter redirectUrl:"+redirectUrl);
            String resp = "{\"code\":0,\"ssotype\":true,\"redirect\":\""+URLEncoder.encode(redirectUrl)+"\"}";
            response.getWriter().write(resp);
        } else {
            String redirUrl = WebUtility.generatorRequestSsoURL(request, loginUrl , sysid);
            //logger.info("【页面重定向】:" + redirUrl);
            response.sendRedirect(redirUrl);
        }
    }

}