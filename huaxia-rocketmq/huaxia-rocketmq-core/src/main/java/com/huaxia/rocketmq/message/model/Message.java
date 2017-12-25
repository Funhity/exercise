/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: Message 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.message.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import java.io.Serializable;

/**
 * 说明: 消息基础类
 * @author guojiandong
 * @version 1.0.0
 */
public class Message implements Serializable {

    /**
     * 生产者
     */
    private String producer;
    /**
     * 消费者
     */
    private String consumer;
    /**
     * 消息标签
     */
    private String tag;
    /**
     * 消息唯一业务主键
     */
    private String key;

    @JsonTypeInfo(use= JsonTypeInfo.Id.CLASS,include= As.PROPERTY,property="@class")
    private Object body;


    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}


