/**
 * @项目名称:
 * @文件名称: UserAuthService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.UserAuth;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: UserAuthService
 * @Description: 用户权限
 * @author
 */
public interface UserAuthService {
    /**
     * 删除用户权限
     * @param id
     * @return
     */
    Long deleteUserAuthById(Long id);

    /**
     * 新增用户权限
     * @param record
     * @return
     */
    Long createUserAuth(UserAuth record);

    /**
     * 更新用户权限
     * @param record
     * @return
     */
    Long updateUserAuth(UserAuth record);

    /**
     * 查询用户权限列表
     * @param record
     * @return
     */
    List<UserAuth> queryUserAuthList(UserAuth record);

    /**
     * 查询用户权限分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserAuth> findUserAuthPage(int pageNum, int pageSize, UserAuth record);

    /**
     * 用户授权
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserAuth> signByCondition(int pageNum, int pageSize, UserAuth record);
}