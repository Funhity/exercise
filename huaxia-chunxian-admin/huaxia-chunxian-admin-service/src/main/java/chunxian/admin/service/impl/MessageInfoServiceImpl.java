/**
 * @项目名称:
 * @文件名称: MessageInfoService 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.MessageInfo;
import chunxian.admin.service.MessageInfoService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: MessageInfoServiceImpl
 * @Description: MessageInfoServiceImpl
 * @author
 */
@Service
public class MessageInfoServiceImpl implements MessageInfoService {
    @Resource(name="mBaseDao")
    private MBaseDao<MessageInfo> mBaseDao;

    @Override
    public Long deleteMessageInfoById(Long id) {
        return mBaseDao.delete("messageinfo.delete", id);
    }

    @Override
    public Long createMessageInfo(MessageInfo record) {
        return mBaseDao.create("messageinfo.insert", record);
    }

    @Override
    public Long updateMessageInfo(MessageInfo record) {
        return mBaseDao.update("messageinfo.update", record);
    }

    @Override
    public List<MessageInfo> queryMessageInfoList(MessageInfo record) {
        return mBaseDao.findListByTemplate("messageinfo.selectByCondition", record);
    }

    @Override
    public PageInfo<MessageInfo> findMessageInfoPage(int pageNum, int pageSize, MessageInfo record) {
        return mBaseDao.findByQuery("messageinfo.selectByCondition", pageNum, pageSize, record);
    }
}