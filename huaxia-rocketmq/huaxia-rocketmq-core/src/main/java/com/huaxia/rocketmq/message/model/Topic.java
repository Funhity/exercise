/**
 * @项目名称: huaxia-rocketmq
 * @文件名称: Topic 版本号：1.0.0
 * @创建日期: 2017年10月09日 11:15
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rocketmq.message.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明: 主题
 * @author guojiandong
 * @version 1.0.0
 */
public class Topic {

    /**
     * 主题名称
     */
    private String name;
    /**
     * 子主题表达式：全部（*），单个（tagA），组合（tagA || tagB），默认*
     */
    private String subExpression = "*";

    @Override
    public String toString() {
        return "Topic [name=" + name + ", subExpression=" + subExpression + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }
}
