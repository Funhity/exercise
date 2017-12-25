package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;

public class RmsUserUpdateRecord implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 编号,工号
     *
     * @mbg.generated
     */
    private Integer userId;

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
    private Date birthday;

    /**
     * 用户类型，默认1正式员工，0非正式
     *
     * @mbg.generated
     */
    private Integer userType;

    /**
     * 账户状态，默认1正常，0关闭，2锁定
     *
     * @mbg.generated
     */
    private Integer accountStatus;

    /**
     * 员工状态，默认1在职，0离职，2休假
     *
     * @mbg.generated
     */
    private Integer empStatus;

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
     * 上级理财师，对应用户表ID
     *
     * @mbg.generated
     */
    private Integer financialPlanner;

    /**
     * 上级理财师，对应用户表name
     *
     * @mbg.generated
     */
    private String fplannerName;

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
     * 用户标签，可多个标签，逗号分隔
     *
     * @mbg.generated
     */
    private String label;

    /**
     * 有效，默认1有效，0无效
     *
     * @mbg.generated
     */
    private Integer enabled;

    /**
     * 用户角色
     *
     * @mbg.generated
     */
    private String roleNames;

    /**
     * 工作组主键
     *
     * @mbg.generated
     */
    private Integer workGroupId;

    /**
     * 工作组名称
     *
     * @mbg.generated
     */
    private String workGroupName;

    /**
     * 职位代码
     *
     * @mbg.generated
     */
    private Integer dutyId;

    /**
     * 职位名称
     *
     * @mbg.generated
     */
    private String dutyName;

    /**
     * 银行卡号
     *
     * @mbg.generated
     */
    private String bankCode;

    /**
     * 电话
     *
     * @mbg.generated
     */
    private String telephone;

    /**
     * 办公电话
     *
     * @mbg.generated
     */
    private String officePhone;

    /**
     * 办公地址
     *
     * @mbg.generated
     */
    private String officeAddress;

    /**
     * 办公传真
     *
     * @mbg.generated
     */
    private String officeFax;

    /**
     * 毕业院校
     *
     * @mbg.generated
     */
    private String school;

    /**
     * 最高学历
     *
     * @mbg.generated
     */
    private String education;

    /**
     * 专业
     *
     * @mbg.generated
     */
    private String major;

    /**
     * 工作时间
     *
     * @mbg.generated
     */
    private String workingDate;

    /**
     * 加入本单位时间
     *
     * @mbg.generated
     */
    private Date joininDate;

    /**
     * 家庭住址
     *
     * @mbg.generated
     */
    private String homeAddress;

    /**
     * 籍贯
     *
     * @mbg.generated
     */
    private String nativePlace;

    /**
     * 政治面貌
     *
     * @mbg.generated
     */
    private String party;

    /**
     * 国籍
     *
     * @mbg.generated
     */
    private String nation;

    /**
     * 民族
     *
     * @mbg.generated
     */
    private String nationality;

    /**
     * 是否离职，默认0否
     *
     * @mbg.generated
     */
    private Integer isDimission;

    /**
     * 离职日期
     *
     * @mbg.generated
     */
    private Date dimissionDate;

    /**
     * 离职原因
     *
     * @mbg.generated
     */
    private String dimissionCause;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String memo;

    /**
     * 生效时间
     *
     * @mbg.generated
     */
    private Date effectiveDate;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;


    private Integer updateUserid;
    /**
     * 更新人
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     * 创建日期
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
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

    public Integer getFinancialPlanner() {
        return financialPlanner;
    }

    public void setFinancialPlanner(Integer financialPlanner) {
        this.financialPlanner = financialPlanner;
    }

    public String getFplannerName() {
        return fplannerName;
    }

    public void setFplannerName(String fplannerName) {
        this.fplannerName = fplannerName;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public Integer getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(Integer workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeFax() {
        return officeFax;
    }

    public void setOfficeFax(String officeFax) {
        this.officeFax = officeFax;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public Date getJoininDate() {
        return joininDate;
    }

    public void setJoininDate(Date joininDate) {
        this.joininDate = joininDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getIsDimission() {
        return isDimission;
    }

    public void setIsDimission(Integer isDimission) {
        this.isDimission = isDimission;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getDimissionCause() {
        return dimissionCause;
    }

    public void setDimissionCause(String dimissionCause) {
        this.dimissionCause = dimissionCause;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}