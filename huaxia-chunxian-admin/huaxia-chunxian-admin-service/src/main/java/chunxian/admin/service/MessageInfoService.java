/**
 * @项目名称:
 * @文件名称: MessageInfoService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.MessageInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: MessageInfoService
 * @Description: 个人消息服务
 * @author xiaogui
 */
public interface MessageInfoService {
    /**
     * 删除个人消息
     * @param id
     * @return
     */
    Long deleteMessageInfoById(Long id);

    /**
     * 新增个人消息
     * @param record
     * @return
     */
    Long createMessageInfo(MessageInfo record);

    /**
     * 更新个人消息
     * @param record
     * @return
     */
    Long updateMessageInfo(MessageInfo record);

    /**
     * 查询个人消息
     * @param record
     * @return
     */
    List<MessageInfo> queryMessageInfoList(MessageInfo record);

    /**
     * 查询个人消息
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<MessageInfo> findMessageInfoPage(int pageNum, int pageSize, MessageInfo record);
}