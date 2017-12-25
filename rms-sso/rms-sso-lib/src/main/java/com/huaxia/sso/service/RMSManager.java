package com.huaxia.sso.service;


import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.model.TRMSUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service
 * 作者-Liu zhilai
 * 说明：SSO权限控制
 * 2016/3/3 17:44
 * 2017华夏信财-版权所有
 */
public interface RMSManager {


    /**
     * 用户是否登录验证
     * @param userid
     * @return
     */
    boolean isLogin(String userid);
    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    TRMSUser getByUserName(String userName);


    /**
     * 用户登录验证
     * 返回map
     *
     * @param userName
     * @param password
     * @param request
     * @return
     */
    Map<String, Object> userLoginValidation(String userName, String password, HttpServletRequest request);

    /**
     * 用户登录验证
     *
     * @param userName
     * @param password
     * @param request
     * @param loginType
     * @return
     */
    boolean userLoginValidation(String userName, String password, HttpServletRequest request, String loginType);

    Map<String, Object> userLoginValidation(TRMSUser user, HttpServletRequest request);

//    /**
//     * 自动用户登录验证
//     *
//     * @param userName
//     * @param password
//     * @param request
//     * @return
//     */
//    boolean userLoginAutoValidation(String userName, String password, HttpServletRequest request);

//    /**
//     * 用户验证码登录验证
//     * @param userName
//     * @param validateCode
//     * @param request
//     * @param loginType
//     * @return
//     */
//    boolean userLoginValidateCodeValidation(String userName, String validateCode, HttpServletRequest request, String sysid, String loginType);
//
//    boolean userNoPwdLoginValidation(String userName, HttpServletRequest request, String sysid);
//    /**
//     * 用户验证码忘记密码
//     * @param userName
//     * @param validateCode
//     * @param request
//     * @return
//     */
//    boolean userLoginValidateCodeFgValidation(String userName, String validateCode, String newPasswd, HttpServletRequest request, String sysid, String loginType);

//    boolean userLoginValidateCodeRegistValidation(String userName, String validateCode, String newPasswd, HttpServletRequest request, String sysid, String loginType);

    boolean pwdUpdatePwdValidation(String userName, String oldPassword, String newPasswd, String sysid);

    /**
     * @Title: userRegistValidateCodeValidation
     * @Description: 用户注册验证码验证
     * @param userName
     * @param validateCode
     * @return
     */
    boolean userRegistValidateCodeValidation(String userName, String validateCode);

    boolean registValidateCode(String username, String validateCode, String passwd, String realname,  HttpServletRequest request);

    boolean getLoginValidateCode(String username, String sysid, String ip);

    boolean getFpLoginValidateCode(String username, String sysid, String ip);

    boolean getRegistValidateCode(String username, String sysid, String ip);

    boolean getRegistPwdValidateCode(String username, String sysid, String ip);

    boolean getVoteValidateCode(String username, String sysid, String ip);

    /**
     * 公共发送短信接口
     * @param mobile
     * @param msgId 短信模板编号
     * @return
     */
    boolean getValidateCode(String mobile, String msgId, String ip);

    /**
     * 票据分配/发放
     *
     * @return
     */
    String ticketGrant(String tgc);

    /**
     * 票据验证
     *
     * @param ticket
     * @return
     */
    boolean ticketValidation(String ticket);

    /**
     * 删除票据
     *
     * @param ticket
     */
    void removeTicket(String ticket);

    /**
     * 获取用户信息
     *
     * @param ticket
     * @return
     */
    Authorization getUserByTicket(String ticket);

    /**
     * 生成TGC
     *
     * @return
     */
    String tgcGrant(int id);

    /**
     * 生成TGC
     *
     * @return
     */
    String tgcGrant(TRMSUser user);

    /**
     * TGC验证
     *
     * @param tgc
     * @return
     */
    boolean tgcValidation(String tgc);

    /**
     * 获取用户信息
     *
     * @param tgc
     * @return
     */
    Authorization getUserByTgc(String tgc);

    /**
     * 删除TGC
     *
     * @param tgc
     */
    void removeTgc(String tgc);

}
