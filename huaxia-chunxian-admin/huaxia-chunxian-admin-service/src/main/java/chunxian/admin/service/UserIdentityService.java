/**
 * @项目名称:
 * @文件名称: UserIdentityService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.UserIdentity;

import java.util.List;

/**
 * @Title: UserIdentityService
 * @Description: 用户服务
 * @author
 */
public interface UserIdentityService {
    /**
     * 查询用户
     * @param id
     * @return
     */
    Long deleteUserIdentityById(Long id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    Long createUserIdentity(UserIdentity record);

    /**
     * 更新用户
     * @param record
     * @return
     */
    Long updateUserIdentity(UserIdentity record);

    /**
     * 查询用户列表
     * @param record
     * @return
     */
    List<UserIdentity> queryUserIdentityList(UserIdentity record);

    /**
     * 查询用户分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserIdentity> findUserIdentityPage(int pageNum, int pageSize, UserIdentity record);
}