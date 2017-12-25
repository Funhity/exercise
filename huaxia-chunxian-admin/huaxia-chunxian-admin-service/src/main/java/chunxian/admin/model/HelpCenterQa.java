/**
 * @项目名称:
 * @文件名称: HelpCenterQa 版本号：1.0
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
 * @Title: HelpCenterQa
 * @Description: HelpCenterQa
 * @author
 */
public class HelpCenterQa extends BaseEntity {
    /**
     * 主键id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 类别ID
     *
     * @mbg.generated
     */
    private Integer typeId;

    /**
     * 问题
     *
     * @mbg.generated
     */
    private String question;

    /**
     * 答案
     *
     * @mbg.generated
     */
    private String answer;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sort;

    private Integer toSort;

    /**
     * 是否显示 0不显示 1显示
     *
     * @mbg.generated
     */
    private String visible;

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
     * 创建人
     *
     * @mbg.generated
     */
    private String createUser;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getToSort() {
        return toSort;
    }

    public void setToSort(Integer toSort) {
        this.toSort = toSort;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}