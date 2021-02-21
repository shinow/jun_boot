package com.pearframe.modules.system.controller;

import com.pearframe.framework.web.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("sysPower")
public class SysPowerController extends BaseController {

    private String prefix = "system/power/";

    @GetMapping("view")
    public ModelAndView view(ModelAndView modelAndView){

        modelAndView.setViewName(prefix+"view");

        return modelAndView;
    }
}
