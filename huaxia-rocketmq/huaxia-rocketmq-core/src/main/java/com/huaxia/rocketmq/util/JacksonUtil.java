/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: JacksonUtil 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明: Jackson工具类
 * @author guojiandong
 * @version 1.0.0
 */
public class JacksonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    static{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String writeValueAsString(Object obj) throws IOException {
		return getInstance().writeValueAsString(obj);
    }

    public static <T> T readValue(String jsonStr, Class<T> clazz) throws IOException {
		return getInstance().readValue(jsonStr, clazz);
    }

    public static <T> T readValueRefer(String jsonStr, Class<T> clazz) throws IOException {
		return getInstance().readValue(jsonStr, new TypeReference<T>() { });
    }

    public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", "111");
		map.put("bbb", "222");
		String json = writeValueAsString(map);
		System.out.println(json);
		System.out.println(readValue(json, Map.class));
	}
}
