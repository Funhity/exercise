/**
 * @项目名称:
 * @文件名称: OrderInfoDoImpl 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.dao.impl;

import java.util.List;
import java.util.Map;

import chunxian.admin.model.OrderInfo;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import chunxian.admin.dao.OrderInfoDoDao;

/**
 * @Title: OrderInfoDoImpl
 * @Description: OrderInfoDoImpl
 * @author
 */
@SuppressWarnings("rawtypes")
public class OrderInfoDoImpl extends SqlSessionDaoSupport implements OrderInfoDoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAllConsumerList(Map map) {
		return super.getSqlSession().selectList("selectAccountInfoByparams",map);
	}
	
	
	@Override
	public List<OrderInfo> getOrderInfoDOList(Map<String, Object> map) {
		return super.getSqlSession().selectList("selectOrderInfoDOByparams",map);
	}

}
