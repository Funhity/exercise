/**
 * @项目名称:
 * @文件名称: UserWork 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;
import java.util.Date;

/**
 * @Title: UserWork
 * @Description: UserWork
 * @author
 */
public class UserWork extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 用户主键
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * 单位名称
     *
     * @mbg.generated
     */
    private String unitName;

    /**
     * 单位性质
     *
     * @mbg.generated
     */
    private String unitNature;

    /**
     * 单位电话
     *
     * @mbg.generated
     */
    private String unitPhone;

    /**
     * 入职时间
     *
     * @mbg.generated
     */
    private Date entryDate;

    /**
     * 单位地址-省
     *
     * @mbg.generated
     */
    private String unitProvince;

    /**
     * 单位地址-市
     *
     * @mbg.generated
     */
    private String unitCity;

    /**
     * 单位地址-区
     *
     * @mbg.generated
     */
    private String unitDistinct;

    /**
     * 单位地址
     *
     * @mbg.generated
     */
    private String unitAddress;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(String unitNature) {
        this.unitNature = unitNature;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getUnitProvince() {
        return unitProvince;
    }

    public void setUnitProvince(String unitProvince) {
        this.unitProvince = unitProvince;
    }

    public String getUnitCity() {
        return unitCity;
    }

    public void setUnitCity(String unitCity) {
        this.unitCity = unitCity;
    }

    public String getUnitDistinct() {
        return unitDistinct;
    }

    public void setUnitDistinct(String unitDistinct) {
        this.unitDistinct = unitDistinct;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
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