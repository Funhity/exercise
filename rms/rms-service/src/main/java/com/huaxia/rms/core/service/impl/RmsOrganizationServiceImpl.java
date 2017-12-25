/**
 * @项目名称:
 * @文件名称: RmsOrganizationService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsOrgUpdateRecord;
import com.huaxia.rms.core.service.RmsOrganizationService;
import com.huaxia.rms.core.model.RmsOrganization;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.StrUtil;
import com.huaxia.rms.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service()
public class RmsOrganizationServiceImpl extends BaseServiceImpl implements RmsOrganizationService {
    private Logger logger = LoggerFactory.getLogger(RmsOrganizationServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsOrganization> mBaseDao;

    @Override
    public Long deleteRmsOrganizationById(Long id) {
        return mBaseDao.delete("rmsorganization.delete", id);
    }

    @Override
    @MethodLog(remark = "新增组织机构")
    public Long createRmsOrganization(RmsOrganization record) {
        return mBaseDao.create("rmsorganization.insert", record);
    }

    @Override
    @MethodLog(remark = "更新组织机构")
    public Long updateRmsOrganization(RmsOrganization record) {
        return mBaseDao.update("rmsorganization.update", record);
    }

    @Override
    //@MethodLog(remark = "查询组织机构")
    public List<RmsOrganization> queryRmsOrganizationList() {
        String key = RedisConstant.ORG_PRE + "all";
        List<RmsOrganization> orglist = null;
        String tnStr = redisDao.get(key);
        if (StringUtils.isNotBlank(key)) {
            orglist = JSON.parseArray(tnStr, RmsOrganization.class);
        }
        if (orglist != null) {
            return orglist;
        } else {
            orglist = mBaseDao.findListByTemplate("rmsorganization.selectAll", null);
            if(orglist != null && orglist.size() > 0) {
                redisDao.save(key, orglist);
            }
            return orglist;
        }
    }

    @Override
    public PageInfo<RmsOrganization> findRmsOrganizationPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsorganization.selectAll", pageNum, pageSize, map);
    }

    public RmsOrganization findOneByName(String name) {
        return (RmsOrganization)mBaseDao.findObjectByTemplate("rmsorganization.selectByName", name);
    }

    @Override
    public RmsOrganization findOneByCode(String code) {
        String key = RedisConstant.ORG_PRE + "org_" + code;
        RmsOrganization org = null;
        if (StringUtils.isNotBlank(key)) {
            org = redisDao.get(key, RmsOrganization.class);
        }
        if (org != null) {
            return org;
        } else {
            org = (RmsOrganization)mBaseDao.findObjectByTemplate("rmsorganization.selectByCode", code);
            if(org != null) {
                redisDao.save(key, org);
            }
            return org;
        }

//        return (RmsOrganization)mBaseDao.findObjectByTemplate("rmsorganization.selectByCode", code);
    }

    @Override
    public RmsOrganization findOneById(int id) {
//        Map paramMap = new HashMap();
//        paramMap.put("code", code);
        return (RmsOrganization)mBaseDao.findObjectByTemplate("rmsorganization.selectById", id);
    }

    @Override
    public Map checkUnique(String code) {

        Map<String, Boolean> map = new HashMap<String, Boolean>();

        try {

            RmsOrganization organization = findOneByCode(code);

            if(organization == null) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
            }
            return map;
        } catch (Exception ex) {
            logger.info(ex.getMessage().toString());
            map.put("valid", false);
            return map;
        }
    }

    @Override
    public Map checkName(String name) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            RmsOrganization organization = findOneByName(name);
            if(organization == null) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("valid", false);
            return map;
        }
    }

    @Override
    public List<TreeNode> getTreeData() {

        // 获取数据
        String key = RedisConstant.ORG_PRE + "nodeTree";
        List<TreeNode> tnlist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            tnlist = JSON.parseArray(tnStr, TreeNode.class);
        }
        if (tnlist != null) {
            return tnlist;
        } else {
            List<RmsOrganization> orgs = queryRmsOrganizationList();
            Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
            for (RmsOrganization org : orgs) {
                TreeNode node = new TreeNode();
                node.setText(org.getName());
                node.setCode(org.getCode());
                node.setParentCode(org.getParentCode());
                node.setParentName(org.getParentName());
                node.setLayer(String.valueOf(org.getLayer()));
                nodelist.put(node.getCode(), node);
            }
            logger.info("----------nodelist: " + nodelist.size());
            // 构造树形结构
            tnlist = TreeUtil.getNodeList(nodelist);
//            logger.info("----------tnlist2: " + tnlist);
            redisDao.save(key, tnlist);
            return tnlist;
        }
    }

    /**
     * 判断部门下是否有员工
     * @return
     */
    public boolean referByUser(String code) {

        Map map = (Map)mBaseDao.findObjectByTemplate("rmsorganization.referByUser", code);
        logger.info("----------map: " + map);

        if(map != null) {
            return true;
        }
        return false;
    }

    public String findMaxCodeByParentCode(String code) {
        String maxCode = (String) mBaseDao.findObjectByTemplate("rmsorganization.selectMaxCodeByParentCode", code);
        return maxCode;
    }

    @Override
    public Result getOrgNames(String code) {
        String key = RedisConstant.ORG_PRE + "name_" + code;
        Map nameMap = redisDao.get(key, Map.class);
        //logger.info("---------getOrgNames.nameMap: " + nameMap);
        if(nameMap == null) {
            nameMap = new HashMap();
            RmsOrganization org = findOneByCode(code);

            String name = org.getName();
            //logger.info("---------getOrgNames.name: " + name);
            /**
             * 存在上级机构的组织，
             * 再获取到上级机构，存入redis
             */
//            String parentName = "";
            if(StringUtils.isNotBlank(org.getParentCode())){
                Map result  = (Map)getOrgNames(org.getParentCode()).getData();
                String parentName = result.get("parentName")+"->"+ org.getName();
                logger.info("---------getOrgNames.parentName: " + parentName);
                nameMap.put("name", org.getName());
                nameMap.put("parentName", parentName);
                redisDao.save(key, nameMap);
                return new Result(true, nameMap,"获取成功");
            } else {
                nameMap.put("name", org.getName());
                nameMap.put("parentName", org.getName());
                redisDao.save(key, nameMap);
                return new Result(true, nameMap,"获取成功");
            }
        } else {
            return new Result(true, nameMap,"获取成功");
        }
    }


    @Override
    public List<RmsOrganization> getOrgsByCode(String code) {
        String key = RedisConstant.ORG_PRE + "parent_" + code;
        List orgs = redisDao.get(key, List.class);
        if (orgs == null) {
            orgs = mBaseDao.findListByTemplate("rmsorganization.selectOrgsByCode", code);
            redisDao.add(key, orgs);
            return orgs;
        } else {
            return orgs;
        }
    }

    @Override
    public List<RmsOrganization> getOrgTree() {
        // 获取数据
        String key = RedisConstant.ORG_PRE + "tree";
        List<RmsOrganization> orglist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            orglist = JSON.parseArray(tnStr, RmsOrganization.class);
        }
        if (orglist != null) {
            return orglist;
        } else {
            List<RmsOrganization> orgs = queryRmsOrganizationList();
            Map<String, RmsOrganization> orgMap = new LinkedHashMap<String, RmsOrganization>();
            for (RmsOrganization org : orgs) {
                orgMap.put(org.getCode(), org);
            }
//            logger.info("----------nodelist: " + nodelist.size());
            // 构造树形结构
            orglist = getOrgList(orgMap);
            logger.info("----------orglist: " + orglist);
            redisDao.save(key, orglist);
            return orglist;
        }

    }

    public List<RmsOrganization> getOrgList(Map<String, RmsOrganization> orgMap) {
        List<RmsOrganization> orglist = new ArrayList<RmsOrganization>();
        for (String id : orgMap.keySet()) {
            RmsOrganization org = orgMap.get(id);
            if (StringUtils.isBlank(org.getParentCode())) {
                orglist.add(org);
            } else {
                RmsOrganization organization = orgMap.get(org.getParentCode());
                if(organization != null) {
                    if (organization.getOrgList() == null) {
                        orgMap.get(org.getParentCode()).setOrgList(new ArrayList<RmsOrganization>());
                    }
                    orgMap.get(org.getParentCode()).getOrgList().add(org);
                }
            }
        }
        return orglist;
    }

//    @Override
//    public Long insertOrgModifyRecord(String code) {
//        return mBaseDao.create("rmsorganization.insertModifyRecord", code);
//    }

    @Override
    public Long insertOrgUpdateRecord(RmsOrgUpdateRecord record) {
        return mBaseDao.create("rmsorgupdaterecord.insert", record);
    }
}