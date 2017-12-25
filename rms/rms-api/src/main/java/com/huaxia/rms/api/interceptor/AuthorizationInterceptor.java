package com.huaxia.rms.api.interceptor;

import com.huaxia.rms.api.model.ResourceRo;
import com.huaxia.rms.api.service.ApiResourceService;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthorizationInterceptor  extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Resource
    private ApiResourceService apiResourceService;

    @Value("${sso.server.login.url}")
    private String ssoLoginUrl;

    @Value("${sysid}")
    private String sysid;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isBlackUser = false;
        Authorization sessionUser = WebUtils.getSessionUser(request);
        if(sessionUser != null) {
            logger.info("----------用户已登录---------");

            /**
             * 获取用户权限
             * 菜单对应的按钮数据及权限
             */
            List<ResourceRo> resourceRoList = apiResourceService.getResourceListByUidAndSysid(sessionUser.getId(), sysid);
            for (ResourceRo resourceRo : resourceRoList) {

            }
        }

        return false;

    }
}
