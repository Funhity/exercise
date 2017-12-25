/**
 * @项目名称:
 * @文件名称: UserBankService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.UserBank;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: UserBankService
 * @Description: 银行卡信息服务
 * @author
 */
public interface UserBankService {
    /**
     * 删除银行卡
     * @param id
     * @return
     */
    Long deleteUserBankById(Long id);

    /**
     * 新增银行卡
     * @param record
     * @return
     */
    Long createUserBank(UserBank record);

    /**
     * 更新银行卡
     * @param record
     * @return
     */
    Long updateUserBank(UserBank record);

    /**
     * 查询银行卡列表
     * @param record
     * @return
     */
    List<UserBank> queryUserBankList(UserBank record);

    /**
     * 查询银行卡分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<UserBank> findUserBankPage(int pageNum, int pageSize, UserBank record);
}