package com.huaxia.rms.api.model;

import java.io.Serializable;
import java.util.Date;

public class UserDetailRo implements Serializable {
    /**
     * 编号,工号
     *
     * @mbg.generated
     */
    private Integer userId;

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
    private String joininDate;

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
     * 创建人
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     * 删除时间
     *
     * @mbg.generated
     */
    private Date deleteTime;

    /**
     * 删除人
     *
     * @mbg.generated
     */
    private String deleteUser;

    /**
     * 删除标志 0 已删除 1 有效
     *
     * @mbg.generated
     */
    private Integer deleteMark;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getJoininDate() {
        return joininDate;
    }

    public void setJoininDate(String joininDate) {
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Integer getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }
}