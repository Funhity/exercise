package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;

public class RmsUserOperationLog implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户主键
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 操作人
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 操作描述
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 操作方法
     *
     * @mbg.generated
     */
    private String method;

    /**
     * IP地址
     *
     * @mbg.generated
     */
    private String ipAddress;

    /**
     * 创建日期
     *
     * @mbg.generated
     */
    private Date createdTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}