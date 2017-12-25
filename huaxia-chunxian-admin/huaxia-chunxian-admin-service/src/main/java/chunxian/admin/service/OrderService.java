/**
 * @项目名称:
 * @文件名称: OrderService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: OrderService
 * @Description: 订单服务
 * @author
 */
public interface OrderService {
    /**
     * 删除订单
     * @param id
     * @return
     */
    Long deleteOrderById(Long id);

    /**
     * 创建订单
     * @param record
     * @return
     */
    Long createOrder(Order record);

    /**
     * 更新订单
     * @param record
     * @return
     */
    Long updateOrder(Order record);

    /**
     * 查询订单列表
     * @param record
     * @return
     */
    List<Order> queryOrderList(Order record);

    /**
     * 查询订单分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<Order> findOrderPage(int pageNum, int pageSize, Order record);
}