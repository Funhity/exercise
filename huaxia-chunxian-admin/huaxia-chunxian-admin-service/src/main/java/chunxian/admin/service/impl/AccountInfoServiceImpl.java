/**
 * @项目名称:
 * @文件名称: AccountInfoService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import chunxian.admin.service.AccountInfoService;
import chunxian.admin.dao.ConsumerDao;
import chunxian.admin.model.AccountInfo;
import chunxian.admin.model.Consumer;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Title: AccountInfoServiceImpl
 * @Description: AccountInfoServiceImpl
 * @author
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Resource(name="mBaseDao")
    private MBaseDao<AccountInfo> mBaseDao;
    
    @Resource(name="consumerDao")
    private ConsumerDao consumerDao;

    @Override
    public Long deleteAccountInfoById(Long id) {
        return mBaseDao.delete("accountinfo.delete", id);
    }

    @Override
    public Long createAccountInfo(AccountInfo record) {
        return mBaseDao.create("accountinfo.insert", record);
    }

    @Override
    public Long updateAccountInfo(AccountInfo record) {
        return mBaseDao.update("accountinfo.update", record);
    }

    @Override
    public List<AccountInfo> queryAccountInfoList(AccountInfo record) {
        return mBaseDao.findListByTemplate("accountinfo.selectByCondition", record);
    }

    @Override
    public PageInfo<AccountInfo> findAccountInfoPage(int pageNum, int pageSize, AccountInfo record) {
        return mBaseDao.findByQuery("accountinfo.selectByCondition", pageNum, pageSize, record);
    }
    
    @Override
    public PageInfo<Consumer> findConsumerPage(int pageNum, int pageSize, Consumer record) {
        return mBaseDao.findByQuery("consumer.selectAccountInfoByparams", pageNum, pageSize, record);
    }
    @Override
    public List<Consumer> queryConsumerList(Consumer record) {
        return mBaseDao.findListByTemplate("consumer.selectAccountInfoByparams", record);
    }
    
    @Override
    public List<AccountInfo> queryCountInfoList(AccountInfo record) {
        return mBaseDao.findListByTemplate("accountinfo.selectCountCondition", record);
    }
    
    @Override
    public List<Map<String, Object>> findListPage(Map map) {
        return consumerDao.getAllConsumerList(map);
    }
    
    @Override
    public PageInfo<AccountInfo> findReceiverPage(int pageNum, int pageSize, AccountInfo record) {
        return mBaseDao.findByQuery("accountinfo.selectReceiverByCondition", pageNum, pageSize, record);
    }
}