package com.huaxia.sso.service;

/**
 * @description 密码加密 验证 规则
 */

public interface PasswordEncoder {

    /**
     * 加密，明码转暗码
     *
     * @param password
     * @return
     */
    String encodePassword(String password);

    /**
     * 密码验证
     *
     * @param password
     * @param inputPassword
     * @return
     */
    boolean isPasswordValid(String password, String inputPassword, String username);
}
