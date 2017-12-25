package com.huaxia.sso.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Configuration {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static Map<String, Enterprise> mappings = new HashMap();

    public static Enterprise getEnterprise(String filename) {
        if (null == mappings.get(filename)) {
            // 出发 加载 配置文件
            getProperty(filename, "id");
        }
        return mappings.get(filename);
    }

    public static Enterprise get() {
        return getEnterprise("system");
    }

    /**
     * 获取 classpath内的 properties 文件
     *
     * @param filepath
     * @return
     */
    public static Properties getProperties(String filepath) {
        Properties properties = new Properties();
        InputStream inputStream = Configuration.class.getResourceAsStream(filepath);
        if (null != inputStream) {
            try {
                properties.load(inputStream);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * 获取 system.properties 文件中的 配置信息
     *
     * @param property
     * @return
     */
    public static String getProperty(String property) {
        return getProperty("system", property);
    }

    /**
     * 获取 指定 properties 文件的 配置信息
     *
     * @param filename
     * @param property
     * @return
     */
    public static String getProperty(String filename, String property) {
        try {
            if (mappings.containsKey(filename)) {
                return mappings.get(filename).getString(property);
            } else {
                if (-1 == filename.indexOf("properties")) {
                    Properties properties = getProperties("/" + filename + ".properties");
                    mappings.put(filename, new Enterprise(properties));
                    return properties.getProperty(property);
                } else {
                    Properties properties = getProperties(filename);
                    mappings.put(filename, new Enterprise(properties));
                    return properties.getProperty(property);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getProperty("memcached", "memcached.server1.host"));
    }
}
