/**
 * @项目名称:
 * @文件名称: ConsumerServiceImpl 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.Consumer;
import chunxian.admin.service.ConsumerService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @Title: ConsumerServiceImpl
 * @Description: ConsumerServiceImpl
 * @author
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public PageInfo<Consumer> findConsumerPage(int pageNum, int pageSize, Consumer record) {
		return null;
	}
	
}
