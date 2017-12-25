/**
 * @项目名称:
 * @文件名称: UserBankService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserBank;
import chunxian.admin.service.UserBankService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserBankServiceImpl
 * @Description: UserBankServiceImpl
 * @author
 */
@Service
public class UserBankServiceImpl implements UserBankService {
    @Resource(name="mBaseDao")
    private MBaseDao<UserBank> mBaseDao;

    @Override
    public Long deleteUserBankById(Long id) {
        return mBaseDao.delete("userbank.delete", id);
    }

    @Override
    public Long createUserBank(UserBank record) {
        return mBaseDao.create("userbank.insert", record);
    }

    @Override
    public Long updateUserBank(UserBank record) {
        return mBaseDao.update("userbank.update", record);
    }

    @Override
    public List<UserBank> queryUserBankList(UserBank record) {
        return mBaseDao.findListByTemplate("userbank.selectByCondition", record);
    }

    @Override
    public PageInfo<UserBank> findUserBankPage(int pageNum, int pageSize, UserBank record) {
        return mBaseDao.findByQuery("userbank.selectByCondition", pageNum, pageSize, record);
    }
}