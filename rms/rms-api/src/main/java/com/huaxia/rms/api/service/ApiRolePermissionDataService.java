/**
 * @项目名称:
 * @文件名称: RmsRolePermissionDataService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.RolePermissionDataRo;
import com.huaxia.rms.api.service.ApiBaseService;

import java.util.List;
import java.util.Map;

/**
 * RmsRolePermissionDataService接口
 */
public interface ApiRolePermissionDataService extends ApiBaseService {

    List<RolePermissionDataRo> findPermissionDatalist(Map map);

    int findPermissionDatalistCount(Map map);

}