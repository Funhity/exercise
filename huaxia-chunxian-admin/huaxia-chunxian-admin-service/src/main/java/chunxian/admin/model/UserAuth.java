/**
 * @项目名称:
 * @文件名称: UserAuth 版本号：1.0
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
 * @Title: UserAuth
 * @Description: UserAuth
 * @author
 */
public class UserAuth extends BaseEntity {
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
     * 运营商认证时间
     *
     * @mbg.generated
     */
    private Date operatorAuthTime;

    /**
     * 银行卡认证时间-华夏财富平台
     *
     * @mbg.generated
     */
    private Date bankAuthH2x4Time;

    /**
     * 银行卡认证时间-花虾财富平台
     *
     * @mbg.generated
     */
    private Date bankAuthH1x1Time;

    /**
     * 公积金认证时间
     *
     * @mbg.generated
     */
    private Date fundAuthTime;

    /**
     * 保单认证时间
     *
     * @mbg.generated
     */
    private Date policyAuthTime;

    /**
     * 邮箱信用卡认证时间
     *
     * @mbg.generated
     */
    private Date creditCardAuthTime;

    /**
     * 芝麻信用认证时间
     *
     * @mbg.generated
     */
    private Date zhimaAuthTime;

    /**
     * 魔蝎运营商认证任务id
     *
     * @mbg.generated
     */
    private String operatorAuthTaskid;

    /**
     * 运营商认证常用手机号
     *
     * @mbg.generated
     */
    private String operatorAuthTelephone;

    /**
     * 魔蝎邮箱信用卡认证任务id
     *
     * @mbg.generated
     */
    private String creditCardAuthTaskid;

    /**
     * 魔蝎邮箱信用卡认证邮箱id
     *
     * @mbg.generated
     */
    private String creditCardAuthEmailid;

    /**
     * 魔蝎邮箱信用卡认证邮箱
     *
     * @mbg.generated
     */
    private String creditCardAuthEmail;

    /**
     * 魔蝎邮箱信用卡认证失败提示信息
     *
     * @mbg.generated
     */
    private String creditCardAuthMsg;

    /**
     * 保单认证登录名
     *
     * @mbg.generated
     */
    private String policyLoginName;

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

    public Date getOperatorAuthTime() {
        return operatorAuthTime;
    }

    public void setOperatorAuthTime(Date operatorAuthTime) {
        this.operatorAuthTime = operatorAuthTime;
    }

    public Date getBankAuthH2x4Time() {
        return bankAuthH2x4Time;
    }

    public void setBankAuthH2x4Time(Date bankAuthH2x4Time) {
        this.bankAuthH2x4Time = bankAuthH2x4Time;
    }

    public Date getBankAuthH1x1Time() {
        return bankAuthH1x1Time;
    }

    public void setBankAuthH1x1Time(Date bankAuthH1x1Time) {
        this.bankAuthH1x1Time = bankAuthH1x1Time;
    }

    public Date getFundAuthTime() {
        return fundAuthTime;
    }

    public void setFundAuthTime(Date fundAuthTime) {
        this.fundAuthTime = fundAuthTime;
    }

    public Date getPolicyAuthTime() {
        return policyAuthTime;
    }

    public void setPolicyAuthTime(Date policyAuthTime) {
        this.policyAuthTime = policyAuthTime;
    }

    public Date getCreditCardAuthTime() {
        return creditCardAuthTime;
    }

    public void setCreditCardAuthTime(Date creditCardAuthTime) {
        this.creditCardAuthTime = creditCardAuthTime;
    }

    public Date getZhimaAuthTime() {
        return zhimaAuthTime;
    }

    public void setZhimaAuthTime(Date zhimaAuthTime) {
        this.zhimaAuthTime = zhimaAuthTime;
    }

    public String getOperatorAuthTaskid() {
        return operatorAuthTaskid;
    }

    public void setOperatorAuthTaskid(String operatorAuthTaskid) {
        this.operatorAuthTaskid = operatorAuthTaskid;
    }

    public String getOperatorAuthTelephone() {
        return operatorAuthTelephone;
    }

    public void setOperatorAuthTelephone(String operatorAuthTelephone) {
        this.operatorAuthTelephone = operatorAuthTelephone;
    }

    public String getCreditCardAuthTaskid() {
        return creditCardAuthTaskid;
    }

    public void setCreditCardAuthTaskid(String creditCardAuthTaskid) {
        this.creditCardAuthTaskid = creditCardAuthTaskid;
    }

    public String getCreditCardAuthEmailid() {
        return creditCardAuthEmailid;
    }

    public void setCreditCardAuthEmailid(String creditCardAuthEmailid) {
        this.creditCardAuthEmailid = creditCardAuthEmailid;
    }

    public String getCreditCardAuthEmail() {
        return creditCardAuthEmail;
    }

    public void setCreditCardAuthEmail(String creditCardAuthEmail) {
        this.creditCardAuthEmail = creditCardAuthEmail;
    }

    public String getCreditCardAuthMsg() {
        return creditCardAuthMsg;
    }

    public void setCreditCardAuthMsg(String creditCardAuthMsg) {
        this.creditCardAuthMsg = creditCardAuthMsg;
    }

    public String getPolicyLoginName() {
        return policyLoginName;
    }

    public void setPolicyLoginName(String policyLoginName) {
        this.policyLoginName = policyLoginName;
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