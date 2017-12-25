package com.huaxia.rms.controller;

import com.alibaba.fastjson.JSONObject;
import com.huaxia.rms.core.model.*;
import com.huaxia.rms.core.service.RmsOrgCategoryRelationService;
import com.huaxia.rms.core.service.RmsOrgCategoryService;
import com.huaxia.rms.core.service.RmsUserService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.service.RmsOrganizationService;
import com.huaxia.rms.utils.MultipleTree;
import com.huaxia.rms.utils.Node;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liuzhilai on 2017/10/18.
 * 组织结构管理控制器
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {
    private Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private RmsOrganizationService rmsOrganizationService;

    @Resource
    private RmsOrgCategoryRelationService orgCategoryRelationService;

    @Resource
    private RmsOrgCategoryService orgCategoryService;

    @Resource
    private RmsUserService userService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/test.jhtml")
    private String test() {

        return "base/org/org_test";
    }

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tree.jhtml")
    @Login(AuthenType.page)
    private String list() {

        return "base/org/org_index";
    }

    /**
     * getTreeData 构造bootstrap-treeview格式数据
     *
     * @return
     */
    @RequestMapping(value = "/treeData.do", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> getTreeData() {
        logger.info("---------构造bootstrap-treeview格式数据-----------");
        List<TreeNode> list = rmsOrganizationService.getTreeData();
        logger.info("-----------list: " + list);
        return list;

    }



    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public String getAll() {

        List<RmsOrganization> list = rmsOrganizationService.queryRmsOrganizationList();

        // 构造无序的多叉树
        String jsonStr = "";

        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (RmsOrganization organization : list) {
            Node node = new Node();
            node.id = organization.getCode();
            node.name = organization.getName();
            node.parentCode = organization.getParentCode();
            nodeList.put(node.id, node);
        }

        String dateStr = MultipleTree.getOrderRecord(nodeList);

        return dateStr;
    }

    @RequestMapping(value = "/get/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public RmsOrganization get(@PathVariable("code") String code) {
        logger.info("--------code: " + code);
        RmsOrganization org = rmsOrganizationService.findOneByCode(code);
        /*if (StringUtils.isBlank(org.getParentCode())) {
            org.setParentName("");
        }*/
        /**
         * 根据code获取机构类别
         */
        Map map = new HashMap();
        map.put("orgCode", org.getCode());
        List<RmsOrgCategory> categorys = orgCategoryService.findListByCodes(org.getCode());
        StringBuilder categoryCodes = new StringBuilder();
        StringBuilder categoryNames = new StringBuilder();

        if(categorys != null && categorys.size() > 0) {
            for (RmsOrgCategory category : categorys) {
                categoryCodes.append(category.getCode()).append(",");
                categoryNames.append(category.getName()).append(",");
            }
        }
        if(categoryCodes.length() > 0) {
            org.setOrgCategoryCodes(categoryCodes.deleteCharAt(categoryCodes.length() - 1).toString());
        }
        if(categoryNames.length() > 0) {
            org.setOrgCategoryNames(categoryNames.deleteCharAt(categoryNames.length() - 1).toString());
        }
        return org;
    }

    @RequestMapping(value = "/getMaxCode", method = RequestMethod.POST)
    @ResponseBody
    public String getMaxCode(HttpServletRequest request) {

        String isAddRoot = request.getParameter("isroot");
        if(StringUtils.isNotBlank(isAddRoot) && "true".equals(isAddRoot)) {
            return rmsOrganizationService.findMaxCodeByParentCode("");
        }

        String code = request.getParameter("code");
        logger.info("--------code: " + code);
        return rmsOrganizationService.findMaxCodeByParentCode(code);

        //return rmsOrganizationService.findMaxCodeByParentCode(code);
    }

    @RequestMapping(value = "/show/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public Result show(@PathVariable("code") String code) {

        return rmsOrganizationService.getOrgNames(code);
    }

    @RequestMapping(value = "/getList/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    public List<RmsOrganization> getOrgsByCode(String code) {
        return rmsOrganizationService.getOrgsByCode(code);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Result save(@RequestBody RmsOrganization org, HttpServletRequest request) {
        logger.info("------------org: " + org);
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsOrganization organization = rmsOrganizationService.findOneByCode(org.getCode());

            /**
             * 判断是否立即生效，isEffect=1 立即生效
             * 如果不是立即生效，则更新信息插入t_rms_org_update_record表，然后通过定时任务跑
             * 新增时无需考虑生效日期, 更新时考虑
             */
            int isEffect = org.getIsEffect();
            if(organization != null && isEffect == 0) {
                org.setUpdateTime(new Date());
                org.setUpdateUser(authorization.getUsername());//从session中获取

                RmsOrgUpdateRecord udateRecord = new RmsOrgUpdateRecord();
                BeanUtils.copyProperties(org, udateRecord);
                udateRecord.setId(null);
                udateRecord.setOrgCategory(org.getOrgCategoryCodes());
                rmsOrganizationService.insertOrgUpdateRecord(udateRecord);
                return new Result(true);
            }

            //-----------------------以下是立即生效时的操作-----------------------------
            /**
             * 判断是否存在，存在更新，不存新增
             */
            if(organization != null) {
                org.setUpdateTime(new Date());
                org.setUpdateUser(authorization.getUsername());//从session中获取
                rmsOrganizationService.updateRmsOrganization(org);

                //记录更新记录表
                RmsOrgUpdateRecord udateRecord = new RmsOrgUpdateRecord();
                BeanUtils.copyProperties(org, udateRecord);
                udateRecord.setId(null);
                udateRecord.setOrgCategory(org.getOrgCategoryCodes());
                rmsOrganizationService.insertOrgUpdateRecord(udateRecord);

            } else {
                org.setCreateUser(authorization.getUsername());//从session中获取
                rmsOrganizationService.createRmsOrganization(org);
            }

            /**
             * 插入组织，组织类别关系表
             * 先删除旧组织类别数据
             */
            String categoryCodes = org.getOrgCategoryCodes();
            if(StringUtils.isNotBlank(categoryCodes)) {
                if(organization != null) {
                    Map map = new HashMap();
                    map.put("orgCode", org.getCode());
                    map.put("username", authorization.getUsername());
                    orgCategoryRelationService.deleteCategoryRelationByOrgcode(map);
                }

                String codes[] = categoryCodes.split(",");
                List<String> codeList = Arrays.asList(codes);
                List<RmsOrgCategoryRelation> relations = new ArrayList<RmsOrgCategoryRelation>();
                for (String code : codeList) {
                    RmsOrgCategoryRelation relation = new RmsOrgCategoryRelation();
                    relation.setOrgCode(org.getCode());
                    relation.setCategoryCode(code);
                    relation.setCreatedUser(authorization.getUsername());
                    relations.add(relation);
                }
                orgCategoryRelationService.saveBatchCategoryRelation(relations);
            }


            /**清除缓存 */
            rmsOrganizationService.deleteCacheByKeys(RedisConstant.ORG_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }

        return new Result(true);
    }

    @RequestMapping(value = "/delete/{code}.do", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Result delete(@PathVariable("code") String code) {

        try {
            RmsOrganization org = rmsOrganizationService.findOneByCode(code);
            if(rmsOrganizationService.referByUser(org.getCode())) {
                return new Result(false,"该机构或子机构被用户表引用，不可删除");
            }
            org.setDeleteMark(0);//删除状态
            org.setDeleteTime(new Date());
//            org.setDeleteUser();//session中获取
            rmsOrganizationService.updateRmsOrganization(org);

            if (StringUtils.isNotBlank(org.getParentCode())) {
                RmsOrganization parent = rmsOrganizationService.findOneByCode(org.getParentCode());
                rmsOrganizationService.deleteCacheByKey(RedisConstant.ORG_PRE + parent.getCode());
            }

            rmsOrganizationService.deleteCacheByKeys(RedisConstant.ORG_PRE);
//            rmsOrganizationService.deleteCacheByKey(RedisConstant.ORG_PRE + "tree");
            return new Result(true);

        } catch (Exception e) {
            return new Result(false, "该组织机构已经被其他数据引用，不可删除");
        }
    }

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
            map = rmsOrganizationService.checkUnique(code);
        } else {
            map.put("valid", true);
        }
        logger.info("------map:" + map);
        return map;
    }

    /**
     * 唯一性校验
     *
     * @return Map 校验通过
     */
    @RequestMapping("/checkName.do")
    @ResponseBody
    public Map checkName(String name, String id) {
        logger.info("-------id: " + id);
        Map map = new HashMap();
        /**
         * 存在ID表示更新，不存在表示新增
         * 更新状态， 无需验证唯一性
         */
        if(StringUtils.isBlank(id)) {
            map = rmsOrganizationService.checkName(name);
        } else {
            /**
             * 机构迁移时，可以修改迁移后的机构信息
             * 如果机构名称不变，则无需验证
             * 如果机构名称有变，则验证名称是否已经存在
             */
            RmsOrganization org = rmsOrganizationService.findOneById(Integer.parseInt(id));
            if(!org.getName().equals(name)) {
                map = rmsOrganizationService.checkName(name);
            } else {
                map.put("valid", true);
            }
        }
        logger.info("------map:" + map);
        return map;
    }

    /**
     * 机构迁移
     */
    @RequestMapping(method = RequestMethod.GET, value = "/move.jhtml")
    @Login(AuthenType.page)
    private String move(String code, HttpServletRequest request) {
        logger.info("--------机构迁移页面-----------");

        RmsOrganization org = rmsOrganizationService.findOneByCode(code);

        /**
         * 获取机构类别
         */
        List<RmsOrgCategory> categorys = orgCategoryService.findListByCodes(org.getCode());
        StringBuilder categoryNames = new StringBuilder();
        if(categorys != null && categorys.size() > 0) {
            for (RmsOrgCategory category : categorys) {
                categoryNames.append(category.getName()).append(",");
            }
        }
        if(categoryNames.length() > 0) {
            org.setOrgCategoryNames(categoryNames.deleteCharAt(categoryNames.length() - 1).toString());
        }

        request.setAttribute("org", org);

        return "base/org/org_move";
    }

    @RequestMapping(value = "/move.do", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.json)
    public Result moveOrg(RmsOrganization org, HttpServletRequest request) {

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            String oldCode = request.getParameter("oldCode");
            logger.info("----------oldCode: " + oldCode);

            /**
             * 机构移动
             * 1、原机构信息parent_code修改成新机构code
             * 2、记录原机构变更前的机构信息 t_rms_org_modify_record
             * 3、修改新机构的机构信息
             * 4、记录新机构变更前的机构信息 t_rms_org_modify_record
             */
            Date updateDate = new Date();

            RmsOrganization oldOrg = rmsOrganizationService.findOneByCode(oldCode);
            oldOrg.setParentCode(org.getCode());
            oldOrg.setParentName(org.getName());
            oldOrg.setPastCode(oldOrg.getParentCode());
            oldOrg.setEffectiveDate(org.getEffectiveDate());//生效日期
            oldOrg.setUpdateTime(updateDate);
            oldOrg.setUpdateUser(authorization.getUsername());//从session中获取

            /**
             * 判断是否立即生效，isEffect=1 立即生效
             * 机构迁移时，需要变更两个机构的信息，新老机构的信息都要变更
             * 如果不是立即生效，则将oldOrg信息和新的org信息都要 插入t_rms_org_update_record表，然后通过定时任务跑
             */
            int isEffect = org.getIsEffect();
            if(isEffect == 0) {
                //变更老机构信息
                RmsOrgUpdateRecord oldOrgUdateRecord = new RmsOrgUpdateRecord();
                BeanUtils.copyProperties(oldOrg, oldOrgUdateRecord);
                oldOrgUdateRecord.setId(null);
                rmsOrganizationService.insertOrgUpdateRecord(oldOrgUdateRecord);

                //变更新机构信息
                org.setUpdateTime(updateDate);
                org.setUpdateUser(authorization.getUsername());//从session中获取
                RmsOrgUpdateRecord newOrgUdateRecord = new RmsOrgUpdateRecord();
                BeanUtils.copyProperties(org, newOrgUdateRecord);
                newOrgUdateRecord.setId(null);
                newOrgUdateRecord.setOrgCategory(org.getOrgCategoryCodes());
                rmsOrganizationService.insertOrgUpdateRecord(newOrgUdateRecord);

                return new Result(true);
            }

            //----------------------------以下是机构迁移立即生效的操作-------------------------------------
            //1、原机构信息parent_code修改成新机构code
            rmsOrganizationService.updateRmsOrganization(oldOrg);

            //2、记录原机构修改前的机构信息
            RmsOrgUpdateRecord oldOrgUdateRecord = new RmsOrgUpdateRecord();
            BeanUtils.copyProperties(oldOrg, oldOrgUdateRecord);
            oldOrgUdateRecord.setId(null);
            rmsOrganizationService.insertOrgUpdateRecord(oldOrgUdateRecord);


            //3、修改新机构的机构信息
//            RmsOrganization organization = rmsOrganizationService.findOneByCode(org.getCode());
            org.setUpdateTime(updateDate);
            org.setUpdateUser(authorization.getUsername());//从session中获取
            rmsOrganizationService.updateRmsOrganization(org);

            /**插入机构类别信息，先删除旧组织类别数据，然后再插入 */
            String categoryCodes = org.getOrgCategoryCodes();
            if(StringUtils.isNotBlank(categoryCodes)) {
                Map map = new HashMap();
                map.put("orgCode", org.getCode());
                map.put("username", authorization.getUsername());
                orgCategoryRelationService.deleteCategoryRelationByOrgcode(map);

                String codes[] = categoryCodes.split(",");
                List<String> codeList = Arrays.asList(codes);
                List<RmsOrgCategoryRelation> relations = new ArrayList<RmsOrgCategoryRelation>();
                for (String code : codeList) {
                    RmsOrgCategoryRelation relation = new RmsOrgCategoryRelation();
                    relation.setOrgCode(org.getCode());
                    relation.setCategoryCode(code);
                    relation.setCreatedUser(authorization.getUsername());
                    relations.add(relation);
                }
                orgCategoryRelationService.saveBatchCategoryRelation(relations);
            }

            //4、记录新机构变更前的机构信息
            RmsOrgUpdateRecord newOrgUdateRecord = new RmsOrgUpdateRecord();
            BeanUtils.copyProperties(org, newOrgUdateRecord);
            newOrgUdateRecord.setId(null);
            newOrgUdateRecord.setOrgCategory(org.getOrgCategoryCodes());
            rmsOrganizationService.insertOrgUpdateRecord(newOrgUdateRecord);

            /**清除缓存*/
            rmsOrganizationService.deleteCacheByKeys(RedisConstant.ORG_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }

        return new Result(true);
    }

    private static void main(String args[]) {

        String jsonStr = "{\"id\":\"25\",\"name\":\"总部\",\"code\":\"100\",\"layer\":\"1\",\"shortName\":\"ZB\",\"manager\":\"00007\",\"user\":{\"mobile\":\"13816638134\"},\"deleteMark\":\"1\",\"maxuser\":\"1000\",\"effectiveDate\":\"2017-12-22\",\"city\":\"2611\",\"address\":\"上海\",\"homepage\":\"http://huaxia.com\",\"promotionCode\":\"xxxxxxxxx\",\"urgentScale\":\"15\",\"orgCategoryCodes\":\"102\",\"orgCategoryNames\":\"财富职能岗\",\"isVisual\":\"0\",\"memo\":\"DDDDDDDDD\"}";

//        RmsOrganization org = JSONObject

    }
}
