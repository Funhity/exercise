/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: NoticeSendType 版本号：1.0
 * @创建日期: 2017年12月19日 5:42
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.constant;

/**
 * 说明: 公告发送状态
 * @author xiaogui
 * @version
 */
public enum NoticeSendType {
    /**
     * 公告已发送
     */
    ALREADY_SEND("已发送", "1"),
    WAITING_SEND("待发送", "2");

    NoticeSendType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
