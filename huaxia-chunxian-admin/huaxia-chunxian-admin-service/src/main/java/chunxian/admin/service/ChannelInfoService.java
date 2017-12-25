/**
 * @项目名称:
 * @文件名称: ChannelInfoService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import chunxian.admin.model.ChannelInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Title: ChannelInfoService接口
 * @Description: ChannelInfoService接口
 * @author
 */
public interface ChannelInfoService {
    /**
     * 删除渠道
     * @param id
     * @return
     */
    Long deleteChannelInfoById(Long id);

    /**
     * 创建渠道
     * @param record
     * @return
     */
    Long createChannelInfo(ChannelInfo record);

    /**
     * 更新渠道
     * @param record
     * @return
     */
    Long updateChannelInfo(ChannelInfo record);

    /**
     * 查询渠道列表
     * @param record
     * @return
     */
    List<ChannelInfo> queryChannelInfoList(ChannelInfo record);

    /**
     * 查询渠道分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<ChannelInfo> findChannelInfoPage(int pageNum, int pageSize, ChannelInfo record);
}