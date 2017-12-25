/**
 * @项目名称:
 * @文件名称: Order 版本号：1.0
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
 * @Title: Order
 * @Description: Order
 * @author
 */
public class Order extends BaseEntity {
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

    /**
     * 借款用途
     *
     * @mbg.generated
     */
    private String loanType;

    /**
     * 其他借款用途详情
     *
     * @mbg.generated
     */
    private String loanTypeRemark;

    /**
     * 订单状态：0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 审核原因
     *
     * @mbg.generated
     */
    private String checkReason;

    /**
     * 审批金额
     *
     * @mbg.generated
     */
    private BigDecimal applyAmount;

    /**
     * 审批期限
     *
     * @mbg.generated
     */
    private Integer applyTerm;

    /**
     * 订单提交时间
     *
     * @mbg.generated
     */
    private Date submitTime;

    /**
     * 信审回退时间
     *
     * @mbg.generated
     */
    private Date checkBackTime;

    /**
     * 审核通过时间
     *
     * @mbg.generated
     */
    private Date checkPassTime;

    /**
     * 审核拒绝时间
     *
     * @mbg.generated
     */
    private Date checkRejectTime;

    /**
     * 审核取消时间
     *
     * @mbg.generated
     */
    private Date checkCancleTime;

    /**
     * 确认签约金额
     *
     * @mbg.generated
     */
    private BigDecimal confirmAmount;

    /**
     * 确认签约时间
     *
     * @mbg.generated
     */
    private Date confirmTime;

    /**
     * 放款时间
     *
     * @mbg.generated
     */
    private Date moneyTime;

    /**
     * 授权协议签订时间
     *
     * @mbg.generated
     */
    private Date contractSignTime;

    /**
     * 授权协议文件名
     *
     * @mbg.generated
     */
    private String contractFileName;

    /**
     * 是否查看过还款计划:0.否1.是
     *
     * @mbg.generated
     */
    private String viewPaymentPlan;

    /**
     * 申请地址
     *
     * @mbg.generated
     */
    private String applyAddress;

    /**
     * 订单来源 1:花财 2:摩尔龙 3:借吧
     *
     * @mbg.generated
     */
    private String appType;

    /**
     * 总月费率
     *
     * @mbg.generated
     */
    private BigDecimal totalRate;

    /**
     * 借款协议是否查阅:0.否1.是
     *
     * @mbg.generated
     */
    private String contractReadStatusLoan;

    /**
     * 咨询协议是否查阅:0.否1.是
     *
     * @mbg.generated
     */
    private String contractReadStatusConsult;

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
     * 财富平台
     *
     * @mbg.generated
     */
    private String wealthPlatfrom;

    /**
     * 白骑士tokenKey
     *
     * @mbg.generated
     */
    private String bqsTokenKey;

    /**
     * 商汤人脸识别与订单关联id
     *
     * @mbg.generated
     */
    private String stTaskId;

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

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanTypeRemark() {
        return loanTypeRemark;
    }

    public void setLoanTypeRemark(String loanTypeRemark) {
        this.loanTypeRemark = loanTypeRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getApplyTerm() {
        return applyTerm;
    }

    public void setApplyTerm(Integer applyTerm) {
        this.applyTerm = applyTerm;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getCheckBackTime() {
        return checkBackTime;
    }

    public void setCheckBackTime(Date checkBackTime) {
        this.checkBackTime = checkBackTime;
    }

    public Date getCheckPassTime() {
        return checkPassTime;
    }

    public void setCheckPassTime(Date checkPassTime) {
        this.checkPassTime = checkPassTime;
    }

    public Date getCheckRejectTime() {
        return checkRejectTime;
    }

    public void setCheckRejectTime(Date checkRejectTime) {
        this.checkRejectTime = checkRejectTime;
    }

    public Date getCheckCancleTime() {
        return checkCancleTime;
    }

    public void setCheckCancleTime(Date checkCancleTime) {
        this.checkCancleTime = checkCancleTime;
    }

    public BigDecimal getConfirmAmount() {
        return confirmAmount;
    }

    public void setConfirmAmount(BigDecimal confirmAmount) {
        this.confirmAmount = confirmAmount;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(Date moneyTime) {
        this.moneyTime = moneyTime;
    }

    public Date getContractSignTime() {
        return contractSignTime;
    }

    public void setContractSignTime(Date contractSignTime) {
        this.contractSignTime = contractSignTime;
    }

    public String getContractFileName() {
        return contractFileName;
    }

    public void setContractFileName(String contractFileName) {
        this.contractFileName = contractFileName;
    }

    public String getViewPaymentPlan() {
        return viewPaymentPlan;
    }

    public void setViewPaymentPlan(String viewPaymentPlan) {
        this.viewPaymentPlan = viewPaymentPlan;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public String getContractReadStatusLoan() {
        return contractReadStatusLoan;
    }

    public void setContractReadStatusLoan(String contractReadStatusLoan) {
        this.contractReadStatusLoan = contractReadStatusLoan;
    }

    public String getContractReadStatusConsult() {
        return contractReadStatusConsult;
    }

    public void setContractReadStatusConsult(String contractReadStatusConsult) {
        this.contractReadStatusConsult = contractReadStatusConsult;
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

    public String getWealthPlatfrom() {
        return wealthPlatfrom;
    }

    public void setWealthPlatfrom(String wealthPlatfrom) {
        this.wealthPlatfrom = wealthPlatfrom;
    }

    public String getBqsTokenKey() {
        return bqsTokenKey;
    }

    public void setBqsTokenKey(String bqsTokenKey) {
        this.bqsTokenKey = bqsTokenKey;
    }

    public String getStTaskId() {
        return stTaskId;
    }

    public void setStTaskId(String stTaskId) {
        this.stTaskId = stTaskId;
    }
}