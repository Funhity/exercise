package com.huaxia.rocketmq.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huaxia.rocketmq.message.model.Message;

import java.util.Date;
import java.util.UUID;

public class Data {

    private String no = UUID.randomUUID().toString();
    private String content;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
