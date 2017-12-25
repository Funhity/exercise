/**
 * @项目名称:
 * @文件名称: ConfigService 版本号：1.0
 * @创建日期: 2017-12-14 10:51:52
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;
import chunxian.admin.model.Config;
import java.util.List;

/**
 * @Title: 配置信息
 * @Description: 配置信息
 * @author
 */
public interface ConfigService {
    /**
     * 获取配置信息
     * @param id
     * @return
     */
    Config getConfigById(Long id);

    /**
     * 删除配置信息
     * @param id
     * @return
     */
    Long deleteConfigById(Long id);

    /**
     * 创建配置信息
     * @param record
     * @return
     */
    Long createConfig(Config record);

    /**
     * 更新配置信息
     * @param record
     * @return
     */
    Long updateConfig(Config record);

    /**
     * 查询配置信息列表
     * @param record
     * @return
     */
    List<Config> queryConfigList(Config record);

    /**
     * 查询配置信息分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<Config> findConfigPage(int pageNum, int pageSize, Config record);
}