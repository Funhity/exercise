package com.huaxia.sso.service;


import com.huaxia.sso.model.RmsSsoUser;
import com.huaxia.sso.utils.Authorization;

import java.util.Map;

/**
 * UserService

 * @description 用户接口, 供各个模块获取 用户是否登录，及 认证信息、通知 SSO退出 等
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param id
     * @return Ticket
     */
    String login(int id);

    /**
     * 依照 Ticket 查询认证信息
     *
     * @param ticket
     * @return
     */
    Authorization getAuthorizationByTicket(String ticket);

    /**
     * 删除 TICKET
     *
     * @param ticket
     */
    void removeAuthorizationByTicket(String ticket);

    /**
     * 依照 TGC 查询认证信息
     *
     * @param tgc
     * @return
     */
    Authorization getAuthorizationByTgc(String tgc);

    /**
     * 依照 TGC,用户编号 验证认证信息
     *
     * @param tgc
     * @return
     */
    boolean validationTgcAndUserId(String tgc, int userId);


    RmsSsoUser getSSOUserByMobile(String mobile);

    RmsSsoUser getSSOUserByUid(String uid);

    /**
     * 删除 TGC / 用户退出
     *
     * @param tgc
     */
    void removeAuthorizationByTgc(String tgc);

    int getMemberId(String tgc);

    /**
     * 公共发送短信接口
     * @param mobile
     * @param msgId 短信模板编号
     * @return
     */
    Map<String, Object> getValidateCode(String mobile, String msgId);


    /**
     * 退出接口
     * @param tgc
     */
    void logout(String tgc);


}
