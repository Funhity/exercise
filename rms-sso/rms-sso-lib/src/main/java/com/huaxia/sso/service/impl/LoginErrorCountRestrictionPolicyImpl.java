package com.huaxia.sso.service.impl;

import com.huaxia.sso.utils.WebUtility;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.common.utility.Utils;
import com.huaxia.sso.dao.TRMSUserLockOutMapper;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.model.TRMSUserLockOut;
import com.huaxia.sso.service.LogonRestrictionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**

 * @description 错误 次数限制
 */
public class LoginErrorCountRestrictionPolicyImpl implements LogonRestrictionPolicy {

    private Logger logger = Logger.getLogger(LoginErrorCountRestrictionPolicyImpl.class.getName());
    @Autowired
    private TRMSUserLockOutMapper userLockOutMapper;

    @Autowired
    private RedisTemplate redisTemplate ;

    /**
     * 登录后 根据 是否登录成功进行 限制记录
     * 作者-Liu zhilai
     * @param userInfo
     * @param isLogin
     */
    public void apply(HttpServletRequest request, TRMSUser userInfo, int type, boolean isLogin) {
        if (!isLogin && null != userInfo) {
            TRMSUserLockOut userLockOut = userLockOutMapper.selectByToday(userInfo.getId());
            String key = Constants.REDIS_LOGIN_ERROR_VALIDATE_CODE_KEY+userInfo.getName()+ Utils.getDate();
            if (null == userLockOut) {
                userLockOut = new TRMSUserLockOut();
                userLockOut.setCreateTime(new Date());
                userLockOut.setUserId(userInfo.getId());
                userLockOut.setUsername(userInfo.getName());
                userLockOut.setFailedSource(this.getClass().getSimpleName());
                if(request != null) {
                    userLockOut.setIp(WebUtility.getIpAddress(request));
                }
                userLockOut.setStatus(type);
                userLockOut.setFailedNum(1);
                userLockOutMapper.insert(userLockOut);
            } else {
                userLockOut.setFailedNum(userLockOut.getFailedNum() + 1);
                userLockOutMapper.updateByPrimaryKey(userLockOut);
            }

            if(1==type) {
                if(redisTemplate.hasKey(key)) {
                    int num = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(key)));
                    redisTemplate.opsForValue().set(key, num + 1, 60 * 10 * 24, TimeUnit.SECONDS);
                } else {
                    redisTemplate.opsForValue().set(key, 1, 60 * 10 * 24, TimeUnit.SECONDS);
                }

            }
        }


    }



}
