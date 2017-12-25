package com.huaxia.sso.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.StringUtils;
import com.huaxia.sso.model.TRMSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.service.RMSManager;
import com.huaxia.sso.service.SSOSupportService;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service.impl
 * 作者-Liu zhilai
 * 说明：
 * 2016/3/3 17:25
 * 2017华夏信财-版权所有
 */
public class SSOSupportServiceImpl implements SSOSupportService {

    /**
     * 顶级域名
     */
    @Value("${sso.domain}")
    private String domain;

//    private RedisUtil redisUtil;
//    redisTemplate
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate ;
    
    @Autowired
    private RMSManager rmsManager;
    
    /**
     * Ticket 默认缓存 10 分钟
     */
    @Value("${redis.ticket.timeout}")
    private int cacheTicketTimeout = 60 * 10;
    /**
     * 默认缓存 10 分钟
     */
    @Value("${redis.tgc.timeout}")
    private int cacheTgcTimeout = 60 * 10;

    /**
     * 自定义 TGC 存储名称  前缀
     */
    @Value("${redis.key.prefix.user.tgc}")
    private String redisKeyPrefixUserTgc;
    /**
     * 自定义 TICKET 存储名称 前缀
     */
    @Value("${redis.key.prefix.user.ticket}")
    private String redisKeyPrefixUserTicket;


	/**
     * 登录成功返回令牌TGC,后续权限验证和用户信息获取都是通过TGC
     * @param username
     * @param passwd
     * @param request
     * @param response
     * @return
     */
    @Override
    public Authorization loginPasswd(String username, String passwd, HttpServletRequest request, HttpServletResponse response) {
        // 验证登录
        if (rmsManager.userLoginValidation(username, passwd, request, getLoginType(request))) {
            return getAuthorizationTgc(username);
        }
        return null;
    }


    private String getLoginType(HttpServletRequest request) {
        if(request == null) return Constants.LOGIN_TYPE_OTHER;
        String loginType = request.getParameter("loginType");
        if(StringUtils.isBlank(loginType)) loginType = Constants.LOGIN_TYPE_OTHER;
        return loginType;
    }

    private Authorization getAuthorizationTgc(String username) {
        final TRMSUser user = rmsManager.getByUserName(username);
        // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
        final String TGC = rmsManager.tgcGrant(user.getId());
        final Authorization authorization = rmsManager.getUserByTgc(TGC);
        return authorization;
    }

//    @Override
//    public Authorization loginValidateCode(String username, String validateCode, HttpServletRequest request, HttpServletResponse response) {
//        // 验证登录
//        if (rmsManager.userLoginValidateCodeValidation(username, validateCode, request, Constants.BXJ_SYSTEM_ID, getLoginType(request))) {
//            return getAuthorizationTgc(username);
//        }
//        return null;
//    }

//    @Override
//    public Authorization registValidateCode(String username, String validateCode, String passwd, String realname, HttpServletRequest request, HttpServletResponse response) {
//        if(rmsManager.userLoginValidateCodeFgValidation(username, validateCode, passwd, request, null, null)) {
//            return getAuthorizationTgc(username);
//        }
//        return null;
//    }

    @Override
    public void getLoginValidateCode(String username, String sysid, String ip) {
        rmsManager.getLoginValidateCode(username, sysid, ip);
    }

//    public Result registValidateCode_V1(String username, String validateCode, String passwd, String realname, String sysid) {
//        Result result = new Result();
//        try {
//            if(rmsManager.userLoginValidateCodeRegistValidation(username, validateCode, passwd, null, sysid, null)) {
//                result.setObject(getAuthorizationTgc(username));
//            } else {
//                result.setCode("500");
//                result.setMessage("操作失败");
//            }
//
//        } catch (CASException e) {
//            result.setCode(e.getCode()+"");
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }

//    public Result resetValidateCode_V1(String username, String validateCode, String passwd, String realname, String sysid) {
//        Result result = new Result();
//        try {
//            if (rmsManager.userLoginValidateCodeFgValidation(username, validateCode, passwd, null, sysid, null)) {
//                result.setObject(getAuthorizationTgc(username));
//            } else {
//                result.setCode("500");
//                result.setMessage("操作失败");
//            }
//        } catch (CASException e) {
//            result.setCode(e.getCode()+"");
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }

//    public Result updatePwdValidateCode_V1(String username, String oldPassword, String passwd, String sysid) {
//        Result result = new Result();
//        try {
//            if (rmsManager.pwdUpdatePwdValidation(username, oldPassword, passwd, sysid)) {
//                result.setObject(getAuthorizationTgc(username));
//            }
//        } catch (CASException e) {
//            result.setCode(e.getCode()+"");
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }


    public void getRegistValidateCode_V1(String username, String sysid, String ip) {
        rmsManager.getRegistPwdValidateCode(username, sysid, ip);
    }


    public void getResetValidateCode_V1(String username, String sysid, String ip) {
        rmsManager.getFpLoginValidateCode(username, sysid, ip);
    }

    @Override
    public boolean validateLoginStatus(String tgc) {
        return rmsManager.tgcValidation(tgc);
    }

    @Override
    public Authorization getAuthorization(String tgc) {
        return rmsManager.getUserByTgc(tgc);
    }

    @Override
    public boolean logout(String tgc) {
        if(rmsManager.tgcValidation(tgc)) {
            rmsManager.removeTgc(tgc);
        }
        return true;
    }

//    public Authorization getAuthorizationByTgc(String tgc) {
//        try {
//        	
//        	Authorization auth = (Authorization)redisUtil.getUnserializeKey(redisKeyPrefixUserTgc + tgc);
//        	redisUtil.expire(redisKeyPrefixUserTgc + tgc, cacheTgcTimeout);
//            return auth;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
    
}
