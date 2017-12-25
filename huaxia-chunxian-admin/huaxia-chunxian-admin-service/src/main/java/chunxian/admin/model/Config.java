/**
 * @项目名称:
 * @文件名称: Config 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;
import java.util.Date;

/**
 * @Title: Config
 * @Description: Config
 * @author
 */
public class Config extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 类型
     *
     * @mbg.generated
     */
    private String type;

    /**
     * 描述
     *
     * @mbg.generated
     */
    private String desc;

    /**
     * 键值
     *
     * @mbg.generated
     */
    private String code;

    /**
     * 配置内容1
     *
     * @mbg.generated
     */
    private String config1;

    /**
     * 配置内容2
     *
     * @mbg.generated
     */
    private String config2;

    /**
     * 配置内容3
     *
     * @mbg.generated
     */
    private String config3;

    /**
     * 配置内容4
     *
     * @mbg.generated
     */
    private String config4;

    /**
     * 配置内容5
     *
     * @mbg.generated
     */
    private String config5;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    private String updateBy;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConfig1() {
        return config1;
    }

    public void setConfig1(String config1) {
        this.config1 = config1;
    }

    public String getConfig2() {
        return config2;
    }

    public void setConfig2(String config2) {
        this.config2 = config2;
    }

    public String getConfig3() {
        return config3;
    }

    public void setConfig3(String config3) {
        this.config3 = config3;
    }

    public String getConfig4() {
        return config4;
    }

    public void setConfig4(String config4) {
        this.config4 = config4;
    }

    public String getConfig5() {
        return config5;
    }

    public void setConfig5(String config5) {
        this.config5 = config5;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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