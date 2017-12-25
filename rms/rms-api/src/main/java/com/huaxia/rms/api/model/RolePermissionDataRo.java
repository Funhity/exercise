package com.huaxia.rms.api.model;

import java.io.Serializable;
import java.util.Date;

public class RolePermissionDataRo implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 角色主键
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     * 菜单主键
     *
     * @mbg.generated
     */
    private Integer resourceId;

    /**
     * 业务键
     *
     * @mbg.generated
     */
    private String funcKey;

    /**
     * 业务值
     *
     * @mbg.generated
     */
    private String funcValue;

    /**
     * 操作类型
     *
     * @mbg.generated
     */
    private String funcOperator;

    /**
     * value字符类型
     *
     * @mbg.generated
     */
    private String classType;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sortCode;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String memo;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getFuncKey() {
        return funcKey;
    }

    public void setFuncKey(String funcKey) {
        this.funcKey = funcKey;
    }

    public String getFuncValue() {
        return funcValue;
    }

    public void setFuncValue(String funcValue) {
        this.funcValue = funcValue;
    }

    public String getFuncOperator() {
        return funcOperator;
    }

    public void setFuncOperator(String funcOperator) {
        this.funcOperator = funcOperator;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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