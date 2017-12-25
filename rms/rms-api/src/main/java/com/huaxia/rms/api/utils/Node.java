package com.huaxia.rms.api.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 节点类
 */
public class Node {
    /**
     * 节点编号
     */
    public String id;
    /**
     * 节点内容
     */
    public String name;
    /**
     * 父节点编号
     */
    public String parentCode;

    public String iconIndex;

    public String url;

    /**
     * 孩子节点列表
     */
    private Children children = new Children();

    // 先序遍历，拼接JSON字符串
    public String toString() {

        String result = "{"
                + "id : \"" + id + "\""
                + ", text : \"" + (StringUtils.isNotBlank(name) ? name : "") + "\""
                + ", parentCode : \"" +  (StringUtils.isNotBlank(parentCode) ? parentCode : "") + "\""
                + ", icon : \"" + (StringUtils.isNotBlank(iconIndex) ? iconIndex : "") + "\""
                + ", url : \"" + (StringUtils.isNotBlank(url) ? url : "") + "\""
                + ", targetType : \"iframe-tab\"";

        if (children != null && children.getSize() != 0) {
            result += ", children : " + children.toString();
        } else {
            //result += ", leaf : true";
        }

        return result + "}";
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加孩子节点
    public void addChild(Node node) {
        this.children.addChild(node);
    }
}