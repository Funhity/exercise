package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RmsResource implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 资源编号
     *
     * @mbg.generated
     */
    private String code;

    /**
     * 父级资源编号
     *
     * @mbg.generated
     */
    private String parentCode;

    /**
     * 父级资源名称
     *
     * @mbg.generated
     */
    private String parentName;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 简称
     *
     * @mbg.generated
     */
    private String shortName;

    /**
     * 层
     *
     * @mbg.generated
     */
    private Integer layer;

    /**
     * 类型，0系统，1目录，2菜单，3按钮
     *
     * @mbg.generated
     */
    private Integer restype;

    /**
     * 图标编号
     *
     * @mbg.generated
     */
    private String iconIndex;

    /**
     * 所属组织
     *
     * @mbg.generated
     */
    private String orgCode;

    /**
     * 所属系统
     *
     * @mbg.generated
     */
    private String sysCode;

    /**
     * 链接地址
     *
     * @mbg.generated
     */
    private String url;

    /**
     * 是否公开，默认1公开，0不公开
     *
     * @mbg.generated
     */
    private Integer isPublic;

    /**
     * 是否受控制，默认1是控制，0否
     *
     * @mbg.generated
     */
    private Integer isControl;

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

    private List<RmsRolePermissionData> rplist;

    /**
     * 是否立即生效
     */
    private Integer isEffect;


    private static final long serialVersionUID = 1L;

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getRestype() {
        return restype;
    }

    public void setRestype(Integer restype) {
        this.restype = restype;
    }

    public String getIconIndex() {
        return iconIndex;
    }

    public void setIconIndex(String iconIndex) {
        this.iconIndex = iconIndex;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getIsControl() {
        return isControl;
    }

    public void setIsControl(Integer isControl) {
        this.isControl = isControl;
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

    public List<RmsRolePermissionData> getRplist() {
        return rplist;
    }

    public void setRplist(List<RmsRolePermissionData> rplist) {
        this.rplist = rplist;
    }
}