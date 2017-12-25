package com.huaxia.rms.controller;

import com.huaxia.rms.annotation.RefreshCSRFToken;
import com.huaxia.rms.annotation.VerifyCSRFToken;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.model.RmsUserBlacklist;
import com.huaxia.rms.core.service.RmsUserBlacklistService;
import com.huaxia.rms.pojo.Result;
import com.huaxia.sso.utils.WebUtils;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import com.huaxia.sso.utils.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 */
@Controller
@RequestMapping("/black")
public class BlacklistController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private RmsUserBlacklistService blacklistService;

    @Resource
    public RedisDao redisDao;

    @RequestMapping(method = RequestMethod.GET, value = "/list.jhtml")
    @Login(AuthenType.page)
    private String pagelist(HttpServletRequest request) {

        logger.info("-------------黑名单列表-----------");
        Authorization authorization = WebUtils.getSessionUser(request);

        return "base/blacklist/black_list";
    }

    /**
     * 用户编辑 new page
     *
     * @return
     */
    @RefreshCSRFToken
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    private String pageEdit(String id, String orgcode, HttpServletRequest request) {

        request.setAttribute("id", id);
        request.setAttribute("orgcode", orgcode);
        return "base/blacklist/black_edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get")
    @ResponseBody
    private RmsUserBlacklist getUser(String id) {
        System.out.println("----logger: " + logger);
        return blacklistService.findOneById(id);
    }

    @VerifyCSRFToken
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    @Login(AuthenType.json)
    private Result saveUser(RmsUserBlacklist blackUser, HttpServletRequest request) {
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            /**
             * 判断用户或者IP是否已存在
             */

            if (StringUtils.isBlank(String.valueOf(blackUser.getId())) || "null".equals(String.valueOf(blackUser.getId()))) {
                blackUser.setCreateUser(authorization.getUsername());
                blacklistService.createRmsUserBlacklist(blackUser);
            } else {
                RmsUserBlacklist oldBlackUser = blacklistService.findOneById(String.valueOf(blackUser.getId()));
                /**
                 * 新修改的黑名单信息复制到oldBlackUser里面
                 */
                BeanUtils.copyProperties(blackUser,oldBlackUser);
                oldBlackUser.setUpdateTime(new Date());
                oldBlackUser.setUpdateUser(authorization.getUsername());//session中获取
                blacklistService.updateRmsUserBlacklist(oldBlackUser);
            }

            /**
             * 清除黑名单缓存
             */
            String key = RedisConstant.BLACK_PRE;
            redisDao.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result(true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    @ResponseBody
    @Login(AuthenType.json)
    private Result deleteUser(@PathVariable("id") String id, HttpServletRequest request) {

        logger.info("-----------删除黑名单用户操作------------");
        try {
            Authorization authorization = WebUtils.getSessionUser(request);

            RmsUserBlacklist blackUser = blacklistService.findOneById(id);
            blackUser.setDeleteMark(0);//删除状态
            blackUser.setDeleteTime(new Date());
            blackUser.setDeleteUser(authorization.getUsername());//session中获取
            blacklistService.updateRmsUserBlacklist(blackUser);

            /**
             * 清除黑名单缓存
             */
            String key = RedisConstant.BLACK_PRE;
            redisDao.delete(key);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
        return new Result(true);
    }
}
