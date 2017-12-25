package com.huaxia.sso.service.impl;

import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;
import com.huaxia.sso.common.utility.Constants;
/**
 * AccountExistAuthenticationHandlerImpl+
 * 作者-Liu zhilai
 * @description 验证规则：用户是否存在规则
 */

public class AccountExistAuthenticationHandlerImpl implements AuthenticationHandler {
    public void validator(TRMSUser userInfo) throws AuthenticationException {
        if (null == userInfo)
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_USER_NOT_EXISTS, "尚未注册，请使用验证码登录");
    }
}
