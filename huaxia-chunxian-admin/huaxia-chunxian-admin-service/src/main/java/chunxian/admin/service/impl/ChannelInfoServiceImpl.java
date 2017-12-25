/**
 * @项目名称:
 * @文件名称: ChannelInfoService 版本号：1.0
 * @创建日期: 2017/12/8
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.ChannelInfo;
import chunxian.admin.service.ChannelInfoService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: ChannelInfoServiceImpl
 * @Description: ChannelInfoServiceImpl
 * @author
 */
@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {
    @Resource(name="mBaseDao")
    private MBaseDao<ChannelInfo> mBaseDao;

    @Override
    public Long deleteChannelInfoById(Long id) {
        return mBaseDao.delete("channelinfo.delete", id);
    }

    @Override
    public Long createChannelInfo(ChannelInfo record) {
        return mBaseDao.create("channelinfo.insert", record);
    }

    @Override
    public Long updateChannelInfo(ChannelInfo record) {
        return mBaseDao.update("channelinfo.update", record);
    }

    @Override
    public List<ChannelInfo> queryChannelInfoList(ChannelInfo record) {
        return mBaseDao.findListByTemplate("channelinfo.selectByCondition", record);
    }

    @Override
    public PageInfo<ChannelInfo> findChannelInfoPage(int pageNum, int pageSize, ChannelInfo record) {
        return mBaseDao.findByQuery("channelinfo.selectByCondition", pageNum, pageSize, record);
    }
}