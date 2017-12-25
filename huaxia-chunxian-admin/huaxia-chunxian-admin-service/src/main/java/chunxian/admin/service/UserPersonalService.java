/**
 * @项目名称:
 * @文件名称: UserPersonalService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.UserPersonal;

import java.util.List;

/**
 * @Title: UserPersonalService
 * @Description: 用户信息服务
 * @author
 */
public interface UserPersonalService {
    /**
     * 删除用户信息
     * @param id
     * @return
     */
    Long deleteUserPersonalById(Long id);

    /**
     * 新增用户信息
     * @param record
     * @return
     */
    Long createUserPersonal(UserPersonal record);

    /**
     * 更新用户信息
     * @param record
     * @return
     */
    Long updateUserPersonal(UserPersonal record);

    /**
     * 查询用户信息列表
     * @param record
     * @return
     */
    List<UserPersonal> queryUserPersonalList(UserPersonal record);

    /**
     * 查询用户信息分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserPersonal> findUserPersonalPage(int pageNum, int pageSize, UserPersonal record);
}