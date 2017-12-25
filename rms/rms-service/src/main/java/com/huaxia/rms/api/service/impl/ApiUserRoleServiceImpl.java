/**
 * @项目名称:
 * @文件名称: RmsUserRoleService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.api.model.UserRo;
import com.huaxia.rms.api.model.UserRoleRo;
import com.huaxia.rms.api.service.ApiUserRoleService;
import com.huaxia.rms.api.service.ApiUserService;
import com.huaxia.rms.core.model.RmsUserRole;
import com.huaxia.rms.core.service.RmsUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("apiUserRoleService")
public class ApiUserRoleServiceImpl implements ApiUserRoleService {
    private Logger logger = LoggerFactory.getLogger(ApiUserRoleServiceImpl.class);

    @Resource
    private RmsUserRoleService userRoleService;

    @Override
    public List<UserRoleRo> queryUserRoleList(Map<String, Object> map) {
        List<UserRoleRo> userRoleRoList = null;
        try {

            List<RmsUserRole> list =  userRoleService.queryUserRoleList(map);
            if(list != null && list.size() > 0) {
                userRoleRoList = new ArrayList<UserRoleRo>();
                BeanUtils.copyProperties(list, userRoleRoList);
            }
        }catch(Exception e){
            return null;
        }
        return userRoleRoList;

    }

    @Override
    public int queryUserRoleListCount(Map<String, Object> map) {
        int userCount = userRoleService.queryUserRoleListCount(map);
        return userCount;
    }


    @Override
    public List<UserRoleRo> queryUserRoleByParam(Map<String, Object> map) {
        List<UserRoleRo> userRoleRoList = null;
        try {

            List<RmsUserRole> list =  userRoleService.queryUserRoleByParam(map);
            if(list != null && list.size() > 0) {
                userRoleRoList = new ArrayList<UserRoleRo>();
                BeanUtils.copyProperties(list, userRoleRoList);
            }
        }catch(Exception e){
            return null;
        }
        return userRoleRoList;
    }
}