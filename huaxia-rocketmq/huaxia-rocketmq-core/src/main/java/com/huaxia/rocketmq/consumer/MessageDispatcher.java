/**
 * @项目名称: huaxia-rocketmq-parent
 * @文件名称: MessageDispatcher 版本号：1.0
 * @创建日期: 2017年10月14日 19:28
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.consumer;

import com.huaxia.rocketmq.Consts;
import com.huaxia.rocketmq.exception.MQException;
import com.huaxia.rocketmq.message.model.Message;
import com.huaxia.rocketmq.message.model.Topic;
import com.huaxia.rocketmq.util.JacksonUtil;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 说明: 消息调度
 * @author guojiandong
 * @version 1.0.0
 */
public class MessageDispatcher implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MessageDispatcher.class);

    private String applicationName;
    private Map<Topic, MessageListener> messageListenerMap;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        MessageExt messageExt = list.get(0);
        logger.debug("messageExt: {}", messageExt);

        String topic = messageExt.getTopic();
        String tag = messageExt.getTags();
        String key = messageExt.getKeys();
        String message = new String(messageExt.getBody(), Charset.forName(Consts.DEFAULT_CHARSET));
        logger.info("RECEIVE_MSG: {}", message);
        try {
            MessageListener messageListener = getMessageListener(topic);
            Message messageObject = JacksonUtil.readValue(message, Message.class);
            messageObject.setConsumer(this.applicationName);
            messageListener.onMessage(messageObject.getBody());
        } catch (Exception e) {
            logger.error("onMessage error!", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private MessageListener getMessageListener(String topicName) {
        Set<Map.Entry<Topic, MessageListener>> entrySet = this.messageListenerMap.entrySet();
        Iterator<Map.Entry<Topic, MessageListener>> iter = entrySet.iterator();
        while (iter.hasNext()) {
            Map.Entry<Topic, MessageListener> entry = iter.next();
            if (entry.getKey().getName().equals(topicName)) {
                return entry.getValue();
            }
        }
        throw new MQException("No MessageListener available for Topic '" + topicName + "'!");
    }


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Map<Topic, MessageListener> getMessageListenerMap() {
        return messageListenerMap;
    }

    public void setMessageListenerMap(Map<Topic, MessageListener> messageListenerMap) {
        this.messageListenerMap = messageListenerMap;
    }

}