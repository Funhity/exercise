/**
 * @项目名称:
 * @文件名称: ProductInfoController 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.productmanage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chunxian.admin.constant.AdminConstant;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;

import chunxian.admin.model.ProductInfo;
import chunxian.admin.service.ProductInfoService;
import chunxian.admin.utils.FastJsonUtil;

/**
 * @Title: ProductInfoController
 * @Description: ProductInfoController
 * @author zhuqingyang
 */
@Controller
@RequestMapping(value = "productmanage")
public class ProductInfoController {
	@Autowired
	ProductInfoService productInfoService;
	private Logger logger = LoggerFactory.getLogger(ProductInfoController.class);

	@RequestMapping(value = "productinfo", method = RequestMethod.GET)
	@Login(AuthenType.page)
	public String base() {
		return "productmanage/product";
	}

	@RequestMapping(value = "productinfos", method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public PageInfo<ProductInfo> listProductInfo(@RequestParam Map<String, Object> params) {

		logger.debug("start process request listProductInfo with params {}", params);
		int pageNum = 1;
		int pageSize = 10;
		if (params.get(AdminConstant.PAGE_NO) != null) {
			pageNum = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
		}
		if (params.get(AdminConstant.PAGE_SIZE) != null) {
			pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
		}

		String json = FastJsonUtil.collectToString(params);
		ProductInfo record = FastJsonUtil.toBean(json, ProductInfo.class);

		return productInfoService.findProductInfoPage(pageNum, pageSize, record);
	}

	@RequestMapping(value = "productinfos", method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addProductInfo(HttpServletRequest request, @RequestBody ProductInfo record) {
		logger.debug("start process request addProductInfo with record {}", record);
		if (record == null) {
			throw new BaseException("传入参数不对");
		}
		String userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
		record.setCreateUser(userName);
		productInfoService.createProductInfo(record);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "productinfos/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT})
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateProductInfo(HttpServletRequest request, @RequestBody ProductInfo record,
			@PathVariable Long id) {
		logger.debug("start process request updateProductInfo by id {} record", id, record);
		Long num = productInfoService.updateProductInfo(record);
		// 更新不成功
		if (num < 1) {
			logger.error("update product info {} by id {} failed", record, id);
			throw new BaseException("9999", "更新失败");
		}

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "productinfo/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Login(AuthenType.json)
	public Object deleteProductInfo(@PathVariable Long id) {
		logger.debug("start process request deleteProductInfo by id {}", id);
		if (id == null || id < 1) {
			logger.error("invaild param id {}", id);
			throw new BaseException("传入参数不对");
		}

		productInfoService.deleteProductInfoById(id);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "synchronize", method = { RequestMethod.PATCH, RequestMethod.POST })
	@ResponseBody
	@Login(AuthenType.json)
	public PageInfo<ProductInfo> synchronizeProductInfos(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		logger.debug("start process request synchronizeProductInfos");

		int pageNo = 1;
		int pageSize = 10;
		if (params.get(AdminConstant.PAGE_NO) != null) {
			pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
		}
		if (params.get(AdminConstant.PAGE_SIZE) != null) {
			pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
		}

		String userName = AdminConstant.SYSTEM_USER;
		if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
			userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
		}

		productInfoService.synchronize(userName);

		return productInfoService.findProductInfoPage(pageNo, pageSize, new ProductInfo());
	}
	
	
	
	@RequestMapping(value = "updateGetProductInfo", method = { RequestMethod.PATCH, RequestMethod.POST})
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateGetProductInfo(@RequestParam Map<String, Object> params) {
		logger.debug("start process request updateProductInfo by id {} record");
		String json = FastJsonUtil.collectToString(params);
		ProductInfo record = FastJsonUtil.toBean(json, ProductInfo.class);
		Long num = productInfoService.updateProductInfo(record);
		// 更新不成功
		if (num < 1) {
			logger.error("update product info {} by id {} failed", record);
			throw new BaseException("9999", "更新失败");
		}

		return MapUtils.EMPTY_MAP;
	}
	
}
