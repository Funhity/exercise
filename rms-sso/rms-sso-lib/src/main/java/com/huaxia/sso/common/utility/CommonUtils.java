package com.huaxia.sso.common.utility;

import com.huaxia.sso.utils.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    /**
     * 获取浏览器IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 生成 返回接入SSO应用的地址
     *
     * @param request
     * @param ticket
     * @return
     */
    public static String generatorRedirectURL(HttpServletRequest request, String redirectURL, String ticket) {
        if (StringUtils.isBlank(redirectURL)) {
            redirectURL = request.getRequestURL().toString();
            redirectURL = redirectURL.substring(0, redirectURL.indexOf("/", 7));
            redirectURL += ActionContext.getServletContext().getContextPath() + "/success.htm";
        } else {
            try {
                redirectURL = URLDecoder.decode(redirectURL, "UTF-8");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (redirectURL.indexOf("?") != -1) {
            String[] url = redirectURL.split("\\?");
            String params[] = url[1].split("&");
            String queryString = new String();
            for (String param : params) {
                if (!param.startsWith(Constants.CAS_TICKET_KEY)) {
                    try {
                        String[] tmpParam = param.split("=");
                        if (tmpParam.length > 1) {
                            if (StringUtils.isNotBlank(tmpParam[1])) {
                                queryString = queryString + tmpParam[0] + "=" + URLEncoder.encode(tmpParam[1], "UTF-8") + "&";
                            } else {
                                queryString = queryString + param + "&";
                            }
                        } else {
                            queryString = queryString + param + "&";
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            redirectURL = url[0] + "?" + queryString + Constants.CAS_TICKET_KEY + "=" + ticket;
        } else {
            redirectURL = redirectURL + "?" + Constants.CAS_TICKET_KEY + "=" + ticket;
        }
        return redirectURL;
    }

    public static String generatorRedirectURL(HttpServletRequest request, String ticket) {
        return generatorRedirectURL(request, request.getParameter(Constants.CAS_REDIRECT_URL_KEY), ticket);
    }


}
