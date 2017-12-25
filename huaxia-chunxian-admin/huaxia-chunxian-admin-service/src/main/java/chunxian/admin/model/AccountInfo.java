/**
 * @项目名称:
 * @文件名称: AccountInfo 版本号：1.0
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
 * @Title: AccountInfo
 * @Description: AccountInfo
 * @author
 */
public class AccountInfo extends BaseEntity {
    /**
     * 主键ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 华夏信财用户中心userid
     *
     * @mbg.generated
     */
    private String hxxcUserId;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    private String loginName;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 电子邮件
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 电话
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    private String realName;

    /**
     * 用户类型:1 用户 2 商户 3 系统管理员
     *
     * @mbg.generated
     */
    private Integer userType;

    /**
     * 用户角色IDS
     *
     * @mbg.generated
     */
    private String roleIds;

    /**
     * 登陆次数
     *
     * @mbg.generated
     */
    private Integer loginNum;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 最后一次登陆时间
     *
     * @mbg.generated
     */
    private Date lastLoginTime;

    /**
     * 用户状态：1.正常（默认值） 2.禁用
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 超级管理员:0.是1.否
     *
     * @mbg.generated
     */
    private Integer superAdmin;

    /**
     * 管理员账号
     *
     * @mbg.generated
     */
    private String adminLoginName;

    /**
     * 同盾设备指纹
     *
     * @mbg.generated
     */
    private String fingerprint;

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
     * 用户唯一标识
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * 注册机型
     *
     * @mbg.generated
     */
    private String deviceModelRegister;

    /**
     * 登录机型
     *
     * @mbg.generated
     */
    private String deviceModelLogin;

    /**
     * 注册渠道
     *
     * @mbg.generated
     */
    private String registerChannel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHxxcUserId() {
        return hxxcUserId;
    }

    public void setHxxcUserId(String hxxcUserId) {
        this.hxxcUserId = hxxcUserId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Integer superAdmin) {
        this.superAdmin = superAdmin;
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceModelRegister() {
        return deviceModelRegister;
    }

    public void setDeviceModelRegister(String deviceModelRegister) {
        this.deviceModelRegister = deviceModelRegister;
    }

    public String getDeviceModelLogin() {
        return deviceModelLogin;
    }

    public void setDeviceModelLogin(String deviceModelLogin) {
        this.deviceModelLogin = deviceModelLogin;
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel;
    }
}