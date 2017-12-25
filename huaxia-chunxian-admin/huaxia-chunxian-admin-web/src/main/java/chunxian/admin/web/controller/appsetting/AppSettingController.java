/**
 * @项目名称:
 * @文件名称: AppSettingController 版本号：1.0
 * @创建日期: 2017-12-07 16:40:12
 * <p>
 * Copyrights © 2017 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.appsetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.ResponseCode;
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

import chunxian.admin.model.JumpImageConfig;
import chunxian.admin.service.JumpImageConfigService;
import chunxian.admin.utils.FastJsonUtil;

/**
 * @Title: AppSettingController
 * @Description: AppSettingController
 * @author
 */
@Controller
@RequestMapping(value = "appsetting")
public class AppSettingController {
	public static final String ERROR_MESSAGE = "传入参数不对";
	@Autowired
	JumpImageConfigService jumpImageConfigService;
	private Logger logger = LoggerFactory.getLogger(AppSettingController.class);

	@RequestMapping(value = "main", method = RequestMethod.GET)
	@Login(AuthenType.page)
	public String base() {
		return "appsetting/appSetting";
	}

	@RequestMapping(value = "jumpimgconfs", method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public PageInfo<JumpImageConfig> listJumpImageConfig(@RequestParam Map<String, Object> params) {

		logger.debug("start process request listJumpImageConfig with params {}", params);
		int pageNum = 1;
		int pageSize = 10;
		if (params.get(AdminConstant.PAGE_NO) != null) {
			pageNum = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
		}
		if (params.get(AdminConstant.PAGE_SIZE) != null) {
			pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
		}

		String json = FastJsonUtil.collectToString(params);
		JumpImageConfig record = FastJsonUtil.toBean(json, JumpImageConfig.class);

		return jumpImageConfigService.findJumpImageConfigPage(pageNum, pageSize, record);
	}

	@RequestMapping(value = "jumpimgconfs", method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addJumpImageConfig(HttpServletRequest request, @RequestBody JumpImageConfig record) {
		logger.debug("start process request addJumpImageConfig with record {}", record);
		if (record == null) {
			throw new BaseException(ERROR_MESSAGE);
		}
		String userName = AdminConstant.SYSTEM_USER;
		if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
			userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
		}
		record.setCreateUser(userName);

		jumpImageConfigService.createJumpImageConfig(record);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "jumpimgconfs/cover", method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addCover(HttpServletRequest request, @RequestBody JumpImageConfig record) {
		logger.debug("start process request addCover with record {}", record);
		if (record == null) {
			throw new BaseException(ERROR_MESSAGE);
		}

		JumpImageConfig jumpImageConfigQuery = new JumpImageConfig();
		jumpImageConfigQuery.setType("5");
		List<JumpImageConfig> jumpImageConfigList = jumpImageConfigService.queryJumpImageConfigList(jumpImageConfigQuery);
		JumpImageConfig jumpImageConfig = record;
		String userName = AdminConstant.SYSTEM_USER;
		if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
			userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
		}
		if(jumpImageConfigList != null && !jumpImageConfigList.isEmpty()) {
			jumpImageConfig = jumpImageConfigList.get(0);
			jumpImageConfig.setImage(record.getImage());
			jumpImageConfig.setUpdateTime(new Date());
			jumpImageConfig.setUpdateUser(userName);
			jumpImageConfigService.updateJumpImageConfig(jumpImageConfig);
		} else {
			jumpImageConfig.setCreateTime(new Date());
			jumpImageConfig.setCreateUser(userName);
			jumpImageConfigService.createJumpImageConfig(jumpImageConfig);
		}

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "jumpimgconfs/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateJumpImageConfig(HttpServletRequest request, @RequestBody JumpImageConfig record,
			@PathVariable Long id) {
		String userName = AdminConstant.SYSTEM_USER;
		if(request.getAttribute(AdminConstant.RMS_USERNAME) != null) {
			userName = request.getAttribute(AdminConstant.RMS_USERNAME).toString();
		}
		record.setUpdateUser(userName);
		record.setUpdateTime(new Date());
		Long num = jumpImageConfigService.updateJumpImageConfig(record);
		// 更新不成功
		if (num < 1) {
			logger.error("update jumpimgconf {} by id {} failed", record, id);
			throw new BaseException(ResponseCode.ERROR.getValue(), "更新失败");
		}

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "jumpimgconfs/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Login(AuthenType.json)
	public Object deleteJumpImageConfig(@PathVariable Long id) {
		logger.debug("start process request deleteJumpImageConfig by id {}", id);
		if (id == null || id < 1) {
			logger.error("invaild param id {}", id);
			throw new BaseException(ERROR_MESSAGE);
		}

		jumpImageConfigService.deleteJumpImageConfigById(id, new Date());

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "jumpimgconfs/{id}", method = { RequestMethod.PATCH, RequestMethod.GET })
	@ResponseBody
	@Login(AuthenType.json)
	public Object getJumpImageConfig(@PathVariable Long id) {
		logger.debug("start process request getJumpImageConfig by id {}", id);

		return jumpImageConfigService.getJumpImageConfigById(id);
	}

}
