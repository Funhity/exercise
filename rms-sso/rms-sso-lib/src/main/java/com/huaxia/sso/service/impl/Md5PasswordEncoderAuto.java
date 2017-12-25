package com.huaxia.sso.service.impl;

import com.huaxia.sso.utils.Md5Utility;
import com.huaxia.sso.service.PasswordEncoder;
import org.apache.commons.lang.StringUtils;

/**
 * LoginErrorCountAuthenticationHandlerImpl
 * 作者-Liu zhilai
 * @description 密码验证/生成密码
 */
public class Md5PasswordEncoderAuto implements PasswordEncoder {
    public String encodePassword(String password) {
        return Md5Utility.getMD5String(password);
    }

    public String encodeSaltPassword(String password, String username) {
        return Md5Utility.md5SaltString(password, username);
    }

    public boolean isPasswordValid(String password, String inputPassword, String username) {
        return StringUtils.equalsIgnoreCase(password, inputPassword);
    }
}
