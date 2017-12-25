package com.huaxia.sso.service.impl;

import com.huaxia.sso.utils.StringUtils;
import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;

/**
 * AccountExistAuthenticationHandlerImpl
 * @description 验证规则：用户状态是否正常
 * 作者-Liu zhilai
 */

public class StatusAuthenticationHandlerImpl implements AuthenticationHandler {
    public void validator(TRMSUser userInfo) throws AuthenticationException {
        if (StringUtils.isNotBlank(userInfo.getEnabled()) &&
                Integer.valueOf(userInfo.getEnabled()) == Constants.USER_FREEZON)
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_USER_STAT_DISABLED, "用户被冻结，请联系管理员");
        if (StringUtils.isNotBlank(userInfo.getEnabled()) &&
                Integer.valueOf(userInfo.getEnabled()) == Constants.USER_DEL)
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_USER_STAT_DELETE, "用户被删除，请联系管理员");
        if (StringUtils.isNotBlank(userInfo.getEnabled()) &&
                Integer.valueOf(userInfo.getEnabled()) == Constants.USER_EXCEPTION)
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_USER_STAT_DELETE, "用户异常，请联系管理员");
    }
}
