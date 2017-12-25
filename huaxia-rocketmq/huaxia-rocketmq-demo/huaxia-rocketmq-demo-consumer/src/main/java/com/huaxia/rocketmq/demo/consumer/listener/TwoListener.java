package com.huaxia.rocketmq.demo.consumer.listener;

import com.huaxia.rocketmq.consumer.MessageListener;
import com.huaxia.rocketmq.demo.model.Data;

import java.text.SimpleDateFormat;

public class TwoListener implements MessageListener {

    @Override
    public void onMessage(Object message) {
        Data data = (Data) message;
        System.out.println("no: " + data.getNo());
        System.out.println("content: " + data.getContent());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(data.getCreateTime());
        System.out.println("createTime: " + createTime);
    }
}
