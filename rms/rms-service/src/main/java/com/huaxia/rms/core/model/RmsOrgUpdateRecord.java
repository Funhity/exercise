package com.huaxia.rms.core.model;

import java.io.Serializable;
import java.util.Date;

public class RmsOrgUpdateRecord implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 编号
     *
     * @mbg.generated
     */
    private String code;

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
     * 上级编号
     *
     * @mbg.generated
     */
    private String parentCode;

    /**
     * 上级机构名称
     *
     * @mbg.generated
     */
    private String parentName;

    /**
     * 机构变更后，原来的机构编号
     *
     * @mbg.generated
     */
    private String pastCode;

    /**
     * 主负责人，对应用户表code
     *
     * @mbg.generated
     */
    private String manager;

    /**
     * 副负责人
     *
     * @mbg.generated
     */
    private String assistantManager;

    /**
     * 外线电话
     *
     * @mbg.generated
     */
    private String outerPhone;

    /**
     * 内线电话
     *
     * @mbg.generated
     */
    private String innerPhone;

    /**
     * 传真
     *
     * @mbg.generated
     */
    private String fax;

    /**
     * 地区
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 网址
     *
     * @mbg.generated
     */
    private String homepage;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sortCode;

    /**
     * 是否虚拟机构，默认0不是，1是
     *
     * @mbg.generated
     */
    private Integer isVisual;

    /**
     * 营业状态,1营业,0不营业
     *
     * @mbg.generated
     */
    private Integer isBusiness;

    /**
     * 成立时间
     *
     * @mbg.generated
     */
    private Date setupDate;

    /**
     * 推广编号
     *
     * @mbg.generated
     */
    private String promotionCode;

    /**
     * 门店加急比例
     *
     * @mbg.generated
     */
    private String urgentScale;

    /**
     * 人数上限，-1表示无上限
     *
     * @mbg.generated
     */
    private Integer maxuser;

    /**
     * 生效时间
     *
     * @mbg.generated
     */
    private Date effectiveDate;

    /**
     * 是否生效，默认0未生效，1已生效
     *
     * @mbg.generated
     */
    private Integer isEffect;

    /**
     * 机构类别
     *
     * @mbg.generated
     */
    private String orgCategory;

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

    /**
     * 系统编码，组织机构会整合各个系统，不同系统orgid是不同的
     *
     * @mbg.generated
     */
    private String syscode;

    /**
     * 兼容老系统组织机构ID
     *
     * @mbg.generated
     */
    private String orgid;

    /**
     * 兼容老系统组织机构父级ID
     *
     * @mbg.generated
     */
    private String relativeOrgid;

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

    public String getPastCode() {
        return pastCode;
    }

    public void setPastCode(String pastCode) {
        this.pastCode = pastCode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAssistantManager() {
        return assistantManager;
    }

    public void setAssistantManager(String assistantManager) {
        this.assistantManager = assistantManager;
    }

    public String getOuterPhone() {
        return outerPhone;
    }

    public void setOuterPhone(String outerPhone) {
        this.outerPhone = outerPhone;
    }

    public String getInnerPhone() {
        return innerPhone;
    }

    public void setInnerPhone(String innerPhone) {
        this.innerPhone = innerPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Integer getIsVisual() {
        return isVisual;
    }

    public void setIsVisual(Integer isVisual) {
        this.isVisual = isVisual;
    }

    public Integer getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getUrgentScale() {
        return urgentScale;
    }

    public void setUrgentScale(String urgentScale) {
        this.urgentScale = urgentScale;
    }

    public Integer getMaxuser() {
        return maxuser;
    }

    public void setMaxuser(Integer maxuser) {
        this.maxuser = maxuser;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
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

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getRelativeOrgid() {
        return relativeOrgid;
    }

    public void setRelativeOrgid(String relativeOrgid) {
        this.relativeOrgid = relativeOrgid;
    }
}