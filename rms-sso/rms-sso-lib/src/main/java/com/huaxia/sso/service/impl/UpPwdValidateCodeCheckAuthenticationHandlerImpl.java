package com.huaxia.sso.service.impl;

import com.huaxia.sso.utils.Configuration;
import com.huaxia.sso.utils.StringUtils;
import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.common.utility.Constants;

import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @description 验证码检测规则
 * 作者-Liu zhilai
 */

public class UpPwdValidateCodeCheckAuthenticationHandlerImpl implements AuthenticationHandler {

    @Autowired
    private RedisTemplate redisTemplate ;

    /**
     * 执行认证
     * 作者-Liu zhilai
     * @param userInfo
     * @throws AuthenticationException
    */
    public void validator(TRMSUser userInfo) throws AuthenticationException {
//        Object memValidateCode = redisTemplate.opsForValue().get(Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY+userInfo.getName());

        Object memValidateCode = new Object();
        String key = Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY + userInfo.getName();
        if(redisTemplate.hasKey(key)) {
            memValidateCode = redisTemplate.opsForValue().get(key);
        }

        if(StringUtils.isBlank(userInfo.getName())) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_USER_NAME_NOT_NULL, "手机号码不能为空");
        }

        //测试验证码，不影响正式环境
        String testValidateCode = Configuration.getProperty("testValidateCode");
        if(!StringUtils.equalsIgnoreCase(testValidateCode, "true")) {
            if(StringUtils.isBlank(memValidateCode)) {
                memValidateCode = testValidateCode;
            }
        }

        if(StringUtils.isBlank(memValidateCode)) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_NO_MEM_VALIDATE_CODE, "请先获取验证码");
        }

        if(!StringUtils.equalsIgnoreCase((String)memValidateCode, userInfo.getPassword())) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_ERROR_VALIDATE_CODE, "验证码不正确");
        }

        if(userInfo.getInputPwd().length()<6 ||userInfo.getInputPwd().length()>20) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS, "密码需为6-20位字母数字或符号组合");
        }

        if (userInfo.getEnabled() != 1) {
            throw new AuthenticationException(Constants.KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS, "用户账号异常，请联系管理员");
        }
    }


}
