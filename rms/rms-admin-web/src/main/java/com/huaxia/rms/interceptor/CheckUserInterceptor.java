package com.huaxia.rms.interceptor;

import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.model.RmsUserBlacklist;
import com.huaxia.rms.core.service.RmsUserBlacklistService;
import com.huaxia.sso.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(CheckUserInterceptor.class);

    @Value("${mrs.blacklist.controller}")
	private String isBlackController;

	@Resource
	public RedisDao redisDao;

	@Resource
	public RmsUserBlacklistService blacklistService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//    	logger.info("***************黑名单过滤器****************");
    	long start_time = System.currentTimeMillis();

        if("CLOSE".equals(isBlackController.toUpperCase())) {
        	logger.info("-----------关闭黑名单过滤功能-----------");
        	return true;
        }

    	String redir = request.getRequestURI();
    	logger.info("*********请求url:"+redir);
    	String location = WebUtility.getLocationHref(request);
		String redirectPath = WebUtility.getFullContextPath(request) + "/error/black.jhtml";

		/**
		 * 黑名单跳转页无需验证
		 */
		if (location.indexOf("/error/black.jhtml") > 0) {
			logger.info("*******黑名单跳转页无需验证*******");
			return true;
		}

    	/**
         * 过滤黑名单用户
         * 黑名单用户加载到redis中
    	 */
		String key = RedisConstant.BLACK_PRE;
		List<RmsUserBlacklist> blacklists = redisDao.getList(key, RmsUserBlacklist.class);
		if(blacklists == null) {
			blacklists = blacklistService.queryAllBlackList();
			if(blacklists != null && blacklists.size() > 0) {
				redisDao.save(key, blacklists);
			}
		}
		logger.info("***********黑名单列表: " + blacklists);
		if(blacklists == null) {
			logger.info("*******黑名单列表为空，无需验证*******");
			return true;
		}
		String ip = WebUtility.getIpAddress(request);
		logger.info("***********用户ip: " + ip);

		boolean isBlackUser = false;
		Authorization sessionUser = WebUtils.getSessionUser(request);
    	if(sessionUser != null) {
    		String username = sessionUser.getUsername();
    		/**
    		 * 判断是否冻结用户
    		 * 清空session，不让其登录
    		 */
			for (RmsUserBlacklist userBlacklist : blacklists) {
				if(sessionUser.getId() == userBlacklist.getUserId()) {
					logger.info("***********[CheckUserInterceptor]该用户【"+ username +"】ID已被加入黑名单。 ");
					isBlackUser = true;
				} else if(sessionUser.getUsername().equals(userBlacklist.getUsername())) {
					logger.info("***********[CheckUserInterceptor]该用户【"+username+"】name已被加入黑名单。 ");
					isBlackUser = true;
				} else if(ip.equals(userBlacklist.getIpAddress())) {
					logger.info("***********[CheckUserInterceptor]该用户【"+username+"】所用IP已被加入黑名单。 ");
					isBlackUser = true;
				}
			}

    	} else {
    		/**
    		 * 过滤黑名单IP
    		 */
			for (RmsUserBlacklist userBlacklist : blacklists) {
				if(ip.equals(userBlacklist.getIpAddress())) {
					logger.info("***********[CheckUserInterceptor]该IP【"+ip+"】已被加入黑名单。 ");
					isBlackUser = true;
				}
			}
    	}

		/**
		 * 黑名单用户
		 * 清空session及登陆信息
		 */
		if(isBlackUser) {
			CookieUtils.removeAllCookie(request, response);
			SessionUtils.removeLoginSession(request, response);
			response.sendRedirect(redirectPath);
			return false;
		}

        logger.info("**********黑名单过滤器[CheckUserInterceptor]耗时：" + (System.currentTimeMillis() - start_time));
        return true;
    }

}
