package com.mos.eboot.admin.platform.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.admin.platform.api.IMenuService;
import com.mos.eboot.admin.platform.api.IRoleService;
import com.mos.eboot.platform.entity.SysRole;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.LayPage;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.shiro.utils.PrincipalUtils;
import com.mos.eboot.tools.util.Constants;
import com.mos.eboot.tools.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 小尘哥
 */
@Controller
@RequestMapping("sys/role")
public class SysRoleController extends BaseController{

    @Resource
    private IRoleService roleService;

    @Resource
	private IMenuService menuService;

    @RequestMapping(value = "to-page",method = RequestMethod.GET)
    public String toPage() {
        return "platform/role/list";
    }

    @ResponseBody
    @RequestMapping(value = "query-all",method = RequestMethod.POST)
    public LayPage<SysRole> queryAll(Page<SysRole> page) {
        SysRole role = new SysRole();
        if (page.getCondition() != null){
            role.setName(page.getCondition().get("name") == null ? null : page.getCondition().get("name").toString());
        }
        return this.getLayPage4List(roleService.queryList(role));
    }

    @RequestMapping(value = "to-edit",method = RequestMethod.GET)
    @RequiresPermissions("sys:role:basic:op")
    public String toEdit(@RequestParam(name = "id",required = false)String id, Model model) {
        SysRole role = roleService.getById(id).getData();
        if (role == null){
            role = new SysRole();
        }
        model.addAttribute("role",role);
        return "platform/role/edit";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam(name = "id")String id, Model model) {
        SysRole role = roleService.getById(id).getData();
        model.addAttribute("role",role);
        return "platform/role/detail";
    }

    @ResponseBody
    @RequestMapping(value = "save-or-update",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:basic:op")
    public ResultModel<String> saveOrUpdate(SysRole role){
        SysUser user = (SysUser) PrincipalUtils.getCurrentUser();
        if (StringUtil.isBlank(role.getId())){
            role.setIsDel(Constants.NEGATIVE);
            role.setCreateTime(new Date());
            role.setCreateUser(user.getId());
        }
        return roleService.saveOrUpdate(role);
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:basic:del")
    public ResultModel<String> del(@RequestParam(name = "id")String id){
        return roleService.delById(id);
    }

	@RequestMapping(value = "to-bind-resource",method = RequestMethod.GET)
    @RequiresPermissions("sys:role:basic:br")
	public String toBindResource(Model model,String id){
		model.addAttribute("menus",menuService.queryByRole(id).getData());
		model.addAttribute("id",id);
		return "platform/role/bind-resource";
	}

	@ResponseBody
	@RequestMapping(value = "bind-resource",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:basic:br")
	public ResultModel<String> bindResource(@RequestParam(name = "id")String id,@RequestParam("resourceIds[]") String[] ids){
		return roleService.bindResource(id,ids);
	}
}
