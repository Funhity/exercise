/**
 * @项目名称:
 * @文件名称: NoticeService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import chunxian.admin.model.Notice;
import com.github.pagehelper.PageInfo;

/**
 * @Title: NoticeService
 * @Description: 公告服务
 * @author xiaogui
 */
public interface NoticeService {
    /**
     * 删除公告
     * @param id
     * @param date
     * @return
     */
    Long deleteNoticeById(Long id,Date date);

    /**
     * 新增公告
     * @param record
     * @return
     */
    Long createNotice(Notice record);

    /**
     * 更新公告
     * @param record
     * @return
     */
    Long updateNotice(Notice record);

    /**
     * 查询公告
     * @param condition
     * @return
     */
    List<Notice> queryNoticeList(Map<String,Object> condition);

    /**
     * 查询公告
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    PageInfo<Notice> findNoticePage(int pageNum, int pageSize, Map<String,Object> condition);

    /**
     * 查询公告
     * @param id
     * @return
     */
    Notice findNoticeById(Long id);
}