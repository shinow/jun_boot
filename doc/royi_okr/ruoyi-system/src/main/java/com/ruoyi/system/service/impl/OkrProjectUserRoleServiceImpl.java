package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrProjectUserRoleMapper;
import com.ruoyi.system.domain.OkrProjectUserRole;
import com.ruoyi.system.service.IOkrProjectUserRoleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 项目权限Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-30
 */
@Service
public class OkrProjectUserRoleServiceImpl implements IOkrProjectUserRoleService 
{
    @Autowired
    private OkrProjectUserRoleMapper okrProjectUserRoleMapper;

    /**
     * 查询项目权限
     * 
     * @param userId 项目权限ID
     * @return 项目权限
     */
    @Override
    public OkrProjectUserRole selectOkrProjectUserRoleById(Long userId)
    {
        return okrProjectUserRoleMapper.selectOkrProjectUserRoleById(userId);
    }

    /**
     * 查询项目权限列表
     * 
     * @param okrProjectUserRole 项目权限
     * @return 项目权限
     */
    @Override
    public List<OkrProjectUserRole> selectOkrProjectUserRoleList(OkrProjectUserRole okrProjectUserRole)
    {
        return okrProjectUserRoleMapper.selectOkrProjectUserRoleList(okrProjectUserRole);
    }

    /**
     * 新增项目权限
     * 
     * @param okrProjectUserRole 项目权限
     * @return 结果
     */
    @Override
    public int insertOkrProjectUserRole(OkrProjectUserRole okrProjectUserRole)
    {
        return okrProjectUserRoleMapper.insertOkrProjectUserRole(okrProjectUserRole);
    }

    @Override
    public int insertOkrProjectUserRoleList(List<OkrProjectUserRole> okrProjectUserRoles) {
        return okrProjectUserRoleMapper.insertOkrProjectUserRoleList(okrProjectUserRoles);
    }

    /**
     * 修改项目权限
     * 
     * @param okrProjectUserRole 项目权限
     * @return 结果
     */
    @Override
    public int updateOkrProjectUserRole(OkrProjectUserRole okrProjectUserRole)
    {
        return okrProjectUserRoleMapper.updateOkrProjectUserRole(okrProjectUserRole);
    }

    /**
     * 删除项目权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectUserRoleByIds(String ids)
    {
        return okrProjectUserRoleMapper.deleteOkrProjectUserRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目权限信息
     * 
     * @param projectId 项目权限ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectUserRoleById(Long projectId)
    {
        return okrProjectUserRoleMapper.deleteOkrProjectUserRoleById(projectId);
    }
}
