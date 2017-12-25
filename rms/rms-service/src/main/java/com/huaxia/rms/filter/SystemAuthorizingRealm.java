package com.huaxia.rms.filter;

import com.huaxia.rms.core.model.RmsResource;
import com.huaxia.rms.core.model.RmsRole;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.service.RmsResourceService;
import com.huaxia.rms.core.service.RmsRoleService;
import com.huaxia.rms.core.service.RmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 系统安全认证实现类
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
    /**
     * 认证回调函数, 登录时调用
     */
    @Resource
    private RmsUserService userService;

    @Resource
    private RmsRoleService roleService;

    @Resource
    private RmsResourceService resourceService;

    @Value("${system.version}")
    private String version;

    /**
     * 用户认证
     *
     * @param authcToken 含登录名密码的信息
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        if (authcToken == null)
            throw new AuthenticationException("parameter token is null");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 校验用户名密码
        String password = String.copyValueOf(token.getPassword());
        RmsUser user = userService.findOneByLoginName(token.getUsername());
        if (user != null) {
//            if(!password.equals(user.getPassword())&& isNeedPassword()){
//                throw new IncorrectCredentialsException();
//            }
            //这样前端页面可取到数据
            SecurityUtils.getSubject().getSession().setAttribute("user",user);
            // 注意此处的返回值没有使用加盐方式,如需要加盐，可以在密码参数上加
            return new SimpleAuthenticationInfo(user.getId(), token.getPassword(), token.getUsername());
        }
        throw new UnknownAccountException();
    }

//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //更改用户名
////        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
////        user.name = newName;
//        AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
//        String username = principal.getName();
//        logger.info("----------username: " + username);
//        if (principal != null) {
//            Map<String, Object> attributes = principal.getAttributes();
//            if (attributes.size() > 0) {
//                String name = (String) attributes.get("name");
//                //若存在，将此用户存放到登录认证info中
//                return new SimpleAuthenticationInfo(username,null, name);
//            }
//        }
//        return null;
//
//    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用 shiro 权限控制有三种
     * 1、通过xml配置资源的权限
     * 2、通过shiro标签控制权限
     * 3、通过shiro注解控制权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

        if (principals == null) {
            throw new AuthorizationException("parameters principals is null");
        }
        //获取已认证的用户名（登录名）
        int userId=(Integer)super.getAvailablePrincipal(principals);
        if(userId == 0){
            return null;
        }
        //Set<String> roleCodes = roleService.getRoleCodeSet(userId);
        logger.info("------AuthorizationInfo.userId: " + userId);

        Set<String> roleCodeSet = new HashSet<String>();//角色CODE列表
        List roleIdList = new ArrayList();//角色ID列表
        List<RmsRole> roles = roleService.getRoleList(userId);
        if(roles != null && roles.size() > 0) {
            for (RmsRole role: roles) {
                roleIdList.add(role.getId());
                if(StringUtils.isNotBlank(role.getCode())) {
                    roleCodeSet.add(role.getCode());
                }
            }
        }
//        List roleIds = roleService.getRoleidList(Integer.parseInt(userId));
        //默认用户拥有所有权限
//        Set<String> functionCodes=functionService.getAllFunctionCode();
        /* Set<String> functionCodes=functionService.getFunctionCodeSet(roleCodes);*/

        //根据角色ID列表，获取资源
        Set<String> resourceCodeSet = resourceService.getResourceCodeSet(roleIdList, String.valueOf(userId));
        /**获取数据资源*/
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleCodeSet);
        authorizationInfo.setStringPermissions(resourceCodeSet);

        return authorizationInfo;
    }

    //是否需要校验密码登录，用于开发环境 0默认为开发环境，其他为正式环境（1，或者不配）
    public boolean isNeedPassword(){
        //String version = PropertiesUtil.getValue("system.version");
        if("0".equals(version))
            return false;
        else
            return true;
    }
}
