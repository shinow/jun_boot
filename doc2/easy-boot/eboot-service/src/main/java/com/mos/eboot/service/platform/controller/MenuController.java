package com.mos.eboot.service.platform.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.enumeration.MenuType;
import com.mos.eboot.platform.entity.SysMenu;
import com.mos.eboot.service.platform.service.ISysMenuService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.StringUtil;
import com.mos.eboot.vo.MenuNode;
import com.mos.eboot.vo.TreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 小尘哥
 * @Time 2018/3/27 10:47
 * @Description
 */
@RestController
@RequestMapping("api/menu")
public class MenuController extends BaseController {

    @Resource
    private ISysMenuService menuService;

    @ApiOperation(value = "获取菜单详情",notes = "根据id获取菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-id",method = {RequestMethod.GET})
    public ResultModel<SysMenu> getById(@RequestParam("id") String id) {
		SysMenu menu = menuService.selectById(id);
		menu.setParentNode(menuService.selectById(menu.getParentId()));
        return new ResultModel<>(ResultStatus.SUCCESS, menu);
    }

    @ApiOperation(value = "删除菜单",notes = "根据id删除菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "del-by-id",method = {RequestMethod.POST})
    public ResultModel<String> delById(@RequestParam("id") String id) {
        return this.basicResult(menuService.deleteById(id));
    }

    @ApiOperation(value = "新增|修改菜单",notes = "新增|修改菜单")
    @RequestMapping(value = "save-or-update",method = {RequestMethod.POST})
    public ResultModel<String> saveOrUpdate(@ApiParam(name = "菜单对象",required = true) @RequestBody SysMenu menu) {
        Boolean r;
        if (StringUtil.isBlank(menu.getId())) {
            r = menuService.insert(menu);
        } else {
            r = menuService.updateById(menu);
        }
        return this.basicResult(r);
    }

    @ApiOperation(value = "分页查询菜单",notes = "分页查询菜单")
    @RequestMapping(value = "query-page",method = RequestMethod.POST)
    public ResultModel<Page<SysMenu>> queryPage(@ApiParam(name = "分页对象",required = true) @RequestBody Page<SysMenu> page) {
        page.setOrderByField("sort_no").setAsc(true);
		page = menuService.selectPage(page,new EntityWrapper<>());
		page.getRecords().forEach(item->{
			item.setMenuTypeText(MenuType.getName(item.getMenuType()));
			SysMenu parent = menuService.selectById(item.getParentId());
			item.setParentNodeName(parent == null ? SysMenu.ROOT_Text : parent.getName());
		});
        return new ResultModel<>(ResultStatus.SUCCESS, page);
    }

    @ApiOperation(value = "获取全部菜单",notes = "获取全部菜单（包括菜单）")
    @RequestMapping(value = "query-menus",method = RequestMethod.GET)
    public ResultModel<List<MenuNode>> queryMenus() {
        List<SysMenu> menus = getSysMenus();
        List<MenuNode> nodes = menus.stream().map(this::getMenuNode).collect(Collectors.toList());
        return new ResultModel<>(ResultStatus.SUCCESS, nodes);
    }

    @ApiOperation(value = "获取全部菜单",notes = "获取全部菜单并组装成树结构")
    @RequestMapping(value = "query4select",method = RequestMethod.GET)
    public ResultModel<List<TreeNode>> queryTreeData() {
        List<SysMenu> menus = getSysMenus();
        List<TreeNode> nodes = menus.stream().map(this::getTreeNode).collect(Collectors.toList());
        return new ResultModel<>(ResultStatus.SUCCESS, nodes);
    }

    private TreeNode getTreeNode(SysMenu item) {
        TreeNode node = new TreeNode();
        node.setId(item.getId());
        node.setpId(item.getParentId());
        node.setName(item.getName());
        node.setOpen(StringUtil.isBlank(node.getpId()));
        return node;
    }

    private MenuNode getMenuNode(SysMenu item) {
        MenuNode node = new MenuNode();
        node.setId(item.getId());
        node.setpId(item.getParentId());
        node.setName(item.getName());
        node.setUrl(item.getPath());
        return node;
    }

    private List<SysMenu> getSysMenus() {
        EntityWrapper<SysMenu> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("menu_type", SysMenu.MENU_TYPE_RESOURCE);
        entityWrapper.orderBy("sort_no", true);
        entityWrapper.isNotNull("parent_id");
        return menuService.selectList(entityWrapper);
    }

    @ApiOperation(value = "获取全部资源",notes = "获取全部资源（包括菜单和权限）")
	@RequestMapping(value = "query-all",method = RequestMethod.GET)
	public ResultModel<List<TreeNode>> queryAll() {
		EntityWrapper<SysMenu> entityWrapper = new EntityWrapper<>();
		entityWrapper.orderBy("sort_no", true);
		List<SysMenu> menus = menuService.selectList(entityWrapper);
		List<TreeNode> nodes = menus.stream().map(this::getTreeNode).collect(Collectors.toList());
		return new ResultModel<>(ResultStatus.SUCCESS, nodes);
	}

    @ApiOperation(value = "根据角色获取菜单",notes = "根据角色获取菜单")
    @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "query-by-role",method = RequestMethod.GET)
	public ResultModel<List<TreeNode>> queryByRoleId(@RequestParam("roleId")String roleId) {
		return new ResultModel<>(ResultStatus.SUCCESS, menuService.selectByRoleId(roleId));
	}

    @ApiOperation(value = "根据用户id获取菜单",notes = "根据用户id获取菜单")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "query-by-user",method = RequestMethod.GET)
	public ResultModel<List<MenuNode>> queryByUserId(@RequestParam("userId")String userId) {
		List<SysMenu> menus = menuService.selectByUser(userId);
		List<MenuNode> nodes = menus.stream().map(this::getMenuNode).collect(Collectors.toList());
		return new ResultModel<>(ResultStatus.SUCCESS, nodes);
	}
}
