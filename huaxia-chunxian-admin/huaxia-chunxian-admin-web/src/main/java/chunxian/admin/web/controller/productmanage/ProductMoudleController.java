/**
 * @项目名称:
 * @文件名称: ProductMoudleController 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.productmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.ProductMoudle;
import chunxian.admin.utils.FastJsonUtil;
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

import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;

import chunxian.admin.service.ProductMoudleService;

/**
 * @Title: ProductMoudleController
 * @Description: ProductMoudleController
 * @author
 */
@Controller
@RequestMapping(value = "productmodulemanage")
public class ProductMoudleController {

	@Autowired
	ProductMoudleService productMoudleService;
	private Logger logger = LoggerFactory.getLogger(ProductInfoController.class);

	@RequestMapping(value = "productmoudle", method = RequestMethod.GET)
	@Login(AuthenType.page)
	public String base() {
		return "productmoudle/productmoudle";
	}

	@RequestMapping(value = "productmoudles", method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public List<ProductMoudle> listProductModule(@RequestParam Map<String, Object> params) {

		logger.debug("start process request listProductModule with params {}", params);
		String json = FastJsonUtil.collectToString(params);
		ProductMoudle record = FastJsonUtil.toBean(json, ProductMoudle.class);

		return productMoudleService.queryProductMoudleList(record);
	}

	@RequestMapping(value = "productmoudles", method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addProductMoudle(@RequestBody ProductMoudle record) {
		logger.debug("start process request addProductMoudle with record {}", record);
		if (record == null) {
			throw new BaseException("传入参数不对");
		}
		productMoudleService.createProductMoudle(record);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "productmoudles/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateProductMoudle(@RequestBody ProductMoudle record, @PathVariable Long id) {
		logger.debug("start process request updateProductMoudle by id {} record", id, record);
		record.setUpdateTime(new Date());
		Long num = productMoudleService.updateProductMoudle(record);
		// 更新不成功
		if (num < 1) {
			logger.error("update product moudle {} by id {} failed", record, id);
			throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
		}

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "productmoudles/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Login(AuthenType.json)
	public Object deleteProductMoudle(@PathVariable Long id) {
		logger.debug("start process request deleteProductMoudle by id {}", id);
		if (id == null || id < 1) {
			logger.error("invaild param id {}", id);
			throw new BaseException("传入参数不对");
		}

		productMoudleService.deleteProductMoudleById(id);

		return MapUtils.EMPTY_MAP;
	}
	
	  @RequestMapping(value={"updateModuleProductInfo"}, method={RequestMethod.PATCH, RequestMethod.POST})
	  @ResponseBody
	  @Login(AuthenType.json)
	  public Object updateGetProductInfo(@RequestParam Map<String, Object> params)
	  {
	    String json = FastJsonUtil.collectToString(params);
	    ProductMoudle record = FastJsonUtil.toBean(json, ProductMoudle.class);
	    if(record.getId()==null ){
	    	logger.info("保存新的模块");
	    	return productMoudleService.createProductMoudle(record);
	    	
	    }
	    this.logger.debug("start process request updateProductMoudle by id {} record", record);
	    record.setUpdateTime(new Date());
	    Long num = this.productMoudleService.updateProductMoudle(record);
	    if (num.longValue() < 1L)
	    {
	      this.logger.error("update product moudle {} by id {} failed", record);
	      throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
	    }
	    return MapUtils.EMPTY_MAP;
	  }
	

}
