/**
 * @项目名称:
 * @文件名称: UserAuthService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserAuth;
import chunxian.admin.service.UserAuthService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserAuthServiceImpl
 * @Description: UserAuthServiceImpl
 * @author
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Resource(name="mBaseDao")
    private MBaseDao<UserAuth> mBaseDao;

    @Override
    public Long deleteUserAuthById(Long id) {
        return mBaseDao.delete("userauth.delete", id);
    }

    @Override
    public Long createUserAuth(UserAuth record) {
        return mBaseDao.create("userauth.insert", record);
    }

    @Override
    public Long updateUserAuth(UserAuth record) {
        return mBaseDao.update("userauth.update", record);
    }

    @Override
    public List<UserAuth> queryUserAuthList(UserAuth record) {
        return mBaseDao.findListByTemplate("userauth.selectByCondition", record);
    }

    @Override
    public PageInfo<UserAuth> findUserAuthPage(int pageNum, int pageSize, UserAuth record) {
        return mBaseDao.findByQuery("userauth.selectByCondition", pageNum, pageSize, record);
    }
    

    @Override
    public PageInfo<UserAuth> signByCondition(int pageNum, int pageSize, UserAuth record) {
        return mBaseDao.findByQuery("userauth.selectSignByCondition", pageNum, pageSize, record);
    }
}