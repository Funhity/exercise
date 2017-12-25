package com.huaxia.rms.controller;

import com.alibaba.fastjson.JSON;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.service.RmsUserRoleService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.core.model.RmsUserRole;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = "/userrole")
public class UserRoleController {

    private Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Resource
    private RmsUserRoleService userRoleService;

    @RequestMapping(method = RequestMethod.GET, value = "/list.jhtml")
    @Login(AuthenType.page)
    private String list() {
        logger.info("-----------test------------");
        Map map = new HashMap();
        map.put("roleId", 1);
        map.put("start", 0);
        map.put("limit", 10);

        List<RmsUserRole> urs = userRoleService.queryUserRoleList(map);
        logger.info("-----------urs" + urs);
//        for (RmsUserRole ur : urs) {
//            logger.info("-----------ur.getUserId" + ur.getUserId());
//            logger.info("-----------ur.getUser" + ur.getUser());
//            logger.info("-----------ur.getUser().getName()" + ur.getUser().getName());
//
//        }
        return "base/auth/role_list";

    }


    /**
     * 用户选择
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/select")
    private String select(String roleId, HttpServletRequest request) {

        request.setAttribute("roleId", roleId);
        return "base/auth/userrole_select";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public RmsUserRole get(int id) {

        RmsUserRole ur = userRoleService.findById(id);
        return ur;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result save(String urlist, HttpServletRequest request) {

        logger.info("--------urlist: " + urlist);
        Authorization authorization = WebUtils.getSessionUser(request);

        List<RmsUserRole> urs = JSON.parseArray(urlist, RmsUserRole.class);

        for (RmsUserRole ur : urs) {
            //ur.setUpdateTime(new Date());
            //ur.setUpdateUser("");//session中获取
            ur.setUserId(ur.getUser().getId());
            ur.setCreateUser(authorization.getUsername());//session中获取
            userRoleService.deleteAuthInRedis(String.valueOf(ur.getUser().getId()));
        }
        userRoleService.batchSave(urs);

        //清除redis缓存
        userRoleService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
        userRoleService.deleteCacheByKeys(RedisConstant.ROLE_PRE);


        return new Result(true);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result delete(String ids, HttpServletRequest request) {
        /**
         * 批量删除
         * TODO
         */
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            List<String> list = Arrays.asList(ids.split(","));

            Map map = new HashMap();
            map.put("ids", list);
            map.put("username", authorization.getUsername());//session中获取

            userRoleService.deleteUserRole(map);

            //清除redis缓存
            userRoleService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            userRoleService.deleteCacheByKeys(RedisConstant.ROLE_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "已经被其他数据引用，不可删除");
        }

        /**
         * 批量删除
         * TODO
         */
        /*try {
            RmsUserRole userRole = userRoleService.findById(ids);
            userRole.setDeleteTime(new Date());
            //userRole.setDeleteUser("");//session中获取
            userRole.setDeleteMark(0);
            userRoleService.updateRmsUserRole(userRole);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "已经被其他数据引用，不可删除");
        }*/
        return new Result(true);
    }

}
