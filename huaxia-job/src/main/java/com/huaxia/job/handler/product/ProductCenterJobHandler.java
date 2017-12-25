package com.huaxia.job.handler.product;

import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.huaxia.cai.center.product.api.ApiInstItemService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 说明: 产品执行执行器逻辑实现
 * @author Michael.Zhang
 */
@JobHander(value="productCenterJobHandler")
@Service
public class ProductCenterJobHandler extends IJobHandler {
    
    @Reference(group = "product-center", owner = "huaxia")
    private  ApiInstItemService apiInstItemService;
    
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		XxlJobLogger.log("================WELCOME TO THE PRODUCT CENTER JOB  Begin==================");
		try {
		    this.apiInstItemService.dealWithProductEffective();
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
		XxlJobLogger.log("================WELCOME TO THE PRODUCT CENTER JOB  END==================");
		return ReturnT.SUCCESS;
	}

}
