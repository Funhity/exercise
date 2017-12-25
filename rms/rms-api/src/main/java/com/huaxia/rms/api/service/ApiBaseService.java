package com.huaxia.rms.api.service;

import java.util.List;
import java.util.Map;


public interface ApiBaseService {

    /**
     * 根据key删除缓存中的数据
     *
     * @param key 键
     */
    void deleteCacheByKey(String key);

    /**
     * 新增缓存
     *
     * @param key    键
     * @param object 对象值
     * @param <T>    数据类型
     * @return
     */
    <T> boolean addCacheByKey(String key, T object);

    /**
     * 新增或更新缓存，有则更新，没有则新增
     *
     * @param key    键
     * @param object 对象值
     * @param <T>    数据类型
     * @return
     */
    <T> boolean saveCacheByKey(String key, T object);

    /**
     * 根据key获取缓存数据
     *
     * @param key 键
     * @return
     */
    String getCacheByKey(String key);

    /**
     * 根据key获取缓存数据
     *
     * @param key   键
     * @param clazz 对象类型
     * @param <T>
     * @return
     */
    <T> T getCacheByKey(String key, Class clazz);
}
