package com.huaxia.rms.controller;

import com.alibaba.fastjson.JSON;

import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.core.service.RmsRolePermissionDataService;
import com.huaxia.rms.core.service.RmsRolePermissionService;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by billJiang on 2017/1/3.
 * e-mail:jrn1012@petrochina.com.cn qq:475572229
 * 角色授权
 */
@Controller
@RequestMapping(value = "/rolefunc")
public class RolePermissionController {
    private Logger logger = LoggerFactory.getLogger(RolePermissionController.class);
    @Resource
    private RmsRolePermissionService rolePermissionService;

    @Resource
    private RmsRolePermissionDataService rolePermissionDataService;

    //角色授权管理主界面
    @RequestMapping(value = "/list.jhtml", method = RequestMethod.GET)
    @Login(AuthenType.page)
    private String list() {
        logger.info("----------权限列表页面-------");
        return "base/auth/rolefunc_list";
    }

    //跳转到授权页面 restful风格
    @RequestMapping(value = "/select/{roleId}", method = RequestMethod.GET)
    private String select(@PathVariable("roleId") int roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        return "base/auth/rolefunc_select";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    private Result delete(@PathVariable("id") int id, HttpServletRequest request) {

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsRolePermission rolePermission = rolePermissionService.findById(id);
            if(rolePermission != null) {
                rolePermission.setDeleteMark(0);
                rolePermission.setDeleteTime(new Date());
                rolePermission.setDeleteUser(authorization.getUsername());//seesion中获取
                rolePermissionService.updateRmsRolePermission(rolePermission);

                //-----------update redis-----------
                /**
                 * 清除缓存
                 */
                rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
                rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
                rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);

//                rolePermissionService.deleteAuthInRedis(rolePermission.getRoleId());
                //----------------------------------
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }

        return new Result();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    private Result save(RmsRolePermission rolePermission, HttpServletRequest request) {

        /**
         * 1、删除该角色对应的资源权限
         * 2、重新存入权限角色
         * 3、删除该角色对应资源的数据权限
         * 4、重新存入数据权限
         */

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            //1、删除该角色对应的资源权限
            if(rolePermission != null && rolePermission.getId() > 0) {
                rolePermission.setDeleteMark(0);
                rolePermission.setDeleteTime(new Date());
                rolePermission.setDeleteUser(authorization.getUsername());//seesion中获取
                rolePermissionService.updateRmsRolePermission(rolePermission);
            }
            //2、重新生成数据
            rolePermission.setCreateUser(authorization.getUsername());//session中获取
            rolePermissionService.createRmsRolePermission(rolePermission);

            //3、删除该角色对应资源的数据权限
            Map<String,Object> params = new HashMap<>();
            params.put("roleId",rolePermission.getRoleId());
            params.put("resourceId",rolePermission.getResourceId());
            List<RmsRolePermissionData> list = rolePermissionDataService.queryRmsRolePermissionDataList(params);
            if (list != null && list.size() > 0) {
                RmsRolePermissionData rolePermissionData = list.get(0);
                rolePermissionData.setDeleteMark(0);
                rolePermissionData.setDeleteTime(new Date());
                rolePermissionData.setDeleteUser(authorization.getUsername());//seesion中获取
                rolePermissionDataService.updateRmsRolePermissionData(rolePermissionData);

            }
            //4、生成数据权限数据
            rolePermissionDataService.saveBatchRolePermissionData(rolePermission.getRplist());

            //-----------清除redis缓存-----------
            rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);
//            rolePermissionService.deleteAuthInRedis(rolePermission.getRoleId());
            //----------------------------------

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result();

    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    private RmsRolePermission get(int roleId, int resourceId) {
        return rolePermissionService.getRolePermission(roleId, resourceId);
    }

    /**
     * 批量保存角色授权（角色绑定功能）
     *
     * @param rflist 角色绑定功能列表
     * @return
     */
    @RequestMapping(value = "/save_batch", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result saveBatch(int roleId, String rflist, HttpServletRequest request) {
        /**
         * 1、删除该角色的授权数据
         * 2、新插入授权数据
         */
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            Map<String,Object> params = new HashMap<>();
            params.put("roleId", roleId);
            params.put("username", authorization.getUsername());//session中获取
            rolePermissionService.deleteByRoleId(params);

            List<RmsRolePermission> roleFunctionList = JSON.parseArray(rflist, RmsRolePermission.class);
            //添加操作用户
            for (RmsRolePermission rrp : roleFunctionList) {
                rrp.setCreateUser(authorization.getUsername());
            }
            rolePermissionService.saveBatchRolePermission(roleFunctionList);

            //-----------清除redis缓存-----------
            rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);
//            rolePermissionService.deleteAuthInRedis(roleId);
            //----------------------------------
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result();

    }

    /**
     * 批量保存
     *
     * @param roleId
     * @param functionId
     * @param fflist
     * @return
     */
    @RequestMapping(value = "/ff/save_batch", method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result saveBatch_ff(int roleId, String functionId, String fflist, HttpServletRequest request) {
        /**
         * 1、删除该角色的授权数据权限
         * 2、新插入新的数据授权
         */
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            Map<String,Object> params = new HashMap<>();
            params.put("roleId", roleId);
            params.put("username", authorization.getUsername());//session中获取
            rolePermissionDataService.deleteByRoleId(params);

            List<RmsRolePermissionData> functionFilterList = JSON.parseArray(fflist, RmsRolePermissionData.class);
            rolePermissionDataService.saveBatchRolePermissionData(functionFilterList);

            //-----------清除redis缓存-----------
            rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);
//            rolePermissionDataService.deleteAuthInRedis(roleId);
            //----------------------------------
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result();

    }

    /**
     * 数据权限编辑
     * @param roleId 角色ID
     * @param resourceId 功能ID
     * @param id 编辑时实体ID
     * @param request
     * @return
     */
    @RequestMapping(value = "/ff/edit/{roleId}/{resourceId}/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("roleId") int roleId, @PathVariable("resourceId") int resourceId, @PathVariable("id") String id, HttpServletRequest request) {
        request.setAttribute("roleId_ff",roleId);
        request.setAttribute("resourceId_ff",resourceId);
        request.setAttribute("id",id);
        return "base/auth/rolefunc_ff_edit";
    }

    /**
     * 保存数据权限
     * @param rpd 数据权限实体
     * @return 执行结果
     */
    @RequestMapping(value="/ff/save",method = RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result save_ff(RmsRolePermissionData rpd, HttpServletRequest request){

        logger.info("--------数据权限操作---------");
        logger.info("--------rpd: " + rpd);
        logger.info("--------rpd.getId: " + rpd.getId());
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            if (StringUtils.isBlank(String.valueOf(rpd.getId())) || "null".equals(String.valueOf(rpd.getId()))) {
                rpd.setCreateUser(authorization.getUsername());
                rolePermissionDataService.createRmsRolePermissionData(rpd);
                //if(rpd.getId() > 0) {
            } else {
                rpd.setUpdateTime(new Date());
                rpd.setUpdateUser(authorization.getUsername());
                rolePermissionDataService.updateRmsRolePermissionData(rpd);
            }

            //-----------清除redis缓存-----------
            rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }

        return new Result();
    }

    /**
     * 删除数据权限
     * @param id
     * @return 执行结果
     */
    @RequestMapping(value="/ff/delete/{id}",method= RequestMethod.POST)
    @ResponseBody
    @Login(AuthenType.page)
    public Result delete_ff(@PathVariable("id") int id, HttpServletRequest request){

        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsRolePermissionData rolePermissionData =  rolePermissionDataService.findById(id);
            rolePermissionData.setDeleteMark(0);
            rolePermissionData.setDeleteTime(new Date());
            rolePermissionData.setDeleteUser(authorization.getUsername());//seesion中获取
            rolePermissionDataService.updateRmsRolePermissionData(rolePermissionData);

            //-----------清除redis缓存-----------
            rolePermissionService.deleteCacheByKeys(RedisConstant.ROLE_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.PERMISSION_PRE);
            rolePermissionService.deleteCacheByKeys(RedisConstant.RES_PRE);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }

        return new Result();
    }

    /**
     * 获取数据权限
     * @param id
     * @return
     */
    @RequestMapping(value="/ff/get/{id}",method= RequestMethod.POST)
    @ResponseBody
    public RmsRolePermissionData get_ff(@PathVariable("id") int id ){
        RmsRolePermissionData rolePermissionData =  rolePermissionDataService.findById(id);

        return rolePermissionData;
    }
}
