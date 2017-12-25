/**
 * @项目名称:
 * @文件名称: Consumer 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import java.util.Date;

/**
 * @Title: Consumer
 * @Description: Consumer
 * @author
 */
public class Consumer {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	/**
	 * 主键ID
	 *
	 * @mbg.generated
	 */

	private String id;

	/**
	 * 华夏信财用户中心userid
	 *
	 * @mbg.generated
	 */
	private String hxxcUserId;

	/**
	 * 姓名
	 *
	 * @mbg.generated
	 */
	private String realName;

	private String sex;
	/**
	 * 电话
	 *
	 * @mbg.generated
	 */
	private String phone;

	/**
	 * 用户设备类型android/ios
	 *
	 * @mbg.generated
	 */
	private String devicType;

	/**
	 * 用户来源 1:花财 2:摩尔龙
	 *
	 * @mbg.generated
	 */
	private String appType;

	/**
	 * 注册渠道
	 *
	 * @mbg.generated
	 */
	private String registerChannel;

	/**
	 * 注册机型
	 *
	 * @mbg.generated
	 */
	private String deviceModelRegister;
	/**
	 * 用户状态：1.正常（默认值） 2.禁用
	 *
	 * @mbg.generated
	 */
	private Integer status;

	private String idCard;

	private String ifReal;

	/**
	 * 创建时间
	 *
	 * @mbg.generated
	 */
	private Date createTime;

	private Date editstartdate;
	private Date editenddate;

	public Date getEditstartdate() {
		return editstartdate;
	}

	public void setEditstartdate(Date editstartdate) {
		this.editstartdate = editstartdate;
	}

	public Date getEditenddate() {
		return editenddate;
	}

	public void setEditenddate(Date editenddate) {
		this.editenddate = editenddate;
	}

	public String getHxxcUserId() {
		return hxxcUserId;
	}

	public void setHxxcUserId(String hxxcUserId) {
		this.hxxcUserId = hxxcUserId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIfReal() {
		return ifReal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIfReal(String ifReal) {
		this.ifReal = ifReal;
	}

	public String getDeviceModelRegister() {
		return deviceModelRegister;
	}

	public void setDeviceModelRegister(String deviceModelRegister) {
		this.deviceModelRegister = deviceModelRegister;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDevicType() {
		return devicType;
	}

	public void setDevicType(String devicType) {
		this.devicType = devicType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getRegisterChannel() {
		return registerChannel;
	}

	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
