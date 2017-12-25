package com.huaxia.sso.utils;

/**
 * Created by Administrator on 2015/12/10.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    public static boolean isEmpty(Object obj) {
        if(obj == null) return true;
        if(org.springframework.util.StringUtils.isEmpty(obj)) return true;
        return false;
    }

    public static boolean isBlank(Object obj) {
        return isEmpty(obj);
    }

    public static boolean isNotEmpty(Object obj) {
        if(isEmpty(obj)) return false;
        return true;
    }

    public static boolean isNotBlank(Object obj) {
       return isNotEmpty(obj);
    }
}
