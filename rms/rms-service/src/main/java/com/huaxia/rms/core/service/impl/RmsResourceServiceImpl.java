/**
 * @项目名称:
 * @文件名称: RmsResourceService 版本号：1.0
 * @创建日期: 2017/10/25
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.model.RmsRolePermission;
import com.huaxia.rms.core.model.RmsRolePermissionData;
import com.huaxia.rms.core.service.RmsResourceService;
import com.huaxia.rms.core.model.RmsResource;
import com.huaxia.rms.core.service.RmsRolePermissionDataService;
import com.huaxia.rms.core.service.RmsRolePermissionService;
import com.huaxia.rms.core.service.RmsRoleService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.MultipleTree;
import com.huaxia.rms.utils.Node;
import com.huaxia.rms.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("resourceService")
public class RmsResourceServiceImpl extends BaseServiceImpl implements RmsResourceService {
    private Logger logger = LoggerFactory.getLogger(RmsResourceServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsResource> mBaseDao;

    @Resource
    private RmsRoleService roleService;

    @Resource
    private RmsRolePermissionService rolePermissionService;

    @Resource
    private RmsRolePermissionDataService rolePermissionServiceData;

    @Override
    @MethodLog(remark = "删除资源")
    public Long deleteRmsResourceById(Long id) {
        return mBaseDao.delete("rmsresource.delete", id);
    }

    @Override
    @MethodLog(remark = "新增资源")
    public Long createRmsResource(RmsResource record) {
        return mBaseDao.create("rmsresource.insert", record);
    }

    @Override
    @MethodLog(remark = "更新资源")
    public Long updateRmsResource(RmsResource record) {
        return mBaseDao.update("rmsresource.update", record);
    }

    @Override
    @MethodLog(remark = "查询所有资源")
    public List<RmsResource> queryRmsResourceList() {
        //TODO
        return mBaseDao.findListByTemplate("rmsresource.selectAll",  null);
    }

    @Override
    public PageInfo<RmsResource> findRmsResourcePage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsresource.selectAll", pageNum, pageSize, map);
    }



    @Override
    public RmsResource findOneByCode(String code) {
//        Map paramMap = new HashMap();
//        paramMap.put("code", code);
        return (RmsResource)mBaseDao.findObjectByTemplate("rmsresource.selectByCode", code);
    }

    @Override
    public RmsResource findOneById(int id) {
//        Map paramMap = new HashMap();
//        paramMap.put("code", code);
        return (RmsResource)mBaseDao.findObjectByTemplate("rmsresource.selectById", id);
    }

    @Override
    public Map checkUnique(String code) {

        Map<String, Boolean> map = new HashMap<String, Boolean>();

        try {

            RmsResource resource = findOneByCode(code);

            if(resource == null) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
            }
            return map;
        } catch (Exception ex) {
            logger.info(ex.getMessage().toString());
            map.put("valid", false);
            return map;
        }
    }

    @Override
    public List<TreeNode> getTreeData(List<RmsResource> ress) {

        // 获取数据
        String key = RedisConstant.RES_PRE + "tree";
        List<TreeNode> tnlist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            tnlist = JSON.parseArray(tnStr, TreeNode.class);
        }
        if (tnlist != null) {
            return tnlist;
        } else {
            //List<RmsResource> ress = queryRmsResourceList();
            Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
            for (RmsResource res : ress) {

                TreeNode node = new TreeNode();
                node.setText(res.getName());
                node.setId(res.getId());
                node.setCode(res.getCode());
                node.setParentCode(res.getParentCode());
                node.setParentName(res.getParentName());
                node.setLayer(String.valueOf(res.getLayer()));
                node.setIconIndex(res.getIconIndex());
                node.setUrl(res.getUrl());
                nodelist.put(node.getCode(), node);
            }
            logger.info("----------nodelist: " + nodelist.size());
            // 构造树形结构
            tnlist = TreeUtil.getNodeList(nodelist);
//            logger.info("----------tnlist2: " + tnlist);
            redisDao.save(key, tnlist);
            return tnlist;
        }
    }

    @Override
    public String getSourceTreeByUid(int uid) {
        // 获取数据
        String key = RedisConstant.RES_PRE + "tree_" + uid;
        String tnStr = redisDao.get(key);
        if (tnStr != null) {
            return tnStr;
        } else {
            /**
             * 获取角色列表的所有资源权限
             * 如果是超级管理员，侧检索所有的资源
             */
            List<RmsResource>  list = new ArrayList<RmsResource>();
            if(uid == 1) {
                list = queryRmsResourceList();
            } else {
                List roleids = roleService.getRoleidList(uid);
                logger.info("----------roleids: " + roleids);
                if(roleids != null && roleids.size() > 0) {
                    list = getResesByCodes(roleids);
                }
            }
            String nodeStr = "";
            if(list != null && list.size() > 0) {
                nodeStr = getTreeStrByReslist(list);
                redisDao.save(key, nodeStr);
            }

            return nodeStr;
        }

    }

    @Override
    public String getSourceTreeByUidAndSyscode(int uid, String syscode) {
        // 获取数据
        String key = RedisConstant.RES_PRE + "tree_" + uid + "_" + syscode;
        String tnStr = redisDao.get(key);
        if (tnStr != null) {
            return tnStr;
        } else {
            /**
             * 如果是超级管理员，侧检索所有的资源
             */
            List<RmsResource>  list = new ArrayList<RmsResource>();
            if(uid == 1) {
                list = queryRmsResourceList();
            } else {
                List roleids = roleService.getRoleidList(uid);
                //logger.info("----------roleids: " + roleids);
                list = getResesByCodesAndSyscode(roleids, syscode);
            }

            String nodeStr = getTreeStrByReslist(list);
            redisDao.save(key, nodeStr);

            return nodeStr;
        }

    }

    @Override
    public List<RmsResource> getSourceListByUidAndSyscode(int uid, String syscode) {
        // 获取数据
        String key = RedisConstant.RES_PRE + "list_" + uid;
        if(StringUtils.isNotBlank(syscode)) {
            key = RedisConstant.RES_PRE + "list_" + uid + "_" + syscode;
        }

        List<RmsResource> resourceList = redisDao.get(key, List.class);
        if (resourceList != null) {
            return resourceList;
        } else {
            /**
             * 如果是超级管理员，侧检索所有的资源
             */
            List<RmsResource>  list = new ArrayList<RmsResource>();
            if(uid == 1) {
                list = queryRmsResourceList();
            } else {
                List roleids = roleService.getRoleidList(uid);
                //logger.info("----------roleids: " + roleids);
                list = getResesByCodesAndSyscode(roleids, syscode);
            }

            redisDao.save(key, list);

            return list;
        }

    }

    private String getTreeStrByReslist(List<RmsResource>  list) {
        String nodeStr = "";
        if(list != null && list.size() > 0) {

            // 节点列表（散列表，用于临时存储节点对象）
            Map nodeList = new HashMap();
            // 根节点
            //Node root = null;
            // 根据结果集构造节点列表（存入散列表）
            for (RmsResource res : list) {
                Node node = new Node();
                node.id = res.getCode();
                node.name = res.getName();
                node.parentCode = res.getParentCode();
                node.iconIndex = res.getIconIndex();
                node.url = res.getUrl();
                nodeList.put(node.id, node);
            }
            nodeStr = MultipleTree.getOrderRecord222(nodeList);
            //logger.info("-------------nodeStr: " + nodeStr);

        }
        return nodeStr;
    }


    /**
     * 判断部门下是否有员工
     * @return
     */
    public boolean referByUser(String code) {

        Map map = (Map)mBaseDao.findObjectByTemplate("rmsresource.referByUser", code);
        logger.info("----------map: " + map);

        if(map != null) {
            return true;
        }
        return false;
    }

    public String findMaxCodeByParentCode(String code) {
        String maxCode = (String) mBaseDao.findObjectByTemplate("rmsresource.selectMaxCodeByParentCode", code);
        return maxCode;
    }

    @Override
    public Result getResNames(String code) {
        Map nameMap = redisDao.get(RedisConstant.RES_PRE + code, Map.class);
        if(nameMap == null) {
            nameMap = new HashMap();
            RmsResource res = findOneByCode(code);

            String name = res.getName();
            /**
             * 存在上级机构的组织，
             * 再获取到上级机构，存入redis
             */
//            String parentName = "";
            if(StringUtils.isNotBlank(res.getParentCode())){
                Map result  = (Map)getResNames(res.getParentCode()).getData();
                String parentName = result.get("parentName")+"->"+ res.getName();
                logger.info("---------getResNames.parentName: " + parentName);
                nameMap.put("name", res.getName());
                nameMap.put("parentName", parentName);
                redisDao.save(RedisConstant.RES_PRE + code, nameMap);
                return new Result(true, nameMap,"获取成功");
            } else {
                nameMap.put("name", res.getName());
                nameMap.put("parentName", res.getName());
                redisDao.save(RedisConstant.RES_PRE + code, nameMap);
                return new Result(true, nameMap,"获取成功");
            }
        } else {
            return new Result(true, nameMap,"获取成功");
        }
    }


    @Override
    public List<RmsResource> getResesByCode(String code) {
        String key = RedisConstant.RES_PRE + code;
        List ress = redisDao.get(key, List.class);
        if (ress == null) {
            ress = mBaseDao.findListByTemplate("rmsresource.selectResesByCode", code);
            redisDao.add(key, ress);
            return ress;
        } else {
            return ress;
        }
    }

    @Override
    public List<RmsResource> getResesByCodes(List ids) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);

        return mBaseDao.findListByTemplate("rmsresource.selectResesByRoleCodes", params);

    }

    @Override
    public List<RmsResource> getResesByCodesAndSyscode(List ids, String syscode) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("syscode", syscode);

        return mBaseDao.findListByTemplate("rmsresource.selectResesByRoleCodes", params);

    }

    @Override
    public Set<String> getResourceCodeSet(List<Integer> roleids, String userId) {
        /*int len = roleCodes.size();
        if (len == 0)
            return null;
        String sql = PropertiesUtil.getValue("shiro.sql.permissions");
        Map<String, Object> params = new HashMap<String, Object>();
        String[] strs = new String[len];
        int i = 0;
        for (String roleCode : roleCodes) {
            strs[i++] = "'" + roleCode + "'";
        }
        //无法注入进去参数
        //params.put("param",StrUtil.join(strs));
        sql = sql.replace(":param", StrUtil.join(strs));
        List<Map<String, Object>> list = super.findMapBySql(sql, params);
        Set<String> retSet = new HashSet<String>();
        for (Map map : list) {
            retSet.add(map.get("code").toString());
        }
        return retSet;*/
        //以上注释，如放开需要将shiro.sql.permissions f.* 改为f.code
        if(roleids.size() == 0) {
            return null;
        }

        List<RmsResource> resourceList = getAllResesByRoleCode(roleids, userId);
        Set<String> retSet = new HashSet<>();
        for (RmsResource resource : resourceList) {
            retSet.add(resource.getShortName());
        }
        return retSet;
    }

    @Override
    public List<RmsResource> getAllResesByRoleCode(List<Integer> roleids, String userId) {
        try {
            String key = RedisConstant.PERMISSION_PRE + userId;
            List<RmsResource> resourceList = redisDao.getList(key, RmsResource.class);
            if(resourceList == null) {
                /**根据角色id集合，获取其所有的资源权限*/
                List<RmsRolePermission> rolePermissions = rolePermissionService.getRolePermissionByRoleCodes(roleids);

                /**根据资源权限，再获取其角色对应的数据权限*/
                if(rolePermissions != null && rolePermissions.size() > 0) {
                    resourceList = getResourceListWithoutRepeat(rolePermissions);
                    redisDao.add(key, resourceList);
                }
            }
            return resourceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //resourceList去重，并注入数据权限PermissionData
    public List<RmsResource> getResourceListWithoutRepeat(List<RmsRolePermission> rolePermissions){
        List<RmsResource> list = new ArrayList<RmsResource>();
        Map<Integer, Boolean> map = new HashMap<>();
        if(rolePermissions != null && rolePermissions.size() > 0) {
            for (RmsRolePermission rolePermission : rolePermissions) {

                /**角色对应的数据权限*/
                Map paramMap = new HashMap();
                paramMap.put("roleId", rolePermission.getRoleId());
                paramMap.put("resourceId", rolePermission.getResourceId());
                List<RmsRolePermissionData> rpList = rolePermissionServiceData.queryRmsRolePermissionDataList(paramMap);

                if(map.containsKey(rolePermission.getResourceId())){
                    /**判断list中是否已经存在Resource，如果存在判断其数据权限，数据权限更多的，优先放入list中*/
                    RmsResource resource = getResourceById(rolePermission.getResourceId(), list);
                    /**
                     * 数据权限合并，以多数据范围优先，
                     * TODO
                     * 数据权限选择逻辑还需要进一步优化，可设置优先级
                     * 另外如果不同角色对应资源的数据权限不一致，则以多为准，但存在一个问题：
                     * A角色对A菜单的查询按钮有1，2，3权限中权限，
                     * B角色对A菜单的查询按钮有3，4权限，如果按取多优先的话，则取1，2，3权限，4权限则被舍弃
                     * 次问题后期优化
                     */
                    if(resource.getRplist() != null) {
                        if(rpList.size() > resource.getRplist().size()){
                            resource.setRplist(rpList);
                        }
                    } else {
                        resource.setRplist(rpList);
                    }
                }else{
                    map.put(rolePermission.getResourceId(), true);
                    RmsResource resource = findOneById(rolePermission.getResourceId());
                    resource.setRplist(rpList);
                    list.add(resource);
                }
            }
        }
        return list;
    }

    public RmsResource getResourceById(int id, List<RmsResource> resources){
        if(resources != null && resources.size() > 0) {
            for (RmsResource resource : resources) {
                if(id == resource.getId()){
                    return resource;
                }
            }
            return null;
        } else {
            return null;
        }
    }

}