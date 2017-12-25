/**
 * @项目名称:
 * @文件名称: UserMessage 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import java.util.Date;

import com.huaxia.common.core.common.BaseEntity;

/**
 * @Title: UserMessage
 * @Description: UserMessage
 * @author
 */
public class UserMessage extends BaseEntity{
	private static final long serialVersionUID = 2663195336448819038L;

	/**
     * 消息ID
     *
     * @mbg.generated
     */
    private Integer id;
    /**
     * 真实姓名
     *
     * @mbg.generated
     */
    private String name;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 电话
     *
     * @mbg.generated
     */
    private String phone;
    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;
    /**
     * 状态:0.未读1.已读
     *
     * @mbg.generated
     */
    private Integer state;
    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String createUser;
    /**
     * 编辑人
     *
     * @mbg.generated
     */
    private String updateUser;
    /**
     * 编辑时间
     *
     * @mbg.generated
     */
    private Date updateTime;
    /**
     * 消息发送时间
     *
     * @mbg.generated
     */
    private Date sendTime;
    /**
     * 注册渠道
     *
     * @mbg.generated
     */
    private String registerChannel;
    /**
     * 注册时间
     *
     * @mbg.generated
     */
    private Date createTime;
    /**
     * 内容
     */
    private String content;
    /**
     * 发送状态
     */
    private String sendStatus;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getRegisterChannel() {
		return registerChannel;
	}
	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
}
