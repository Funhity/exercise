/**
 * @项目名称:
 * @文件名称: ChannelInfo 版本号：1.0
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
 * @Title: ChannelInfo
 * @Description: ChannelInfo
 * @author
 */
public class ChannelInfo extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 渠道编码
     *
     * @mbg.generated
     */
    private String channelCode;

    /**
     * 渠道名称
     *
     * @mbg.generated
     */
    private String channelName;

    /**
     * 渠道描述
     *
     * @mbg.generated
     */
    private String channelDesc;

    /**
     * 1 有用，2删除
     *
     * @mbg.generated
     */
    private String channelPath;


	/**
     * 渠道状态
     *
     * @mbg.generated
     */
    private Integer channelStatus;

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

	private Date editstartdate;
    
    private Date editenddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

 

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
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
    public String getChannelPath() {
		return channelPath;
	}

	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
	}
    
    public Date getEditstartdate() {
		return editstartdate;
	}

	public void setEditstartdate(Date editstartdate) {
		this.editstartdate = editstartdate;
	}

	public Date getEditenddate() {
		return editenddate;
	}

	public void setEditenddate(Date editenddate) {
		this.editenddate = editenddate;
	}

}