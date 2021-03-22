package com.pearadmin.pro.modules.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.log.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping(ControllerConstant.PREFIX_LOG)
public class LogController extends BaseController {

    @Resource
    private LogService logService;

    @GetMapping("page")
    public Result page(Page page){
       return success(logService.lambdaQuery().page(page));
    }

}
