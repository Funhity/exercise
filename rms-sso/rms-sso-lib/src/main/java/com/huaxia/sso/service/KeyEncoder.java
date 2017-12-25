package com.huaxia.sso.service;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.service
 * 作者-Liu zhilai
 * 说明：生成 Redis 使用的 KEY
 * 2016/3/3 17:44
 * 2017华夏信财-版权所有
 */
public interface KeyEncoder {

    String generator(int id);

}
