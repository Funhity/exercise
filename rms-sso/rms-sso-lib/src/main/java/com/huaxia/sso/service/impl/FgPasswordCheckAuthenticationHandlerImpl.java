package com.huaxia.sso.service.impl;

import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;
import com.huaxia.sso.service.PasswordEncoder;
import org.apache.commons.lang.StringUtils;


/**
 * @description 忘记密码检测规则
 * 作者-Liu zhilai
 */

public class FgPasswordCheckAuthenticationHandlerImpl implements AuthenticationHandler {
    private PasswordEncoder passwordEncoder;
    /**
     * 执行认证
     * 作者-Liu zhilai
     * @param userInfo
     * @throws AuthenticationException
     */
    public void validator(TRMSUser userInfo) throws AuthenticationException {
        /*if (StringUtils.equalsIgnoreCase(userInfo.getUserName(), "admin")) {
            return;
        }*/
        // 验证 密码是否匹配
        if (StringUtils.isBlank(userInfo.getInputPwd())) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_NULL, "密码不能为空");
        }

        if (userInfo.getEnabled() != 1) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS, "用户账号异常，请联系管理员");
        }
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
