package com.huaxia.sso.service;


import com.huaxia.sso.model.TRMSUser;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service
 * 作者-Liu zhilai
 * 说明：认证规则
 * 2016/3/3 17:44
 * 2017华夏信财-版权所有
 */
public interface AuthenticationHandler {
    /**
     * 执行认证
     *
     * @param userInfo
     * @return
     */
    void validator(TRMSUser userInfo);
}
