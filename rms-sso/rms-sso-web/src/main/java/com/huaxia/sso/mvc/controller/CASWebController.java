package com.huaxia.sso.mvc.controller;


import com.huaxia.rms.api.model.OrganizationRo;
import com.huaxia.rms.api.service.ApiOrganizationService;
import com.huaxia.rms.api.service.ApiResourceService;
import com.huaxia.sso.utils.Authorization;
import com.huaxia.sso.utils.Configuration;
import com.huaxia.sso.utils.Md5Utility;
import com.huaxia.sso.utils.StringUtils;
import com.huaxia.sso.utils.WebUtility;
import com.huaxia.sso.common.exception.CASException;
import com.huaxia.sso.common.utility.CommonUtils;
import com.huaxia.sso.common.utility.Constants;
import com.huaxia.sso.model.TRMSUser;
import com.huaxia.sso.service.RMSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.mvc.controller
 * 作者-Liu zhilai
 * 说明：SSO HTML登录处理类
 * 2016/3/21 15:19
 * 2017华夏信财-版权所有
 */
@Controller
@RequestMapping("/user")
public class CASWebController {

    private Logger logger = LoggerFactory.getLogger(CASWebController.class.getName());
    
    @Autowired
    private RMSManager rmsManager;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Value("${rms_url}")
    private String rms_url;

//    @Resource
//    private ApiOrganizationService apiOrganizationService;

    @RequestMapping("/orgTest")
    public String orgTest(HttpServletRequest request, HttpServletResponse response) {

//        OrganizationRo org = apiOrganizationService.getOrgByCode("100100");
//
//        List<OrganizationRo> orgs2 = apiOrganizationService.getAllOrgList();
//
//        List<OrganizationRo> orgs = apiOrganizationService.getOrgTree();
//
//        logger.info("----------org: " + org);
//        logger.info("----------orgs2: " + orgs2);
//        logger.info("----------orgs: " + orgs);

        return "user/notice";
    }


    @RequestMapping("/notice")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "user/notice";
    }

    @RequestMapping("/notice_1012")
    public String notice(HttpServletRequest request, HttpServletResponse response) {
        return "user/notice_1012";
    }
    @RequestMapping("/notice_core_1001")
    public String notice_core_1012(HttpServletRequest request, HttpServletResponse response) {
        return "user/notice_core_1001";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        logger.info("------------用戶登陆页面-------------");
        String redirectURL = request.getParameter(Constants.CAS_REDIRECT_URL_KEY);
        String sysid = getSysid(request);
        
        logger.info("redirect: " + redirectURL);
        logger.info("sysid: " + sysid);

        if (StringUtils.isBlank(redirectURL)) {
            redirectURL = rms_url;
        }

        map.addAttribute("redirect", redirectURL);
        map.addAttribute("sysid", sysid);

        /**
         * 检查本地Cookie中 是否含 TGC ，校验是否失效
         */
        Map cookie = WebUtility.getCookies(request);
        logger.info("cookie: " + cookie);
        // TGC
        String tgc = (null == cookie.get(Constants.CAS_TGC_KEY) ? null : cookie.get(Constants.CAS_TGC_KEY).toString());
        logger.info("tgc: " + tgc);
        // 临时票据
        String _ticket = (null == request.getParameter("_ticket") ? null : request.getParameter("_ticket"));
        logger.info("_ticket: " + _ticket);
        if (StringUtils.isNotBlank(tgc) && rmsManager.tgcValidation(tgc)) {
            // 认为用户已登录 生成 tick/ 生成票据
            String ticket = rmsManager.ticketGrant(cookie.get(Constants.CAS_TGC_KEY).toString());
            String url = CommonUtils.generatorRedirectURL(request, ticket);
            logger.info("------------重定向URL: " + url);
            // 重定向URL 回 web端
            return "redirect:" + url;
        } else if (StringUtils.isNotBlank(_ticket) && rmsManager.ticketValidation(_ticket)) {
            final Authorization authorization = rmsManager.getUserByTicket(_ticket);
            // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
            final String TGC = rmsManager.tgcGrant(authorization.getId());
            Map cookiesMap = new HashMap();
            cookiesMap.put(Constants.CAS_UID_KEY, authorization.getId());
            cookiesMap.put(Constants.CAS_UNAME_KEY, authorization.getUsername());
            cookiesMap.put(Constants.CAS_TGC_KEY, TGC);
            cookiesMap.put("timeout", Constants.CAS_TIMEOUT_KEY);
            cookiesMap.put("domain", Configuration.getProperty("sso.domain"));

            WebUtility.setCookies(response, cookiesMap);
            // 重定向URL 回 APP端
            String url = CommonUtils.generatorRedirectURL(request, _ticket);
            logger.info("------------重定向URL2: " + url);
            return "redirect:" + url;
        } else {
            logger.info("------------自动登陆操作-----------");

        }

        return getLoginPage(sysid);
    }

    private String getSysid(HttpServletRequest request) {
        String sysid = request.getParameter("sysid");
        if (StringUtils.isBlank(sysid)) sysid = Constants.RMS_SYSTEM_ID;
        return sysid;
    }

    private String getLoginType(HttpServletRequest request) {
        String loginType = request.getParameter("loginType");
        if (StringUtils.isBlank(loginType)) loginType = Constants.LOGIN_TYPE_OTHER;
        return loginType;
    }


    @RequestMapping("login_submit")
    public String login_submit(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String sysid = getSysid(request);//不同渠道登陆，显示的登陆页面不同
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String loginType = this.getLoginType(request);//登陆类型，0：安卓登录 1：IOS登录 2：其它登录 3：自动登录
//            String agree = request.getParameter("agree");
            // 验证登录
            if (rmsManager.userLoginValidation(username, password, request, loginType)) {
                final TRMSUser user = rmsManager.getByUserName(username);
                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
                final String TGC = rmsManager.tgcGrant(user.getId());
                final Authorization authorization = rmsManager.getUserByTgc(TGC);
                Map cookieMap =  new HashMap();
                cookieMap.put(Constants.CAS_UID_KEY, user.getId());
                cookieMap.put(Constants.CAS_UNAME_KEY, authorization.getUsername());
                cookieMap.put(Constants.CAS_TGC_KEY, TGC);
                cookieMap.put(Constants.CAS_AUTOCODE_KEY, user.getPassword());
                cookieMap.put("timeout", Constants.CAS_TIMEOUT_KEY);
                cookieMap.put("domain", Configuration.getProperty("sso.domain"));
                WebUtility.setCookies(response, cookieMap);

                // 登录成功 生成票据
                String ticket = rmsManager.ticketGrant(TGC);
                // 重定向URL 回 APP端
                request.setAttribute("success", true);
                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
                request.setAttribute("redirect", redirect);
            }
        } catch (CASException ex) {
            request.setAttribute("code", ex.getCode());
            request.setAttribute("success", false);
            request.setAttribute("message", ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return getLoginSubmitPage(sysid);
    }

    @RequestMapping("login_submit_json")
    @ResponseBody
    public Map<String, Object> login_submit_json(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Map<String, Object> respInfo = new HashMap<String, Object>();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //String loginType = this.getLoginType(request);
            TRMSUser user = rmsManager.getByUserName(username);
            if(user == null) {
                respInfo.put("code", Constants.KEY_EXCEPTION_AUTHENTICATION_USER_NOT_EXISTS);
                respInfo.put("message", "对不起，该用户不存在");
                return respInfo;
            }
            if (null != user) {
                user.setInputPwd(password);
            }

            // 验证登录
            Map<String, Object> retMap = rmsManager.userLoginValidation(user, request);
            if(retMap == null) {
                respInfo.put("code", "");
                respInfo.put("success", false);
                respInfo.put("message", "登陆失败，请稍后重试");
                return respInfo;
            }

            if(Constants.AUTHENTICATION_USER_SUCCESS.equals(retMap.get("code"))) {
                //final TRMSUser user = rmsManager.getByUserName(username);
                // 登录成功 生成 会话 TGC ,存入redis ，并且写入 客户端 COOKIE
                final String TGC = rmsManager.tgcGrant(user);
                final Authorization authorization = rmsManager.getUserByTgc(TGC);

                Map<String, Object> cookiesMap = new HashMap<String, Object>();
                cookiesMap.put(Constants.CAS_UID_KEY, user.getId());
                cookiesMap.put(Constants.CAS_UNAME_KEY, authorization.getUsername());
                cookiesMap.put(Constants.CAS_TGC_KEY, TGC);
//                cookiesMap.put(Constants.CAS_AUTOCODE_KEY, user.getPassword());
                cookiesMap.put("timeout", Constants.CAS_TIMEOUT_KEY);
                //测试时关闭，发布时需要开启
                cookiesMap.put("domain", Configuration.getProperty("sso.domain"));
                WebUtility.setCookies(response, cookiesMap);

                // 登录成功 生成票据
                String ticket = rmsManager.ticketGrant(TGC);
                //logger.info("--------ticket: " + ticket);
                // 重定向URL
                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
                logger.info("登陆成功，重定向: " + redirect);
                respInfo.put("success", true);
                respInfo.put("redirect", redirect);
            } else {
                respInfo.put("success", false);
                respInfo.put("code", retMap.get("code"));
                respInfo.put("message", retMap.get("message"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            respInfo.put("success", false);
            respInfo.put("code", "");
            respInfo.put("message", "登陆失败，请稍后重试");
        }

        return respInfo;
    }

    @RequestMapping("logout")
//    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String redirectURL = request.getParameter(Constants.CAS_REDIRECT_URL_KEY);//退出后到登陆页面+ redirectURL
        String returnUrl = request.getParameter(Constants.CAS_RETURN_URL_KEY);//自动跳转指定链接
        String sysid = getSysid(request);

        if (StringUtils.isBlank(redirectURL)) {
            redirectURL = rms_url;
        }

        // 退出链接，直接清除tgc
        Map cookie = WebUtility.getCookies(request);
        String tgc = (null == cookie.get(Constants.CAS_TGC_KEY) ? null : cookie.get(Constants.CAS_TGC_KEY).toString());
        if (StringUtils.isNotBlank(tgc)){
            rmsManager.removeTgc(tgc);
        }

        Map cookieMap = new HashMap<String, Object>();
//        cookieMap.put("usys", "");
//        cookieMap.put(Constants.CAS_AUTOCODE_KEY, "");
        cookieMap.put("uid", "");
        cookieMap.put("tgc", "");
        cookieMap.put("uname", "");
        cookieMap.put("timeout", 0);
        cookieMap.put("domain", Configuration.getProperty("sso.domain"));

        WebUtility.setCookies(response, cookieMap);
        try {
            if (StringUtils.isNotBlank(returnUrl)) {
//                request.setAttribute("returnUrl", URLDecoder.decode(URLDecoder.decode(returnUrl, "UTF-8"), "UTF-8"));
                return "redirect:" + URLDecoder.decode(URLDecoder.decode(returnUrl, "UTF-8"), "UTF-8");
            }
        } catch (Exception ex) {
            logger.info("退出返回异常:" + ex.getMessage());
            ex.printStackTrace();
        }

        map.addAttribute("redirect", redirectURL);
        map.addAttribute("sysid", sysid);

        return getLoginPage(sysid);
    }

//    @RequestMapping("login_code_submit")
//    public String login_code_submit(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        String sysid = getSysid(request);
//        try {
//            String username = request.getParameter("username");
//            String validatecode = request.getParameter("validatecode");
//            String loginType = this.getLoginType(request);
////            String agree = request.getParameter("agree");
//            // 验证登录
//            if (rmsManager.userLoginValidateCodeValidation(username, validatecode, request, sysid, loginType)) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                WebUtility.setCookies(response, new HashMap() {{
//                    put(Constants.CAS_UID_KEY, user.getId());
//                    put(Constants.CAS_UNAME_KEY, authorization.getUsername());
//                    put(Constants.CAS_TGC_KEY, TGC);
//                    put(Constants.CAS_AUTOCODE_KEY, user.getPasswd());
//                    put("timeout", Constants.CAS_TIMEOUT_KEY);
//                    put("domain", Configuration.getProperty("sso.domain"));
//                }});
//                // 登录成功 生成票据
//                String ticket = rmsManager.ticketGrant(TGC);
//                // 重定向URL 回 APP端
//                request.setAttribute("success", true);
//                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
//                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
//                request.setAttribute("redirect", redirect);
//            }
//        } catch (CASException ex) {
//            request.setAttribute("code", ex.getCode());
//            request.setAttribute("success", false);
//            request.setAttribute("message", ex.getMessage());
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
//        return getLoginCodeSubmitPage(sysid);
//    }

//    @RequestMapping("login_code_submit_json")
//    @ResponseBody
//    public Map<String, Object> login_code_submit_json(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        Map<String, Object> respInfo = new HashMap<String, Object>();
//        try {
//            String username = request.getParameter("username");
//            String validatecode = request.getParameter("validatecode");
//            String sysid = getSysid(request);
//            String loginType = getLoginType(request);
////            String agree = request.getParameter("agree");
//            // 验证登录
//            if (rmsManager.userLoginValidateCodeValidation(username, validatecode, request, sysid, loginType)) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                WebUtility.setCookies(response, new HashMap() {{
//                    put(Constants.CAS_UID_KEY, user.getId());
//                    put(Constants.CAS_UNAME_KEY, authorization.getUsername());
//                    put(Constants.CAS_TGC_KEY, TGC);
//                    put(Constants.CAS_AUTOCODE_KEY, user.getPasswd());
//                    put("timeout", Constants.CAS_TIMEOUT_KEY);
//                    put("domain", Configuration.getProperty("sso.domain"));
//                }});
//                // 登录成功 生成票据
//                String ticket = rmsManager.ticketGrant(TGC);
//                // 重定向URL 回 APP端
//                respInfo.put("success", true);
//                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
//                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
//                respInfo.put("redirect", redirect);
//            }
//        } catch (CASException ex) {
//            respInfo.put("code", ex.getCode());
//            respInfo.put("success", false);
//            respInfo.put("message", ex.getMessage());
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
//        return respInfo;
//    }

//    @RequestMapping("login_code_fg_submit")
//    public String login_code_fg_submit(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        String sysid = getSysid(request);
//        try {
//            String username = request.getParameter("username");
//            String validatecode = request.getParameter("validatecode");
//            String newPasswd = request.getParameter("newPasswd");
//            String loginType = this.getLoginType(request);
////            String agree = request.getParameter("agree");
//            // 验证登录
//            if (rmsManager.userLoginValidateCodeFgValidation(username, validatecode, newPasswd, request, sysid, loginType)) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                WebUtility.setCookies(response, new HashMap() {{
//                    put(Constants.CAS_UID_KEY, user.getId());
//                    put(Constants.CAS_UNAME_KEY, authorization.getUsername());
//                    put(Constants.CAS_TGC_KEY, TGC);
//                    put(Constants.CAS_AUTOCODE_KEY, user.getPasswd());
//                    put("timeout", Constants.CAS_TIMEOUT_KEY);
//                    put("domain", Configuration.getProperty("sso.domain"));
//                }});
//                // 登录成功 生成票据
//                String ticket = rmsManager.ticketGrant(TGC);
//                // 重定向URL 回 APP端
//                request.setAttribute("success", true);
//                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
//                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
//                request.setAttribute("redirect", redirect);
//            }
//        } catch (CASException ex) {
//            request.setAttribute("code", ex.getCode());
//            request.setAttribute("success", false);
//            request.setAttribute("message", ex.getMessage());
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
//        return getLoginCodeFgSubmitPage(sysid);
//    }

//    @RequestMapping("login_code_fg_submit_json")
//    @ResponseBody
//    public Map<String, Object> login_code_fg_submit_json(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        Map<String, Object> respInfo = new HashMap<String, Object>();
//        try {
//            String username = request.getParameter("username");
//            String validatecode = request.getParameter("validatecode");
//            String newPasswd = request.getParameter("newPasswd");
//            String sysid = getSysid(request);
//            String loginType = this.getLoginType(request);
//
////            String agree = request.getParameter("agree");
//            // 验证登录
//            if (rmsManager.userLoginValidateCodeFgValidation(username, validatecode, newPasswd, request, sysid, loginType)) {
//                final UcUser user = rmsManager.getByUserName(username);
//                // 登录成功 生成 会话 TGC ,存入 CACHE ，并且写入 客户端 COOKIE
//                final String TGC = rmsManager.tgcGrant(user.getId());
//                final Authorization authorization = rmsManager.getUserByTgc(TGC);
//                WebUtility.setCookies(response, new HashMap() {{
//                    put(Constants.CAS_UID_KEY, user.getId());
//                    put(Constants.CAS_UNAME_KEY, authorization.getUsername());
//                    put(Constants.CAS_TGC_KEY, TGC);
//                    put(Constants.CAS_AUTOCODE_KEY, user.getPasswd());
//                    put("timeout", Constants.CAS_TIMEOUT_KEY);
//                    put("domain", Configuration.getProperty("sso.domain"));
//                }});
//                // 登录成功 生成票据
//                String ticket = rmsManager.ticketGrant(TGC);
//                // 重定向URL 回 APP端
//                respInfo.put("success", true);
//                String redirect = CommonUtils.generatorRedirectURL(request, ticket);
//                redirect = URLDecoder.decode(URLDecoder.decode(redirect, "UTF-8"), "UTF-8");
//                respInfo.put("redirect", redirect);
//            }
//        } catch (CASException ex) {
//            respInfo.put("code", ex.getCode());
//            respInfo.put("success", false);
//            respInfo.put("message", ex.getMessage());
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        }
//        return respInfo;
//    }



    /**
     * 图形验证码验证
     */
    private boolean validateShapeCode(HttpServletRequest request) {
        String sysid = getSysid(request);
        if(StringUtils.isBlank(sysid)) return false; //如果系统编号为空，验证不通过
        if("1001".equals(sysid) || "1009".equals(sysid) || "1012".equals(sysid)|| "core_1001".equals(sysid)) return true;
        HttpSession session = request.getSession();
        String randCheckCode = (String) session.getAttribute("randCheckCode");
        if(randCheckCode == null) return false;
        String checkCode = request.getParameter("checkCode");
        if(StringUtils.isBlank(checkCode)) return false;
        String md5CheckCode = Md5Utility.getMD5String(checkCode.toLowerCase());
        if(randCheckCode.equalsIgnoreCase(md5CheckCode)) {
            session.removeAttribute("randCheckCode");
            return true;
        }
        return false;
    }

    @RequestMapping("getValidateCode")
    @ResponseBody
    public Object getValidateCode(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            String mobile = request.getParameter("mobile");
            String sysid = getSysid(request);
            String ip = WebUtility.getIpAddress(request);
            if(!validateShapeCode(request)) {
                rMap.put("fail", true);
                rMap.put("msg", "图形验证码错误，请重新输入！");
                return rMap;
            }
            if ("1009".equals(sysid)) sysid = "1001";
            String key = Constants.MEMCACHE_MSG_VALIDATE_CODE_KEY + sysid + mobile;
//            Boolean flag = memcachedUtils.loadObject(key);
            Boolean flag = (Boolean)redisTemplate.opsForValue().get(key);
            
            redisTemplate.opsForValue().get(key);
            if (flag != null && flag.booleanValue()) {
                rMap.put("fail", true);
                rMap.put("msg", "短信发送过于频繁，请稍后再试");
                return rMap;
            }
            if (rmsManager.getLoginValidateCode(mobile, sysid, ip)) {
                rMap.put("success", true);
                rMap.put("msg", "短信发送成功");
//                memcachedUtils.cacheObject(key, true, 60);//保存60秒短信发送状态
                redisTemplate.opsForValue().set(key, true, 60, TimeUnit.SECONDS);
            } else {
                rMap.put("fail", true);
                rMap.put("msg", "短信发送异常");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            rMap.put("fail", true);
            rMap.put("msg", "系统繁忙，请重试");
        }
        return rMap;
    }

    @RequestMapping("getFpValidateCode")
    @ResponseBody
    public Object getFpValidateCode(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            String mobile = request.getParameter("mobile");
            String sysid = getSysid(request);
            String ip = WebUtility.getIpAddress(request);
            String key = Constants.MEMCACHE_MSG_VALIDATE_CODE_KEY + sysid + mobile;
//            Boolean flag = memcachedUtils.loadObject(key);
            Boolean flag = (Boolean)redisTemplate.opsForValue().get(key);
            
            if(!validateShapeCode(request)) {
                rMap.put("fail", true);
                rMap.put("msg", "图形验证码错误，请重新输入！");
                return rMap;
            }
            if (flag != null && flag.booleanValue()) {
                rMap.put("fail", true);
                rMap.put("msg", "短信发送过于频繁，请稍后再试");
                return rMap;
            }
            if (rmsManager.getFpLoginValidateCode(mobile, sysid, ip)) {
                rMap.put("success", true);
                rMap.put("msg", "短信发送成功");
//                memcachedUtils.cacheObject(key, true, 60);//保存60秒短信发送状态
                redisTemplate.opsForValue().set(key, true, 60, TimeUnit.SECONDS);
            } else {
                rMap.put("fail", true);
                rMap.put("msg", "短信发送失败");
            }
        } catch (Exception ex) {
            rMap.put("fail", true);
            rMap.put("msg", "短信发送异常");
        }
        return rMap;
    }


    // 获取登录页模板,不同系统的登录页适配不一样
    private String getLoginSubmitPage(String syscode) {
        return "user/" + syscode + "/login_submit";
    }

    // 获取登录页模板,不同系统的登录页适配不一样
    private String getLoginCodeSubmitPage(String syscode) {
        return "user/" + syscode + "/login_code_submit";
    }

    // 获取登录页模板,不同系统的登录页适配不一样
    private String getLoginCodeFgSubmitPage(String syscode) {
        return "user/" + syscode + "/login_code_fg_submit";
    }

    // 获取登录页模板,不同系统的登录页适配不一样
    private String getLoginPage(String syscode) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(syscode) && !syscode.equals("1002") && !syscode.contains("1003")
                && !syscode.contains("1009") && !syscode.contains("1012") && !syscode.contains("core_1001")) {
            syscode = "100";
        }
        return "login/login";
    }

    // 获取登录页模板,不同系统的登录页适配不一样
    private String getForgetPwdPage(String syscode) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(syscode) && !syscode.equals("1002") && !syscode.contains("1003") && !syscode.contains("1012") && !syscode.contains("core_1001")) {
            syscode = "1001";
        }
        return "user/" + syscode + "/forget_pwd";
    }

}
