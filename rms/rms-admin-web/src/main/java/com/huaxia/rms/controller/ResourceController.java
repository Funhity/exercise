package com.huaxia.rms.controller;

import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsResource;
import com.huaxia.rms.core.service.RmsResourceService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.MultipleTree;
import com.huaxia.rms.utils.Node;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhilai on 2017/10/18.
 * 组织结构管理控制器
 */
@Controller
@RequestMapping("/res")
public class ResourceController {
    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private RmsResourceService resourceService;

    /**
     * 唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkUnique.do")
    @ResponseBody
    public Map checkUnique(String code, String id) {
        logger.info("-------id: " + id);
        Map map = new HashMap();
        /**
         * 存在ID标准更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
            map = resourceService.checkUnique(code);
        } else {
            map.put("valid", true);
        }
        logger.info("------map:" + map);
        return map;
    }

    /**
     * 資源列表
     */
//    @RequestMapping(method = RequestMethod.GET, value = "/tree2.jhtml")
//    private String list() {
//        return "base/resource/res_tree";
//    }

    /**
     * 資源列表2
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tree.jhtml")
    @Login(AuthenType.page)
    private String list2() {
        return "base/resource/function_tree";
    }

    /**
     * 获取所有资源
     * 树状结构
     * getTreeData 构造bootstrap-treeview格式数据
     *
     * @return
     */
    @RequestMapping(value = "/treeData.do", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> getTreeData() {
        logger.info("---------构造bootstrap-treeview格式数据-----------");
        List<RmsResource> ress = resourceService.queryRmsResourceList();
        List<TreeNode> list = resourceService.getTreeData(ress);
        logger.info("-----------list: " + list);
        return list;

    }

    /**
     * 根据用户ID获取改用户的所有资源
     *
     * @return
     */
    @RequestMapping(value = "/userTreeData.do", method = RequestMethod.POST)
    @Login(AuthenType.page)
    @ResponseBody
    public List<TreeNode> getUserResTree(HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        //获取用户被授权的资源列表
        List<RmsResource> userRess = resourceService.getSourceListByUidAndSyscode(authorization.getId(), null);
        List<TreeNode> list = resourceService.getTreeData(userRess);
        logger.info("-----------当前用户所拥有的资源列表：" + list);
        return list;

    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public String getAll() {
//        return resourceService.queryRmsResourceList();
        List<RmsResource> list = resourceService.queryRmsResourceList();
        logger.info("-------------list: " + list);
        // 构造无序的多叉树
        String jsonStr = "";

        // 节点列表（散列表，用于临时存储节点对象）
        Map<String, TreeNode> nodeList = new HashMap();
        // 根节点
        //Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (RmsResource res : list) {
            TreeNode node = new TreeNode();
            node.setId(res.getId());
            node.setText(res.getName());
            node.setCode(res.getCode());
            node.setParentCode(res.getParentCode());
            node.setIconIndex(res.getIconIndex());
            node.setUrl(res.getUrl());
            node.setLayer(String.valueOf(res.getLayer()));
            nodeList.put(node.getCode(), node);

            /*
            node.id = res.getCode();
            node.name = res.getName();
            node.parentCode = res.getParentCode();
            node.iconIndex = res.getIconIndex();
            node.url = res.getUrl();
            nodeList.put(node.id, node);*/
        }
        logger.info("-------------nodeList: " + nodeList);

        String dateStr = MultipleTree.getOrderRecord2(nodeList);
        logger.info("-------------dateStr: " + dateStr);
        return dateStr;

    }

    @RequestMapping(value = "/allres", method = RequestMethod.POST)
    @ResponseBody
    public String getAll2() {

        List<RmsResource> list = resourceService.queryRmsResourceList();
        logger.info("-------------list: " + list);
        // 构造无序的多叉树
        String jsonStr = "";

        // 节点列表（散列表，用于临时存储节点对象）
        Map nodeList = new HashMap();
        // 根节点
        //Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (RmsResource res : list) {
            Node node = new Node();
            node.id = res.getCode();
            node.name = res.getName();
            node.parentCode = res.getParentCode();
            node.iconIndex = res.getIconIndex();
            node.url = res.getUrl();
            nodeList.put(node.id, node);
        }
        logger.info("-------------nodeList: " + nodeList);

        String nodeStr = MultipleTree.getOrderRecord222(nodeList);
        logger.info("-------------nodeStr: " + nodeStr);
        return nodeStr;
    }


    @RequestMapping(value = "/userres", method = RequestMethod.POST)
    @ResponseBody
    public String getUserResource(HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        logger.info("---------userres.authorization: " + authorization);
        String nodeStr = "";
        if(authorization != null) {
            nodeStr = resourceService.getSourceTreeByUid(authorization.getId());
        }

        //logger.info("-----------nodeStr: " + nodeStr);
        request.setAttribute("nodeStr", nodeStr);
        return nodeStr;
    }

    @RequestMapping(value = "/get/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public RmsResource get(@PathVariable("code") String code) {
        logger.info("--------code: " + code);
        RmsResource res = resourceService.findOneByCode(code);
        /*if (StringUtils.isBlank(res.getParentCode())) {
            res.setParentName("");
        }*/
        return res;
    }

    @RequestMapping(value = "/getMaxCode", method = RequestMethod.POST)
    @ResponseBody
    public String getMaxCode(HttpServletRequest request) {

        String isAddRoot = request.getParameter("isroot");
        if(StringUtils.isNotBlank(isAddRoot) && "true".equals(isAddRoot)) {
            return resourceService.findMaxCodeByParentCode("");
        }

        String code = request.getParameter("code");
        return resourceService.findMaxCodeByParentCode(code);
    }

    @RequestMapping(value = "/show/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public Result show(@PathVariable("code") String code) {
        return resourceService.getResNames(code);
    }

    @RequestMapping(value = "/getList/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public List<RmsResource> getResesByCode(String code) {
        return resourceService.getResesByCode(code);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @Login(AuthenType.json)
    @ResponseBody
    public Result save(RmsResource res, HttpServletRequest request) {

        /**
         * 判断是否存在
         * 存在更新
         * 不存新增
         */
        Authorization authorization = WebUtils.getSessionUser(request);

        RmsResource resource = resourceService.findOneByCode(res.getCode());
        if(resource != null) {
            logger.info("------------getIconIndex: "+res.getIconIndex());
            //res.setId(resource.getId());
            res.setUpdateTime(new Date());
            res.setUpdateUser(authorization.getUsername());//从session中获取
            resourceService.updateRmsResource(res);
        } else {
            if(StringUtils.isBlank(res.getParentCode())) {
                res.setParentName("");
            }
            res.setSysCode(res.getCode());
            res.setCreateUser(authorization.getUsername());//从session中获取
            resourceService.createRmsResource(res);
        }
        if (StringUtils.isNotBlank(res.getParentCode())) {
            RmsResource parent = resourceService.findOneByCode(res.getParentCode());
            resourceService.deleteCacheByKey(RedisConstant.RES_PRE + parent.getCode());
        }

        //删除该用户的菜单缓存
//        resourceService.deleteCacheByKey(RedisConstant.RES_PRE + "tree");
//        String key = RedisConstant.RES_PRE + "tree_" + authorization.getId();
//        resourceService.deleteCacheByKey(key);
        /**
         * 清除缓存
         */
        resourceService.deleteCacheByKeys(RedisConstant.RES_PRE);

        return new Result(true);
    }

    @RequestMapping(value = "/delete/{code}.do", method = RequestMethod.POST)
    @Login(AuthenType.page)
    @ResponseBody
    public Result delete(@PathVariable("code") String code, HttpServletRequest request) {

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsResource res = resourceService.findOneByCode(code);
//            if(resourceService.referByUser(res.getCode())) {
//                return new Result(false,"该资源被其他资源引用，不可删除");
//            }
            res.setDeleteMark(0);//删除状态
            res.setDeleteTime(new Date());
            res.setDeleteUser(authorization.getUsername());//session中获取
            resourceService.updateRmsResource(res);

//            if (StringUtils.isNotBlank(res.getParentCode())) {
//                RmsResource parent = resourceService.findOneByCode(res.getParentCode());
//                resourceService.deleteCacheByKey(RedisConstant.RES_PRE + parent.getCode());
//            }
//            resourceService.deleteCacheByKey(RedisConstant.RES_PRE + "tree");
//            //删除该用户的菜单缓存
//            String key = RedisConstant.RES_PRE + "tree_" + authorization.getId();
//            resourceService.deleteCacheByKey(key);
            /**
             * 清除缓存
             */
            resourceService.deleteCacheByKeys(RedisConstant.RES_PRE);


            return new Result(true);

        } catch (Exception e) {
            return new Result(false, "该资源已经被其他数据引用，不可删除");
        }
    }
}
