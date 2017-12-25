package com.huaxia.sso.service.impl;

import com.huaxia.sso.common.exception.AuthenticationException;
import com.huaxia.sso.common.exception.RestrictionPolicyException;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.common.utility.Utils;
import com.huaxia.sso.dao.TRMSUserLockOutMapper;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * LoginErrorCountAuthenticationHandlerImpl
 * 作者-Liu zhilai
 * @description 登录次数限制
 */
public class LoginErrorCountAuthenticationHandlerImpl implements AuthenticationHandler {

    @Autowired
    private TRMSUserLockOutMapper userLockOutMapper;

    private Integer maxLoginErrorCount = 5;

    @Autowired
    private RedisTemplate redisTemplate ;

    public void validator(TRMSUser userInfo) throws AuthenticationException {
//        UcUserLockOut userLockOut = ucUserLockOutMapper.selectByToday(userInfo.getUsername());
//        if (null != userLockOut && userLockOut.getFailedNum() >= maxLoginErrorCount) {
//            throw new RestrictionPolicyException(Constants.KEY_EXCEPTION_RESTRICTION_POLICY_MAX_ERROR_COUNT, "错误登录次数超过最大限制");
//        }
        String key = Constants.REDIS_LOGIN_ERROR_VALIDATE_CODE_KEY+userInfo.getName()+ Utils.getDate();
        String lockkey = Constants.REDIS_LOGIN_USER_VALIDATE_CODE_KEY+userInfo.getName()+ Utils.getDate();
        if(redisTemplate.hasKey(lockkey)) {
            Boolean lock = Boolean.parseBoolean(String.valueOf(redisTemplate.opsForValue().get(lockkey)));
            if(lock != null) {
                if(lock.booleanValue()) {
                    redisTemplate.delete(key);
                    throw new RestrictionPolicyException(Constants.KEY_EXCEPTION_RESTRICTION_POLICY_MAX_ERROR_COUNT, "用户登录错误次数过多，已被锁定，10分钟后再登录！");
                }
            }
        }
        if(redisTemplate.hasKey(key)) {
            Integer num = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(key)));
            if (num != null && (num.intValue()+1) >= maxLoginErrorCount) {
                redisTemplate.opsForValue().set(lockkey, true, 60*10, TimeUnit.SECONDS);
                //throw new RestrictionPolicyException(Constants.KEY_EXCEPTION_RESTRICTION_POLICY_MAX_ERROR_COUNT, "错误登录次数超过最大限制");
            }
        }




    }


    public Integer getMaxLoginErrorCount() {
        return maxLoginErrorCount;
    }

    public void setMaxLoginErrorCount(Integer maxLoginErrorCount) {
        this.maxLoginErrorCount = maxLoginErrorCount;
    }
}
