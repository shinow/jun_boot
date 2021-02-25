package com.usthe.bootshiro.service;

import com.usthe.bootshiro.domain.bo.AuthRole;

import java.util.List;

/* *
 * @author Wujun
 * @Description 
 * @Date 9:10 2018/3/20
 */
public interface RoleService {


    boolean authorityRoleResource(int roleId, int resourceId);

    boolean addRole(AuthRole role);

    boolean updateRole(AuthRole role);

    boolean deleteRoleByRoleId(Integer roleId);

    boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId);

    List<AuthRole> getRoleList();
}
