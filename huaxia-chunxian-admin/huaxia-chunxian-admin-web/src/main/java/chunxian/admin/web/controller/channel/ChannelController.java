/**
 * @项目名称:
 * @文件名称: ChannelController 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.channel;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.model.ChannelInfo;
import chunxian.admin.service.ChannelInfoService;
import chunxian.admin.utils.FastJsonUtil;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.common.BaseException;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Title: ChannelController
 * @Description: ChannelController
 * @author
 */
@Controller
@RequestMapping({ "channel" })
public class ChannelController {
	@Autowired
	ChannelInfoService channelService;

	@RequestMapping(value = { "channelInfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@Login(AuthenType.page)
	public String base() {
		return "channel/channelInfo";
	}

	/**
	 * 查询渠道信息
	 * 
	 * @param params
	 * @return
	 */

	@RequestMapping(value = { "channellist" }, method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public PageInfo<ChannelInfo> listDemoUser(@RequestParam Map<String, Object> params) {
		int pageNo = 1;
		int pageSize = 10;
		if (params.get(AdminConstant.PAGE_NO) != null) {
			pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
		}
		if (params.get(AdminConstant.PAGE_SIZE) != null) {
			pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
		}
		String json = FastJsonUtil.collectToString(params);
		ChannelInfo record = FastJsonUtil.toBean(json, ChannelInfo.class);

		return this.channelService.findChannelInfoPage(pageNo, pageSize, record);
	}

	/**
	 * 新增渠道信息
	 * 
	 * @param channelInfo
	 * @return
	 */

	@RequestMapping(value = "addChannel", method = RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object addChannelInfo(ChannelInfo channelInfo) {
		channelService.createChannelInfo(channelInfo);

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = "addChannel/{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateChannelInfo(@RequestBody ChannelInfo channelInfo, @PathVariable Long id) {

		channelInfo.setId(Integer.parseInt(String.valueOf(id)));
		Long num = channelService.updateChannelInfo(channelInfo);
		// 更新不成功
		if (num < 1) {
			throw new BaseException("更新不成功");
		}

		return MapUtils.EMPTY_MAP;
	}

	@RequestMapping(value = { "creatChannelCode" }, method = RequestMethod.GET)
	@ResponseBody
	@Login(AuthenType.json)
	public Object creatChannelCode(@RequestParam Map<String, Object> params) {
		Map<String, String> map = new HashMap<>(2);
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");

		map.put("value", uuidStr);
		return map;

	}
}
