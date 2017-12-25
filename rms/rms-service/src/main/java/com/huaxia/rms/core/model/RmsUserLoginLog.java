package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;

public class RmsUserLoginLog implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户id
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
     * 用户ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * http user_agent 头
     *
     * @mbg.generated
     */
    private String userAgent;

    /**
     * 登录系统来源
     *
     * @mbg.generated
     */
    private String sysid;

    /**
     * 系统来源
     *
     * @mbg.generated
     */
    private String source;

    /**
     * 应用版本
     *
     * @mbg.generated
     */
    private String version;

    /**
     * 类型 0其它登录;1标准平台登陆
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * 方式 0：注册码登录 1：用户密码登录
     *
     * @mbg.generated
     */
    private Integer method;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 修改日期
     *
     * @mbg.generated
     */
    private Date updateTime;

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

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}