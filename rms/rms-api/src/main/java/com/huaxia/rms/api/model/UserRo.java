package com.huaxia.rms.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserRo implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private String code;

    /**
     * 登录名
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    private String realname;

    /**
     * 用户密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 密码加盐
     *
     * @mbg.generated
     */
    private String passwordSalt;

    /**
     * 手机
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * 身份证号码
     *
     * @mbg.generated
     */
    private String idcard;

    /**
     * 性别,默认0男，1女
     *
     * @mbg.generated
     */
    private Integer sex;

    /**
     * 年龄
     *
     * @mbg.generated
     */
    private Integer age;

    /**
     * 出生日期
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 用户类型
     *
     * @mbg.generated
     */
    private Integer userType;

    /**
     * 电子邮件
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 用户来源
     *
     * @mbg.generated
     */
    private String userFrom;

    /**
     * 部门代码
     *
     * @mbg.generated
     */
    private String orgCode;

    /**
     * 部门名称
     *
     * @mbg.generated
     */
    private String orgName;

    /**
     * 在线状态，默认0不在线，1在线
     *
     * @mbg.generated
     */
    private Integer userOnline;

    /**
     * 第一次访问时间
     *
     * @mbg.generated
     */
    private Date firstVisit;

    /**
     * 上一次访问时间
     *
     * @mbg.generated
     */
    private Date previousVisit;

    /**
     * 最后访问时间
     *
     * @mbg.generated
     */
    private Date lastVisit;

    /**
     * 登录次数
     *
     * @mbg.generated
     */
    private Integer loginCount;

    /**
     * 审核状态
     *
     * @mbg.generated
     */
    private Integer auditStatus;

    /**
     * 是否显示，默认1显示，0不显示
     *
     * @mbg.generated
     */
    private Integer isVisible;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sortCode;

    /**
     * 有效，默认1有效，0无效
     *
     * @mbg.generated
     */
    private Integer enabled;

    /**
     * 创建日期
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(Integer userOnline) {
        this.userOnline = userOnline;
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Date getPreviousVisit() {
        return previousVisit;
    }

    public void setPreviousVisit(Date previousVisit) {
        this.previousVisit = previousVisit;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}