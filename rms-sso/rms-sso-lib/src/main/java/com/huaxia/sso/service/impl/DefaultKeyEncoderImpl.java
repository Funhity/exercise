package com.huaxia.sso.service.impl;

import com.huaxia.sso.service.KeyEncoder;

import java.util.UUID;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service.impl
 * 作者-Liu zhilai
 * 说明：生成key的默认实现
 * 2016/3/3 17:47
 * 2017华夏信财-版权所有
 */
public class DefaultKeyEncoderImpl implements KeyEncoder {

    public String generator(int id) {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
