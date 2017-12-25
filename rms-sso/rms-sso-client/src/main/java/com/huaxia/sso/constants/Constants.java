package com.huaxia.sso.constants;

/**
 * Constants

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
    // 用户名称
    public final static String CAS_UNAME_KEY = "uname";
    // 自动登陆密码
    public final static String CAS_AUTOCODE_KEY = "autocode";

    // 票据/会话
    public final static String CAS_TGC_KEY = "tgc";

    // 票据
    public final static String CAS_TICKET_KEY = "ticket";
    // 重定向
    public final static String CAS_REDIRECT_URL_KEY = "redirect";

    //默认一个月缓存
    public final static int CAS_TIMEOUT_KEY = 60*60*24*30*6;


    //================== 验证码MEMCACHE CACHE KEY    =================================================================*/
    //验证码超时时间10分钟
    public final static int MEMCACHE_VALIDATE_CODE_TIMEOUT = 60*10;
    //用户登录MEMCACHE prefix
    public final static String MEMCACHE_VALIDATE_CODE_KEY = "memcache_validate_code_key_";

    //用户注册MEMCACHE prefix
    public final static String MEMCACHE_REGIST_VALIDATE_CODE_KEY = "memcache_regist_validate_code_key_";

    /**
     * 异常定义
     */
    /*================== Authentication ==============================================================================*/
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
     * 平台 系统编号
     */
    /*================== OPEN SYSTEM ID ==============================================================================*/
//    public final static String OPEN_SYSTEM_ID_WEIBO = "10001";
//    public final static String OPEN_SYSTEM_ID_QQ = "10002";

    public final static String RMS_SYSTEM_ID = "100";

    /**
     * 用户冻结
     */
    public final static int USER_FREEZON = 1;

    /**
     * 用户异常
     */
    public final static int USER_EXCEPTION = 2;

    /**
     * 用户删除
     */
    public final static int USER_DEL= 3;


}
