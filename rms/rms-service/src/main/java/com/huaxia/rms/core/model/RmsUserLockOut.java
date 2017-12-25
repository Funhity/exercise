package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;

public class RmsUserLockOut implements Serializable {
    /**
     * 主键ID
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
     * 用户名称
     *
     * @mbg.generated
     */
    private String username;

    /**
     * IP地址
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * 登录失败次数
     *
     * @mbg.generated
     */
    private Integer failedNum;

    /**
     * 1.密码登录失败2.验证码登录失败
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 登录失败来源class类
     *
     * @mbg.generated
     */
    private String failedSource;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(Integer failedNum) {
        this.failedNum = failedNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailedSource() {
        return failedSource;
    }

    public void setFailedSource(String failedSource) {
        this.failedSource = failedSource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}