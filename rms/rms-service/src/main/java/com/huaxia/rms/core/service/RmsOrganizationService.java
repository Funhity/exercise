/**
 * @项目名称:
 * @文件名称: RmsOrganizationService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsOrgUpdateRecord;
import com.huaxia.rms.core.model.RmsOrganization;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * RmsOrganizationService接口
 */
public interface RmsOrganizationService extends BaseService {
    Long deleteRmsOrganizationById(Long id);
    Long createRmsOrganization(RmsOrganization record);
    Long updateRmsOrganization(RmsOrganization record);
    List<RmsOrganization> queryRmsOrganizationList();
    PageInfo<RmsOrganization> findRmsOrganizationPage(int pageNum, int pageSize, Map<String, Object> map);

    RmsOrganization findOneByCode(String code);
    RmsOrganization findOneById(int id);

    Map checkUnique(String code);
    Map checkName(String code);

    List<TreeNode> getTreeData();

    boolean referByUser(String code);

    String findMaxCodeByParentCode(String code);

//    Result getOrgFullNames(String code);

    Result getOrgNames(String code);

    /**
     * 获取同级部门
     * 父级下的下一级所有子级
     * @param code
     * @return
     */
    List<RmsOrganization> getOrgsByCode(String code);
//    List<RmsOrganization> getTreeData();

    List<RmsOrganization> getOrgTree();

//    Long insertOrgModifyRecord(String code);

    Long insertOrgUpdateRecord(RmsOrgUpdateRecord record);
}