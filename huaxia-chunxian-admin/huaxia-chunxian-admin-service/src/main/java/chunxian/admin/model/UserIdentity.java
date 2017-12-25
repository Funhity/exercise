/**
 * @项目名称:
 * @文件名称: UserIdentity 版本号：1.0
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
 * @Title: UserIdentity
 * @Description: UserIdentity
 * @author
 */
public class UserIdentity extends BaseEntity {
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
     * 身份证号
     *
     * @mbg.generated
     */
    private String idCard;

    /**
     * 真实姓名
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 性别 1男 2女
     *
     * @mbg.generated
     */
    private String sex;

    /**
     * 身份证正面照影像平台id
     *
     * @mbg.generated
     */
    private String frontPhotoId;

    /**
     * 身份证反面照影像平台id
     *
     * @mbg.generated
     */
    private String backPhotoId;

    /**
     * 大头照影像平台id
     *
     * @mbg.generated
     */
    private String facePhotoId;

    /**
     * 人脸照片1影像平台id
     *
     * @mbg.generated
     */
    private String facePhotoId1;

    /**
     * 人脸照片2影像平台id
     *
     * @mbg.generated
     */
    private String facePhotoId2;

    /**
     * 人脸照片3影像平台id
     *
     * @mbg.generated
     */
    private String facePhotoId3;

    /**
     * 人脸照片4影像平台id
     *
     * @mbg.generated
     */
    private String facePhotoId4;

    /**
     * 影像平台临时案卷编号
     *
     * @mbg.generated
     */
    private String archivesTempId;

    /**
     * 有效期起始日期
     *
     * @mbg.generated
     */
    private Date idCardStartTime;

    /**
     * 有效期结束日期
     *
     * @mbg.generated
     */
    private String idCardEndTime;

    /**
     * 身份证住址
     *
     * @mbg.generated
     */
    private String liveAddress;

    /**
     * 身份证出生日期
     *
     * @mbg.generated
     */
    private Date birthday;

    /**
     * 身份证签发机关
     *
     * @mbg.generated
     */
    private String issuedDep;

    /**
     * 人脸照采集时间
     *
     * @mbg.generated
     */
    private Date facePhotoCollectTime;

    /**
     * 是否是黑名单用户 0不是 1是
     *
     * @mbg.generated
     */
    private Integer blacklist;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFrontPhotoId() {
        return frontPhotoId;
    }

    public void setFrontPhotoId(String frontPhotoId) {
        this.frontPhotoId = frontPhotoId;
    }

    public String getBackPhotoId() {
        return backPhotoId;
    }

    public void setBackPhotoId(String backPhotoId) {
        this.backPhotoId = backPhotoId;
    }

    public String getFacePhotoId() {
        return facePhotoId;
    }

    public void setFacePhotoId(String facePhotoId) {
        this.facePhotoId = facePhotoId;
    }

    public String getFacePhotoId1() {
        return facePhotoId1;
    }

    public void setFacePhotoId1(String facePhotoId1) {
        this.facePhotoId1 = facePhotoId1;
    }

    public String getFacePhotoId2() {
        return facePhotoId2;
    }

    public void setFacePhotoId2(String facePhotoId2) {
        this.facePhotoId2 = facePhotoId2;
    }

    public String getFacePhotoId3() {
        return facePhotoId3;
    }

    public void setFacePhotoId3(String facePhotoId3) {
        this.facePhotoId3 = facePhotoId3;
    }

    public String getFacePhotoId4() {
        return facePhotoId4;
    }

    public void setFacePhotoId4(String facePhotoId4) {
        this.facePhotoId4 = facePhotoId4;
    }

    public String getArchivesTempId() {
        return archivesTempId;
    }

    public void setArchivesTempId(String archivesTempId) {
        this.archivesTempId = archivesTempId;
    }

    public Date getIdCardStartTime() {
        return idCardStartTime;
    }

    public void setIdCardStartTime(Date idCardStartTime) {
        this.idCardStartTime = idCardStartTime;
    }

    public String getIdCardEndTime() {
        return idCardEndTime;
    }

    public void setIdCardEndTime(String idCardEndTime) {
        this.idCardEndTime = idCardEndTime;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIssuedDep() {
        return issuedDep;
    }

    public void setIssuedDep(String issuedDep) {
        this.issuedDep = issuedDep;
    }

    public Date getFacePhotoCollectTime() {
        return facePhotoCollectTime;
    }

    public void setFacePhotoCollectTime(Date facePhotoCollectTime) {
        this.facePhotoCollectTime = facePhotoCollectTime;
    }

    public Integer getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Integer blacklist) {
        this.blacklist = blacklist;
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