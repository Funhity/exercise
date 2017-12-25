package com.huaxia.sso.service.impl;

import com.huaxia.sso.service.UniqueTicketIdGenerator;

import java.util.UUID;


/**
 * 华夏信财-OCC
 * com.huaxia.sso.service.impl
 * 作者-Liu zhilai
 * 说明：票据生成 默认实现
 * 2016/3/3 17:47
 * 2017华夏信财-版权所有
 */
public class DefaultUniqueTicketIdGeneratorImpl implements UniqueTicketIdGenerator {
    public String getNewTickId(String prefix) {
        return prefix + UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
