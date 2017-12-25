package com.huaxia.sso.common.utility;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Date;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.common.utility
 * 作者-Liu zhilai
 * 说明：
 * 2016/3/8 17:35
 * 2017华夏信财-版权所有
 */
public class Utils {

    // 4位数短信验证码
    public static Integer getValidateCode() {
        return RandomUtils.nextInt(9000) + 1000;
    }

    public static String getDate() {
        return formateDate(new Date(), "yyyy-MM-dd");
    }

    public static String formateDate(Date date, String pattern) {
        return org.apache.commons.lang.time.DateFormatUtils.format(date, pattern);
    }
}
