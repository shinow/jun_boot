package com.mos.eboot.admin.platform.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.admin.platform.api.IMenuService;
import com.mos.eboot.platform.entity.SysMenu;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.LayPage;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.shiro.utils.PrincipalUtils;
import com.mos.eboot.tools.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author 小尘哥
 * @Time 2018/3/27 11:26
 * @Description
 */
@Controller
@RequestMapping("sys/menu/")
public class SysMenuController extends BaseController {

    @Resource
    private IMenuService menuService;

    @GetMapping("to-page")
    public String toPage() {
        return "platform/menu/list";
    }

    @ResponseBody
    @PostMapping("query-page")
    public LayPage<SysMenu> queryAll(Page<SysMenu> page) {
        return this.getLayPage(menuService.queryPage(page));
    }


    @GetMapping("to-edit")
    @RequiresPermissions("sys:menu:basic:op")
    public String toEdit(@RequestParam(name = "id",required = false)String id, Model model) {
        SysMenu sysMenu = menuService.getById(id).getData();
        if (sysMenu == null){
            sysMenu = new SysMenu();
            sysMenu.setParentNode(new SysMenu());
        }
        model.addAttribute("sysMenu",sysMenu);
        return "platform/menu/edit";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "id")String id, Model model) {
        SysMenu sysMenu = menuService.getById(id).getData();
        model.addAttribute("sysMenu",sysMenu);
        return "platform/menu/detail";
    }

    @ResponseBody
    @PostMapping("save-or-update")
    @RequiresPermissions("sys:menu:basic:op")
    public ResultModel<String> saveOrUpdate(SysMenu menu){
        SysUser user = (SysUser) PrincipalUtils.getCurrentUser();
        if (StringUtil.isBlank(menu.getId())){
            menu.setCreateTime(new Date());
            menu.setCreateUser(user.getId());
        }else {
            menu.setModifyTime(new Date());
            menu.setModifyUser(user.getId());
        }
        return menuService.saveOrUpdate(menu);
    }

    @ResponseBody
    @PostMapping("del")
    @RequiresPermissions("sys:menu:basic:del")
    public ResultModel<String> del(@RequestParam(name = "id")String id){
        return menuService.delById(id);
    }

    @GetMapping("tree")
    public String query4select(Model model){
        model.addAttribute("menus", menuService.query4select().getData());
        return "platform/menu/tree";
    }
}
