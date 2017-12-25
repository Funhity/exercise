package com.huaxia.sso.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private Logger logger = LoggerFactory.getLogger(CookieUtils.class);
	public static final String COOKIE_LOGIN_USER_KEY = "COOKIE_LOGIN_USER_KEY";
	public static final String COOKIE_LOGIN_USER_PWD = "COOKIE_LOGIN_USER_PWD";
	public static final String COOKIE_SEARCH_HISTORY = "COOKIE_SEARCH_HISTORY";
	public static final String HJB_WX_COOKIE_NAME = "hjb_wx_openid";

	/** 找回密码 end */

//	public static void setUserCookie22(HttpSession session,
//                                       HttpServletResponse response, Map user) {
//		/**
//		 * 客户端Cookie保存 (cookieInterval)
//		 */
//		// cookie过期时间，天计
//		int cookieInterval = Integer.parseInt(ResponseHelper
//				.getResponse("HJB_COOKIE_EXPITIME")) * 24 * 3600;
//		// session.setMaxInactiveInterval(interval);
//		Cookie sessionCookie = new Cookie(WebConstants.SESSION_COOKIE_NAME,
//				session.getId());
//		sessionCookie.setMaxAge(cookieInterval);
//		// cookie.setPath("/");同一应用服务器内共享cookie,一个tomcat内多个应用可共享cookie
//		sessionCookie.setPath("/");
//		response.addCookie(sessionCookie);
//
//		Cookie cookie = new Cookie(CookieUtils.COOKIE_LOGIN_USER_KEY,
//				(String) user.get("member_code"));
//		cookie.setMaxAge(cookieInterval);
//		cookie.setPath("/");
//		response.addCookie(cookie);
//
//		Cookie cookiepwd = new Cookie(CookieUtils.COOKIE_LOGIN_USER_PWD,
//				(String) user.get("member_pwd"));
//		cookiepwd.setMaxAge(cookieInterval);
//		cookiepwd.setPath("/");
//		response.addCookie(cookiepwd);
//	}

	public static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie == null || StringUtils.isBlank(cookie.getName()))
					continue;
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void cookie(String key, String value,
			HttpServletResponse response) {
		int interval = 365 * 24 * 3600;
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(interval);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void removeCookie(String key, HttpServletResponse response) {
		int interval = 0;
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(interval);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void removeAllCookie(HttpServletRequest request,
			HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
//			if(!CookieUtils.HJB_WX_COOKIE_NAME.equals(cookie.getName())) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
//			}
		}
	}

//	public static boolean checkUserCookie(HttpServletRequest request) {
//		Cookie[] cookies = request.getCookies();
//		if (cookies == null) {
//			return false;
//		}
//		for (Cookie cookie : cookies) {
//			if (cookie == null || StringUtils.isBlank(cookie.getName()))
//				continue;
//			if (cookie.getName().equals(CookieUtils.COOKIE_LOGIN_USER_KEY)) {
//				Map memberInfo = WebUtils.getSessionUser(request);
//				if (memberInfo != null) {
//					String member_code = (String) memberInfo.get("member_code");
//					if (memberInfo.get("fid") != null
//							&& member_code.equals(cookie.getValue())) {
//						return true;
//					} else {
//						break;
//					}
//				}
//			}
//		}
//		return false;
//	}

	// public Map getCookieUser(HttpServletRequest request, HttpServletResponse
	// response) {
	//
	// logger.info("Method[BashImpl.getCookieUser]**********获取用户cookiee**********："+WebUtils.getSessionMember(request));
	// Map user = new HashMap();
	// if (WebUtils.getSessionMember(request) != null) {
	// logger.info("***********Method[BashImpl.getCookieUser]重置session************");
	// memberDao.reSetLoginUser(request);
	//
	// logger.info("***********Method[BashImpl.getCookieUser]重置后的session："+
	// request.getSession().getAttributeNames());
	// //非激活用户，清除session
	// Object status_id = WebUtils.getMemberParams(request, "status_id");
	// logger.info("***********status_id: " + status_id);
	// if (status_id != null && !status_id.toString().equals("1")) {
	// removeLoginSession(request, response);
	// }
	// return user;
	// }
	//
	// /**
	// * 从cookie中获取用户的登录信息
	// * 然后把根据手机密码，获取member_info表中的记录，放入session中
	// */
	// Cookie[] cookies = request.getCookies();
	// logger.info("***********Method[BashImpl.getCookieUser]用户cookie信息："+cookies);
	// if (cookies == null)
	// return user;
	// String mobile_code = null;
	// String member_cookie_pwd = null;
	// for (Cookie cookie : cookies) {
	// logger.info("***********Method[BashImpl.getCookieUser]用户cookie.name："+cookie.getName());
	// logger.info("***********Method[BashImpl.getCookieUser]用户cookie.value："+cookie.getValue());
	// logger.info("************华丽分割线**************");
	// if (cookie == null || StringUtils.isBlank(cookie.getName()))
	// continue;
	// if (cookie.getName().equals(CookieUtils.COOKIE_LOGIN_USER_KEY)) {
	// mobile_code = cookie.getValue();
	// } else if (cookie.getName().equals(CookieUtils.COOKIE_LOGIN_USER_PWD)) {
	// member_cookie_pwd = cookie.getValue();
	// }
	// }
	// if (StringUtils.isBlank(mobile_code) ||
	// StringUtils.isBlank(member_cookie_pwd)) {
	// return user;
	// } else {
	// Map memberInfo = memberDao.getMemberInfoByMobile(mobile_code);
	// Object status_id = memberInfo.get("status_id");
	// if (status_id != null && !status_id.toString().equals("1")) {
	// removeLoginSession(request, response);
	// return user;
	// }
	// String member_pwd = (String) memberInfo.get("member_pwd");
	// if (member_pwd != null && member_cookie_pwd.equals(member_pwd)) {
	// return memberInfo;
	// }
	// return user;
	// }
	// }

}
