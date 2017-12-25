/**
 * @项目名称:
 * @文件名称: UserWorkService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.UserWork;
import chunxian.admin.service.UserWorkService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: UserWorkServiceImpl
 * @Description: UserWorkServiceImpl
 * @author
 */
@Service
public class UserWorkServiceImpl implements UserWorkService {
    @Resource(name="mBaseDao")
    private MBaseDao<UserWork> mBaseDao;

    @Override
    public Long deleteUserWorkById(Long id) {
        return mBaseDao.delete("userwork.delete", id);
    }

    @Override
    public Long createUserWork(UserWork record) {
        return mBaseDao.create("userwork.insert", record);
    }

    @Override
    public Long updateUserWork(UserWork record) {
        return mBaseDao.update("userwork.update", record);
    }

    @Override
    public List<UserWork> queryUserWorkList(UserWork record) {
        return mBaseDao.findListByTemplate("userwork.selectByCondition", record);
    }

    @Override
    public PageInfo<UserWork> findUserWorkPage(int pageNum, int pageSize, UserWork record) {
        return mBaseDao.findByQuery("userwork.selectByCondition", pageNum, pageSize, record);
    }
}