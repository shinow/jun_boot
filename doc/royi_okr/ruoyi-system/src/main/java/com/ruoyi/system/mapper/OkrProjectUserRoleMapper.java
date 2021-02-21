package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrProjectUserRole;

/**
 * 项目权限Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-30
 */
public interface OkrProjectUserRoleMapper 
{
    /**
     * 查询项目权限
     * 
     * @param userId 项目权限ID
     * @return 项目权限
     */
    public OkrProjectUserRole selectOkrProjectUserRoleById(Long userId);

    /**
     * 查询项目权限列表
     * 
     * @param okrProjectUserRole 项目权限
     * @return 项目权限集合
     */
    public List<OkrProjectUserRole> selectOkrProjectUserRoleList(OkrProjectUserRole okrProjectUserRole);

    /**
     * 新增项目权限
     * 
     * @param okrProjectUserRole 项目权限
     * @return 结果
     */
    public int insertOkrProjectUserRole(OkrProjectUserRole okrProjectUserRole);


    /**
     * 批量新增项目权限
     *
     * @param okrProjectUserRoles 项目权限
     * @return 结果
     */
    public int insertOkrProjectUserRoleList(List<OkrProjectUserRole> okrProjectUserRoles);

    /**
     * 修改项目权限
     * 
     * @param okrProjectUserRole 项目权限
     * @return 结果
     */
    public int updateOkrProjectUserRole(OkrProjectUserRole okrProjectUserRole);

    /**
     * 删除项目权限
     * 
     * @param projectId 项目权限ID
     * @return 结果
     */
    public int deleteOkrProjectUserRoleById(Long projectId);

    /**
     * 批量删除项目权限
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectUserRoleByIds(String[] userIds);
}
