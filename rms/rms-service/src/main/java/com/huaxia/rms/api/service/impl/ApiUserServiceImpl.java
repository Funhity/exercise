/**
 * @项目名称:
 * @文件名称: RmsUserService 版本号：1.0
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
import com.huaxia.rms.api.model.OrganizationRo;
import com.huaxia.rms.constant.RedisConstant;

import com.huaxia.rms.api.model.UserRo;
import com.huaxia.rms.api.model.UserDetailRo;
import com.huaxia.rms.api.service.ApiUserService;
import com.huaxia.rms.core.model.RmsOrganization;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.model.RmsUserDetail;
import com.huaxia.rms.core.service.RmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("apiUserService")
public class ApiUserServiceImpl implements ApiUserService {
    private Logger logger = LoggerFactory.getLogger(ApiUserServiceImpl.class);

    @Resource
    private RmsUserService  userService;

    @Override
    public UserRo findOneByCode(String code) {
        UserRo userRo = null;
        try {
            RmsUser user = userService.findOneByCode(code);
            if(user != null) {
                userRo = new UserRo();
                BeanUtils.copyProperties(user, userRo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return userRo;
    }

    @Override
    public UserRo findOneById(int id) {
        UserRo userRo = null;
        try {
            RmsUser user = userService.findOneById(id);
            if(user != null) {
                userRo = new UserRo();
                BeanUtils.copyProperties(user, userRo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return userRo;

    }

    @Override
    public UserDetailRo findOneByUserId(int user_id) {
        UserDetailRo userDetailRo = null;
        try {
            RmsUserDetail userDetail = userService.findOneByUserId(user_id);
            if(userDetail != null) {
                userDetailRo = new UserDetailRo();
                BeanUtils.copyProperties(userDetail, userDetailRo);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return userDetailRo;

    }

    @Override
    //@MethodLog(remark = "查询部门用户")
    public List<UserRo> queryUserListByDeptCode(Map map) {

        List<UserRo> userRoList = null;
        try {

            List<RmsUser> list =  userService.queryUserListByDeptCode(map);
            if(list != null && list.size() > 0) {
                userRoList = new ArrayList<UserRo>();
                BeanUtils.copyProperties(list, userRoList);
            }
        }catch(Exception e){
            return null;
        }
        return userRoList;

    }

    @Override
    public int queryUserListByDeptCodeCount(Map map) {
        int userCount = userService.queryUserListByDeptCodeCount(map);

        return userCount;
    }

//    @Override
//    public List<UserRo> queryUser(Map<String, Object> map) {
//        return mBaseDao.findListByTemplate("rmsuser.selectUserByPage", map);
//    }
//
//    @Override
//    public int findUserCount(Map<String, Object> map) {
//        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectCount", map)));
//        return userCount;
//    }


}