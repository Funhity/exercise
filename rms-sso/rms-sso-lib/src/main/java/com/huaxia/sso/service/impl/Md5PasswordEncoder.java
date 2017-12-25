package com.huaxia.sso.service.impl;

import com.huaxia.sso.utils.Md5Utility;
import com.huaxia.sso.service.PasswordEncoder;
import org.apache.commons.lang.StringUtils;

/**
 * LoginErrorCountAuthenticationHandlerImpl
 * 作者-Liu zhilai
 * @description 密码验证/生成密码
 */
public class Md5PasswordEncoder implements PasswordEncoder {
    public String encodePassword(String password) {
        return Md5Utility.getMD5String(password);
    }

    public String encodeSaltPassword(String password, String username) {
        return Md5Utility.md5SaltString(password, username);
    }

    public boolean isPasswordValid(String password, String inputPassword, String username) {
        //兼容华夏信财所有项目密码，先验证加盐密码，如果验证失败，再验证非加盐密码
        if(StringUtils.equalsIgnoreCase(password,encodeSaltPassword(inputPassword, username))) return true;
        return StringUtils.equalsIgnoreCase(password, encodePassword(inputPassword));
    }

}
