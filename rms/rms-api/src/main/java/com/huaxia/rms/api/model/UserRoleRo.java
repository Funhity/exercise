package com.huaxia.rms.api.model;

import java.io.Serializable;
import java.util.Date;

public class UserRoleRo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 用户主键
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 角色主键
     *
     * @mbg.generated
     */
    private Integer roleId;

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

    private RoleRo role;

    private UserRo user;


    public UserRo getUser() {
        return user;
    }

    public void setUser(UserRo user) {
        this.user = user;
    }

    public RoleRo getRole() {
        return role;
    }

    public void setRole(RoleRo role) {
        this.role = role;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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