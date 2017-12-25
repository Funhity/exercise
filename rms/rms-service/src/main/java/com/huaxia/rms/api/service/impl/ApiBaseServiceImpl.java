package com.huaxia.rms.api.service.impl;

import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.api.service.ApiBaseService;
import com.huaxia.rms.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
@Service("apiBaseService")
public class ApiBaseServiceImpl implements ApiBaseService {

    private Logger logger = LoggerFactory.getLogger(ApiBaseServiceImpl.class);

    @Resource
    public RedisDao redisDao;

    //redis接口通用方法
    public void deleteCacheByKey(String key) {
        redisDao.delete(key);
    }

    public <T> boolean addCacheByKey(String key, T object) {
        return redisDao.add(key, object);
    }

    public <T> boolean saveCacheByKey(String key, T object) {
        return redisDao.save(key, object);
    }

    public String getCacheByKey(String key) {
        return redisDao.get(key);
    }

    public <T> T getCacheByKey(String key, Class clazz) {
        return redisDao.get(key, clazz);
    }


}
