/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: BaseTest 版本号：1.0
 * @创建日期: 2017年11月01日 20:44
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.test;

/**
 * 说明: 测试基类
 * @author xiaogui
 * @version
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context.xml"})
public class BaseTest {
    @Test
    public void init() {

    }
}
