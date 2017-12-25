/**
 * @项目名称:
 * @文件名称: ConsumerService 版本号：1.0
 * @创建日期: 2017-12-14 10:51:52
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service;

import com.github.pagehelper.PageInfo;

import chunxian.admin.model.Consumer;

/**
 * @Title: ConsumerService
 * @Description: ConsumerService
 * @author
 */
public interface ConsumerService {
    /**
     * 查询用户分页
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<Consumer> findConsumerPage(int pageNum, int pageSize, Consumer record);
}
