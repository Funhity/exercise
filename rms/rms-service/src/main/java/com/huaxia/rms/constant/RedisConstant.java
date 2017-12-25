package com.huaxia.rms.constant;

/**
 * Created by billJiang on 2017/4/20.
 * e-mail:475572229@qq.com  qq:475572229
 */
public class RedisConstant {
    /**
     * shiro-redis的session对象前缀
     */
    public static final String SHIRO_REDIS_SESSION_PRE = "rms_shiro_session:";

    /**
     * 存放uid的对象前缀
     */
    public static final String SHIRO_SESSION_PRE = "rms_shiro_sessionid:";

    /**
     * 存放uid当前状态状态的前缀 uid
     */
    public static final String UID_PRE = "rms_uid:";

    /**
     * 存放用户信息uid
     */
    public static final String USER_PRE="rms_user:";

    /**
     * 存放用户权限的前缀
     */
    public static final String PERMISSION_PRE = "rms_permission:";

    /**
     * 角色中的权限
     */
    public static final String ROLE_PRE = "rms_role:";
    /**
     * 字典缓存前缀
     */
    public static final String DICT_PRE = "rms_dict:";

    /**
     * 组织机构缓存前缀
     */
    public static final String ORG_PRE="rms_org:";

    /**
     * 系统资源缓存前缀
     */
    public static final String RES_PRE="rms_res:";

    /**
     * 消息缓存前缀
     */
    public static final String MESSAGE_PRE = "rms_message:";

    public static final String  BLACK_PRE = "rms_black:";

    public static final String  ORG_CATEGORY_PRE = "rms_org_category:";

    /**
     * 组装key
     * @param pre 前缀
     * @param after 后缀
     * @return key
     */
    public static final String getKey(String pre,String after){
        return pre+after;
    }
}
