/**
 * @项目名称:
 * @文件名称: UserBank 版本号：1.0
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
 * @Title: UserBank
 * @Description: UserBank
 * @author
 */
public class UserBank extends BaseEntity {
    private Integer id;

    /**
     * 唯一标识
     *
     * @mbg.generated
     */
    private String loginName;

    /**
     * 银行卡开户手机号
     *
     * @mbg.generated
     */
    private String bankCellphone;

    /**
     * 银行CODE
     *
     * @mbg.generated
     */
    private String bankCode;

    /**
     * 银行卡号
     *
     * @mbg.generated
     */
    private String bankCard;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    private String realName;

    /**
     * 银行卡认证结果码
     *
     * @mbg.generated
     */
    private String resultCode;

    /**
     * 银行卡认证结果说明
     *
     * @mbg.generated
     */
    private String resultMsg;

    /**
     * 开户时间
     *
     * @mbg.generated
     */
    private String createAccountDate;

    private Date createTime;

    /**
     * 子账户ID
     *
     * @mbg.generated
     */
    private String cusAcctId;

    /**
     * 身份证
     *
     * @mbg.generated
     */
    private String idCard;

    /**
     * 银行卡类型 0平安银行自动还款卡 1手动还款卡
     *
     * @mbg.generated
     */
    private String cardType;

    /**
     * 是否软删除 0正常 1已软删除
     *
     * @mbg.generated
     */
    private String deleted;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * 绑定id，供支付使用
     *
     * @mbg.generated
     */
    private String bindId;

    /**
     * 财富平台
     *
     * @mbg.generated
     */
    private String wealthPlatfrom;

    /**
     * 鉴权订单号
     *
     * @mbg.generated
     */
    private String flowOrderId;

    /**
     * 平安绑定帐号状态1:正常 2:异常 3:待确认
     *
     * @mbg.generated
     */
    private String acctStatus;

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

    public String getBankCellphone() {
        return bankCellphone;
    }

    public void setBankCellphone(String bankCellphone) {
        this.bankCellphone = bankCellphone;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getCreateAccountDate() {
        return createAccountDate;
    }

    public void setCreateAccountDate(String createAccountDate) {
        this.createAccountDate = createAccountDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCusAcctId() {
        return cusAcctId;
    }

    public void setCusAcctId(String cusAcctId) {
        this.cusAcctId = cusAcctId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getWealthPlatfrom() {
        return wealthPlatfrom;
    }

    public void setWealthPlatfrom(String wealthPlatfrom) {
        this.wealthPlatfrom = wealthPlatfrom;
    }

    public String getFlowOrderId() {
        return flowOrderId;
    }

    public void setFlowOrderId(String flowOrderId) {
        this.flowOrderId = flowOrderId;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }
}