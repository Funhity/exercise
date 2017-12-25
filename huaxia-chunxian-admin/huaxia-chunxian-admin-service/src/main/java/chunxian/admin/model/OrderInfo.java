/**
 * @项目名称:
 * @文件名称: OrderInfo 版本号：1.0
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
 * @Title: OrderInfo
 * @Description: OrderInfo
 * @author
 */
public class OrderInfo extends BaseEntity {
    /**
     * 主键ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    private String loginName;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    private String telephone;

    /**
     * 身份认证0.否1.是
     *
     * @mbg.generated
     */
    private Integer identityAuth;

    /**
     * 完善信息0.否1.是
     *
     * @mbg.generated
     */
    private Integer supplementInfo;

    /**
     * 公积金认证0.否1.是
     *
     * @mbg.generated
     */
    private Integer fundAuth;

    /**
     * 公积金认证时间
     *
     * @mbg.generated
     */
    private Date fundAuthTime;

    /**
     * 运营商认证0.否1.是
     *
     * @mbg.generated
     */
    private Integer operatorAuth;

    /**
     * 运营商认证任务ID
     *
     * @mbg.generated
     */
    private String operatorAuthTaskid;

    /**
     * 运营商认证时间
     *
     * @mbg.generated
     */
    private Date operatorAuthTime;

    /**
     * 银行卡认证0.否1.是
     *
     * @mbg.generated
     */
    private Integer bankAuth;

    /**
     * 银行卡认证时间
     *
     * @mbg.generated
     */
    private Date bankAuthTime;

    /**
     * 真实姓名
     *
     * @mbg.generated
     */
    private String realName;

    /**
     * 性别
     *
     * @mbg.generated
     */
    private String sex;

    /**
     * 身份证正面照ID
     *
     * @mbg.generated
     */
    private String identityFrontPhotoId;

    /**
     * 身份证正面照URL
     *
     * @mbg.generated
     */
    private String identityFrontPhoto;

    /**
     * 身份证反面照ID
     *
     * @mbg.generated
     */
    private String identityBackPhotoId;

    /**
     * 身份证反面照URL
     *
     * @mbg.generated
     */
    private String identityBackPhoto;

    /**
     * 手持身份证照ID
     *
     * @mbg.generated
     */
    private String identityHandPhotoId;

    /**
     * 手持身份证照URL
     *
     * @mbg.generated
     */
    private String identityHandPhoto;

    /**
     * 身份证号
     *
     * @mbg.generated
     */
    private String identityId;

    /**
     * 有效期起始日期YYYY/MM/DD
     *
     * @mbg.generated
     */
    private String identityStartTime;

    /**
     * 有效期结束日期YYYY/MM/DD
     *
     * @mbg.generated
     */
    private String identityEndTime;

    /**
     * 产品编号
     *
     * @mbg.generated
     */
    private String productNo;

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
     * 是否本地户籍
     *
     * @mbg.generated
     */
    private String isLocal;

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
    private Double rent;

    /**
     * 居住开始日期YYYY/MM/DD
     *
     * @mbg.generated
     */
    private String liveDate;

    /**
     * 来本市日期YYYY/MM/DD
     *
     * @mbg.generated
     */
    private String moveInDate;

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
     * 入职时间YYYY/MM/DD
     *
     * @mbg.generated
     */
    private String entryDate;

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
     * 单位电话
     *
     * @mbg.generated
     */
    private String unitPhone;

    /**
     * 借款金额
     *
     * @mbg.generated
     */
    private Double loanAmount;

    /**
     * 借款期限
     *
     * @mbg.generated
     */
    private Integer term;

    /**
     * 银行卡号
     *
     * @mbg.generated
     */
    private String cardNo;

    /**
     * 开户姓名
     *
     * @mbg.generated
     */
    private String accountHolder;

    /**
     * 银行名称
     *
     * @mbg.generated
     */
    private String bankName;

    /**
     * 支行名称
     *
     * @mbg.generated
     */
    private String bankBranch;

    /**
     * 银行卡开户行地址-省
     *
     * @mbg.generated
     */
    private String bankProvince;

    /**
     * 银行卡开户行地址-市
     *
     * @mbg.generated
     */
    private String bankCity;

    /**
     * 联系人列表 jsonArray,ontractReletived 联系人与本人关系,ContractPhone 联系人电话,ContractName 联系人姓名,Id 联系人id 关系编码 01父亲 02母亲 03配偶 04子女 05同学 06同事 07朋友 08兄妹
     *
     * @mbg.generated
     */
    private String contractJson;

    /**
     * 公积金用户名
     *
     * @mbg.generated
     */
    private String cpfUserName;

    /**
     * 公积金账户
     *
     * @mbg.generated
     */
    private String cpfAccount;

    /**
     * 公积金所属单位
     *
     * @mbg.generated
     */
    private String cpfInsuredUnit;

    /**
     * 公积金缴纳基数
     *
     * @mbg.generated
     */
    private String cpfBase;

    /**
     * 公积金缴存状态
     *
     * @mbg.generated
     */
    private String cpfPayStatus;

    /**
     * 公积金月缴纳额
     *
     * @mbg.generated
     */
    private String cpfPayMonth;

    /**
     * 公积金缴纳总月数
     *
     * @mbg.generated
     */
    private String cpfTotalMonth;

    /**
     * 订单状态：0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中
     *
     * @mbg.generated
     */
    private Integer state;

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
    private Double applyAmount;

    /**
     * 审批期限
     *
     * @mbg.generated
     */
    private Integer applyTerm;

    /**
     * 风险等级
     *
     * @mbg.generated
     */
    private String riskLevel;

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
    private Date checkNopassTime;

    /**
     * 审核取消时间
     *
     * @mbg.generated
     */
    private Date checkCanclTime;

    /**
     * 签约状态:0.未签1.已签
     *
     * @mbg.generated
     */
    private Integer signState;

    /**
     * 签约金额
     *
     * @mbg.generated
     */
    private Double signAmount;

    /**
     * 签约时间
     *
     * @mbg.generated
     */
    private Date signTime;

    /**
     * 放款时间
     *
     * @mbg.generated
     */
    private Date montyTime;

    /**
     * 合同签订状态:0.未签1.已签
     *
     * @mbg.generated
     */
    private Integer contractSignState;

    /**
     * 合同签订时间
     *
     * @mbg.generated
     */
    private Date contractSignTime;

    /**
     * 合同文件名
     *
     * @mbg.generated
     */
    private String contractFileName;

    /**
     * 是否查看过还款计划:0.否1.是
     *
     * @mbg.generated
     */
    private Integer viewPaymentPlan;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 银行预留手机号
     *
     * @mbg.generated
     */
    private String bankTelephone;

    /**
     * 华夏客户ID
     *
     * @mbg.generated
     */
    private String bankCustomerId;

    /**
     * 公积金城市
     *
     * @mbg.generated
     */
    private String cpfCity;

    /**
     * 公积金姓名
     *
     * @mbg.generated
     */
    private String cpfRealName;

    /**
     * 申请地址
     *
     * @mbg.generated
     */
    private String applyAddress;

    /**
     * 订单来源：花财APP、摩尔龙
     *
     * @mbg.generated
     */
    private String sourceType;

    /**
     * 身份证住址
     *
     * @mbg.generated
     */
    private String idLiveAddress;

    /**
     * 身份证出生日期
     *
     * @mbg.generated
     */
    private String idBirthday;

    /**
     * 身份证签发机关
     *
     * @mbg.generated
     */
    private String idIssued;

    /**
     * 总月费率
     *
     * @mbg.generated
     */
    private Double totalRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIdentityAuth() {
        return identityAuth;
    }

    public void setIdentityAuth(Integer identityAuth) {
        this.identityAuth = identityAuth;
    }

    public Integer getSupplementInfo() {
        return supplementInfo;
    }

    public void setSupplementInfo(Integer supplementInfo) {
        this.supplementInfo = supplementInfo;
    }

    public Integer getFundAuth() {
        return fundAuth;
    }

    public void setFundAuth(Integer fundAuth) {
        this.fundAuth = fundAuth;
    }

    public Date getFundAuthTime() {
        return fundAuthTime;
    }

    public void setFundAuthTime(Date fundAuthTime) {
        this.fundAuthTime = fundAuthTime;
    }

    public Integer getOperatorAuth() {
        return operatorAuth;
    }

    public void setOperatorAuth(Integer operatorAuth) {
        this.operatorAuth = operatorAuth;
    }

    public String getOperatorAuthTaskid() {
        return operatorAuthTaskid;
    }

    public void setOperatorAuthTaskid(String operatorAuthTaskid) {
        this.operatorAuthTaskid = operatorAuthTaskid;
    }

    public Date getOperatorAuthTime() {
        return operatorAuthTime;
    }

    public void setOperatorAuthTime(Date operatorAuthTime) {
        this.operatorAuthTime = operatorAuthTime;
    }

    public Integer getBankAuth() {
        return bankAuth;
    }

    public void setBankAuth(Integer bankAuth) {
        this.bankAuth = bankAuth;
    }

    public Date getBankAuthTime() {
        return bankAuthTime;
    }

    public void setBankAuthTime(Date bankAuthTime) {
        this.bankAuthTime = bankAuthTime;
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

    public String getIdentityFrontPhotoId() {
        return identityFrontPhotoId;
    }

    public void setIdentityFrontPhotoId(String identityFrontPhotoId) {
        this.identityFrontPhotoId = identityFrontPhotoId;
    }

    public String getIdentityFrontPhoto() {
        return identityFrontPhoto;
    }

    public void setIdentityFrontPhoto(String identityFrontPhoto) {
        this.identityFrontPhoto = identityFrontPhoto;
    }

    public String getIdentityBackPhotoId() {
        return identityBackPhotoId;
    }

    public void setIdentityBackPhotoId(String identityBackPhotoId) {
        this.identityBackPhotoId = identityBackPhotoId;
    }

    public String getIdentityBackPhoto() {
        return identityBackPhoto;
    }

    public void setIdentityBackPhoto(String identityBackPhoto) {
        this.identityBackPhoto = identityBackPhoto;
    }

    public String getIdentityHandPhotoId() {
        return identityHandPhotoId;
    }

    public void setIdentityHandPhotoId(String identityHandPhotoId) {
        this.identityHandPhotoId = identityHandPhotoId;
    }

    public String getIdentityHandPhoto() {
        return identityHandPhoto;
    }

    public void setIdentityHandPhoto(String identityHandPhoto) {
        this.identityHandPhoto = identityHandPhoto;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityStartTime() {
        return identityStartTime;
    }

    public void setIdentityStartTime(String identityStartTime) {
        this.identityStartTime = identityStartTime;
    }

    public String getIdentityEndTime() {
        return identityEndTime;
    }

    public void setIdentityEndTime(String identityEndTime) {
        this.identityEndTime = identityEndTime;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public String getLiveType() {
        return liveType;
    }

    public void setLiveType(String liveType) {
        this.liveType = liveType;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(String liveDate) {
        this.liveDate = liveDate;
    }

    public String getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(String moveInDate) {
        this.moveInDate = moveInDate;
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
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

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getContractJson() {
        return contractJson;
    }

    public void setContractJson(String contractJson) {
        this.contractJson = contractJson;
    }

    public String getCpfUserName() {
        return cpfUserName;
    }

    public void setCpfUserName(String cpfUserName) {
        this.cpfUserName = cpfUserName;
    }

    public String getCpfAccount() {
        return cpfAccount;
    }

    public void setCpfAccount(String cpfAccount) {
        this.cpfAccount = cpfAccount;
    }

    public String getCpfInsuredUnit() {
        return cpfInsuredUnit;
    }

    public void setCpfInsuredUnit(String cpfInsuredUnit) {
        this.cpfInsuredUnit = cpfInsuredUnit;
    }

    public String getCpfBase() {
        return cpfBase;
    }

    public void setCpfBase(String cpfBase) {
        this.cpfBase = cpfBase;
    }

    public String getCpfPayStatus() {
        return cpfPayStatus;
    }

    public void setCpfPayStatus(String cpfPayStatus) {
        this.cpfPayStatus = cpfPayStatus;
    }

    public String getCpfPayMonth() {
        return cpfPayMonth;
    }

    public void setCpfPayMonth(String cpfPayMonth) {
        this.cpfPayMonth = cpfPayMonth;
    }

    public String getCpfTotalMonth() {
        return cpfTotalMonth;
    }

    public void setCpfTotalMonth(String cpfTotalMonth) {
        this.cpfTotalMonth = cpfTotalMonth;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public Double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(Double applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getApplyTerm() {
        return applyTerm;
    }

    public void setApplyTerm(Integer applyTerm) {
        this.applyTerm = applyTerm;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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

    public Date getCheckNopassTime() {
        return checkNopassTime;
    }

    public void setCheckNopassTime(Date checkNopassTime) {
        this.checkNopassTime = checkNopassTime;
    }

    public Date getCheckCanclTime() {
        return checkCanclTime;
    }

    public void setCheckCanclTime(Date checkCanclTime) {
        this.checkCanclTime = checkCanclTime;
    }

    public Integer getSignState() {
        return signState;
    }

    public void setSignState(Integer signState) {
        this.signState = signState;
    }

    public Double getSignAmount() {
        return signAmount;
    }

    public void setSignAmount(Double signAmount) {
        this.signAmount = signAmount;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getMontyTime() {
        return montyTime;
    }

    public void setMontyTime(Date montyTime) {
        this.montyTime = montyTime;
    }

    public Integer getContractSignState() {
        return contractSignState;
    }

    public void setContractSignState(Integer contractSignState) {
        this.contractSignState = contractSignState;
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

    public Integer getViewPaymentPlan() {
        return viewPaymentPlan;
    }

    public void setViewPaymentPlan(Integer viewPaymentPlan) {
        this.viewPaymentPlan = viewPaymentPlan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBankTelephone() {
        return bankTelephone;
    }

    public void setBankTelephone(String bankTelephone) {
        this.bankTelephone = bankTelephone;
    }

    public String getBankCustomerId() {
        return bankCustomerId;
    }

    public void setBankCustomerId(String bankCustomerId) {
        this.bankCustomerId = bankCustomerId;
    }

    public String getCpfCity() {
        return cpfCity;
    }

    public void setCpfCity(String cpfCity) {
        this.cpfCity = cpfCity;
    }

    public String getCpfRealName() {
        return cpfRealName;
    }

    public void setCpfRealName(String cpfRealName) {
        this.cpfRealName = cpfRealName;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getIdLiveAddress() {
        return idLiveAddress;
    }

    public void setIdLiveAddress(String idLiveAddress) {
        this.idLiveAddress = idLiveAddress;
    }

    public String getIdBirthday() {
        return idBirthday;
    }

    public void setIdBirthday(String idBirthday) {
        this.idBirthday = idBirthday;
    }

    public String getIdIssued() {
        return idIssued;
    }

    public void setIdIssued(String idIssued) {
        this.idIssued = idIssued;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }
}