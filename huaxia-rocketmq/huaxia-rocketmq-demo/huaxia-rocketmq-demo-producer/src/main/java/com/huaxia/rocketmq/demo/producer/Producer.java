package com.huaxia.rocketmq.demo.producer;

import com.huaxia.rocketmq.demo.model.Data;
import com.huaxia.rocketmq.message.model.Message;
import com.huaxia.rocketmq.producer.DefaultProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${mq.topic.one}")
    private String oneTopic;

    @Value("${mq.topic.two}")
    private String twoTopic;

    @Autowired
    private DefaultProducer producer;

    public void send(Data data) {
        {
            Message message = new Message();
            //把唯一业务主键放入key中，便于控制台跟踪。
            message.setKey(data.getNo());
            message.setBody(data);
            producer.send(oneTopic, message);
        }
        {
            Message message = new Message();
            //把唯一业务主键放入key中，便于控制台跟踪。
            message.setKey(data.getNo());
            message.setBody(data);
            producer.send(twoTopic, message);
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();

        Producer producer = (Producer) context.getBean("producer");
        for (int i = 0; i < 4; i++) {
            Data data = new Data();
            data.setContent("send data by rocketmq.");
            producer.send(data);
        }

        context.destroy();
    }
}
