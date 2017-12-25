/**
 * @项目名称:
 * @文件名称: Notice 版本号：1.0
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
 * @Title: Notice
 * @Description: Notice
 * @author
 */
public class Notice extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String content;

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

    /**
     * 是否允许 0不允许分享 1允许分享
     *
     * @mbg.generated
     */
    private String shareSwitch;

    /**
     * 分享标题
     *
     * @mbg.generated
     */
    private String shareTitle;

    /**
     * 分享内容
     *
     * @mbg.generated
     */
    private String shareContent;

    /**
     * 分享图标地址
     *
     * @mbg.generated
     */
    private String shareIcon;

    /**
     * 分享链接
     *
     * @mbg.generated
     */
    private String shareUrl;

    /**
     * 分享界面提示文字
     *
     * @mbg.generated
     */
    private String shareTooltip;

    /**
     * 分享界面提示显示时间
     *
     * @mbg.generated
     */
    private Integer shareTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    private String updateUser;

    /**
     * 公告发送时间
     *
     * @mbg.generated
     */
    private Date sendTime;

    /**
     * 公告发送状态 0待发送 1已发送
     *
     * @mbg.generated
     */
    private String sendStatus;

    /**
     * 删除时间
     *
     * @mbg.generated
     */
    private Date deleteTime;
    
    /**
     * 0 及时发送
     * 1 固定时间
     */
    private String sendType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getShareSwitch() {
        return shareSwitch;
    }

    public void setShareSwitch(String shareSwitch) {
        this.shareSwitch = shareSwitch;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTooltip() {
        return shareTooltip;
    }

    public void setShareTooltip(String shareTooltip) {
        this.shareTooltip = shareTooltip;
    }

    public Integer getShareTime() {
        return shareTime;
    }

    public void setShareTime(Integer shareTime) {
        this.shareTime = shareTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
}