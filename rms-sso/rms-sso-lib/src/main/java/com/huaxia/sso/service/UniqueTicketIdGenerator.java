package com.huaxia.sso.service;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service.impl
 * 作者-Liu zhilai
 * 说明：票据生成
 * 2016/3/3 17:25
 * 2017华夏信财-版权所有
 */

public interface UniqueTicketIdGenerator {
    /**
     * 获取票据
     * @param prefix
     * @return
     */
    String getNewTickId(String prefix);
}
