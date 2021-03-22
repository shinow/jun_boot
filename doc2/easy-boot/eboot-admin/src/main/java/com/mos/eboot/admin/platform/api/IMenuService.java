package com.mos.eboot.admin.platform.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.SysMenu;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.vo.MenuNode;
import com.mos.eboot.vo.TreeNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 小尘哥
 */
@FeignClient("boot-service")
public interface IMenuService {

    /**
     * 根据id获取菜单
     *
     * @param id 菜单id
     * @return 菜单对象
     */
    @RequestMapping(value = "api/menu/get-by-id",method = RequestMethod.GET)
    ResultModel<SysMenu> getById(@RequestParam("id") String id);


    /**
     * 根据id删除菜单
     *
     * @param id 菜单id
     * @return 删除结果
     */
    @RequestMapping(value = "api/menu/del-by-id",method = RequestMethod.POST)
    ResultModel<String> delById(@RequestParam("id") String id);

    /**
     * 根据条件查询所有菜单
     *
     * @param page 分页对象
     * @return
     */
    @RequestMapping(value = "api/menu/query-page",method = {RequestMethod.POST})
    ResultModel<Page<SysMenu>> queryPage(@RequestBody Page<SysMenu> page);

    /**
     * 添加或修改菜单
     *
     * @param sysMenu 菜单对象
     * @return
     */
    @RequestMapping(value = "api/menu/save-or-update",method = RequestMethod.POST)
    ResultModel<String> saveOrUpdate(SysMenu sysMenu);

    /**
     * 查询有效菜单
     * @return
     */
    @RequestMapping(value = "api/menu/query-menus",method = RequestMethod.GET)
    ResultModel<List<MenuNode>> queryMenus();

    /**
     * 查询有效菜单
     * @return
     */
    @RequestMapping(value = "api/menu/query4select",method = RequestMethod.GET)
    ResultModel<List<TreeNode>> query4select();

	/**
	 * 根据角色查询
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "api/menu/query-by-role",method = RequestMethod.GET)
	ResultModel<List<TreeNode>> queryByRole(@RequestParam("roleId")String roleId);

	/**
	 * 根据userId查询菜单
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "api/menu/query-by-user",method = RequestMethod.GET)
	ResultModel<List<MenuNode>> queryByUser(@RequestParam("userId")String userId);
}
