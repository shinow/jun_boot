package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 角 色 控 制 器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 角色列表信息
     *
     * @return {@link SysRole}
     * */
    @GetMapping("list")
    public Result list(){
        List<SysRole> data = sysRoleService.lambdaQuery().list();
        return success(data);
    }

    /**
     * 角色分页列表
     *
     * @param page
     * @return {@link SysRole}
     * */
    @GetMapping("page")
    public Result page(Page page){
        IPage<SysRole> pageInfo = sysRoleService.lambdaQuery().page(page);
        return success(pageInfo);
    }
}
