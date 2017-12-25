/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: MQException 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.exception;

/**
 * 说明: 消息异常
 * @author guojiandong
 * @version 1.0.0
 */
public class MQException extends RuntimeException {

    private String code = null; //异常代码

    /**
     * 构造函数
     */
    public MQException() {
        super();
    }

    /**
     * 构造函数
     * @param code 异常编码
     * @param message 异常信息
     */
    public MQException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数
     * @param message 异常信息
     * @param cause 异常类
     */
    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造函数
     * @param message 异常信息
     */
    public MQException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param cause 异常类
     */
    public MQException(Throwable cause) {
        super(cause);
    }

    protected String getCode() {
        return code;
    }
}
