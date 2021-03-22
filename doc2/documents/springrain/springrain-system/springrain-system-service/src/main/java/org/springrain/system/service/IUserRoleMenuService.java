package org.springrain.system.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.RoleMenu;
import org.springrain.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户角色权限菜单,用于处理用户,角色,菜单之间的权限关系
 */
@RpcServiceAnnotation
public interface IUserRoleMenuService extends IBaseSpringrainService {


    /**
     * 根据用户ID 获取角色,按照privateOrg和sortno倒叙,先处理强制部门权限的角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Role> findRoleByUserId(String userId) throws Exception;


    /**
     * 根据角色Id 获取能够访问的菜单
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Menu> findMenuByRoleId(String roleId) throws Exception;


    /**
     * 根据角色  查询角色下的所有人员.
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<User> findUserByRoleId(String roleId) throws Exception;

    /**
     * 根据userId  查询用户有权限的菜单,按照角色forceOrg和sortno倒叙,查询出所有的权限菜单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Menu> findMenuByUserId(String userId) throws Exception;

    /**
     * 根据userId,查询菜单树
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Menu> findMenuTreeByUsreId(String userId) throws Exception;


    /**
     * 查询所有的菜单树
     *
     * @return
     * @throws Exception
     */
    List<Menu> findAllMenuTree() throws Exception;


    /**
     * 返回 VUE 格式的树形菜单
     *
     * @param listMenu
     * @param listMap
     */
    void wrapVueMenu(List<Menu> listMenu, List<Map<String, Object>> listMap);

    /**
     * ajax更新操作
     *
     * @param roleMenu
     * @return
     * @throws Exception
     */
    String updateRoleMenu(RoleMenu roleMenu) throws Exception;

    /**
     * 更新用户的角色信息
     *
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    String updateUserRoles(String userId, List<String> roleIds) throws Exception;

}
