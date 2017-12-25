/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: MessageListener 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.consumer;

import com.huaxia.rocketmq.message.model.Message;

/**
 * 说明: 监听器
 * @author guojiandong
 * @version 1.0.0
 */
public interface MessageListener {

    void onMessage(Object message);
}
