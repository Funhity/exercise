package com.huaxia.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @RequestMapping("index.jhtml")
    public ModelAndView index() {
        String jspPage = "admin/index/index";
        ModelAndView view = new ModelAndView(jspPage);
        view.getModelMap().addAttribute("test", jspPage);
        return view;
    }
}
