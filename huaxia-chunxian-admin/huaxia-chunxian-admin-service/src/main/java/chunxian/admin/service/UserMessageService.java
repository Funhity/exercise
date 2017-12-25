/**
 * @项目名称:
 * @文件名称: JumpImageConfigService 版本号：1.0
 * @创建日期: 2017/12/13
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import chunxian.admin.model.UserMessage;

/**
 * 用户消息服务接口
 * 说明: 注释范例
 * @author gaoyouli
 */
public interface UserMessageService {
    /**
     * 查询用户信息列表
     * @param condition
     * @return List<UserMessage>
     */
    List<UserMessage> queryUserMessageList(Map<String,Object> condition);

    /**
     * 查询用户信息分页信息
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return PageInfo<UserMessage>
     */
    PageInfo<UserMessage> findUserMessagePage(int pageNum, int pageSize, Map<String,Object> condition);
}
