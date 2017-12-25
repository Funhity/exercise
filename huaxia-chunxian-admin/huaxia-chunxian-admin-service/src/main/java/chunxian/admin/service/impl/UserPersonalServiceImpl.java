/**
 * @项目名称:
 * @文件名称: UserPersonalService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserPersonal;
import chunxian.admin.service.UserPersonalService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserPersonalServiceImpl
 * @Description: UserPersonalServiceImpl
 * @author
 */
@Service
public class UserPersonalServiceImpl implements UserPersonalService {
    @Resource(name="mBaseDao")
    private MBaseDao<UserPersonal> mBaseDao;

    @Override
    public Long deleteUserPersonalById(Long id) {
        return mBaseDao.delete("userpersonal.delete", id);
    }

    @Override
    public Long createUserPersonal(UserPersonal record) {
        return mBaseDao.create("userpersonal.insert", record);
    }

    @Override
    public Long updateUserPersonal(UserPersonal record) {
        return mBaseDao.update("userpersonal.update", record);
    }

    @Override
    public List<UserPersonal> queryUserPersonalList(UserPersonal record) {
        return mBaseDao.findListByTemplate("userpersonal.selectByCondition", record);
    }

    @Override
    public PageInfo<UserPersonal> findUserPersonalPage(int pageNum, int pageSize, UserPersonal record) {
        return mBaseDao.findByQuery("userpersonal.selectByCondition", pageNum, pageSize, record);
    }
}