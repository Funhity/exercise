/**
 * @项目名称:
 * @文件名称: FeedbackType 版本号：1.0
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
 * @Title: FeedbackType
 * @Description: FeedbackType
 * @author
 */
public class FeedbackType extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     * 是否显示 0不显示 1显示
     *
     * @mbg.generated
     */
    private Integer visible;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
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