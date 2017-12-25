/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: ResponseCode 版本号：1.0
 * @创建日期: 2017年12月19日 5:42
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.constant;

/**
 * 说明: 响应编码
 * @author xiaogui
 * @version
 */
public enum ResponseCode {
    /**
     * 成功编码
     */
    SUCCESS("成功", "0000"),
    ERROR("系统异常", "9999");

    ResponseCode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }
}
