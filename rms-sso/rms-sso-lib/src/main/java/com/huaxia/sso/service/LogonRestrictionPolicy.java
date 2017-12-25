package com.huaxia.sso.service;


import com.huaxia.sso.model.TRMSUser;

import javax.servlet.http.HttpServletRequest;



public interface LogonRestrictionPolicy {

    /**
     * 登录之后
     *
     * @param userInfo
     * @return
     */
    void apply(HttpServletRequest request, TRMSUser userInfo, int type, boolean isLogin);

}
