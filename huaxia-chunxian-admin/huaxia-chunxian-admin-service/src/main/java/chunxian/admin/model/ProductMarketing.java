/**
 * @项目名称:
 * @文件名称: ProductMarketing 版本号：1.0
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
 * 说明: 营销消息
 * @author zhuqingyang
 * @version 1.0
 */
public class ProductMarketing extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 产品编码
     *
     * @mbg.generated
     */
    private String productNo;

    /**
     * 营销名称
     *
     * @mbg.generated
     */
    private String marketingName;

    /**
     * 营销概括
     *
     * @mbg.generated
     */
    private String marketingSummary;

    /**
     * 营销开关 0关 1开
     *
     * @mbg.generated
     */
    private String marketingSwitch;

    /**
     * 营销图标
     *
     * @mbg.generated
     */
    private String marketingIcon;

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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getMarketingSummary() {
        return marketingSummary;
    }

    public void setMarketingSummary(String marketingSummary) {
        this.marketingSummary = marketingSummary;
    }

    public String getMarketingSwitch() {
        return marketingSwitch;
    }

    public void setMarketingSwitch(String marketingSwitch) {
        this.marketingSwitch = marketingSwitch;
    }

    public String getMarketingIcon() {
        return marketingIcon;
    }

    public void setMarketingIcon(String marketingIcon) {
        this.marketingIcon = marketingIcon;
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