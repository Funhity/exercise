/**
 * @项目名称:
 * @文件名称: UserIdentityService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserIdentity;
import chunxian.admin.service.UserIdentityService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserIdentityServiceImpl
 * @Description: UserIdentityServiceImpl
 * @author
 */
@Service
public class UserIdentityServiceImpl implements UserIdentityService {
    @Resource(name="mBaseDao")
    private MBaseDao<UserIdentity> mBaseDao;

    @Override
    public Long deleteUserIdentityById(Long id) {
        return mBaseDao.delete("useridentity.delete", id);
    }

    @Override
    public Long createUserIdentity(UserIdentity record) {
        return mBaseDao.create("useridentity.insert", record);
    }

    @Override
    public Long updateUserIdentity(UserIdentity record) {
        return mBaseDao.update("useridentity.update", record);
    }

    @Override
    public List<UserIdentity> queryUserIdentityList(UserIdentity record) {
        return mBaseDao.findListByTemplate("useridentity.selectByCondition", record);
    }

    @Override
    public PageInfo<UserIdentity> findUserIdentityPage(int pageNum, int pageSize, UserIdentity record) {
        return mBaseDao.findByQuery("useridentity.selectByCondition", pageNum, pageSize, record);
    }
}