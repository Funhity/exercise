/**
 * @项目名称:
 * @文件名称: OrderService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.Order;
import chunxian.admin.service.OrderService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: OrderServiceImpl
 * @Description: OrderServiceImpl
 * @author
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource(name="mBaseDao")
    private MBaseDao<Order> mBaseDao;

    @Override
    public Long deleteOrderById(Long id) {
        return mBaseDao.delete("order.delete", id);
    }

    @Override
    public Long createOrder(Order record) {
        return mBaseDao.create("order.insert", record);
    }

    @Override
    public Long updateOrder(Order record) {
        return mBaseDao.update("order.update", record);
    }

    @Override
    public List<Order> queryOrderList(Order record) {
        return mBaseDao.findListByTemplate("order.selectByCondition", record);
    }

    @Override
    public PageInfo<Order> findOrderPage(int pageNum, int pageSize, Order record) {
        return mBaseDao.findByQuery("order.selectByCondition", pageNum, pageSize, record);
    }
}