/**
 * @项目名称:
 * @文件名称: AccountInfoService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.AccountInfo;
import com.github.pagehelper.PageInfo;
import chunxian.admin.model.Consumer;

import java.util.List;
import java.util.Map;

/**
 * @Title: AccountInfoService
 * @Description: 账号信息服务
 * @author
 */
public interface AccountInfoService {
    /**
     * 删除账号信息
     * @param id
     * @return
     */
    Long deleteAccountInfoById(Long id);

    /**
     * 新增账号信息
     * @param record
     * @return
     */
    Long createAccountInfo(AccountInfo record);

    /**
     * 更新账号信息
     * @param record
     * @return
     */
    Long updateAccountInfo(AccountInfo record);

    /**
     * 查询账号信息列表
     * @param record
     * @return
     */
    List<AccountInfo> queryAccountInfoList(AccountInfo record);

    /**
     * 查询账号信息分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<AccountInfo> findAccountInfoPage(int pageNum, int pageSize, AccountInfo record);

    /**
     * 查询账号信息
     * @param map
     * @return
     */
    List<Map<String, Object>> findListPage(Map map);

    /**
     * 查询用户信息
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<Consumer> findConsumerPage(int pageNum, int pageSize, Consumer record);

    /**
     * 查询用户信息
     * @param record
     * @return
     */
    List<Consumer> queryConsumerList(Consumer record);

    /**
     * 查询账号信息
     * @param record
     * @return
     */
    List<AccountInfo> queryCountInfoList(AccountInfo record);

    /**
     * 查询账号信息
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<AccountInfo> findReceiverPage(int pageNum, int pageSize, AccountInfo record);
    
}