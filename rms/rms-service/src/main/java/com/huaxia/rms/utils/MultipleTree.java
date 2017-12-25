package com.huaxia.rms.utils;

import com.huaxia.rms.pojo.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 多叉树类
 */
public class MultipleTree {

    private static Logger logger = LoggerFactory.getLogger(MultipleTree.class);

    public static String getDisorderRecord(HashMap recordMap) {
        // 构造无序的多叉树
        String jsonStr = "";
        // 根节点
        Node root = null;
        Set entrySet = recordMap.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentCode == null || node.parentCode.equals("")) {
                root = node;
            } else {
                ((Node) recordMap.get(node.parentCode)).addChild(node);
            }
        }
        jsonStr = root.toString();
        // 输出无序的树形菜单的JSON字符串
        System.out.println(jsonStr);
        return jsonStr;
    }

    public static String getOrderRecord222(Map nodeList) {
        // 构造无序的多叉树
        String jsonStr = "";
        // 根节点

        List<Node> rootList = new ArrayList<Node>();
        Node root = null;
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentCode == null || node.parentCode.equals("")) {
                root = node;
                rootList.add(root);
            } else {
                ((Node) nodeList.get(node.parentCode)).addChild(node);
            }
        }

        logger.info("-----------rootList: " + rootList);
        StringBuilder sb = new StringBuilder();
        for (Node node : rootList) {
            // 对多叉树进行横向排序
            node.sortChildren();
            jsonStr = node.toString();
            sb.append(jsonStr).append(",");
        }

        // 输出有序的树形菜单的JSON字符串
        String retStr = sb.deleteCharAt(sb.length() - 1).toString();
        logger.info("-----------retStr: "+ retStr);

        return retStr;
    }

    public static String getOrderRecord(Map nodeList) {
        // 构造无序的多叉树
        String jsonStr = "";
        // 根节点
        Node root = null;
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentCode == null || node.parentCode.equals("")) {
                root = node;
            } else {
                ((Node) nodeList.get(node.parentCode)).addChild(node);
            }
        }

        // 对多叉树进行横向排序
        root.sortChildren();
        jsonStr = root.toString();
        // 输出有序的树形菜单的JSON字符串
        System.out.println(jsonStr);

        return jsonStr;
    }

    public static String getOrderRecord2(Map<String, TreeNode> recordMap) {
        // 构造有序的多叉树
        String jsonStr = "";
        // 根节点
        TreeNode root = null;
        List<TreeNode> nodes = new ArrayList<TreeNode>();

        List<TreeNode> rootList = new ArrayList<TreeNode>();

        Set entrySet = recordMap.entrySet();
        Iterator<String> it = recordMap.keySet().iterator();
        while (it.hasNext()) {
            String code = it.next();
            TreeNode node = recordMap.get(code);
            if(StringUtils.isBlank(node.getParentCode())) {
                root = node;
                rootList.add(root);
            } else {
                //得到childNode的list，往这个List中加入新的Node
                node.getNodes().add(recordMap.get(node.getParentCode()));

            }

        }

        // 对多叉树进行横向排序
        //root.sortChildren();
        jsonStr = root.toString();
        // 输出有序的树形菜单的JSON字符串
        System.out.println(jsonStr);

        return jsonStr;
    }

    public static void main(String[] args) {
        // 读取层次数据结果集列表
        List dataList = VirtualDataGenerator.getVirtualResult();

        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext();) {
            Map dataRecord = (Map) it.next();
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.name = (String) dataRecord.get("text");
            node.parentCode = (String) dataRecord.get("parentCode");
            node.iconIndex = (String) dataRecord.get("iconIndex");
            node.url = (String) dataRecord.get("url");
            nodeList.put(node.id, node);
        }

        getOrderRecord(nodeList);
//        // 构造无序的多叉树
//        Set entrySet = nodeList.entrySet();
//        for (Iterator it = entrySet.iterator(); it.hasNext();) {
//            Node node = (Node) ((Map.Entry) it.next()).getValue();
//            if (node.parentCode == null || node.parentCode.equals("")) {
//                root = node;
//            } else {
//                ((Node) nodeList.get(node.parentCode)).addChild(node);
//            }
//        }
//        // 输出无序的树形菜单的JSON字符串
//        System.out.println(root.toString());
//
//        // 对多叉树进行横向排序
//        root.sortChildren();
//        // 输出有序的树形菜单的JSON字符串
//        System.out.println(root.toString());

    }

}






