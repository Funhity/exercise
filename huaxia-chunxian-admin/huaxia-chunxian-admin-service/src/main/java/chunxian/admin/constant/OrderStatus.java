/**
 * @项目名称: huaxia-chunxian-admin
 * @文件名称: OrderStatus 版本号：1.0
 * @创建日期: 2017年12月19日 5:42
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.constant;

/**
 * 说明: 订单状态
 * 0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中
 * @author xiaogui
 * @version
 */
public enum OrderStatus {
    /**
     * 订单未提交
     */
    NO_SUBMIT("未提交", "0"),
    AUDITING("审核中", "1"),
    AUDIT("审核通过", "2"),
    FAIL_AUDIT("审核未通过", "3"),
    NEED_PATCH("需补件", "4"),
    CANCELED("已取消", "5"),
    WAITING_LOAN("等待放款", "6"),
    COMPLETE_LOAN("已放款", "7"),
    COMPLETE_PATCH("补件完成", "8"),
    COMPLETE_SIGN("签约完成", "9"),
    BANK_HANDLING("银行处理中", "10");

    OrderStatus(String name, String value) {
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
