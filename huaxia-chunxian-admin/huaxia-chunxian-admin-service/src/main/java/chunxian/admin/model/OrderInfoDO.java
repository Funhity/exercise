package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: OrderInfoDO
 * @Description: OrderInfoDO
 * @author zhuqingyang
 */
public class OrderInfoDO extends BaseEntity {

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
	 * 申请产品编号
	 *
	 * @mbg.generated
	 */
	private String productNo;

	/**
	 * 姓名
	 *
	 * @mbg.generated
	 */
	private String realName;

	/**
	 * 订单编号
	 *
	 * @mbg.generated
	 */
	private String orderNo;

	/**
	 * 申请金额
	 *
	 * @mbg.generated
	 */
	private BigDecimal loanAmount;

	/**
	 * 申请期限
	 *
	 * @mbg.generated
	 */
	private Integer loanTerm;

	private String phone;

	/**
	 * 注册渠道
	 *
	 * @mbg.generated
	 */
	private String registerChannel;

	/**
	 * 订单来源 1:花财 2:摩尔龙 3:借吧
	 *
	 * @mbg.generated
	 */
	private String appType;

	/**
	 * 创建时间
	 *
	 * @mbg.generated
	 */
	private Date createTime;
	/**
	 * 借款用途
	 *
	 * @mbg.generated
	 */
	private String loanType;

	private String status;

	/**
	 * 用户名-特需字段
	 *
	 * @mbg.generated
	 */
	private String loginName;

	/**
	 * 次数
	 *
	 * @mbg.generated
	 */
	private Integer loginNum;
	
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

	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getRegisterChannel() {
		return registerChannel;
	}

	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
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

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
