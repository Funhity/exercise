/**
 * @项目名称:
 * @文件名称: ProductMarketingService 版本号：1.0
 * @创建日期: 2017/12/19
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.ProductMarketing;

import java.util.List;

/**
 * 说明: ProductMarketingService
 * @author zhuqingyang
 * @version 1.0
 */
public interface ProductMarketingService {
    /**
     * 新增产品营销信息
     * @param record
     * @return
     */
	Long createProductMarketing(ProductMarketing record);

    /**
     * 产品查询
     * @param record
     * @return
     */
    List<ProductMarketing> queryProductMarketingList(ProductMarketing record);

    /**
     * 更新产品营销信息
     * @param record
     * @return
     */
    Long updateProductMarketing(ProductMarketing record);
}