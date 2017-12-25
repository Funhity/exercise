package com.huaxia.sso.utils;

//import org.apache.commons.lang.StringUtils;
import com.huaxia.sso.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * ActionContext
 * @description 封装 Web 容器 中的 环境变量 与当前线程绑定，以便在任何地方调用
 */
public class ActionContext {

    private static ApplicationContext applicationContext = null;
    private static ServletContext servletContext = null;

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static <T> T getRequestAttr(String key, Class<T> clazz) {
        return (T) getRequest().getAttribute(key);
    }


    public static void setRequestAttr(String key, Object value) {
        if (null == value) {
            getRequest().removeAttribute(key);
        } else {
            getRequest().setAttribute(key, value);
        }
    }

    public static HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    public static <T> T getSessionAttr(String key) {
        HttpSession session = getSession(false);
        return null == session ? null : (T) session.getAttribute(key);
    }

    public static void setSessionAttr(String key, Object value) {
        HttpSession session = getSession(true);
        if (null == value) {
            session.removeAttribute(key);
        } else {
            session.setAttribute(key, value);
        }
    }

    /**
     * 获取授权信息
     * SSO 登录
     *
     * @return
     */
    public static Authorization getAuthorization() {
        Map<String, String> cookie = WebUtility.getCookies(getRequest());
        if (StringUtils.isBlank(cookie.get("tgc")) || StringUtils.isBlank(cookie.get("uid"))) {
            HttpServletRequest request = getRequest();
            if (null != request.getAttribute(Constants.CAS_AUTHENTICATION_KEY)) {
                return (Authorization) request.getAttribute(Constants.CAS_AUTHENTICATION_KEY);
            }
            return null;
        } else {
            Authorization authorization = new Authorization();
            authorization.setId(Integer.parseInt(cookie.get("uid")));
            authorization.setUsername(cookie.get("uname"));
            authorization.setTgc(cookie.get("tgc"));
            return authorization;
        }
    }

    /**
     * 设置 授权信息
     *
     * @param authorization
     */
    public static void setAuthorization(HttpServletResponse response, Authorization authorization) {
        HttpServletRequest request = getRequest();
//        request.setAttribute(Constants.CAS_AUTHENTICATION_KEY, authorization);
        Map cookie = new HashMap();
        cookie.put("uid", authorization.getId());
        cookie.put("tgc", authorization.getTgc());
        cookie.put("uname", authorization.getUsername());
        // 30 分钟
        cookie.put("timeout", Constants.CAS_TIMEOUT_KEY);
        cookie.put("domain", Configuration.getProperty("sso.domain"));
        WebUtility.setCookies(response, cookie);

    }

    public static ServletContext getServletContext() {
        if (null != servletContext) {
            return servletContext;
        }
        return servletContext = getSession(true).getServletContext();
    }

    public static ApplicationContext getApplicationContext() {
        if (null != applicationContext) {
            return applicationContext;
        }
        return applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }
}

