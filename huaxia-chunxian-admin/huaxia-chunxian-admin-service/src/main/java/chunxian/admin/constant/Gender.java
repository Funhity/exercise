/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: Gender 版本号：1.0
 * @创建日期: 2017年12月19日 5:42
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.constant;

/**
 * 说明: 性别
 * @author xiaogui
 * @version
 */
public enum Gender {
    /**
     * 性别未知
     */
    UN_KNOWN("未知", "0"),
    MAN("男", "1"),
    WOMAN("女", "2");

    Gender(String name, String value) {
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
