package com.huaxia.sso.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.dao.TRMSUserMapper;
import com.huaxia.sso.model.TRMSUser;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huaxia.sso.model.RmsSsoUser;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.service.KeyEncoder;
import com.huaxia.sso.service.RMSManager;
import com.huaxia.sso.service.UniqueTicketIdGenerator;
import com.huaxia.sso.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @description 用户接口 实现类
 */
@Service("ssoUserService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /* 自定义 TGC 存储名称 前缀 */
    @Value("${redis.key.prefix.user.tgc}")
    private String redisKeyPrefixUserTgc;
    /**
     * 自定义 TICKET 存储名称 前缀
     */
    @Value("${redis.key.prefix.user.ticket}")
    private String redisKeyPrefixUserTicket;
    /* Ticket生成规则 */
    private UniqueTicketIdGenerator uniqueTicketIdGenerator;
    /* Cache存储 后缀生成规则 */
    private KeyEncoder keyEncoder;
    @Autowired
    private TRMSUserMapper ucUserMapper;

    @Autowired
    private RMSManager rmsManager;

    @Autowired
    private RedisTemplate redisTemplate ;
    /**
     * 默认缓存 5 分钟
     */
    @Value("${redis.tgc.timeout}")
    private int cacheTgcTimeout = 60 * 5;
    /**
     * Ticket 默认缓存 5 分钟
     */
    @Value("${redis.ticket.timeout}")
    private int cacheTicketTimeout = 60 * 5;

    @Override
    public String login(int id) {
        String tgc = keyEncoder.generator(id);
        TRMSUser ucUser = ucUserMapper.selectByPrimaryKey(id);
        if (null == ucUser) {
            return null;
        }
        Authorization authorization = new Authorization();
        authorization.setId(id);
        authorization.setUsername(ucUser.getName());
        authorization.setTgc(tgc);

        redisTemplate.opsForValue().set(redisKeyPrefixUserTgc + tgc, authorization, cacheTgcTimeout, TimeUnit.SECONDS);
        String tick = uniqueTicketIdGenerator.getNewTickId("");
        redisTemplate.opsForValue().set(redisKeyPrefixUserTicket + tick, tgc, cacheTicketTimeout, TimeUnit.SECONDS);
        return tick;
    }

    public Authorization getAuthorizationByTicket(String ticket) {
        String tgc = String.valueOf(redisTemplate.opsForValue().get(redisKeyPrefixUserTicket + ticket));
        Authorization authorization = (Authorization) redisTemplate.opsForValue().get(redisKeyPrefixUserTgc + tgc);
        return authorization;
    }

    public void removeAuthorizationByTicket(String ticket) {
        redisTemplate.delete(redisKeyPrefixUserTicket + ticket);
    }

    public Authorization getAuthorizationByTgc(String tgc) {
        try {
            Authorization authorization = (Authorization) redisTemplate.opsForValue().get(redisKeyPrefixUserTgc + tgc);
            if(authorization != null) {
                redisTemplate.expire(redisKeyPrefixUserTgc + tgc, cacheTgcTimeout, TimeUnit.SECONDS);
                return authorization;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean validationTgcAndUserId(String tgc, int userId) {
        Authorization authorization = getAuthorizationByTgc(tgc);
        if (null != authorization && authorization.getId() == userId) {
            return true;
        }
        return false;
    }

    @Override
    public RmsSsoUser getSSOUserByMobile(String mobile) {
        TRMSUser user = ucUserMapper.selectByMobile(mobile);
        RmsSsoUser u = new RmsSsoUser();
        try {
            BeanUtils.copyProperties(u, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public RmsSsoUser getSSOUserByUid(String uid) {
        TRMSUser user = ucUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
        RmsSsoUser u = new RmsSsoUser();
        try {
            BeanUtils.copyProperties(u, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public int getMemberId(String tgc) {
        Authorization authorization = this.getAuthorizationByTgc(tgc);
        if (authorization == null)
            return 0;
        return authorization.getId();
    }

//    @Override
//    public Authorization autoLogin(String username, String pwd) {
//        try {
//            if (rmsManager.userLoginAutoValidation(username, pwd, null)) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                return authorization;
//            }
//        } catch (CASException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @Override
//    public Authorization autoLogin(String username) {
//        try {
//            if (rmsManager.userNoPwdLoginValidation(username, null, "1002")) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                return authorization;
//            }
//        } catch (CASException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    @Override
    public Map<String, Object> getValidateCode(String mobile, String msgId) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        String key = Constants.REDIS_MSG_VALIDATE_CODE_KEY + msgId + mobile;
//        Boolean flag = Boolean.parseBoolean(redisUtil.getString(key));
        Boolean flag = Boolean.parseBoolean(String.valueOf(redisTemplate.opsForValue().get(key)));
        if (flag != null && flag.booleanValue()) {
            rMap.put("code", Constants.RESP_CODE_FAIL);
            rMap.put("msg", "短信发送过于频繁，请稍后再试");
            return rMap;
        }
        if (rmsManager.getValidateCode(mobile, msgId, null)) {
            rMap.put("code", Constants.RESP_CODE_SUCC);
            rMap.put("msg", "短信发送成功");
//            redisUtil.setString(key, "true", 60);// 保存60秒短信发送状态
            redisTemplate.opsForValue().set(key, "true", 60, TimeUnit.SECONDS);
        } else {
            rMap.put("code", Constants.RESP_CODE_FAIL);
            rMap.put("msg", "短信发送失败");
        }
        return rMap;

    }

    public void removeAuthorizationByTgc(String tgc) {
        redisTemplate.delete(redisKeyPrefixUserTgc + tgc);

    }

    /**
     * 清除用户登录信息
     *
     * @param tgc
     */
    public void logout(String tgc) {
        redisTemplate.delete(tgc);
    }




    public void setKeyEncoder(KeyEncoder keyEncoder) {
        this.keyEncoder = keyEncoder;
    }

    public void setRedisKeyPrefixUserTgc(String redisKeyPrefixUserTgc) {
        this.redisKeyPrefixUserTgc = redisKeyPrefixUserTgc;
    }

    public void setRedisKeyPrefixUserTicket(String redisKeyPrefixUserTicket) {
        this.redisKeyPrefixUserTicket = redisKeyPrefixUserTicket;
    }

    public void setUniqueTicketIdGenerator(UniqueTicketIdGenerator uniqueTicketIdGenerator) {
        this.uniqueTicketIdGenerator = uniqueTicketIdGenerator;
    }

    public void setCacheTicketTimeout(int cacheTicketTimeout) {
        this.cacheTicketTimeout = cacheTicketTimeout;
    }

    public void setCacheTgcTimeout(int cacheTgcTimeout) {
        this.cacheTgcTimeout = cacheTgcTimeout;
    }

}
