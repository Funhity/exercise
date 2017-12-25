/**
 * @项目名称:
 * @文件名称: RmsRolePermissionService 版本号：1.0
 * @创建日期: 2017/10/26
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.huaxia.rms.api.model.RolePermissionRo;
import com.huaxia.rms.api.service.ApiRolePermissionService;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.service.RmsRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("apiRolePermissionService")
public class ApiRolePermissionServiceImpl extends ApiBaseServiceImpl implements ApiRolePermissionService {
    private Logger logger = LoggerFactory.getLogger(ApiRolePermissionServiceImpl.class);

    @Resource
    private RmsRolePermissionService rolePermissionService;

    @Override
    public List<RolePermissionRo> queryRolePermissionList(Map<String, Object> map) {
        List<RolePermissionRo> rolePermissionRos = null;
        try {

            List<RmsRolePermission> list =  rolePermissionService.queryRolePermissionList(map);
            if(list != null && list.size() > 0) {
                rolePermissionRos = new ArrayList<RolePermissionRo>();
                BeanUtils.copyProperties(list, rolePermissionRos);
            }
        }catch(Exception e){
            return null;
        }
        return rolePermissionRos;

    }

    @Override
    public int queryRolePermissionListCount(Map<String, Object> map) {

        return rolePermissionService.queryRolePermissionListCount(map);
    }


    public List<RolePermissionRo> queryRmsRolePermissionList(Map<String, Object> map) {
        List<RolePermissionRo> rolePermissionRos = null;
        try {

            List<RmsRolePermission> list =  rolePermissionService.queryRmsRolePermissionList(map);
            if(list != null && list.size() > 0) {
                rolePermissionRos = new ArrayList<RolePermissionRo>();
                BeanUtils.copyProperties(list, rolePermissionRos);
            }
        }catch(Exception e){
            return null;
        }
        return rolePermissionRos;
    }

}