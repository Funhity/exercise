/**
 * @项目名称:
 * @文件名称: ProductMarketingController 版本号：1.0
 * @创建日期: 2017/12/20
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.productmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;

import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.ProductInfo;
import chunxian.admin.model.ProductMarketing;
import chunxian.admin.service.ProductInfoService;
import chunxian.admin.service.ProductMarketingService;
import chunxian.admin.utils.FastJsonUtil;
import org.apache.commons.collections.MapUtils;

/**
 * 说明: ProductMarketingController
 * 
 * @author zhuqingyang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "productmarket")
public class ProductMarketingController {
	@Autowired
	ProductMarketingService productMarketingService;
	@Autowired
	ProductInfoService productInfoService;
	private Logger logger = LoggerFactory.getLogger(ProductMarketingController.class);

	@RequestMapping(value = "productmarkets", method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public List<ProductMarketing> listProductModule(@RequestParam Map<String, Object> params) {

		logger.debug("start process request listProductModule with params {}", params);
		String json = FastJsonUtil.collectToString(params);
		ProductMarketing record = FastJsonUtil.toBean(json, ProductMarketing.class);

		return productMarketingService.queryProductMarketingList(record);
	}

	@RequestMapping(value = { "addmarket" }, method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addProductMarketing(ProductMarketing productMarketing) {
		this.productMarketingService.createProductMarketing(productMarketing);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = { "updateMarketProductInfo" }, method = {RequestMethod.PATCH, RequestMethod.POST })
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateMarketProductInfo(@RequestParam Map<String, Object> params) {
		String json = FastJsonUtil.collectToString(params);
		ProductMarketing record = FastJsonUtil.toBean(json, ProductMarketing.class);
		String marketingIcon = (String) params.get("marketingIcon");
		String productId = (String) params.get("productId");
		// 修改产品表中的营销标签

		ProductInfo productInfo = new ProductInfo();
		productInfo.setId(Integer.parseInt(productId));
		productInfo.setProductNo(record.getProductNo());
		productInfo.setMarketingIcon(marketingIcon);
		Long num2 = productInfoService.updateProductInfo(productInfo);
		if (num2.longValue() < 1L) {
			this.logger.error("update product  by productno {} failed", record);
			throw new BaseException(ResponseCode.ERROR.getValue(), "营销图片更新失败");
		}
		if (record.getId() == null) {
			logger.info("保存新的营销模块");
			return productMarketingService.createProductMarketing(record);
		}
		this.logger.debug("start process request updateProductMoudle by id {} record", record);
		record.setUpdateTime(new Date());
		Long num = this.productMarketingService.updateProductMarketing(record);
		if (num.longValue() < 1L) {
			this.logger.error("update product marketing {} by id {} failed", record);
			throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
		}
		return MapUtils.EMPTY_MAP;
	}

}
