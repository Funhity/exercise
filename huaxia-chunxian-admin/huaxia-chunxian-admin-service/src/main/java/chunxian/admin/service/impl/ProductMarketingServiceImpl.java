/**
 * @项目名称:
 * @文件名称: ProductMarketingServiceImpl 版本号：1.0
 * @创建日期: 2017/12/19
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import chunxian.admin.model.ProductMarketing;
import chunxian.admin.service.ProductMarketingService;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明: ProductMarketingService
 * @author zhuqingyang
 * @version 1.0
 */
@Service
public class ProductMarketingServiceImpl implements ProductMarketingService {
    @Resource(name="mBaseDao")
    private MBaseDao<ProductMarketing> mBaseDao;
	private Logger logger = LoggerFactory.getLogger(ProductMarketingServiceImpl.class);

	@Override
    public Long createProductMarketing(ProductMarketing record)
    {
      return Long.valueOf(this.mBaseDao.create("productmarketing.insert", record));
    }
    
    @Override
    public List<ProductMarketing> queryProductMarketingList(ProductMarketing record) {
        return mBaseDao.findListByTemplate("productmarketing.selectByCondition", record);
    }

	@Override
	public Long updateProductMarketing(ProductMarketing record) {
		ProductMarketing old = (ProductMarketing) mBaseDao.findObjectByTemplate("productmarketing.selectById", record.getId());
		if (old == null) {
			logger.error("Product Moudle {} is not exist", record.getId());
			return 0L;
		}
		return mBaseDao.update("productmarketing.update", record);
	}
}