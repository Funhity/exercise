/**
 * @项目名称:
 * @文件名称: ProductMoudleService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.ProductMoudle;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: ProductMoudleService
 * @Description: 产品模块服务
 * @author
 */
public interface ProductMoudleService {
    /**
     * 查询产品模块
     * @param id
     * @return
     */
    Long deleteProductMoudleById(Long id);

    /**
     * 新增产品模块
     * @param record
     * @return
     */
    Long createProductMoudle(ProductMoudle record);

    /**
     * 更新产品模块
     * @param record
     * @return
     */
    Long updateProductMoudle(ProductMoudle record);

    /**
     * 查询产品模块列表
     * @param record
     * @return
     */
    List<ProductMoudle> queryProductMoudleList(ProductMoudle record);

    /**
     * 查询产品模块分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<ProductMoudle> findProductMoudlePage(int pageNum, int pageSize, ProductMoudle record);
    
    public Integer countBySort(ProductMoudle record) ;
    
    
    public Long updateSortByLowToHigh(ProductMoudle record);
    
    public Long updateSortByHighToLow(ProductMoudle record);
}