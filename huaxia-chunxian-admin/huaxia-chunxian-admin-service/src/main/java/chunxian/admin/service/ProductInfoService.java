/**
 * @项目名称:
 * @文件名称: ProductInfoService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import chunxian.admin.model.ProductInfo;

/**
 * @Title: ProductInfoService
 * @Description: 产品信息服务
 * @author
 */
public interface ProductInfoService {
    /**
     * 删除产品信息
     * @param id
     * @return
     */
    Long deleteProductInfoById(Long id);

    /**
     * 新增产品信息
     * @param record
     * @return
     */
    Long createProductInfo(ProductInfo record);

    /**
     * 更新产品信息
     * @param record
     * @return
     */
    Long updateProductInfo(ProductInfo record);

    /**
     * 查询产品信息
     * @param record
     * @return
     */
    List<ProductInfo> queryProductInfoList(ProductInfo record);

    /**
     * 查询产品信息
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<ProductInfo> findProductInfoPage(int pageNum, int pageSize, ProductInfo record);

    /**
     * 同步安硕产品信息
     * @param userName
     * @return
     */
    void synchronize(String userName);
}