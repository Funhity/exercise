package com.huaxia.rms.core.service.impl;

import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.service.BaseService;
import com.huaxia.rms.query.util.QueryUtil;
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
import java.util.Set;

/**
 *
 *
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {

    private Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<Class<?>> mBaseDao;

    @Autowired
    private SpringContextUtil springContextUtil;

    @Resource
    public RedisDao redisDao;

    public List getObjectList(String method, String serviceName, Map<String, Object> map) {


        List list = new ArrayList<>();
        //获取所属的类
        try {
            //Class clazz = className.getClass();
//            Class<?> clazz = Class.forName(className);
//            Method queryMethod = clazz.getDeclaredMethod(method, Map.class);//获取到方法对象,假设方法的参数是一个int,String,method名为getAge

            //获得参数Object
            Object[] arguments = new Object[]{map};
            /**
             * 利用spring的反射通用类
             * 执行service方法
             */
            //logger.info("----------------getObjectList.method: " + method);
            //logger.info("----------------getObjectList.serviceName: " + serviceName);
            Object clsObject = springContextUtil.getBean(serviceName);
            Method  mh = ReflectionUtils.findMethod(clsObject.getClass(), method, Map.class);
            Object retObj = ReflectionUtils.invokeMethod(mh, clsObject, map);
            //logger.info("----------------retObj："+ retObj);
            list = (ArrayList)retObj;
            //logger.info("----------------getObjectList.list：" + list);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getObjectCount(String method, String serviceName, Map<String, Object> map) {
        int count = 0;
        /**
         * 利用spring的反射通用类
         * 执行service方法
         */
        //logger.info("----------------getObjectCount.method: " + method);
        //logger.info("----------------getObjectCount.serviceName: " + serviceName);

        try {
            Object clsObject = springContextUtil.getBean(serviceName);
            Method  mh = ReflectionUtils.findMethod(clsObject.getClass(), method + "Count", Map.class);
            Object retObj = ReflectionUtils.invokeMethod(mh, clsObject, map);
            //logger.info("----------------retObj：" + retObj);
            count = Integer.parseInt(String.valueOf(retObj));
            //logger.info("----------------getObjectList.count：" + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }


    //redis接口通用方法
    public void deleteCacheByKey(String key) {
        redisDao.delete(key);
    }

    //模糊删除制定key
    public void deleteCacheByKeys(String key) {
        Set<String> keys = redisDao.keys(key + "*");
        redisDao.delete(keys);
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
