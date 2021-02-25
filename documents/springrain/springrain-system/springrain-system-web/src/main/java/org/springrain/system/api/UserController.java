package org.springrain.system.api;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.system.api.dto.MenuDto;
import org.springrain.system.api.dto.MetaDto;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.*;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserRoleOrgService;
import org.springrain.system.service.IUserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/system/user", method = RequestMethod.POST)
public class UserController extends BaseController {

    @Resource
    private IUserRoleMenuService userRoleMenuService;

    @Resource
    private IUserRoleOrgService userRoleOrgService;

    @Resource
    private IUserService userService;

    /**
     * 后台用户列表
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<User>> list(@RequestBody Page<User> page) {
        ReturnDatas<List<User>> returnObject = ReturnDatas.getSuccessReturnDatas();

        List<User> datas = null;
		try {
			datas = userService.findUserList(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}

        returnObject.setResult(datas);
        returnObject.setPage(page);
        return returnObject;
    }

    /**
     * 查看的Json格式数据
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<User> look(java.lang.String id) throws Exception {
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            User user = userService.findUserById(id);
            returnObject.setResult(user);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<User> save(@RequestBody User user) {
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            java.lang.String id = user.getId();
            if (StringUtils.isBlank(id)) {
                user.setId(null);
            }
            userService.save(user);

            returnObject.setResult(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 修改 操作,返回json格式数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<User> update(@RequestBody User user) {
        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            java.lang.String id = user.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            userService.update(user, false);

            returnObject.setResult(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;
    }

    /**
     * 更新用户角色关系
     *
     * @param map userId,roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateuserrole", method = RequestMethod.POST)
    public ReturnDatas<String> updateuserrole(@RequestBody Map<String, Object> map)
            throws Exception {
        String userId = (String) map.get("userId");
        @SuppressWarnings("unchecked")
        List<String> roleIds = (List<String>) map.get("roleIds");
        String str = userRoleMenuService.updateUserRoles(userId, roleIds);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 用户获取部门
     *
     * @param map userId,userOrgs
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findOrgByUserId", method = RequestMethod.POST)
    public ReturnDatas<List<UserOrg>> findOrgByUserId(@RequestBody Map<String, Object> map)
            throws Exception {
        String userId = (String) map.get("id");
        List<UserOrg> orgs = userRoleOrgService.findUserOrgByUserId(userId, null);

        ReturnDatas<List<UserOrg>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(orgs);
        return returnDatas;
    }

    /**
     * 用户根据角色获取部门
     *
     * @param map userId,userOrgs
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findOrgByRoleId", method = RequestMethod.POST)
    public ReturnDatas<List<RoleOrg>> findOrgByRoleId(@RequestBody Map<String, Object> map)
            throws Exception {
        String roleId = (String) map.get("roleid");
        List<RoleOrg> orgs = userRoleOrgService.findOrgByRoleId(roleId, null);

        ReturnDatas<List<RoleOrg>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        returnDatas.setResult(orgs);
        return returnDatas;
    }

    /**
     * 更新用户部门关系
     *
     * @param userOrg
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateuserorg", method = RequestMethod.POST)
    public ReturnDatas<String> updateuserorg(@RequestBody UserOrg userOrg) throws Exception {
        String str = userRoleOrgService.updateUserOrg(userOrg);
        if (StringUtils.isBlank(str)) {
            return ReturnDatas.getSuccessReturnDatas();
        } else {
            return ReturnDatas.getErrorReturnDatas(str);
        }
    }

    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<User> delete(java.lang.String id) throws Exception {
        try {
            if (StringUtils.isNotBlank(id)) {
                userService.deleteById(id, User.class);
                return new ReturnDatas<User>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<User>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas<User>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    /**
     * 删除多条记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/more", method = RequestMethod.POST)
    public ReturnDatas<Object> deleteMore(@RequestBody java.lang.String[] ids) {

        if (ids == null || ids.length < 1) {
            return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
        }
        try {
            List<String> listIds = Arrays.asList(ids);
            userService.deleteByIds(listIds, User.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas<Object>(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_ERROR);
        }
        return new ReturnDatas<Object>(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

    }

    /**
     * 获取用户的 权限菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRouters", method = RequestMethod.POST)
    public ReturnDatas<List<String>> menuIds() throws Exception {
        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas<List<String>> successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Menu> listMenu = userRoleMenuService.findMenuByUserId(userId);

        List<String> listMenuIds = new ArrayList<>();
        listMenuIds.add("default");
        successReturnDatas.setResult(listMenuIds);

        if (CollectionUtils.isEmpty(listMenu)) {
            return successReturnDatas;
        }

        for (Menu menu : listMenu) {
            listMenuIds.add(menu.getId());
        }

        // List<Menu> listMenu =
        // userRoleMenuService.findMenuTreeByUsreId(userId);

        // List<Map<String,Object>> listMap=new ArrayList<>();
        // 包装成Vue使用的树形结构
        // userRoleMenuService.wrapVueMenu(listMenu,listMap);

        // successReturnDatas.setResult(listMap);

        return successReturnDatas;
    }

    /**
     * 获取用户的角色
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRolesByUserId", method = RequestMethod.POST)
    public ReturnDatas<List<Role>> getRolesByUserId() throws Exception {
        // 获取当前登录人
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }
        ReturnDatas<List<Role>> successReturnDatas = ReturnDatas.getSuccessReturnDatas();
        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);

        successReturnDatas.setResult(roles);

        return successReturnDatas;
    }

    /**
     * 获取用户的 信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ReturnDatas<User> info() throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }

        User user = userService.findUserById(userId);
        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);
        if (user != null) {
            user.setRoles(roles);
        }

        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setResult(user);
        return returnObject;
    }

    /**
     * 获取用户的信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public ReturnDatas<User> getInfo() throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }

        User user = userService.findUserById(userId);
        List<Role> roles = userRoleMenuService.findRoleByUserId(userId);
        if (user != null) {
            user.setRoles(roles);
        }

        ReturnDatas<User> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setResult(user);
        return returnObject;
    }

    /**
     * 获取用户的 路由权限菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ReturnDatas<List<MenuDto>> getRouters() throws Exception {
        String userId = SessionUser.getUserId();
        if (StringUtils.isBlank(userId)) {
            return ReturnDatas.getErrorReturnDatas("用户不存在");
        }

        List<MenuDto> menuDto = new ArrayList<MenuDto>();
        MenuDto menu = new MenuDto();

        MetaDto meta = new MetaDto();
        meta.setTitle("系统管理");
        meta.setIcon("system");
        meta.setNoCache(false);
        menu.setMeta(meta);

        menu.setName("System");
        menu.setPath("/system");
        menu.setComponent("Layout");
        menu.setAlwaysShow(true);
        menu.setHidden(false);
        menu.setRedirect("noRedirect");

        List<MenuDto> children = new ArrayList<MenuDto>();

        MenuDto menu1 = new MenuDto();
        MetaDto meta1 = new MetaDto();
        meta1.setTitle("用户管理");
        meta1.setIcon("user");
        meta1.setNoCache(false);
        menu1.setMeta(meta1);
        menu1.setName("User");
        menu1.setPath("user");
        menu1.setHidden(false);
        menu1.setComponent("system/user/index");
        children.add(menu1);

        MetaDto meta2 = new MetaDto();
        meta2.setTitle("角色管理");
        meta2.setIcon("peoples");
        meta2.setNoCache(false);
        MenuDto menu2 = new MenuDto();
        menu2.setName("Role");
        menu2.setPath("role");
        menu2.setHidden(false);
        menu2.setComponent("system/role/index");
        menu2.setMeta(meta2);
        children.add(menu2);

        MetaDto meta3 = new MetaDto();
        meta3.setTitle("菜单管理");
        meta3.setIcon("tree-table");
        meta3.setNoCache(false);
        MenuDto menu3 = new MenuDto();
        menu3.setName("Menu");
        menu3.setPath("menu");
        menu3.setHidden(false);
        menu3.setComponent("system/menu/index");
        menu3.setMeta(meta3);
        children.add(menu3);

        MenuDto menu4 = new MenuDto();
        MetaDto meta4 = new MetaDto();
        meta4.setTitle("部门管理");
        meta4.setIcon("tree");
        meta4.setNoCache(false);
        menu4.setName("Dept");
        menu4.setPath("dept");
        menu4.setHidden(false);
        menu4.setComponent("system/dept/index");
        menu4.setMeta(meta4);
        children.add(menu4);
        menu.setChildren(children);
        menuDto.add(menu);

        ReturnDatas<List<MenuDto>> retrunObject = ReturnDatas.getSuccessReturnDatas();
        retrunObject.setResult(menuDto);
        return retrunObject;
    }
}
