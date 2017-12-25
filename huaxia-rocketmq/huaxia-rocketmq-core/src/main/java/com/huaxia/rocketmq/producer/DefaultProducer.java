/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: DefaultProducer 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.producer;

import com.huaxia.rocketmq.Consts;
import com.huaxia.rocketmq.util.JacksonUtil;
import com.huaxia.rocketmq.exception.MQException;
import com.huaxia.rocketmq.message.model.Message;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 说明: rocketmq默认生产者
 * @author guojiandong
 * @version 1.0.0
 */
public class DefaultProducer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProducer.class);

    private DefaultMQProducer producer;
    private final String producerGroup;

    private final String namesrvAddr;
    private final String applicationName;

    public DefaultProducer(String namesrvAddr, String applicationName) {
        this.namesrvAddr = namesrvAddr;
        this.applicationName = applicationName;
        this.producerGroup = this.applicationName + Consts.MQ_GROUP_NAME_SUFFIX;
    }

    @PostConstruct
    public void init() throws MQClientException {
        if (null == namesrvAddr || null == producerGroup ) {
            throw new RuntimeException("properties not set");
        }

        producer = new DefaultMQProducer(this.producerGroup);
        producer.setNamesrvAddr(namesrvAddr);

        producer.start();
        logger.info("mqProducer '{}' started!", this.producerGroup);
    }

    @PreDestroy
    public void destroy() {
        logger.info("mqProducer '{}' shutdown!", this.producerGroup);
        producer.shutdown();
        logger.info("mqProducer '{}' shutdown success!", this.producerGroup);
    }

    public void send(String topic, Message message) {
        try {
            message.setProducer(this.applicationName);
            String messageBody = JacksonUtil.writeValueAsString(message);
            logger.info("SEND_MSG: {}", messageBody);
            org.apache.rocketmq.common.message.Message rocketmqMessage = new org.apache.rocketmq.common.message.Message(topic,
                    message.getTag(),
                    message.getKey(),
                    messageBody.getBytes(Consts.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(rocketmqMessage);
            logger.debug(sendResult.toString());
        } catch (Exception e) {
            logger.error("send message error!", e);
            throw new MQException(e);
        }
    }
}

