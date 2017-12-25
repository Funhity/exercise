/**
 * @项目名称:
 * @文件名称: OrderInfoService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.OrderInfo;
import chunxian.admin.model.OrderInfoDO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Title: OrderInfoService
 * @Description: 订单
 * @author zhuqingyang
 */
public interface OrderInfoService {
    /**
     * 删除订单
     * @param id
     * @return
     */
    Long deleteOrderInfoById(Long id);

    /**
     * 新建订单
     * @param record
     * @return
     */
    Long createOrderInfo(OrderInfo record);

    /**
     * 更新订单
     * @param record
     * @return
     */
    Long updateOrderInfo(OrderInfo record);

    /**
     * 查询订单列表
     * @param record
     * @return
     */
    List<OrderInfo> queryOrderInfoList(OrderInfo record);

    /**
     * 查询订单分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<OrderInfo> findOrderInfoPage(int pageNum, int pageSize, OrderInfo record);

    /**
     * 查询订单分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<OrderInfoDO> findOrderDoInfoPage(int pageNum, int pageSize, OrderInfoDO record);

    /**
     * 查询订单列表
     * @param record
     * @return
     */
    List<OrderInfoDO> queryOrderDoList(OrderInfoDO record);

    /**
     * 查询订单列表
     * @param map
     * @return
     */
    List<OrderInfo> queryOrderDoInfoList(Map<String, Object> map);

    /**
     * 查询订单总数
     * @param record
     * @return
     */
    List<OrderInfoDO> queryCountOrderInfoList(OrderInfoDO record);
}