package com.huaxia.sso.service;

import com.huaxia.sso.utils.Authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.client
 * 作者-Liu zhilai
 * 说明：华夏信财统一权限对接支持类
 * 2016/3/3 15:35
 * 2017 华夏信财-版权所有
 */
public interface SSOSupportService {

    /**
     * SSO提供给api方式登录,登录成功后返回tgc,其他平台保存tgc到cookie中
     * @param username
     * @param passwd
     * @return
     */
    Authorization loginPasswd(String username, String passwd, HttpServletRequest request, HttpServletResponse response);

//    /**
//     * SSO提供给api方式登录,登录成功后返回tgc,其他平台保存tgc到cookie中
//     * @param username
//     * @return
//     */
//    Authorization loginValidateCode(String username, String validateCode, HttpServletRequest request,  HttpServletResponse response);

//    /**
//     * SSO提供给api注册接口
//     * @param username
//     * @return
//     */
//    Authorization registValidateCode(String username, String validateCode, String passwd, String realname, HttpServletRequest request,  HttpServletResponse response);

//    /**
//     * 验证码注册
//     * @param username
//     * @param validateCode
//     * @param passwd
//     * @param realname
//     * @param sysid
//     * @return
//     */
//    Result registValidateCode_V1(String username, String validateCode, String passwd, String realname, String sysid);

//    /**
//     * 验证码重置
//     * @param username
//     * @param validateCode
//     * @param passwd
//     * @param realname
//     * @param sysid
//     * @return
//     */
//    Result resetValidateCode_V1(String username, String validateCode, String passwd, String realname, String sysid);

//    /**
//     * 老密码验证修改密码
//     * @param username
//     * @param oldPassword
//     * @param passwd
//     * @param sysid
//     * @return
//     */
//    Result updatePwdValidateCode_V1(String username, String oldPassword, String passwd, String sysid);

    void getLoginValidateCode(String username, String sysid, String ip);

    public void getRegistValidateCode_V1(String username, String sysid, String ip);

    public void getResetValidateCode_V1(String username, String sysid, String ip);
    /**
     * 验证用户登录状态，true已登录，false，未登录跳转至登录页面
     * @param tgc
     * @return
     */
    boolean validateLoginStatus(String tgc);

    /**
     * 获取登录后的用户信息
     * @param tgc
     * @return
     */
    Authorization getAuthorization(String tgc);


    /**
     * 退出登录
     * @param tgc
     * @return
     */
    boolean logout(String tgc);



}
