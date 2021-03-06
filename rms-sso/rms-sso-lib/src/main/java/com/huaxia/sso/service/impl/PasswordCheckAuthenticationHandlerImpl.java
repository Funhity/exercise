package com.huaxia.sso.service.impl;

import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.common.utility.Utils;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;
import com.huaxia.sso.service.PasswordEncoder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @description 密码检测规则
 * 作者-Liu zhilai
 */

public class PasswordCheckAuthenticationHandlerImpl implements AuthenticationHandler {
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate ;
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

        String lockkey = Constants.REDIS_LOGIN_USER_VALIDATE_CODE_KEY+userInfo.getName()+ Utils.getDate();
//        Boolean lock = Boolean.parseBoolean(String.valueOf(redisTemplate.opsForValue().get(lockkey)));

        // 验证 密码是否匹配
        if (StringUtils.isBlank(userInfo.getInputPwd())) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_NULL, "密码不能为空");
        }
        if (!passwordEncoder.isPasswordValid(userInfo.getPassword(), userInfo.getInputPwd(), userInfo.getName())) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS, "密码不匹配");
        }
        if (userInfo.getEnabled() != 1) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS, "用户账号异常，请联系管理员");
        }
        //登录成功，刷新登录错误次数
        String key = Constants.REDIS_LOGIN_ERROR_VALIDATE_CODE_KEY + userInfo.getName()+ Utils.getDate();
        redisTemplate.delete(key);
        redisTemplate.delete(lockkey);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
