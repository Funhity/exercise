/**
 * @项目名称:
 * @文件名称: ProductInfoService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.constant.ResponseCode;
import chunxian.admin.model.ProductInfo;
import chunxian.admin.service.ProductInfoService;
import chunxian.admin.service.ProductMoudleService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.middleware.sdk.communication.PlatformMessageHandle;
import com.huaxia.middleware.sdk.model.MessageObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @Title: ProductInfoServiceImpl
 * @Description: ProductInfoServiceImpl
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    public static final String PRODUCTINFO_SELECT_BY_CONDITION = "productinfo.selectByCondition";
    public static final String PRODUCE_CODE = "ProduceCode";
    public static final String PRODUCT_NAME = "ProductName";
    public static final String MAX_AMOUNT = "MaxAmount";
    public static final String MIN_AMOUNT = "MinAmount";
    public static final String TERM = "Term";
    @Value("${synchronize.url}")
    private String synchronizeUrl;
    @Value("${public.key}")
    private String publicKey;
    @Value("${private.key}")
    private String privateKey;
    @Value("${page.size}")
    private int pageSize;
    @Autowired
    private ProductMoudleService productMouduleService;

    private Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);

    @Resource(name = "mBaseDao")
    private MBaseDao<ProductInfo> mBaseDao;

    @Override
    public Long deleteProductInfoById(Long id) {
        return mBaseDao.delete("productinfo.delete", id);
    }

    @Override
    public Long createProductInfo(ProductInfo record) {
        return mBaseDao.create("productinfo.insert", record);
    }

    @Override
    public Long updateProductInfo(ProductInfo record) {
        ProductInfo old = (ProductInfo) mBaseDao.findObjectByTemplate("productinfo.selectById", record.getId());
        if (old == null) {
            logger.error("Product Info {} {} is not exist", record.getProductNo(), record.getProductName());
            return 0L;
        }
        return mBaseDao.update("productinfo.update", record);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductInfo> queryProductInfoList(ProductInfo record) {
        return mBaseDao.findListByTemplate(PRODUCTINFO_SELECT_BY_CONDITION, record);
    }

    @Override
    public PageInfo<ProductInfo> findProductInfoPage(int pageNum, int pageSize, ProductInfo record) {
        return mBaseDao.findByQuery(PRODUCTINFO_SELECT_BY_CONDITION, pageNum, pageSize, record);
    }

    /**
     * 同步安硕产品到本地数据库中
     *
     * @param userName
     * @return
     */
    @Override
    public void synchronize(String userName) {
        String productListStr = "ProductList";
        String chunxian = "chunxian";

        Map<String, Object> paramMap = new HashMap<>(5);

        // 纯线产品类型 2005
        paramMap.put("ProductType", "2005");
        MessageObject messageObject = PlatformMessageHandle.execTradeTask(synchronizeUrl, "productList", chunxian,
                "pc", chunxian, privateKey, publicKey, paramMap);

        if (ResponseCode.SUCCESS.getValue().equals(messageObject.getHead().getResponseCode())) {
            return;
        }

        List<Map<String, Object>> synchronizedMaps = (List<Map<String, Object>>) messageObject.getBody()
                .get(productListStr);
        if (!(synchronizedMaps == null || synchronizedMaps.isEmpty())) {
            return;
        }

        List<ProductInfo> productInfoList = queryProductInfoList(new ProductInfo());

        sycnAnshuoProduct(userName, synchronizedMaps, productInfoList);
    }

    private void sycnAnshuoProduct(String userName, List<Map<String, Object>> synchronizedMaps, List<ProductInfo> productInfoList) {
        for (Map<String, Object> asProductMap : synchronizedMaps) {
            boolean exists = false;
            if (productInfoList != null && !productInfoList.isEmpty()) {
                for (ProductInfo productInfo : productInfoList) {
                    if (asProductMap.containsKey(productInfo.getProductNo())) {
                        updateAnshuoProduct(userName, productInfo, asProductMap);
                        exists = true;
                        break;
                    }
                }
            }

            if (!exists) {
                createAnshuoProduct(userName, asProductMap);
            }
        }
    }

    private void updateAnshuoProduct(String userName, ProductInfo productInfo, Map<String, Object> asProductMap) {
        productInfo.setProductName((String) asProductMap.get(PRODUCT_NAME));
        productInfo.setAnshuoproductName((String) asProductMap.get(PRODUCT_NAME));
        productInfo.setTerm(String.valueOf(asProductMap.get(TERM)));
        if (asProductMap.get(MAX_AMOUNT) != null && StringUtils.isNotEmpty(asProductMap.get(MAX_AMOUNT).toString())) {
            productInfo.setMaxAmcount(Double.valueOf(asProductMap.get(MAX_AMOUNT).toString()));
        }
        if (asProductMap.get(MIN_AMOUNT) != null && StringUtils.isNotEmpty(asProductMap.get(MIN_AMOUNT).toString())) {
            productInfo.setMinAmcount(Double.valueOf(asProductMap.get(MIN_AMOUNT).toString()));
        }
        productInfo.setUpdateUser(userName);
        productInfo.setAnshuoSyncTime(new Date());
        updateProductInfo(productInfo);
    }

    private void createAnshuoProduct(String userName, Map<String, Object> synchronizedMap) {
        ProductInfo pi = new ProductInfo();
        pi.setProductNo(synchronizedMap.get(PRODUCE_CODE).toString());
        pi.setProductName(synchronizedMap.get(PRODUCT_NAME).toString());
        pi.setAnshuoproductName(synchronizedMap.get(PRODUCT_NAME).toString());
        pi.setTerm(String.valueOf(synchronizedMap.get(TERM)));
        if (StringUtils.isNotEmpty(synchronizedMap.get(MAX_AMOUNT).toString())) {
            pi.setMaxAmcount(Double.valueOf(synchronizedMap.get(MAX_AMOUNT).toString()));
        }
        if (StringUtils.isNotEmpty(synchronizedMap.get(MIN_AMOUNT).toString())) {
            pi.setMinAmcount(Double.valueOf(synchronizedMap.get(MIN_AMOUNT).toString()));
        }
        pi.setCreateUser(userName);
        pi.setAnshuoCreateTime(new Date());
        createProductInfo(pi);
    }

}