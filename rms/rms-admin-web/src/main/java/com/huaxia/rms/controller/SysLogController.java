package com.huaxia.rms.controller;

import com.huaxia.rms.core.service.RmsUserOperationLogService;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/log")
public class SysLogController {

	private final Logger logger = LoggerFactory.getLogger(SysLogController.class);

	@Autowired
	private RmsUserOperationLogService operationLogService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("sysLog.jhtml")
    @Login(AuthenType.page)
    public String sysLog() {
        return "base/log/log_list";
    }
    
//	/**
//	 * 查询操作日志
//	 * @param query
//	 * @return Result
//	 */
//    @RequestMapping("querySysLogList.json")
//    public @ResponseBody Result querySysLogList(@ModelAttribute SysLogQuery query) {
//    	Result result = new Result();
//    	int count = operationLogService.queryPageDataCount(query);
//    	if(count > 0){
//    		List<RmsUserOperationLog> sysLogList = operationLogService.queryPageData(query);
//    		result.setData(sysLogList);
//    	}
//    	query.setTotalItem(count);
//    	result.setQuery(query);
//        return result;
//    }
    
}
