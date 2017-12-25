/**
 * @项目名称:
 * @文件名称: ConsumerDao 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.dao;

import java.util.List;
import java.util.Map;

import chunxian.admin.model.AccountInfo;

/**
 * @Title: ConsumerDao
 * @Description: ConsumerDao
 * @author
 */
public interface ConsumerDao   {
 /**
  * 用户信息
  * @param map
  * @return List<Map<String, Object>>
  */
 public List<Map<String, Object>> getAllConsumerList(Map map);

 /**
  * 账户列表
  * @param map
  * @return List<AccountInfo>
  */
 public List<AccountInfo> getAllCountList(Map map);

}
