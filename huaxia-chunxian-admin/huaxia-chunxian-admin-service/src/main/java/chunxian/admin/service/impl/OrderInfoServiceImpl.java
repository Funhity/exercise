/**
 * @项目名称:
 * @文件名称: OrderInfoService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.dao.OrderInfoDoDao;
import chunxian.admin.model.OrderInfo;
import chunxian.admin.model.OrderInfoDO;
import chunxian.admin.service.OrderInfoService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @Title: OrderInfoServiceImpl
 * @Description: OrderInfoServiceImpl
 * @author
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
	@Resource(name = "mBaseDao")
	private MBaseDao<OrderInfo> mBaseDao;

	@Resource(name = "orderInfoDoDao")
	private OrderInfoDoDao orderInfoDoDao;

	@Override
	public Long deleteOrderInfoById(Long id) {
		return mBaseDao.delete("orderinfo.delete", id);
	}

	@Override
	public Long createOrderInfo(OrderInfo record) {
		return mBaseDao.create("orderinfo.insert", record);
	}

	@Override
	public Long updateOrderInfo(OrderInfo record) {
		return mBaseDao.update("orderinfo.update", record);
	}

	@Override
	public List<OrderInfo> queryOrderInfoList(OrderInfo record) {
		return mBaseDao.findListByTemplate("orderinfo.selectByCondition", record);
	}

	@Override
	public PageInfo<OrderInfo> findOrderInfoPage(int pageNum, int pageSize, OrderInfo record) {
		return mBaseDao.findByQuery("orderinfo.selectByCondition", pageNum, pageSize, record);
	}

	@Override
	public PageInfo<OrderInfoDO> findOrderDoInfoPage(int pageNum, int pageSize, OrderInfoDO record) {
		return mBaseDao.findByQuery("orderinfodo.selectorderInfoByparams", pageNum, pageSize, record);
	}

	@Override
	public List<OrderInfoDO> queryOrderDoList(OrderInfoDO record) {
		return mBaseDao.findListByTemplate("orderinfodo.selectorderInfoByparams", record);
	}

	@Override
	public List<OrderInfo> queryOrderDoInfoList(Map<String, Object> paramterMap) {
		return orderInfoDoDao.getOrderInfoDOList(paramterMap);
	}

	@Override
	public List<OrderInfoDO> queryCountOrderInfoList(OrderInfoDO record) {
		return mBaseDao.findListByTemplate("orderinfodo.selectCountOrderCondition", record);
	}

}