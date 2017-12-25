package com.huaxia.sso.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;


import com.huaxia.sso.dao.TRMSUserLoginLogMapper;
import com.huaxia.sso.dao.TRMSUserMapper;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.Md5Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;


import com.huaxia.sso.common.exception.CASException;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.common.utility.Utils;
import com.huaxia.sso.service.AuthenticationHandler;
import com.huaxia.sso.service.KeyEncoder;
import com.huaxia.sso.service.LogonRestrictionPolicy;
import com.huaxia.sso.service.RMSManager;
import com.huaxia.sso.service.UniqueTicketIdGenerator;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service
 * 作者-Liu zhilai
 * 说明：SSO权限主控制类
 * 2016/3/3 17:44
 * 2017华夏信财-版权所有
 */

public class
RMSManagerImpl implements RMSManager {
    private Logger logger = LoggerFactory.getLogger(RMSManagerImpl.class.getName());
    /**
     * 认证规则
     */
    private List<AuthenticationHandler> authenticationHandler = new ArrayList<AuthenticationHandler>();

    /**
     * 认证规则
     */
    private List<AuthenticationHandler> authenticationAutoHandler = new ArrayList<AuthenticationHandler>();

    /**
     * 验证码认证规则
     */
    private List<AuthenticationHandler> authenticationValidateCodeHandler = new ArrayList<AuthenticationHandler>();
    /**
     * 忘记密码验证码认证规则
     */
    private List<AuthenticationHandler> fgAuthenticationValidateCodeHandler = new ArrayList<AuthenticationHandler>();

    /**
     * 修改密码验证码认证规则
     */
    private List<AuthenticationHandler> upPwdAuthenticationValidateCodeHandler = new ArrayList<AuthenticationHandler>();


    /**
     * 注册密码验证码认证规则
     */
    private List<AuthenticationHandler> registAuthenticationValidateCodeHandler = new ArrayList<AuthenticationHandler>();


    /**
     * 注册认证规则
     */
    private List<AuthenticationHandler> registValidateCodeHandler = new ArrayList<AuthenticationHandler>();


    private UniqueTicketIdGenerator uniqueTicketIdGenerator;

    @Autowired
    private TRMSUserMapper userMapper;

    @Autowired
    private TRMSUserLoginLogMapper userLoginLogMapper;

    @Autowired
    private RedisTemplate redisTemplate ;
    

    /**
     * Ticket 默认缓存 10 分钟
     */
    @Value("${redis.ticket.timeout}")
    private int cacheTicketTimeout = 60 * 10;
    /**
     * 默认缓存 10 分钟
     */
    @Value("${redis.tgc.timeout}")
    private int cacheTgcTimeout = 60 * 10;

    /**
     * 自定义 TGC 存储名称  前缀
     */
    @Value("${redis.key.prefix.user.tgc}")
    private String redisKeyPrefixUserTgc;
    /**
     * 自定义 TICKET 存储名称 前缀
     */
    @Value("${redis.key.prefix.user.ticket}")
    private String redisKeyPrefixUserTicket;
    /**
     * Cache存储 后缀生成规则
     */

    private KeyEncoder keyEncoder;


    private LogonRestrictionPolicy logonRestrictionPolicy;

    /**
     * 用户是否登录验证
     *
     * @param userid
     * @return
     */
    public boolean isLogin(String userid) {
        return false;
    }

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    public TRMSUser getByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    public TRMSUser getByUserId(int id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * 用户密码登录验证
     *
     * @param userName
     * @param password
     * @param request
     * @return
     */
    public Map<String, Object> userLoginValidation(String userName, String password, HttpServletRequest request) {

        Map<String, Object> retMap = new HashMap<String, Object>();

        TRMSUser user = getByUserName(userName);
        if(user == null) {
            retMap.put("code", Constants.KEY_EXCEPTION_AUTHENTICATION_USER_NOT_EXISTS);
            retMap.put("message", "对不起，该用户不存在");
            return retMap;
        }

        //logger.info("user.sysid: "+ user.getSysid());
        try {
            if (null != user) {
                user.setInputPwd(password);
            }
            // 认证策略
            for (AuthenticationHandler handler : authenticationHandler) {
                handler.validator(user);
            }
            // 记录登录登录日期，登录IP
            TRMSUser ucUser = new TRMSUser();
            ucUser.setId(user.getId());
            ucUser.setPreviousVisit(user.getLastVisit());
            ucUser.setLastVisit(new Date());
            //if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));

            userMapper.updateByPrimaryKeySelective(ucUser);

            retMap.put("code", Constants.AUTHENTICATION_USER_SUCCESS);
            retMap.put("message", "验证成功");

        } catch (CASException exception) {
            System.out.println("login error message: " + exception.getMessage());
            logonRestrictionPolicy.apply(request, user, 1, false);
            retMap.put("code", exception.getCode());
            retMap.put("message", exception.getMessage());

        }
        return retMap;
    }

    /**
     * 用户密码登录验证
     *
     * @param user
     * @param request
     * @return
     */
    public Map<String, Object> userLoginValidation(TRMSUser user, HttpServletRequest request) {

        Map<String, Object> retMap = new HashMap<String, Object>();

        try {
            // 认证策略
            for (AuthenticationHandler handler : authenticationHandler) {
                handler.validator(user);
            }
            // 记录登录登录日期，登录IP
            TRMSUser ucUser = new TRMSUser();
            ucUser.setId(user.getId());
            ucUser.setPreviousVisit(user.getLastVisit());
            ucUser.setLastVisit(new Date());
            //if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));

            userMapper.updateByPrimaryKeySelective(ucUser);

            retMap.put("code", Constants.AUTHENTICATION_USER_SUCCESS);
            retMap.put("message", "验证成功");

        } catch (CASException exception) {
            System.out.println("login error message: " + exception.getMessage());
            logonRestrictionPolicy.apply(request, user, 1, false);
            retMap.put("code", exception.getCode());
            retMap.put("message", exception.getMessage());

        }
        return retMap;
    }

    /**
     * 用户密码登录验证
     *
     * @param userName
     * @param password
     * @param request
     * @return
     */
    public boolean userLoginValidation(String userName, String password, HttpServletRequest request, String loginType) {
        TRMSUser user = getByUserName(userName);
//        logger.info("user.sysid: "+user.getSysid());
        try {
            if (null != user) {
                user.setInputPwd(password);
            }
            // 认证策略
            for (AuthenticationHandler handler : authenticationHandler) {
                handler.validator(user);
            }
            // 记录登录登录日期，登录IP
            TRMSUser ucUser = new TRMSUser();
            ucUser.setId(user.getId());
            ucUser.setPreviousVisit(user.getLastVisit());
            ucUser.setLastVisit(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));

            userMapper.updateByPrimaryKeySelective(ucUser);

        } catch (CASException exception) {
            System.out.println("login error message: " + exception.getMessage());
            logonRestrictionPolicy.apply(request, user, 1, false);
            return false;
        }
        return true;
    }

//    public boolean userLoginAutoValidation(String userName, String password, HttpServletRequest request) {
//        UcUser user = getByUserName(userName);
//        try {
//            if (null != user) {
//                user.setInputPwd(password);
//            }
//            // 认证策略
//            for (AuthenticationHandler handler : authenticationAutoHandler) {
//                handler.validator(user);
//            }
//            // 记录登录登录日期，登录IP
//            UcUser ucUser = new UcUser();
//            ucUser.setId(user.getId());
//            ucUser.setLastLoginDate(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));
//
//            userMapper.updateByPrimaryKeySelective(ucUser);
//
//        } catch (CASException exception) {
////            logonRestrictionPolicy.apply(request, user, 1, false);
//            throw exception;
//        }
//        return true;
//    }

//    /**
//     * 用户验证码登录验证
//     *
//     * @param userName
//     * @param request
//     * @return
//     */
//    public boolean userLoginValidateCodeValidation(String userName, String validateCode, HttpServletRequest request, String sysid, String loginType) {
//        UcUser user = getByUserName(userName);
//        boolean isNewUser = true;
//        long id = 0;
//        if (null != user) {
//            id = user.getId();
//            isNewUser = false;
//        } else {
//            user = new UcUser();
//        }
//        user.setValidateCode(validateCode);
//        user.setUsername(userName);
//        try {
//            // 认证策略
//            for (AuthenticationHandler handler : authenticationValidateCodeHandler) {
//                handler.validator(user);
//            }
//            // 记录登录登录日期，登录IP
//            UcUser ucUser = new UcUser();
//            //如果新用户验证码验证通过，则创建一个默认新账号
//            Date ctime = new Date();
//            if (isNewUser) {
//                UcUser newUser = new UcUser();
//                newUser.setUsername(userName);
//                newUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                newUser.setValidateCode(validateCode);
//                newUser.setMobile(userName);
//                newUser.setSysid(sysid);
//                newUser.setSourceid(Constants.REGIST_SOURCE_ID_CODE);
//                newUser.setCreateTime(ctime);
//                newUser.setUpdateTime(ctime);
//                userMapper.insertSelective(newUser);
//                id = newUser.getId();
//                user.setId(id);
//
//            } else {
//                if (StringUtils.isBlank(user.getPasswd())) {
//                    ucUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                }
//            }
//
//
//            ucUser.setId(id);
//            ucUser.setLastLoginDate(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));
//
//            userMapper.updateByPrimaryKeySelective(ucUser);
//
//        } catch (CASException exception) {
//            logonRestrictionPolicy.apply(request, user, 2, false);
//            throw exception;
//        }
//        return true;
//    }


//    /**
//     * 用户验证码登录验证
//     *
//     * @param userName
//     * @param request
//     * @return
//     */
//    public boolean userNoPwdLoginValidation(String userName, HttpServletRequest request, String sysid) {
//        UcUser user = getByUserName(userName);
//        boolean isNewUser = true;
//        long id = 0;
//        if (null != user) {
//            id = user.getId();
//            isNewUser = false;
//        } else {
//            user = new UcUser();
//        }
//        user.setUsername(userName);
//        try {
//            // 记录登录登录日期，登录IP
//            UcUser ucUser = new UcUser();
//            //如果新用户，则创建一个默认新账号
//            Date ctime = new Date();
//            if (isNewUser) {
//                UcUser newUser = new UcUser();
//                newUser.setUsername(userName);
//                newUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                newUser.setMobile(userName);
//                newUser.setSysid(sysid);
//                newUser.setCreateTime(ctime);
//                newUser.setUpdateTime(ctime);
//                userMapper.insertSelective(newUser);
//                id = newUser.getId();
//            } else {
//                if (StringUtils.isBlank(user.getPasswd())) {
//                    ucUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                }
//            }
//
//
//            ucUser.setId(id);
//            ucUser.setLastLoginDate(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));
//
//            userMapper.updateByPrimaryKeySelective(ucUser);
//
//            // 插入登录日志  判断是否首次登录，首次登录进行加券处理
////            user.setLastLoginIp(WebUtility.getIpAddress(request));
////            addVoucherAndUserLoginLog(user, Constants.LOGIN_TYPE_OTHER, Constants.LOGIN_METHOD_CODE);
//        } catch (CASException exception) {
//            logonRestrictionPolicy.apply(request, user, 3, false);
//            throw exception;
//        }
//        return true;
//    }

//    public boolean userLoginValidateCodeFgValidation(String userName, String validateCode, String newPasswd, HttpServletRequest request, String sysid, String loginType) {
//        TRMSUser user = getByUserName(userName);
//        boolean isNewUser = true;
//        long id = 0;
//        if (null != user) {
//            id = user.getId();
//            isNewUser = false;
//        } else {
//            user = new TRMSUser();
//        }
//        //user.setValidateCode(validateCode);
//        user.setName(userName);
//        user.setInputPwd(newPasswd);
//        try {
//            // 认证策略
//            for (AuthenticationHandler handler : fgAuthenticationValidateCodeHandler) {
//                handler.validator(user);
//            }
//
//            //如果新用户验证码验证通过，则创建一个默认新账号
//            Date ctime = new Date();
//            if (isNewUser) {
//                UcUser newUser = new UcUser();
//                newUser.setUsername(userName);
//                newUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                newUser.setValidateCode(validateCode);
//                newUser.setMobile(userName);
//                newUser.setSysid(sysid);
//                newUser.setCreateTime(ctime);
//                newUser.setUpdateTime(ctime);
//                userMapper.insertSelective(newUser);
//                id = newUser.getId();
//            }
//
//            // 记录登录登录日期，登录IP
//            UcUser ucUser = new UcUser();
//            ucUser.setId(id);
//            ucUser.setPasswd(Md5Utility.md5SaltString(newPasswd, userName));
//            ucUser.setLastLoginDate(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));
//
//            userMapper.updateByPrimaryKeySelective(ucUser);
//
//        } catch (CASException exception) {
//            logonRestrictionPolicy.apply(request, user, 3, false);
//            throw exception;
//        }
//        return true;
//    }

//    public boolean userLoginValidateCodeRegistValidation(String userName, String validateCode, String newPasswd, HttpServletRequest request, String sysid, String loginType) {
//        UcUser user = getByUserName(userName);
//        if(user != null) throw new AuthenticationException(10022, "用户已存在！");
//        boolean isNewUser = true;
//        long id = 0;
//        if (null != user) {
//            id = user.getId();
//            isNewUser = false;
//        } else {
//            user = new UcUser();
//        }
//        user.setValidateCode(validateCode);
//        user.setUsername(userName);
//        user.setInputPwd(newPasswd);
//        try {
//            // 认证策略
//            for (AuthenticationHandler handler : registAuthenticationValidateCodeHandler) {
//                handler.validator(user);
//            }
//
//            //如果新用户验证码验证通过，则创建一个默认新账号
//            Date ctime = new Date();
//            if (isNewUser) {
//                UcUser newUser = new UcUser();
//                newUser.setUsername(userName);
//                newUser.setPasswd(Md5Utility.md5SaltString(System.currentTimeMillis() + "", userName));
//                newUser.setValidateCode(validateCode);
//                newUser.setMobile(userName);
//                newUser.setSysid(sysid);
//                newUser.setCreateTime(ctime);
//                newUser.setUpdateTime(ctime);
//                userMapper.insertSelective(newUser);
//                id = newUser.getId();
//            }
//
//            // 记录登录登录日期，登录IP
//            UcUser ucUser = new UcUser();
//            ucUser.setId(id);
//            ucUser.setPasswd(Md5Utility.md5SaltString(newPasswd, userName));
//            ucUser.setLastLoginDate(new Date());
//            if (request != null) ucUser.setLastLoginIp(WebUtility.getIpAddress(request));
//
//            userMapper.updateByPrimaryKeySelective(ucUser);
//
//        } catch (CASException exception) {
//            logonRestrictionPolicy.apply(request, user, 3, false);
//            throw exception;
//        }
//        return true;
//    }

    public boolean pwdUpdatePwdValidation(String userName, String oldPassword, String newPasswd, String sysid) {
        TRMSUser user = getByUserName(userName);
        if (null == user) {
            throw new CASException("用户不存在") ;
        }
        user.setName(userName);
        user.setInputPwd(newPasswd);
        String oldPasswordSalt = Md5Utility.md5SaltString(oldPassword, userName);
        if(!user.getPassword().equalsIgnoreCase(oldPasswordSalt)) throw new CASException("旧密码验证异常") ;
        try {
            user.setPassword(Md5Utility.md5SaltString(newPasswd, userName));
            //user.setUpdateTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            throw new CASException("系统异常") ;
        }
        return true;
    }

    public boolean registValidateCode(String username, String validateCode, String passwd, String realname, HttpServletRequest request) {

        return false;
    }

    public boolean getLoginValidateCode(String username, String sysid, String ip) {

//        String validateCode = redisUtil.getString(Constants.REDIS_LOGIN_VALIDATE_CODE_KEY  + username);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(Constants.REDIS_LOGIN_VALIDATE_CODE_KEY  + username));
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(Constants.REDIS_LOGIN_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(Constants.REDIS_LOGIN_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
        }

        final String uu = username;
        final String vv = validateCode;
        final String ss = sysid;
        final String ipp = ip;
        //发送短信到用户手机
        logger.info("手机：" + username + ",登录获取验证码为：" + validateCode + ",当前ip:"+ip + ",sysid:" + sysid);

        new Thread() {
            public void run() {

//                ssoService.sendLoginValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();

        return true;
    }

    public boolean getFpLoginValidateCode(String username, String sysid, String ip) {
//        String validateCode = redisUtil.getString(Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY + username);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY  + username));
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(Constants.REDIS_FP_LOGIN_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
        }
        //发送短信到用户手机
        logger.info("手机：" + username + ",忘记密码获取验证码为：" + validateCode+ ",当前ip:"+ip+ ",sysid:" + sysid);

        final String uu = username;
        final String vv = validateCode;
        final String ss = sysid;
        final String ipp = ip;
        new Thread() {
            public void run() {
//                ssoService.sendFgValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();
        return true;
    }


    public boolean getRegistValidateCode(String username, String sysid, String ip) {
//        String validateCode = redisUtil.getString(Constants.REDIS_REGIST_VALIDATE_CODE_KEY + username);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(Constants.REDIS_REGIST_VALIDATE_CODE_KEY  + username));
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(Constants.REDIS_REGIST_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(Constants.REDIS_REGIST_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
            
        }
        //发送短信到用户手机
        logger.info("注册用户：" + username + ",验证码获取：" + validateCode+ ",当前ip:"+ip+ ",sysid:" + sysid);
        final String uu = username;
        final String vv = validateCode;
        final String ss = sysid;
        final String ipp = ip;
        new Thread() {
            public void run() {
//                ssoService.sendRegistValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();
        return true;
    }


    public boolean getRegistPwdValidateCode(String username, String sysid, String ip) {
//        String validateCode = redisUtil.getString(Constants.REDIS_REGIST_PWD_VALIDATE_CODE_KEY + username);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(Constants.REDIS_REGIST_PWD_VALIDATE_CODE_KEY  + username));
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(Constants.REDIS_REGIST_PWD_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(Constants.REDIS_REGIST_PWD_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
            
        }
        //发送短信到用户手机
        logger.info("注册用户：" + username + ",验证码获取：" + validateCode+ ",当前ip:"+ip);
        final String uu = username;
        final String vv = validateCode;
        final String ss = sysid;
        final String ipp = ip;
        new Thread() {
            public void run() {
//                ssoService.sendRegistValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();
        return true;
    }

    public boolean getVoteValidateCode(String username, String sysid, String ip) {
//        String validateCode = redisUtil.getString(Constants.REDIS_VOTE_VALIDATE_CODE_KEY + username);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(Constants.REDIS_VOTE_VALIDATE_CODE_KEY  + username));
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(Constants.REDIS_VOTE_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(Constants.REDIS_VOTE_VALIDATE_CODE_KEY + username, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
            
        }
        //发送短信到用户手机
        logger.info("投票报名用户：" + username + ",验证码获取：" + validateCode+ ",当前ip:"+ip);
        final String uu = username;
        final String vv = validateCode;
        final String ss = "1004";
        final String ipp = ip;
        new Thread() {
            public void run() {
//                ssoService.sendRegistValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();
        return true;
    }

    public boolean getValidateCode(String mobile, String msgId, String ip) {
        String key = Constants.REDIS_VOTE_VALIDATE_CODE_KEY+msgId+mobile;
//        String validateCode = redisUtil.getString(key);
        String validateCode = String.valueOf(redisTemplate.opsForValue().get(key));
        
        if (validateCode == null) {
            validateCode = Utils.getValidateCode() + "";
//            redisUtil.setString(key, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT);
            redisTemplate.opsForValue().set(key, validateCode, Constants.REDIS_VALIDATE_CODE_TIMEOUT, TimeUnit.SECONDS);
            
        }
        final String uu = mobile;
        final String vv = validateCode;
        final String ss = msgId;
        final String ipp = ip;
        new Thread() {
            public void run() {
//                ssoService.sendRegistValicateCodeRequest(uu, vv + "", ss, ipp);
            }
        }.run();
        return true;
    }


    /**
     * 生成  ticket 并保存 与 TGC 之间的关系
     *
     * @param tgc
     * @return
     */
    public String ticketGrant(String tgc) {
        String tick = uniqueTicketIdGenerator.getNewTickId("");
//        redisUtil.setString(redisKeyPrefixUserTicket + tick, tgc, cacheTicketTimeout);
        
        redisTemplate.opsForValue().set(redisKeyPrefixUserTicket + tick, tgc, cacheTicketTimeout, TimeUnit.SECONDS);
        
        return tick;
    }

    /**
     * 票据验证
     *
     * @param ticket
     * @return
     */
    public boolean ticketValidation(String ticket) {
        return null != getUserByTicket(ticket);
    }

    /**
     * 删除票据
     *
     * @param ticket
     */
    public void removeTicket(String ticket) {
//        redisUtil.delKey(redisKeyPrefixUserTicket + ticket);
        
        redisTemplate.delete(redisKeyPrefixUserTicket + ticket);
    }

    /**
     * 获取通过临时ticket获取用户信息
     *
     * @param ticket
     * @return
     */
    public Authorization getUserByTicket(String ticket) {
//        String tgc = redisUtil.getString(redisKeyPrefixUserTicket + ticket);
        
        String tgc = String.valueOf(redisTemplate.opsForValue().get(redisKeyPrefixUserTicket + ticket));
        
//        Authorization authorization = (Authorization)redisUtil.getUnserializeKey(redisKeyPrefixUserTgc + tgc);

        Authorization authorization = (Authorization) redisTemplate.opsForValue().get(redisKeyPrefixUserTgc + tgc);
        return authorization;
    }

    /**
     * 生成TGC
     *
     * @param id
     * @return
     */
    public String tgcGrant(int id) {
        String tgc = keyEncoder.generator(id);
        TRMSUser ucUser = getByUserId(id);
        Authorization authorization = new Authorization();
        authorization.setId(id);
        authorization.setUsername(ucUser.getName());
        authorization.setTgc(tgc);
        //ucUser.setTgc(tgc);
        //userMapper.updateByPrimaryKeySelective(ucUser);
//        redisUtil.setSerializeKey(redisKeyPrefixUserTgc + tgc, authorization, cacheTgcTimeout);
        
        redisTemplate.opsForValue().set(redisKeyPrefixUserTgc + tgc, authorization, cacheTgcTimeout, TimeUnit.SECONDS);

        return tgc;
    }

    /**
     * 生成TGC
     *
     * @param ucUser
     * @return
     */
    public String tgcGrant(TRMSUser ucUser) {
        String tgc = keyEncoder.generator(ucUser.getId());
        Authorization authorization = new Authorization();
        authorization.setId(ucUser.getId());
        authorization.setUsername(ucUser.getName());
        authorization.setTgc(tgc);
        redisTemplate.opsForValue().set(redisKeyPrefixUserTgc + tgc, authorization, cacheTgcTimeout, TimeUnit.SECONDS);

        return tgc;
    }

    /**
     * TGC验证
     *
     * @param tgc
     * @return
     */
    public boolean tgcValidation(String tgc) {
        return null != getUserByTgc(tgc);
    }

    /**
     * 通过TGC获取用户信息
     *
     * @param tgc
     * @return
     */
    public Authorization getUserByTgc(String tgc) {
        try {
            Authorization authorization = null;
            if(redisTemplate.hasKey(redisKeyPrefixUserTgc + tgc)) {
                authorization = (Authorization) redisTemplate.opsForValue().get(redisKeyPrefixUserTgc + tgc);
                //刷新tgc时间
                if(authorization != null) {
                    redisTemplate.expire(redisKeyPrefixUserTgc + tgc, cacheTgcTimeout, TimeUnit.SECONDS);
                }
            }
            return authorization;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<AuthenticationHandler> getAuthenticationAutoHandler() {
        return authenticationAutoHandler;
    }

    public void setAuthenticationAutoHandler(List<AuthenticationHandler> authenticationAutoHandler) {
        this.authenticationAutoHandler = authenticationAutoHandler;
    }

    /**
     * 删除TGC
     *
     * @param tgc
     */
    public void removeTgc(String tgc) {
    	redisTemplate.delete(redisKeyPrefixUserTgc + tgc);
    }


    // Get Set 方法 。。。。
    public List getAuthenticationHandler() {
        return authenticationHandler;
    }

    public void setAuthenticationHandler(List authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    public List<AuthenticationHandler> getFgAuthenticationValidateCodeHandler() {
        return fgAuthenticationValidateCodeHandler;
    }

    public void setFgAuthenticationValidateCodeHandler(List<AuthenticationHandler> fgAuthenticationValidateCodeHandler) {
        this.fgAuthenticationValidateCodeHandler = fgAuthenticationValidateCodeHandler;
    }

    public List<AuthenticationHandler> getRegistAuthenticationValidateCodeHandler() {
        return registAuthenticationValidateCodeHandler;
    }

    public void setRegistAuthenticationValidateCodeHandler(List<AuthenticationHandler> registAuthenticationValidateCodeHandler) {
        this.registAuthenticationValidateCodeHandler = registAuthenticationValidateCodeHandler;
    }

    public KeyEncoder getKeyEncoder() {
        return keyEncoder;
    }

    public void setKeyEncoder(KeyEncoder keyEncoder) {
        this.keyEncoder = keyEncoder;
    }

    public LogonRestrictionPolicy getLogonRestrictionPolicy() {
        return logonRestrictionPolicy;
    }

    public void setLogonRestrictionPolicy(LogonRestrictionPolicy logonRestrictionPolicy) {
        this.logonRestrictionPolicy = logonRestrictionPolicy;
    }

    public UniqueTicketIdGenerator getUniqueTicketIdGenerator() {
        return uniqueTicketIdGenerator;
    }

    public void setUniqueTicketIdGenerator(UniqueTicketIdGenerator uniqueTicketIdGenerator) {
        this.uniqueTicketIdGenerator = uniqueTicketIdGenerator;
    }

    public List<AuthenticationHandler> getAuthenticationValidateCodeHandler() {
        return authenticationValidateCodeHandler;
    }

    public void setAuthenticationValidateCodeHandler(List<AuthenticationHandler> authenticationValidateCodeHandler) {
        this.authenticationValidateCodeHandler = authenticationValidateCodeHandler;
    }

    public List<AuthenticationHandler> getRegistValidateCodeHandler() {
        return registValidateCodeHandler;
    }

    public void setRegistValidateCodeHandler(List<AuthenticationHandler> registValidateCodeHandler) {
        this.registValidateCodeHandler = registValidateCodeHandler;
    }

    public List<AuthenticationHandler> getUpPwdAuthenticationValidateCodeHandler() {
        return upPwdAuthenticationValidateCodeHandler;
    }

    public void setUpPwdAuthenticationValidateCodeHandler(List<AuthenticationHandler> upPwdAuthenticationValidateCodeHandler) {
        this.upPwdAuthenticationValidateCodeHandler = upPwdAuthenticationValidateCodeHandler;
    }

    public boolean userRegistValidateCodeValidation(String userName, String validateCode) {
        TRMSUser user = new TRMSUser();
        //user.setValidateCode(validateCode);
        user.setName(userName);
        user.setEnabled(1);
        try {
            // 认证策略
            for (AuthenticationHandler handler : registValidateCodeHandler) {
                handler.validator(user);
            }
        } catch (CASException exception) {
            throw exception;
        }
        return true;
    }

}
