/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: DefaultConsumer 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.consumer;

import com.huaxia.rocketmq.Consts;
import com.huaxia.rocketmq.message.model.Topic;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 说明: rocketmq消费者
 * @author guojiandong
 * @version 1.0.0
 */
public class DefaultConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultConsumer.class);

    private DefaultMQPushConsumer consumer;
    private String consumerGroup;

    private String namesrvAddr;
    private String applicationName;
    private MessageDispatcher messageDispatcher;

    /**
     * Spring bean init-method
     * @throws MQClientException
     */
    @PostConstruct
    public void init() throws MQClientException {
        if (null == namesrvAddr || null == applicationName || null == messageDispatcher) {
            throw new RuntimeException("properties not set");
        }

        this.consumerGroup = this.applicationName + Consts.MQ_GROUP_NAME_SUFFIX;
        consumer = new DefaultMQPushConsumer(this.consumerGroup);
        consumer.setNamesrvAddr(this.namesrvAddr);

        Set<Topic> topicSet = messageDispatcher.getMessageListenerMap().keySet();
        this.subscribe(topicSet);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        this.messageDispatcher.setApplicationName(this.applicationName);
        consumer.registerMessageListener(this.messageDispatcher);
        consumer.start();

        logger.info("mqConsumer '{}' start!", this.consumerGroup);
    }

    private void subscribe(Set<Topic> set) throws MQClientException {
        Iterator<Topic> iter = set.iterator();
        while (iter.hasNext()) {
            Topic topic = iter.next();
            this.consumer.subscribe(topic.getName(), topic.getSubExpression());
        }
    }

    /**
     * Spring bean destroy-method
     */
    @PreDestroy
    public void destroy() {
        logger.info("mqConsumer '{}' shutdown!", this.consumerGroup);
        consumer.shutdown();
        logger.info("mqConsumer '{}' shutdown success!", this.consumerGroup);
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }
}

