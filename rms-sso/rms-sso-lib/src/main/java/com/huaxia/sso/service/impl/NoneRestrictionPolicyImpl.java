package com.huaxia.sso.service.impl;

import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.LogonRestrictionPolicy;

import javax.servlet.http.HttpServletRequest;


/**
 * NoneRestrictionPolicyImpl
 * 作者-Liu zhilai
 * @description 空实现
 */
public class NoneRestrictionPolicyImpl implements LogonRestrictionPolicy {
    public void apply(HttpServletRequest request, TRMSUser userInfo, int type, boolean isLogin) {
    }
}
