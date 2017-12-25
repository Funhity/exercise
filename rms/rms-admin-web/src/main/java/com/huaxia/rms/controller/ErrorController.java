package com.huaxia.rms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/black.jhtml")
    private String black(HttpServletRequest request) {
        logger.info("-------------黑名单页面-----------");

        return "error/black";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/error.jhtml")
    private String error(HttpServletRequest request) {
        logger.info("-------------错误页面-----------");
        return "error/sorry";
    }
}
