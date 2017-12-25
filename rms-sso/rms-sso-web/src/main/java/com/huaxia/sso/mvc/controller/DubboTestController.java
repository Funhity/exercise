package com.huaxia.sso.mvc.controller;

import com.huaxia.rms.api.model.OrganizationRo;
import com.huaxia.rms.api.service.ApiOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/dubbo")
public class DubboTestController {

    private Logger logger = LoggerFactory.getLogger(DubboTestController.class);

    @Resource
    private ApiOrganizationService apiOrganizationService;

    @RequestMapping("/orgTest")
    public String orgTest(HttpServletRequest request, HttpServletResponse response) {

        OrganizationRo org = apiOrganizationService.getOrgByCode("100100");

        String orgName = apiOrganizationService.getOrgFullNameByCode("100100");
//
//        List<OrganizationRo> orgs2 = apiOrganizationService.getAllOrgList();
//
//        List<OrganizationRo> orgs = apiOrganizationService.getOrgTree();
//
        logger.info("----------org: " + org);
        logger.info("----------orgName: " + orgName);
//        logger.info("----------orgs: " + orgs);

        return "user/notice";
    }

}