/**
 * @项目名称:
 * @文件名称: UserPersonal 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: UserPersonal
 * @Description: UserPersonal
 * @author
 */
public class UserPersonal extends BaseEntity {
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
     * 学历
     *
     * @mbg.generated
     */
    private String education;

    /**
     * 婚姻状况
     *
     * @mbg.generated
     */
    private String marriage;

    /**
     * 是否本地户籍 0非本地籍 1本地籍
     *
     * @mbg.generated
     */
    private String isLocal;

    /**
     * 来本市日期
     *
     * @mbg.generated
     */
    private Date moveInDate;

    /**
     * 居住类型
     *
     * @mbg.generated
     */
    private String liveType;

    /**
     * 租金
     *
     * @mbg.generated
     */
    private BigDecimal rent;

    /**
     * 现居住地址-省
     *
     * @mbg.generated
     */
    private String liveProvince;

    /**
     * 现居住地址-市
     *
     * @mbg.generated
     */
    private String liveCity;

    /**
     * 现居住地址-区
     *
     * @mbg.generated
     */
    private String liveDistinct;

    /**
     * 现居住地址-详细
     *
     * @mbg.generated
     */
    private String liveAddress;

    /**
     * 现居住起始日期
     *
     * @mbg.generated
     */
    private Date liveDate;

    /**
     * 车辆情况
     *
     * @mbg.generated
     */
    private String carState;

    /**
     * 购买保险情况
     *
     * @mbg.generated
     */
    private String carSafeState;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public String getLiveType() {
        return liveType;
    }

    public void setLiveType(String liveType) {
        this.liveType = liveType;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getLiveProvince() {
        return liveProvince;
    }

    public void setLiveProvince(String liveProvince) {
        this.liveProvince = liveProvince;
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity;
    }

    public String getLiveDistinct() {
        return liveDistinct;
    }

    public void setLiveDistinct(String liveDistinct) {
        this.liveDistinct = liveDistinct;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Date getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(Date liveDate) {
        this.liveDate = liveDate;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getCarSafeState() {
        return carSafeState;
    }

    public void setCarSafeState(String carSafeState) {
        this.carSafeState = carSafeState;
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