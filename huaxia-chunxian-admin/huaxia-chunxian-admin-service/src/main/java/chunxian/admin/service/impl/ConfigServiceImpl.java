/**
 * @项目名称:
 * @文件名称: ConfigServiceImpl 版本号：1.0
 * @创建日期: 2017-12-14 10:51:52
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.Config;
import chunxian.admin.service.ConfigService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: ConfigServiceImpl
 * @Description: ConfigServiceImpl
 * @author
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource(name="mBaseDao")
    private MBaseDao<Config> mBaseDao;

    @Override
    public Config getConfigById(Long id) {
        return (Config) mBaseDao.findObjectByTemplate("config.selectById", id);
    }

    @Override
    public Long deleteConfigById(Long id) {
        return mBaseDao.delete("config.delete", id);
    }

    @Override
    public Long createConfig(Config record) {
        return mBaseDao.create("config.insert", record);
    }

    @Override
    public Long updateConfig(Config record) {
        return mBaseDao.update("config.update", record);
    }

    @Override
    public List<Config> queryConfigList(Config record) {
        return mBaseDao.findListByTemplate("config.selectByCondition", record);
    }

    @Override
    public PageInfo<Config> findConfigPage(int pageNum, int pageSize, Config record) {
        return mBaseDao.findByQuery("config.selectByCondition", pageNum, pageSize, record);
    }
}