/**
 * @项目名称:
 * @文件名称: UserWorkService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.UserWork;

import java.util.List;

/**
 * UserWorkService接口
 * @author
 */
public interface UserWorkService {
    /**
     * 删除用户工作
     * @param id
     * @return
     */
    Long deleteUserWorkById(Long id);

    /**
     * 创建用户工作
     * @param record
     * @return
     */
    Long createUserWork(UserWork record);

    /**
     * 更新用户工作
     * @param record
     * @return
     */
    Long updateUserWork(UserWork record);

    /**
     * 查询用户工作列表
     * @param record
     * @return
     */
    List<UserWork> queryUserWorkList(UserWork record);

    /**
     * 查询用户工作分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserWork> findUserWorkPage(int pageNum, int pageSize, UserWork record);
}