package com.huaxia.sso.utils;

import com.huaxia.sso.constants.Constants;
import com.huaxia.sso.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * 华夏信财-SSO
 * com.huaxia.sso.client
 * 作者-Liu zhilai
 * 说明：
 * 2016/3/23 16:14
 * 2017华夏信财-版权所有
 */
public class WebUtils {

    private static Logger log = LoggerFactory.getLogger(WebUtils.class);

    public static String getTgc() {
        Authorization authorization = getAuthorization();
        if(authorization != null) return authorization.getTgc();
        return null;
    }

    public static Authorization getAuthorization() {
        Authorization authorization = ActionContext.getAuthorization();
        return authorization;
    }


    public static Integer getMemberId(HttpServletRequest request) {
        return getAuthorization().getId();
    }

    public static String getMobile(HttpServletRequest request) {
        return getAuthorization().getUsername();
    }

    /**
     * 不使用权限拦截器，使用如下方法拦截权限
     * @param request
     * @param response
     * @return
     */
    public static Authorization getAuthorization(HttpServletRequest request, HttpServletResponse response){
        try {
            loginFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Authorization authorization = ActionContext.getAuthorization();
        return authorization;
    }

    public static boolean loginFilter(ApplicationContext context, HttpServletRequest request, HttpServletResponse response, String returnUrl) throws Exception {
        return context(context, request, response, returnUrl);
    }

    public static boolean loginFilter(ApplicationContext context, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return context(context, request, response, "");
    }

    public static boolean loginFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        return context(context, request, response, "");
    }

    private static boolean context(ApplicationContext context, HttpServletRequest request, HttpServletResponse response, String returnUrl) throws Exception {
        UserService userService = (UserService) context.getBean("UserService");
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String url = requestURI.substring(contextPath.length());
        //refreshGenerateBbssoSessionId(request, response, userService);

        // 授权信息
        Authorization authorization = ActionContext.getAuthorization();
        // 获取到的授权信息
        Authorization authorizationTgc = ActionContext.getAuthorization();
        // 解决 Swfupload 上传无法 获取 Cookie 导致 重定向 到 SSO 登录页面 BUG.
        if (null == authorization && StringUtils.isNotBlank(request.getParameter("tgc"))) {
            authorization = new Authorization();
            authorization.setTgc(request.getParameter("tgc"));
            log.info("有TGC，无用户信息，赋值TGC");
        }
        if (null != authorization) {
            authorizationTgc = userService.getAuthorizationByTgc(authorization.getTgc());
            log.info("通过TGC获取用户信息");
        }
        // 检测是否已登录
        if (null != authorization && (null != authorizationTgc && (0 == authorization.getId() || authorization.getId() == authorizationTgc.getId()))) {
            log.info("已登录");
            authorization = authorizationTgc;
            //  解决 Swfupload 上传后续无法 获取 authorization
            if (StringUtils.isNotBlank(request.getParameter("tgc"))) {
                request.setAttribute(Constants.CAS_AUTHENTICATION_KEY, authorization);
            }

            // 检测是否是 退出请求
            if (StringUtils.isNotBlank(request.getParameter(Constants.CAS_LOGOUT_KEY))) {
                userService.removeAuthorizationByTgc(authorization.getTgc());
                // 执行拦截器
//                if (null != loginListener) {
//                    loginListener.logout(authorization);
//                }

                Map cookieMap =  new HashMap<String, Object>() {
                    {
//                        put("bbssoSessionId", "");
                        put("usys", "");
                        put("uid", "");
                        put("tgc", "");
                        put("uname", "");
//                       put(Constants.CAS_AUTOCODE_KEY, "");
                        put("timeout", 0);
                    }
                };
                cookieMap.put("domain", Configuration.getProperty("sso.domain"));
                WebUtility.setCookies(response, cookieMap);
                log.info("退出成功");
                //退出界面
                response.sendRedirect(WebUtility.generatorRequestSsoURL(request, Configuration.getProperty("sso.server.logout.url"), Configuration.getProperty("sysid"), returnUrl));
                return false;
            }

            return true;
        }
        String ticket = request.getParameter(Constants.CAS_TICKET_KEY);
        if (StringUtils.isNotBlank(ticket)) {
            // 验证 Ticket是否有效
            // 还原 validationTicket方法。
            authorization = userService.getAuthorizationByTicket(ticket);
            if (null != authorization) {
                log.info("通过Ticket，授权业务系统成功");
                // 清理 Ticket
                userService.removeAuthorizationByTicket(ticket);
                ActionContext.setAuthorization(response, authorization);
                StringBuffer redirectURL = request.getRequestURL();
                String queryString = request.getQueryString();
                if(StringUtils.isNotBlank(request.getQueryString())) {
                    queryString = queryString.replaceAll("&?cas_logout=[^=&]+", "");
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
            log.info("Ticket验证失败,重定向到 SSO");
        }

        //从COOKIE中获取缓存的用户名，密码，自动登陆
        Map cookie = WebUtility.getCookies(request);
//        if(!org.apache.commons.lang.StringUtils.isBlank((String)cookie.get(Constants.CAS_UNAME_KEY))
//                && !org.apache.commons.lang.StringUtils.isBlank((String)cookie.get(Constants.CAS_AUTOCODE_KEY))) {
//            Authorization auth = userService.autoLogin((String)cookie.get(Constants.CAS_UNAME_KEY), (String)cookie.get(Constants.CAS_AUTOCODE_KEY));
//            log.info("自动登陆auth:"+(auth!=null));
//            if(auth != null) {
//                final Authorization authorization1 = auth;
//                Map autoCookieMap = new HashMap() {{
//                    put(Constants.CAS_UID_KEY, authorization1.getId());
//                    put(Constants.CAS_UNAME_KEY, authorization1.getUsername());
//                    put(Constants.CAS_TGC_KEY, authorization1.getTgc());
//                    put("timeout", Constants.CAS_TIMEOUT_KEY);
//                    put("domain", Configuration.getProperty("sso.domain"));
//                }};
//
//                WebUtility.setCookies(response, autoCookieMap);
//                log.info("业务系统设置cookie：" + Configuration.getProperty("sso.domain"));
//
////                autoCookieMap.put("domain", Configuration.getProperty("sso.domain"));
////                log.info("CAS SSO设置cookie" + Configuration.getProperty("sso.domain"));
////                WebUtility.setCookies(response, autoCookieMap);
//                return true;
//            }
//        }


        log.info("重定向到 SSO 验证失败");
        // 重定向到 SSO 去登录
        pageOrJson(request, response);
        return false;
    }




    /**
     * 权限失败重定向或json
     * @param request
     * @param response
     * @throws Exception
     */
    public static void pageOrJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(WebUtility.generatorRequestSsoURL(request, Configuration.getProperty("sso.server.login.url") , Configuration.getProperty("sysid")));
    }


    public static Authorization getSessionUser(HttpServletRequest request) {

        /**
         * 1、从本地服务器中获取session
         * 2、存在，返回，不存在，从sso统一登录管理平台获取用户手机
         * 3、存在手机，则表明，用户已经在统一平台登录，则从数据库里获取用户信息
         * 4、如果不存在，则用户没有登录
         *
         * 用户登录的情况下，判断手机号是否为会员
         * 如果为会员直接获取信息set到session中，如果不是，则新增会员，再放入session
         */
//    	Map<String, Object> memberMap = new HashMap<String, Object>();
        //没有session, 新建
        HttpSession session = request.getSession(false);

        Authorization author = null;

        if(session != null) {
            //logger.info("------------session.getAttributeNames： " + session.getAttributeNames());
            if(session.getAttribute(SessionUtils.LOGIN_USER_SESSION) != null) {
                author = (Authorization)session.getAttribute(SessionUtils.LOGIN_USER_SESSION);
                //logger.info("------------session中存在用户信息： " + author.getUsername());
            } else {
                log.info("------------session中不存在用户信息--------------" );
            }

            if(author != null) {
                return author;
            }
        }

        author = getAuthorization();
        if(author == null) {
            return null;
        }

        /**
         * 根据授权信息，获取用户的信息，包括用户的权限信息，放入session中
         * 登陆用户信息放入session中
         */
        SessionUtils.setLoginSession(request, author);
        return author;
    }

}
