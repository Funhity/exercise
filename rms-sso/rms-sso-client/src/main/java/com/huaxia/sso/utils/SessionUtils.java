package com.huaxia.sso.utils;

import com.huaxia.sso.model.RmsSsoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


public class SessionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SessionUtils.class);

	/**
	 * 保存用户的验证码
	 */
	public static final String SESSION_LOGIN_VCODE = "SESSION_LOGIN_VCODE";//用户登录验证码
	public static final String SESSION_REGISTER_VCODE = "SESSION_REGISTER_VCODE";//用户注册验证码
	public static final String SESSION_FORGETPD_VCODE = "SESSION_FORGETPD_VCODE";//用户忘记密码验证码
	
	public static final String LOGIN_MEMBERINFO_SESSION = "LOGIN_MEMBER_INFO";//用户登录信息，放入session
	public static final String LOGIN_COOKIE_SESSIONID = "LOGIN_COOKIE_SESSIONID";
	
	
	public static final String SESSION_LOGIN_VERIFY_CODE = "SESSION_LOGIN_VERIFY_CODE";
	
    public static final String SESSION_UUID = "SESSION_UUID";
    public static final String SESSION_CART_UUID = "SESSION_CART_UUID";//购物车

    /**
     * 修改密码 begin
     */
    public static final String SESSION_MOBILE_VERIFY_CODE = "SESSION_MOBILE_VERIFY_CODE";
    public static final String SESSION_MOBILE_VERIFY_TIME = "SESSION_MOBILE_VERIFY_TIME";
    public static final String SESSION_MAIL_VERIFY_CODE = "SESSION_MAIL_VERIFY_CODE";
    public static final String SESSION_MAIL_VERIFY_TIME = "SESSION_MAIL_VERIFY_TIME";
    public static final String _MOBILE_CODE = "_mobilecode";
    /**修改密码 end*/

    public static final int SESSION_EXPITIME = 30 * 60;
    public static final int COOKIE_EXPIRATION_TIME = 30 * 60;
    public static String LOGIN_USER_SESSION = "LOGIN_USER";
    public static String SESSION_COOKIE_NAME = "SESSION_RMS";


    /**
     * 找回密码 begin
     */
    public static final String SESSION_MOBILE_VERIFY_CODE_FORGET_PWD = "SESSION_MOBILE_VERIFY_CODE_FORGET_PWD";
    public static final String SESSION_MOBILE_VERIFY_TIME_FORGET_PWD = "SESSION_MOBILE_VERIFY_TIME_FORGET_PWD";
    public static final String SESSION_MAIL_VERIFY_CODE_FORGET_PWD = "SESSION_MAIL_VERIFY_CODE_FORGET_PWD";
    public static final String SESSION_MAIL_VERIFY_TIME_FORGET_PWD = "SESSION_MAIL_VERIFY_TIME_FORGET_PWD";
    /**
     * 找回密码 end
     */

    public static final String SESSION_CLICK_ON_PRODUCT = "SESSION_CLICK_ON_PRODUCT";
    /**
     * 有效的短信类型
     */
    public final static String DRAGON_CODE = "dragon_code";
    public final static String REGISTER_CODE = "register_code";
    public final static String FOGOTPWD_CODE = "fogotpwd_code";

    /**
     * 投保信息
     */
    public static final String SESSION_INSURED_INFO = "SESSION_INSURED_INFO";
    public static final String SESSION_TRADE_ID = "SESSION_TRADE_ID";


    public static final String SESSION_BACK_IFRAME = "SESSION_BACK_IFRAME";

    /***
     * 微信openid
     */
    public static final String SESSION_WEIXIN_OPEN_ID = "SESSION_WEIXIN_OPEN_ID";

    /***
     * 回调
     */
    public static final String SESSION_CALLBACKFUNC = "SESSION_CALLBACKFUNC";
    

    public static void setPhoneVerCode(HttpServletRequest request, String send_type, String verCode, String mobileCode) {
        if (request == null)
            return;
        request.getSession().setAttribute(SessionUtils.SESSION_MOBILE_VERIFY_CODE + "_" + send_type, verCode);
        request.getSession().setAttribute(SessionUtils.SESSION_MOBILE_VERIFY_CODE + "_" + send_type + SessionUtils._MOBILE_CODE, mobileCode);
        request.getSession().setAttribute(SessionUtils.SESSION_MOBILE_VERIFY_TIME + "_" + send_type, new Date());
    }

    public static void removePhoneVerCode(HttpServletRequest request, String sendType) {
        if (request == null) {
        	return;
        }
        request.getSession().removeAttribute(SessionUtils.SESSION_MOBILE_VERIFY_CODE + "_" + sendType);
        request.getSession().removeAttribute(SessionUtils.SESSION_MOBILE_VERIFY_CODE + "_" + sendType + SessionUtils._MOBILE_CODE);
        request.getSession().removeAttribute(SessionUtils.SESSION_MOBILE_VERIFY_TIME + "_" + sendType);
    }

    public static String getPhoneSession(HttpServletRequest request, String sendType) {
        if (request == null) {
        	return null;
        }
        return (String) request.getSession().getAttribute(SessionUtils.SESSION_MOBILE_VERIFY_CODE + "_" + sendType + SessionUtils._MOBILE_CODE);
    }

    /**
     * 获取session中的登陆验证码 
     * @param request
     * @return
     */
    public static String getSessionLoginVcode(HttpServletRequest request) {
    	//request.getSession(false) == null 表示session已过期
    	//HttpSession session = request.getSession();
    	/**
    	 * getSession(boolean create)意思是返回当前reqeust中的HttpSession 
    	 * 如果当前reqeust中的HttpSession 为null，当create为true，就创建一个新的Session，否则返回null；
    	 * request.getSession() 等同于 request.getSession(true)
    	 * 如果session不存在的话你又创建了一个
    	 */
    	HttpSession session = request.getSession(false);
    	if (request == null || session == null) {
    		logger.info("【getSessionLoginVcode】**********Session已过期："+session);
            return null;
        }
    	Object vcode = session.getAttribute(SESSION_LOGIN_VERIFY_CODE);
    	String code = "";
    	if(vcode != null) {
    		code = String.valueOf(vcode);
    	}
        return code;
    }
    
//    public void doLoginSession(HttpServletRequest request, HttpServletResponse response, String mobile_code) {
//        Map user = memberDao.getMemberInfoByMobile(mobile_code);
//        if (user.size() == 0) {
//            return;
//        }
//        doLoginSession(request, response, user);
//    }

    /**
     * 用户登录，把用户信息放入session中
     * @param request
     * @param response
     * @param member
     */
    public static void setMemberinfoToSession(HttpServletRequest request, HttpServletResponse response, RmsSsoUser member) {
        if (member == null) {
            return;
        }
        //没有session, 新建
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBERINFO_SESSION, member);
//        request.getSession().setMaxInactiveInterval(WebConstants.COOKIE_EXPIRATION_TIME);
        //服务端设置session过期时间，sessionExpTime 秒计
        session.setMaxInactiveInterval(SESSION_EXPITIME);
        
        /**
      	 * 客户端Cookie保存 (cookieInterval)
      	 */
		// cookie过期时间，秒计
//		int cookieInterval = Integer.parseInt(ResponseHelper.getResponse("HJB_COOKIE_EXPITIME"));
//		int cookiePwdInterval = Integer.parseInt(ResponseHelper.getResponse("HJB_COOKIEPWD_EXPITIME"));
		
		// session.setMaxInactiveInterval(interval);
		Cookie sessionCookie = new Cookie(LOGIN_COOKIE_SESSIONID, session.getId());
		sessionCookie.setMaxAge(SESSION_EXPITIME);
		// cookie.setPath("/");同一应用服务器内共享cookie,一个tomcat内多个应用可共享cookie
		sessionCookie.setPath("/");
		response.addCookie(sessionCookie);

		Cookie cookie = new Cookie(CookieUtils.COOKIE_LOGIN_USER_KEY, member.getName());
		cookie.setMaxAge(SESSION_EXPITIME);
		cookie.setPath("/");
		response.addCookie(cookie);

		Cookie cookiepwd = new Cookie(CookieUtils.COOKIE_LOGIN_USER_PWD, member.getPassword());
		cookiepwd.setMaxAge(SESSION_EXPITIME);
		cookiepwd.setPath("/");
		response.addCookie(cookiepwd);
        
    }

    
    public static void setLoginSession(HttpServletRequest request, Object user) {
        if (user == null) {
            return;
        }
        //没有session, 新建
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_USER_SESSION, user);
        //服务端设置session过期时间，sessionExpTime 秒计
        session.setMaxInactiveInterval(SESSION_EXPITIME);
        
        /**
      	 * 客户端Cookie保存 (cookieInterval)
      	 */
		// cookie过期时间，秒计
//		int cookieInterval = Integer.parseInt(ResponseHelper.getResponse("HJB_COOKIE_EXPITIME"));
//		int cookiePwdInterval = Integer.parseInt(ResponseHelper.getResponse("HJB_COOKIEPWD_EXPITIME"));
		
		// session.setMaxInactiveInterval(interval);
//		Cookie sessionCookie = new Cookie(SESSION_COOKIE_NAME, session.getId());
//		sessionCookie.setMaxAge(SESSION_EXPITIME);
//		// cookie.setPath("/");同一应用服务器内共享cookie,一个tomcat内多个应用可共享cookie
//		sessionCookie.setPath("/");
//		response.addCookie(sessionCookie);
//
//		Cookie cookie = new Cookie(CookieUtils.COOKIE_LOGIN_USER_KEY, (String) user.get("member_code"));
//		cookie.setMaxAge(SESSION_EXPITIME);
//		cookie.setPath("/");
//		response.addCookie(cookie);
//
//		Cookie cookiepwd = new Cookie(CookieUtils.COOKIE_LOGIN_USER_PWD, (String) user.get("member_pwd"));
//		cookiepwd.setMaxAge(SESSION_EXPITIME);
//		cookiepwd.setPath("/");
//		response.addCookie(cookiepwd);
        
    }
    
    

    public static void removeLoginSession(HttpServletRequest request, HttpServletResponse response) {
        
    	HttpSession session = request.getSession(false);
    	if(session != null) {
//            if (session.getAttribute(LOGIN_USER_SESSION) != null) {
//            	//logger.info("清除登录用户session： "+ServerSession.getSessionMap());
//            	//ServerSession.getSessionMap().remove(user.get("_uuid"));
//            }
            
            session.removeAttribute(LOGIN_USER_SESSION);
            session.setMaxInactiveInterval(0);
//          Cookie cookie = new Cookie(CookieUtils.COOKIE_LOGIN_USER_KEY, "");
//          cookie.setMaxAge(-1);
//          cookie.setPath("/");
//          response.addCookie(cookie);

//          Cookie cookiepwd = new Cookie(CookieUtils.COOKIE_LOGIN_USER_PWD, "");
//          cookiepwd.setMaxAge(-1);//浏览器关闭的同时cookie死亡
//          cookiepwd.setPath("/");
//          response.addCookie(cookiepwd);
            
            
    	}
    	
            
       
        
    }


    
    
}
