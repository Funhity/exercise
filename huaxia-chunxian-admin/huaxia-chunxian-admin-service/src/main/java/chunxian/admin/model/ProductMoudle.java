/**
 * @项目名称:
 * @文件名称: ProductMoudle 版本号：1.0
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
 * @Title: ProductMoudle
 * @Description: ProductMoudle
 * @author
 */
public class ProductMoudle extends BaseEntity {
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
     * 模块名称
     *
     * @mbg.generated
     */
    private String moudleName;

    /**
     * 模块概括
     *
     * @mbg.generated
     */
    private String moudleSummary;

    /**
     * 模块开关 0关 1开
     *
     * @mbg.generated
     */
    private String moudleSwitch;

    /**
     * 模块内容
     *
     * @mbg.generated
     */
    private String moudleContent;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sort;
    
    private Integer toSort;

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

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public String getMoudleSummary() {
        return moudleSummary;
    }

    public void setMoudleSummary(String moudleSummary) {
        this.moudleSummary = moudleSummary;
    }

    public String getMoudleSwitch() {
        return moudleSwitch;
    }

    public void setMoudleSwitch(String moudleSwitch) {
        this.moudleSwitch = moudleSwitch;
    }

    public String getMoudleContent() {
        return moudleContent;
    }

    public void setMoudleContent(String moudleContent) {
        this.moudleContent = moudleContent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	public Integer getToSort() {
		return toSort;
	}

	public void setToSort(Integer toSort) {
		this.toSort = toSort;
	}

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}