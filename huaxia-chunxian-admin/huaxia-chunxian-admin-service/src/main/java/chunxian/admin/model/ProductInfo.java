package chunxian.admin.model;

import com.huaxia.common.core.common.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Title: ProductInfo
 * @Description: ProductInfo
 * @author
 */
public class ProductInfo extends BaseEntity {
	private static final long serialVersionUID = 4707986390574431315L;

	/**
	 * 主键ID
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 * LOGO
	 *
	 * @mbg.generated
	 */
	private String logo;

	/**
	 * 产品名称
	 *
	 * @mbg.generated
	 */
	private String productName;

	/**
	 * 产品编号
	 *
	 * @mbg.generated
	 */
	private String productNo;

	/**
	 * 借款期限
	 *
	 * @mbg.generated
	 */
	private String term;

	/**
	 * 最大借款金额
	 *
	 * @mbg.generated
	 */
	private Double maxAmcount;

	/**
	 * 最小借款金额
	 *
	 * @mbg.generated
	 */
	private Double minAmcount;

	/**
	 * 最高利率
	 *
	 * @mbg.generated
	 */
	private Double maxInterestRate;

	/**
	 * 最低利率
	 *
	 * @mbg.generated
	 */
	private Double minInterestRate;

	/**
	 * 利率说明
	 *
	 * @mbg.generated
	 */
	private String interestRateRemark;

	/**
	 * 支持城市
	 *
	 * @mbg.generated
	 */
	private String supportCity;

	/**
	 * 提供资料
	 *
	 * @mbg.generated
	 */
	private String information;

	/**
	 * 申请条件
	 *
	 * @mbg.generated
	 */
	private String applyCondition;

	/**
	 * 摘要
	 *
	 * @mbg.generated
	 */
	private String summary;

	/**
	 * 内容
	 *
	 * @mbg.generated
	 */
	private String content;

	/**
	 * 创建人
	 *
	 * @mbg.generated
	 */
	private String createUser;

	/**
	 * 创建时间
	 *
	 * @mbg.generated
	 */
	private Date createTime;

	/**
	 * 上下架状态:0.未发布 1.发布
	 *
	 * @mbg.generated
	 */
	private Integer publishStatus;

	/**
	 * 上下架时间
	 *
	 * @mbg.generated
	 */
	private Date publishTime;

	/**
	 * 内容LOGO
	 *
	 * @mbg.generated
	 */
	private String contentLogo;

	/**
	 * 费率等级
	 *
	 * @mbg.generated
	 */
	private String rateLevel;

	/**
	 * 默认借款期数
	 *
	 * @mbg.generated
	 */
	private Integer defaultLoanTerm;

	/**
	 * 营销图标地址
	 *
	 * @mbg.generated
	 */
	private String marketingIcon;

	/**
	 * 营销日利率说明
	 *
	 * @mbg.generated
	 */
	private String marketingRateRemark;

	/**
	 * 营销日利率
	 *
	 * @mbg.generated
	 */
	private BigDecimal marketingRate;

	/**
	 * 营销最高可借说明
	 *
	 * @mbg.generated
	 */
	private String marketingMaxAmcountRemark;

	/**
	 * 营销最高可借金额
	 *
	 * @mbg.generated
	 */
	private String marketingMaxAmcount;

	/**
	 * 是否推荐 0不推荐 1推荐
	 *
	 * @mbg.generated
	 */
	private String recommend;

	/**
	 * 是否推荐 0不允许分享 1允许分享
	 *
	 * @mbg.generated
	 */
	private String shareSwitch;

	/**
	 * 分享标题
	 *
	 * @mbg.generated
	 */
	private String shareTitle;

	/**
	 * 分享内容
	 *
	 * @mbg.generated
	 */
	private String shareContent;

	/**
	 * 分享图标地址
	 *
	 * @mbg.generated
	 */
	private String shareIcon;

	/**
	 * 分享链接
	 *
	 * @mbg.generated
	 */
	private String shareUrl;

	/**
	 * 分享界面提示文字
	 *
	 * @mbg.generated
	 */
	private String shareTooltip;

	/**
	 * 分享界面提示显示时间
	 *
	 * @mbg.generated
	 */
	private Integer shareTime;

	/**
	 * 显示顺序
	 *
	 * @mbg.generated
	 */
	private Integer sort;

	/**
	 * 必要认证项
	 *
	 * @mbg.generated
	 */
	private String necessaryCertification;

	/**
	 * 非必要认证项
	 *
	 * @mbg.generated
	 */
	private String unnecessaryCertification;

	/**
	 * 单位(10/100/1000/10000)
	 *
	 * @mbg.generated
	 */
	private Integer unit;

	/**
	 * 是否在app显示0.不显示 1.显示
	 *
	 * @mbg.generated
	 */
	private Integer showOnApp;

	/**
	 * 更新人
	 *
	 * @mbg.generated
	 */
	private String updateUser;

	/**
	 * 安硕创建时间
	 *
	 * @mbg.generated
	 */
	private Date anshuoCreateTime;

	/**
	 * 同步安硕时间
	 *
	 * @mbg.generated
	 */
	private Date anshuoSyncTime;

	/**
	 * 产品模块列表
	 */
	private List<ProductMoudle> productModels;

	/**
	 * 安硕产品名称
	 *
	 * @mbg.generated
	 */
	private String anshuoproductName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Double getMaxAmcount() {
		return maxAmcount;
	}

	public void setMaxAmcount(Double maxAmcount) {
		this.maxAmcount = maxAmcount;
	}

	public Double getMinAmcount() {
		return minAmcount;
	}

	public void setMinAmcount(Double minAmcount) {
		this.minAmcount = minAmcount;
	}

	public Double getMaxInterestRate() {
		return maxInterestRate;
	}

	public void setMaxInterestRate(Double maxInterestRate) {
		this.maxInterestRate = maxInterestRate;
	}

	public Double getMinInterestRate() {
		return minInterestRate;
	}

	public void setMinInterestRate(Double minInterestRate) {
		this.minInterestRate = minInterestRate;
	}

	public String getInterestRateRemark() {
		return interestRateRemark;
	}

	public void setInterestRateRemark(String interestRateRemark) {
		this.interestRateRemark = interestRateRemark;
	}

	public String getSupportCity() {
		return supportCity;
	}

	public void setSupportCity(String supportCity) {
		this.supportCity = supportCity;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getApplyCondition() {
		return applyCondition;
	}

	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getContentLogo() {
		return contentLogo;
	}

	public void setContentLogo(String contentLogo) {
		this.contentLogo = contentLogo;
	}

	public String getRateLevel() {
		return rateLevel;
	}

	public void setRateLevel(String rateLevel) {
		this.rateLevel = rateLevel;
	}

	public Integer getDefaultLoanTerm() {
		return defaultLoanTerm;
	}

	public void setDefaultLoanTerm(Integer defaultLoanTerm) {
		this.defaultLoanTerm = defaultLoanTerm;
	}

	public String getMarketingIcon() {
		return marketingIcon;
	}

	public void setMarketingIcon(String marketingIcon) {
		this.marketingIcon = marketingIcon;
	}

	public String getMarketingRateRemark() {
		return marketingRateRemark;
	}

	public void setMarketingRateRemark(String marketingRateRemark) {
		this.marketingRateRemark = marketingRateRemark;
	}

	public BigDecimal getMarketingRate() {
		return marketingRate;
	}

	public void setMarketingRate(BigDecimal marketingRate) {
		this.marketingRate = marketingRate;
	}

	public String getMarketingMaxAmcountRemark() {
		return marketingMaxAmcountRemark;
	}

	public void setMarketingMaxAmcountRemark(String marketingMaxAmcountRemark) {
		this.marketingMaxAmcountRemark = marketingMaxAmcountRemark;
	}

	public String getMarketingMaxAmcount() {
		return marketingMaxAmcount;
	}

	public void setMarketingMaxAmcount(String marketingMaxAmcount) {
		this.marketingMaxAmcount = marketingMaxAmcount;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getShareSwitch() {
		return shareSwitch;
	}

	public void setShareSwitch(String shareSwitch) {
		this.shareSwitch = shareSwitch;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTooltip() {
		return shareTooltip;
	}

	public void setShareTooltip(String shareTooltip) {
		this.shareTooltip = shareTooltip;
	}

	public Integer getShareTime() {
		return shareTime;
	}

	public void setShareTime(Integer shareTime) {
		this.shareTime = shareTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNecessaryCertification() {
		return necessaryCertification;
	}

	public void setNecessaryCertification(String necessaryCertification) {
		this.necessaryCertification = necessaryCertification;
	}

	public String getUnnecessaryCertification() {
		return unnecessaryCertification;
	}

	public void setUnnecessaryCertification(String unnecessaryCertification) {
		this.unnecessaryCertification = unnecessaryCertification;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getShowOnApp() {
		return showOnApp;
	}

	public void setShowOnApp(Integer showOnApp) {
		this.showOnApp = showOnApp;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getAnshuoCreateTime() {
		return anshuoCreateTime;
	}

	public void setAnshuoCreateTime(Date anshuoCreateTime) {
		this.anshuoCreateTime = anshuoCreateTime;
	}

	public Date getAnshuoSyncTime() {
		return anshuoSyncTime;
	}

	public void setAnshuoSyncTime(Date anshuoSyncTime) {
		this.anshuoSyncTime = anshuoSyncTime;
	}

	public List<ProductMoudle> getProductModels() {
		return productModels;
	}

	public void setProductModels(List<ProductMoudle> productModels) {
		this.productModels = productModels;
	}

	public String getAnshuoproductName() {
		return anshuoproductName;
	}

	public void setAnshuoproductName(String anshuoproductName) {
		this.anshuoproductName = anshuoproductName;
	}
}