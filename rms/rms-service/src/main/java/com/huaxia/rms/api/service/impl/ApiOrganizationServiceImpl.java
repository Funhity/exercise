/**
 * @项目名称:
 * @文件名称: RmsOrganizationService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.api.model.OrganizationRo;
import com.huaxia.rms.api.service.ApiOrganizationService;
import com.huaxia.rms.api.pojo.TreeNode;
import com.huaxia.rms.api.utils.TreeUtil;
import com.huaxia.rms.core.model.RmsOrganization;
import com.huaxia.rms.core.service.RmsOrganizationService;
import com.huaxia.rms.pojo.Result;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("apiOrganizationService")
public class ApiOrganizationServiceImpl extends ApiBaseServiceImpl implements ApiOrganizationService {
    private Logger logger = LoggerFactory.getLogger(ApiOrganizationServiceImpl.class);

    @Resource
    private RmsOrganizationService organizationService;

    @Override
    public List<OrganizationRo> getAllOrgList() {

        List<OrganizationRo> orgRoList = new ArrayList<OrganizationRo>();
        try {
            List<RmsOrganization> list =  organizationService.queryRmsOrganizationList();
            if(list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    OrganizationRo orgRo = new OrganizationRo();
                    BeanUtils.copyProperties(list.get(i), orgRo);
                    orgRoList.add(orgRo);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return orgRoList;
    }

    @Override
    public OrganizationRo getOrgByCode(String code) {

        OrganizationRo organizationRo = null;
        try {
            RmsOrganization org = organizationService.findOneByCode(code);
            if(org != null) {
                organizationRo = new OrganizationRo();
                BeanUtils.copyProperties(org, organizationRo);
            }
        }catch(Exception e){
            return null;
        }

        return organizationRo;

    }

    @Override
    public List<OrganizationRo> getOrgTree() {
        // 获取数据
        String key = RedisConstant.ORG_PRE + "tree";
        List<OrganizationRo> orglist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            orglist = JSON.parseArray(tnStr, OrganizationRo.class);
        }
        if (orglist != null) {
            return orglist;
        } else {
            List<OrganizationRo> orgs = getAllOrgList();
            Map<String, OrganizationRo> orgMap = new LinkedHashMap<String, OrganizationRo>();
            for (OrganizationRo org : orgs) {
                orgMap.put(org.getCode(), org);
            }
            // 构造树形结构
            orglist = getOrgList(orgMap);
            logger.info("----------orglist: " + orglist);
            redisDao.save(key, orglist);
            return orglist;
        }

    }

    public List<OrganizationRo> getOrgList(Map<String, OrganizationRo> orgMap) {
        List<OrganizationRo> orglist = new ArrayList<OrganizationRo>();
        for (String id : orgMap.keySet()) {
            OrganizationRo org = orgMap.get(id);
            if (StringUtils.isBlank(org.getParentCode())) {
                orglist.add(org);
            } else {
                OrganizationRo organization = orgMap.get(org.getParentCode());
                if(organization != null) {
                    if (organization.getOrgList() == null) {
                        orgMap.get(org.getParentCode()).setOrgList(new ArrayList<OrganizationRo>());
                    }
                    orgMap.get(org.getParentCode()).getOrgList().add(org);
                }
            }
        }
        return orglist;
    }


    @Override
    public String getOrgFullNameByCode(String code) {
        Result result = organizationService.getOrgNames(code);
        String fullName = "";
        if(result != null) {
            Map<String, String> nameMap = (Map<String, String>)result.getData();
            if(nameMap != null) {
                fullName = nameMap.get("parentName");
            }
        }
        return fullName;
    }


}