package com.huaxia.rms.listener;


import com.huaxia.rms.query.pojo.QueryDefinition;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目启动时扫描query/*.xml的xml文件
 * 获取查询的信息配置
 */
public class QueryListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent context) {

    }

    public void contextInitialized(ServletContextEvent context) {

        QueryDefinition.getInstance().initQuery();
    }

}
