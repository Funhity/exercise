/**
 * @项目名称:
 * @文件名称: RmsOrgCategoryService 版本号：1.0
 * @创建日期: 2017/12/5
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
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.service.RmsOrgCategoryService;
import com.huaxia.rms.core.model.RmsOrgCategory;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("orgCategoryService")
public class RmsOrgCategoryServiceImpl extends BaseServiceImpl implements RmsOrgCategoryService {
    private Logger logger = LoggerFactory.getLogger(RmsOrgCategoryServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsOrgCategory> mBaseDao;
    @Resource
    public RedisDao redisDao;

    @Override
    public Long deleteRmsOrgCategoryById(Long id) {
        return mBaseDao.delete("rmsorgcategory.delete", id);
    }

    @Override
    @MethodLog(remark = "创建机构类型")
    public Long createRmsOrgCategory(RmsOrgCategory record) {
        return mBaseDao.create("rmsorgcategory.insert", record);
    }

    @Override
    @MethodLog(remark = "更新机构类型")
    public Long updateRmsOrgCategory(RmsOrgCategory record) {
        return mBaseDao.update("rmsorgcategory.update", record);
    }

    @Override
    public List<RmsOrgCategory> queryRmsOrgCategoryList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsorgcategory.selectAll", map);
    }

    @Override
    public PageInfo<RmsOrgCategory> findRmsOrgCategoryPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsorgcategory.selectAll", pageNum, pageSize, map);
    }

    @Override
    public List<RmsOrgCategory> queryCategoryList(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmsorgcategory.selectCategorylist", map);
    }

    @Override
    public int queryCategoryListCount(Map map) {
        int count = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsorgcategory.selectCategorylistCount", map)));
        return count;
    }

    @Override
    public RmsOrgCategory findOneById(String id) {
        return (RmsOrgCategory)mBaseDao.findObjectByTemplate("rmsorgcategory.selectById", id);
    }

    @Override
    public RmsOrgCategory findOneByCode(String code) {
        return (RmsOrgCategory)mBaseDao.findObjectByTemplate("rmsorgcategory.selectByCode", code);
    }

    @Override
    public List<RmsOrgCategory> findListByCodes(String  orgCode) {
        return mBaseDao.findListByTemplate("rmsorgcategory.selectByCodes", orgCode);
    }

    @Override
    public List<RmsOrgCategory> queryAllCategoryList() {
        return mBaseDao.findListByTemplate("rmsorgcategory.selectAll", null);
    }

    @Override
    public List<TreeNode> getTreeData() {

        // 获取数据
        String key = RedisConstant.ORG_CATEGORY_PRE + "nodeTree";
        List<TreeNode> tnlist = null;
        String tnStr = redisDao.get(key);
        if (!StringUtils.isEmpty(key)) {
            tnlist = JSON.parseArray(tnStr, TreeNode.class);
        }
        if (tnlist != null) {
            return tnlist;
        } else {
            List<RmsOrgCategory> orgs = queryAllCategoryList();
            Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
            for (RmsOrgCategory org : orgs) {
                TreeNode node = new TreeNode();
                node.setText(org.getName());
                node.setCode(org.getCode());
                nodelist.put(node.getCode(), node);
            }
            // 构造树形结构
            tnlist = TreeUtil.getNodeList(nodelist);
            redisDao.save(key, tnlist);
            return tnlist;
        }
    }
}