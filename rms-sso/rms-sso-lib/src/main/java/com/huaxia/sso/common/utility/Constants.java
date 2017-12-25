package com.huaxia.sso.common.utility;

/**
 * Constants
 *
 * @description 常量类
 */
public class Constants {
    /**
     * Web层
     */
    /*================== COOKIE TGC KEY  redirectURL =================================================================*/

    // SESSION 中存放 用户信息的 KEY
    public final static String CAS_AUTHENTICATION_KEY = "cas_authentication_key";
    // 退出请求
    public final static String CAS_LOGOUT_KEY = "cas_logout";

    // 用户编号
    public final static String CAS_UID_KEY = "uid";

    // 自动登陆密码
    public final static String CAS_AUTOCODE_KEY = "autocode";

    // 用户名称
    public final static String CAS_UNAME_KEY = "uname";

    // 票据/会话
    public final static String CAS_TGC_KEY = "tgc";

    // 票据
    public final static String CAS_TICKET_KEY = "ticket";
    // 重定向
    public final static String CAS_REDIRECT_URL_KEY = "redirect";
    // 重定向返回URL
    public final static String CAS_RETURN_URL_KEY = "returnUrl";

    // 自动重定向到指定的URL
    //public final static String CAS_AUTO_REDIRECT_KEY = "autoredirect";

    //默认一个月缓存
    public final static int CAS_TIMEOUT_KEY = 60 * 60 * 24 * 30 * 6;


    //================== 验证码MEMCACHE CACHE KEY    =================================================================*/
    //验证码超时时间10分钟
    public final static int MEMCACHE_VALIDATE_CODE_TIMEOUT = 60 * 10;

    //用户登录MEMCACHE prefix
    public final static String MEMCACHE_LOGIN_VALIDATE_CODE_KEY = "memcache_login_validate_code_key_";

    //忘记密码MEMCACHE prefix
    public final static String MEMCACHE_FP_LOGIN_VALIDATE_CODE_KEY = "memcache_fp_login_validate_code_key_";

    //用户注册MEMCACHE prefix
    public final static String MEMCACHE_REGIST_VALIDATE_CODE_KEY = "memcache_regist_validate_code_key_";

    //用户密码注册MEMCACHE prefix
    public final static String MEMCACHE_REGIST_PWD_VALIDATE_CODE_KEY = "memcache_regist_pwd_validate_code_key_";

    //投票活动MEMCACHE prefix
    public final static String MEMCACHE_VOTE_VALIDATE_CODE_KEY = "memcache_vote_validate_code_key_";

    //一分钟内只允许发送一条短信
    public final static String MEMCACHE_MSG_VALIDATE_CODE_KEY = "memcache_msg_validate_code_key";

    //用户登录错误计数MEMCACHE prefix
    public final static String MEMCACHE_LOGIN_ERROR_VALIDATE_CODE_KEY = "memcache_login_error_validate_code_key";

    //用户登录锁定状态MEMCACHE prefix
    public final static String MEMCACHE_LOGIN_USER_VALIDATE_CODE_KEY = "memcache_login_user_validate_code_key";


    //================== 验证码REDIS CACHE KEY    =================================================================*/
    //验证码超时时间10分钟
    public final static int REDIS_VALIDATE_CODE_TIMEOUT = 60 * 10;

    //用户登录REDIS prefix
    public final static String REDIS_LOGIN_VALIDATE_CODE_KEY = "rms_sso_login_validate_code_key_";

    //忘记密码REDIS prefix
    public final static String REDIS_FP_LOGIN_VALIDATE_CODE_KEY = "rms_sso_fp_login_validate_code_key_";

    //用户注册REDIS prefix
    public final static String REDIS_REGIST_VALIDATE_CODE_KEY = "rms_sso_regist_validate_code_key_";

    //用户密码注册REDIS prefix
    public final static String REDIS_REGIST_PWD_VALIDATE_CODE_KEY = "rms_sso_regist_pwd_validate_code_key_";

    //投票活动REDIS prefix
    public final static String REDIS_VOTE_VALIDATE_CODE_KEY = "rms_sso_vote_validate_code_key_";

    //一分钟内只允许发送一条短信
    public final static String REDIS_MSG_VALIDATE_CODE_KEY = "rms_sso_msg_validate_code_key_";

    //用户登录错误计数REDIS prefix
    public final static String REDIS_LOGIN_ERROR_VALIDATE_CODE_KEY = "rms_sso_login_error_validate_code_key_";

    //用户登录锁定状态REDIS prefix
    public final static String REDIS_LOGIN_USER_VALIDATE_CODE_KEY = "rms_sso_login_user_validate_code_key_";

    /**
     * 异常定义
     */
    /*================== Authentication ==============================================================================*/
    // 用户验证成功
    public static Integer AUTHENTICATION_USER_SUCCESS = 000000;
    // 用户名不能为空
    public static Integer KEY_EXCEPTION_AUTHENTICATION_USER_NAME_NOT_NULL = 100000;
    // 用户不存在
    public static Integer KEY_EXCEPTION_AUTHENTICATION_USER_NOT_EXISTS = 100001;
    // 用户被冻结
    public static Integer KEY_EXCEPTION_AUTHENTICATION_USER_STAT_DISABLED = 100005;
    // 用户被删除
    public static Integer KEY_EXCEPTION_AUTHENTICATION_USER_STAT_DELETE = 100006;
    // 请先获取验证码，服务器验证码不存在
    public static Integer KEY_EXCEPTION_AUTHENTICATION_NO_MEM_VALIDATE_CODE = 100007;
    // 验证码错误
    public static Integer KEY_EXCEPTION_AUTHENTICATION_ERROR_VALIDATE_CODE = 100007;

    // 密码不能为空
    public static Integer KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_NULL = 100004;
    // 密码不匹配
    public static Integer KEY_EXCEPTION_AUTHENTICATION_PASSWORD_NOT_EQUALS = 100002;
    // 超过错误登录数
    public static Integer KEY_EXCEPTION_RESTRICTION_POLICY_MAX_ERROR_COUNT = 100003;
    // MAC地址为空
    public static Integer KEY_EXCEPTION_MAC_NOT_NULL = 100006;
    // IP地址为空
    public static Integer KEY_EXCEPTION_IP_NOT_NULL = 100007;
    // data参数为空
    public static Integer KEY_EXCEPTION_DATA_NOT_NULL = 100008;
    // 权限不足
    public static Integer KEY_EXCEPTION_ROLE_NOT_HAS = 100009;


    /**
     * 响应码
     */
    /*================== RESP_CODE ==============================================================================*/
    // 响应成功
    public static Integer RESP_CODE_SUCC = 1000;
    // 响应失败
    public static Integer RESP_CODE_FAIL = 1001;
    // 手机号码已注册
    public static Integer RESP_CODE_EXIST = 1002;


    /**
     * 平台 系统编号
     */
    /*================== OPEN SYSTEM ID ==============================================================================*/
//    public final static String OPEN_SYSTEM_ID_WEIBO = "10001";
//    public final static String OPEN_SYSTEM_ID_QQ = "10002";

    public final static String RMS_SYSTEM_ID = "100";

    /**
     * 注册来源ID 0:验证码注册
     */
    public final static String REGIST_SOURCE_ID_CODE = "0";

    /**
     * 注册来源ID 1:邀请注册
     */
    public final static String REGIST_SOURCE_ID_INVITE = "1";

    /**
     * 注册来源ID 2:投票报名注册
     */
    public final static String REGIST_SOURCE_ID_VOTE = "2";

    /**
     * 登录类型 0:ANDROID
     */
    public final static String LOGIN_TYPE_ANDROID = "0";

    /**
     * 登录类型 1:IOS
     */
    public final static String LOGIN_TYPE_IOS = "1";
    /**
     * 登录类型 2:其他
     */
    public final static String LOGIN_TYPE_OTHER = "2";
    /**
     * 登录类型 3:自动登录
     */
    public final static String LOGIN_TYPE_AUTO = "3";

    /**
     * 用户冻结
     */
    public final static int USER_FREEZON = 2;

    /**
     * 用户异常
     */
    public final static int USER_EXCEPTION = 3;

    /**
     * 用户删除
     */
    public final static int USER_DEL = 0;

    /** ----------- 字典常量 ----------- */
    /**
     * 认证状态 0:未认证
     */
    public static final int USER_CARD_CERTSTATE_NOT_AUTH = 0;
    /**
     * 认证状态 1:认证
     */
    public static final int USER_CARD_CERTSTATE_AUTH = 1;
    /**
     * 模板类型 1:简约(默认)
     */
    public static final int USER_CARD_MODEL_SIMPLE = 1;
    /**
     * 模板类型 2:生活
     */
    public static final int USER_CARD_MODEL_LIFE = 2;
    /**
     * 模板类型 3:商务
     */
    public static final int USER_CARD_MODEL_BUSINESS = 3;
    /**
     * 是否删除 1 否
     */
    public static final int COMPANY_IS_ENABLE_YES = 1;
    /**
     * 是否删除 0 是
     */
    public static final int COMPANY_IS_ENABLE_NO = 0;

    /**
     * 登录方式 0 验证码登录
     */
    public static final int LOGIN_METHOD_CODE = 0;

    /**
     * 登录方式 1 密码登录
     */
    public static final int LOGIN_METHOD_PASSWORD = 1;

    /**
     * 登录方式 2 忘记密码登录
     */
    public static final int LOGIN_METHOD_FG_PASSWORD = 2;




}
