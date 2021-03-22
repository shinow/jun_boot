package org.springrain.system.service;

import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.RoleOrg;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;

import java.util.List;

/**
 * 用户部门权限 服务
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 15:28:18
 */
@RpcServiceAnnotation
public interface IUserRoleOrgService extends IBaseSpringrainService {


    /**
     * 根据部门Id 查找部门下人员的userId.不包括会员
     *
     * @param orgId
     * @param page
     * @return
     * @throws Exception
     */
    List<String> findUserIdByOrgId(String orgId, Page page) throws Exception;

    /**
     * 根据部门Id 查找部门下的User对象.不包括会员
     *
     * @param orgId
     * @param page
     * @return
     * @throws Exception
     */
    List<User> findUserByOrgId(String orgId, Page page) throws Exception;


    /**
     * 根据部门ID,查找部门下(包括所有子部门)的人员的userId.不包括会员
     *
     * @param orgId
     * @param page
     * @return
     * @throws Exception
     */
    List<String> findAllUserIdByOrgId(String orgId, Page page) throws Exception;

    /**
     * 根据部门ID,查找部门下(包括所有子部门)的User对象.不包括会员
     *
     * @param orgId
     * @param page
     * @return
     * @throws Exception
     */
    List<User> findAllUserByOrgId(String orgId, Page page) throws Exception;


    /**
     * 根据用户ID 查找所在的部门的Id
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<String> findOrgIdByUserId(String userId, Page page) throws Exception;

    /**
     * 根据用户ID 查找所在的部门.
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<Org> findOrgByUserId(String userId, Page page) throws Exception;


    /**
     * 根据用户ID 查找所在的UserOrg
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<UserOrg> findUserOrgByUserId(String userId, Page page) throws Exception;

    /**
     * 根据用户ID 查找管理的部门Id,不包括角色扩展的部门
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<String> findManagerOrgIdByUserId(String userId, Page page) throws Exception;

    /**
     * 根据用户ID 查找管理的部门,不包括角色扩展的部门
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<Org> findManagerOrgByUserId(String userId, Page page) throws Exception;

    /**
     * 根据部门ID 查找主管Id.一个部门只有一个主管,其他管理人员可以通过角色进行扩展分配  --有缓存
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    String findManagerUserIdByOrgId(String orgId) throws Exception;

    /**
     * 根据部门ID 查找主管.一个部门只有一个主管,其他管理人员可以通过角色进行扩展分配.调用  findManagerUserIdByOrgId 方法
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    User findManagerUserByOrgId(String orgId) throws Exception;


    /**
     * 查询用户有权限管理的所有部门,包括角色关联分配产生的部门权限.分装成Finder的形式用于关联查询的finder实体类
     * 1.获取用户所有的  List<UserRole> list,包含主管的部门和角色分配的部门
     * 2.wrapOrgIdFinderByUserRole(list) 生成完整的Finder对象.
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Finder wrapOrgIdFinderByUserId(String userId) throws Exception;

    /**
     * 查询私有部门角色的部门范围,构造成Finder,用于权限范围限制.
     *
     * @param roleId
     * @param userId
     * @return
     * @throws Exception
     */
    Finder wrapOrgIdFinderByPrivateOrgRoleId(String roleId, String userId) throws Exception;

    /**
     * 查询用户有权限管理的所有部门,包括角色关联分配产生的部门权限.分装成Finder的形式用于关联查询的finder实体类
     * 这个是基础的Finder 封装方法,其他的方式也在转化为List<UserRole> list,调用此方法
     * 1.基于 List<UserRole> list 拼装WHERE条件
     * 2.完善 前面的查询语句,构造完整的Finder 查询语句
     *
     * @param list
     * @return
     * @throws Exception
     */
    Finder wrapOrgIdFinderByUserRole(List<RoleOrg> list) throws Exception;

    /**
     * 查询用户根据角色派生和自身主管的所有部门. 不处理私有部门类型的角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<RoleOrg> findManagerOrgAndRoleOrgByUserId(String userId) throws Exception;


    /**
     * 根据部门ID,查找部门下(包括所有子部门)的人员数量.不包括会员
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    Integer findAllUserCountByOrgId(String orgId) throws Exception;


    /**
     * 根据roleId,查询role下管理的部门,用于角色自定的部门范围,查询 t_role_org 中间表
     *
     * @param roleId
     * @param page
     * @return
     * @throws Exception
     */
    List<RoleOrg> findOrgByRoleId(String roleId, Page page) throws Exception;


    /**
     * 更新用户和部门的关系
     *
     * @param userOrg
     * @return
     * @throws Exception
     */
    String updateUserOrg(UserOrg userOrg) throws Exception;


    /**
     * 更新角色和部门的关系
     *
     * @param roleOrg
     * @return
     * @throws Exception
     */
    String updateRoleOrg(RoleOrg roleOrg) throws Exception;


    /**
     * 用户是否在指定的组织下,校验组织结构关系
     *
     * @param userId
     * @param orgId
     * @return
     * @throws Exception
     */
    boolean isUserInOrg(String userId, String orgId) throws Exception;

	/**
	 * 根据部门id查询该部门以及子级部门所包含的用户id
	 * @param deptId
	 * @return
	 * @throws Exception 
	 */
	List<String> findUserIdListByOrgId(String deptId) throws Exception;

}
