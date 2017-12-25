/**
 * @项目名称:
 * @文件名称: RmsRolePermissionDataService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.api.model.RolePermissionDataRo;
import com.huaxia.rms.api.model.RolePermissionRo;
import com.huaxia.rms.api.service.ApiRolePermissionDataService;
import com.huaxia.rms.api.service.ApiUserRoleService;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.core.service.RmsRolePermissionDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("apiRolePermissionServiceData")
public class ApiRolePermissionDataServiceImpl extends ApiBaseServiceImpl implements ApiRolePermissionDataService {
    private Logger logger = LoggerFactory.getLogger(ApiRolePermissionDataServiceImpl.class);

    @Resource
    private RmsRolePermissionDataService rolePermissionServiceData;

    @Override
    public List<RolePermissionDataRo> findPermissionDatalist(Map map) {

        List<RolePermissionDataRo> rolePermissionDataRos = null;
        try {

            List<RmsRolePermissionData> list =  rolePermissionServiceData.findPermissionDatalist(map);
            if(list != null && list.size() > 0) {
                rolePermissionDataRos = new ArrayList<RolePermissionDataRo>();
                BeanUtils.copyProperties(list, rolePermissionDataRos);
            }
        }catch(Exception e){
            return null;
        }
        return rolePermissionDataRos;

    }

    @Override
    public int findPermissionDatalistCount(Map map) {
        return rolePermissionServiceData.findPermissionDatalistCount(map);
    }

}