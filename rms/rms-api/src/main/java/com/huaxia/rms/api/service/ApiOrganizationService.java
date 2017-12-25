/**
 * @项目名称:
 * @文件名称: RmsOrganizationService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.OrganizationRo;
import com.huaxia.rms.api.service.ApiBaseService;
import com.huaxia.rms.api.pojo.Result;
import com.huaxia.rms.api.pojo.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * RmsOrganizationService接口
 */
public interface ApiOrganizationService extends ApiBaseService {

    /**
     * 获取所有的组织机构列表
     * @return
     */
    List<OrganizationRo> getAllOrgList();

    /**
     * 根据组织机构的编号获取相应的机构实体
     * @param code
     * @return
     */
    OrganizationRo getOrgByCode(String code);

    /**
     * 获取组织机构树形结构
     * @return
     */
    List<OrganizationRo> getOrgTree();

    /**
     * 根据组织机构的编号获取相应的机构全称，包括所有的父级名称
     * 如：总部->信息技术部->系统运维处
     * @param code
     * @return
     */
    String getOrgFullNameByCode(String code);

}