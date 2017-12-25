/**
 * @项目名称:
 * @文件名称: ProductMoudleService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.JumpImageConfig;
import chunxian.admin.model.ProductMoudle;
import chunxian.admin.service.ProductMoudleService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: ProductMoudleServiceImpl
 * @Description: ProductMoudleServiceImpl
 * @author
 */
@Service
public class ProductMoudleServiceImpl implements ProductMoudleService {
	private Logger logger = LoggerFactory.getLogger(ProductMoudleServiceImpl.class);

	@Resource(name = "mBaseDao")
	private MBaseDao<ProductMoudle> mBaseDao;

	@Override
	public Long deleteProductMoudleById(Long id) {
		return mBaseDao.delete("productmoudle.delete", id);
	}

	@Override
	public Long createProductMoudle(ProductMoudle record) {
		ProductMoudle sortRecord = new ProductMoudle();
		sortRecord.setSort(record.getSort());
		Integer count = countBySort(sortRecord);
		if(count > 0) {
			//sort存在,低位到高位规则，存在sort及之后+1
			updateSortByLowToHigh(sortRecord);
		}
		return mBaseDao.create("productmoudle.insert", record);
	}

	@Override
	public Long updateProductMoudle(ProductMoudle record) {

		ProductMoudle oldRecord = (ProductMoudle) mBaseDao.findObjectByTemplate("productmoudle.selectById", record.getId());
		if (oldRecord == null) {
			logger.error("Product Moudle {} is not exist", record.getId());
			return 0L;
		}
		Integer oldSort = oldRecord.getSort();
		ProductMoudle sortRecord = new ProductMoudle();
		sortRecord.setSort(record.getSort());
		Integer count = countBySort(sortRecord);

		if(count > 0) {
			ProductMoudle hlRecord = new ProductMoudle();
			hlRecord.setSort(oldSort);
			hlRecord.setToSort(record.getSort());
			if(oldSort < record.getSort()) {
				//sort低位到高位规则，存在sort及之后+1
				updateSortByLowToHigh(hlRecord);
			} else {
				//高位到低位规则,低位到高位之间+1
				updateSortByHighToLow(hlRecord);
			}
		}
		
		
		return mBaseDao.update("productmoudle.update", record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMoudle> queryProductMoudleList(ProductMoudle record) {
		return mBaseDao.findListByTemplate("productmoudle.selectByCondition", record);
	}

	@Override
	public PageInfo<ProductMoudle> findProductMoudlePage(int pageNum, int pageSize, ProductMoudle record) {
		return mBaseDao.findByQuery("productmoudle.selectByCondition", pageNum, pageSize, record);
	}
	
	
	@Override
	public Integer countBySort(ProductMoudle record) {
		return (Integer) mBaseDao.findObjectByTemplate("productmoudle.countBySort", record);
	}
	
	@Override
	public Long updateSortByLowToHigh(ProductMoudle record) {
		return mBaseDao.update("productmoudle.updateSortByLowToHigh", record);
	}

	@Override
	public Long updateSortByHighToLow(ProductMoudle record) {
		return mBaseDao.update("productmoudle.updateSortByHighToLow", record);
	}
	
}