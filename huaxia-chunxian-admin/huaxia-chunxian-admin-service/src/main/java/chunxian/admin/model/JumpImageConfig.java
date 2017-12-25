/**
 * @项目名称:
 * @文件名称: JumpImageConfig 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.model;

import java.util.Date;

import com.huaxia.common.core.common.BaseEntity;

/**
 * @Title: JumpImageConfig
 * @Description: JumpImageConfig
 * @author
 */
public class JumpImageConfig extends BaseEntity {
	private static final long serialVersionUID = -7622234908290218396L;

	/**
	 * 主键id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 * 类型 1app启动图 2首页Banner 3首页推广icon 4app底部导航
	 *
	 * @mbg.generated
	 */
	private String type;

	/**
	 * 图片地址
	 *
	 * @mbg.generated
	 */
	private String image;

	/**
	 * 标题
	 *
	 * @mbg.generated
	 */
	private String title;

	/**
	 * 1H5页面 2app原生界面
	 *
	 * @mbg.generated
	 */
	private String jumpType;

	/**
	 * 跳转地址
	 *
	 * @mbg.generated
	 */
	private String jumpPageUrl;

	/**
	 * 排序
	 *
	 * @mbg.generated
	 */
	private Integer sort;

	private Integer toSort;

	/**
	 * app启动图停留时间(s)
	 *
	 * @mbg.generated
	 */
	private Integer appStartImageTime;

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

	/**
	 * 删除时间
	 *
	 * @mbg.generated
	 */
	private Date deleteTime;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
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

	public Integer getAppStartImageTime() {
		return appStartImageTime;
	}

	public void setAppStartImageTime(Integer appStartImageTime) {
		this.appStartImageTime = appStartImageTime;
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

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Override
	public String toString() {
		return "JumpImageConfig [id=" + id + ", type=" + type + ", image=" + image + ", title=" + title + ", jumpType="
				+ jumpType + ", jumpPageUrl=" + jumpPageUrl + ", sort=" + sort + ", appStartImageTime="
				+ appStartImageTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUser="
				+ createUser + ", updateUser=" + updateUser + ", deleteTime=" + deleteTime + "]";
	}

}