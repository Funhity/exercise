package com.huaxia.rms.controller;

import com.huaxia.rms.annotation.RefreshCSRFToken;
import com.huaxia.rms.annotation.VerifyCSRFToken;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.model.RmsOrgCategory;
import com.huaxia.rms.core.model.RmsOrganization;
import com.huaxia.rms.core.service.RmsOrgCategoryService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.rms.pojo.TreeNode;
import com.huaxia.rms.utils.TreeUtil;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class OrgCategoryController {

    private Logger logger = LoggerFactory.getLogger(OrgCategoryController.class);

    @Resource
    private RmsOrgCategoryService orgCategoryService;

    @Resource
    public RedisDao redisDao;

    @RequestMapping(method = RequestMethod.GET, value = "/list.jhtml")
//    @Login(AuthenType.page)
    private String pagelist(HttpServletRequest request) {
        Authorization authorization = WebUtils.getSessionUser(request);
        return "base/org/category_list";
    }

    /**
     * getTreeData 构造bootstrap-treeview格式数据
     *
     * @return
     */
    @RequestMapping(value = "/treeData.do", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> getTreeData() {
        logger.info("---------构造bootstrap-treeview格式数据-----------");
        List<TreeNode> list = orgCategoryService.getTreeData();
        logger.info("-----------list: " + list);
        return list;

    }
    /**
     * 用户编辑 new page
     *
     * @return
     */
    @RefreshCSRFToken
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    private String pageEdit(String id, HttpServletRequest request) {

        request.setAttribute("id", id);
        return "base/org/category_edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get")
    @ResponseBody
    private RmsOrgCategory getUser(String id) {
        return orgCategoryService.findOneById(id);
    }

    @VerifyCSRFToken
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
//    @Login(AuthenType.json)
    private Result saveUser(RmsOrgCategory category, HttpServletRequest request) {
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            if (StringUtils.isBlank(String.valueOf(category.getId())) || "null".equals(String.valueOf(category.getId()))) {
                category.setCreatedUser(authorization.getUsername());
                orgCategoryService.createRmsOrgCategory(category);
            } else {
                RmsOrgCategory oldCategory = orgCategoryService.findOneById(String.valueOf(category.getId()));
                BeanUtils.copyProperties(category, oldCategory);
                oldCategory.setUpdateTime(new Date());
                oldCategory.setUpdateUser(authorization.getUsername());//session中获取
                orgCategoryService.updateRmsOrgCategory(oldCategory);
            }

            /**
             * 清除机构类别缓存
             */
            String key = RedisConstant.ORG_CATEGORY_PRE;
            redisDao.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result(true);
    }
}
