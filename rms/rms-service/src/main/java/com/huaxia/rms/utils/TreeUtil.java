package com.huaxia.rms.utils;

import com.huaxia.rms.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by billJiang on 2017/2/12.
 * e-mail:jrn1012@petrochina.com.cn qq:475572229
 */
public class TreeUtil {

    public static List<TreeNode> getNodeList(Map<String, TreeNode> nodelist) {
        List<TreeNode> tnlist = new ArrayList<>();
        for (String id : nodelist.keySet()) {
            TreeNode node = nodelist.get(id);
            if (StrUtil.isEmpty(node.getParentCode())) {
                tnlist.add(node);
            } else {
                TreeNode treeNode = nodelist.get(node.getParentCode());
                if(treeNode != null) {
                    if (treeNode.getNodes() == null) {
                        nodelist.get(node.getParentCode()).setNodes(new ArrayList<TreeNode>());
                    }
                    nodelist.get(node.getParentCode()).getNodes().add(node);
                }

//                if (nodelist.get(node.getParentCode()).getNodes() == null)
//                    nodelist.get(node.getParentCode()).setNodes(new ArrayList<TreeNode>());
//                nodelist.get(node.getParentCode()).getNodes().add(node);
            }
        }
        return tnlist;
    }
}
