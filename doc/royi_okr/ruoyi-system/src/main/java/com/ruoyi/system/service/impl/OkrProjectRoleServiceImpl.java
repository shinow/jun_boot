package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrProjectRoleMapper;
import com.ruoyi.system.domain.OkrProjectRole;
import com.ruoyi.system.service.IOkrProjectRoleService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目OKR关系Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
@Service
public class OkrProjectRoleServiceImpl implements IOkrProjectRoleService 
{
    @Autowired
    private OkrProjectRoleMapper okrProjectRoleMapper;

    /**
     * 查询项目OKR关系
     * 
     * @param id 项目OKR关系ID
     * @return 项目OKR关系
     */
    @Override
    public OkrProjectRole selectOkrProjectRoleById(Long id)
    {
        return okrProjectRoleMapper.selectOkrProjectRoleById(id);
    }

    /**
     * 查询项目OKR关系列表
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 项目OKR关系
     */
    @Override
    public List<OkrProjectRole> selectOkrProjectRoleList(OkrProjectRole okrProjectRole)
    {
        return okrProjectRoleMapper.selectOkrProjectRoleList(okrProjectRole);
    }

    /**
     * 新增项目OKR关系
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 结果
     */
    @Override
    public int insertOkrProjectRole(OkrProjectRole okrProjectRole)
    {
        return okrProjectRoleMapper.insertOkrProjectRole(okrProjectRole);
    }

    /**
     * 修改项目OKR关系
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 结果
     */
    @Override
    public int updateOkrProjectRole(OkrProjectRole okrProjectRole)
    {
        return okrProjectRoleMapper.updateOkrProjectRole(okrProjectRole);
    }

    /**
     * 删除项目OKR关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectRoleByIds(String ids)
    {
        return okrProjectRoleMapper.deleteOkrProjectRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目OKR关系信息
     * 
     * @param id 项目OKR关系ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectRoleById(Long id)
    {
        return okrProjectRoleMapper.deleteOkrProjectRoleById(id);
    }

    /**
     * 删除当前用户当前OKR的所有项目关联
     * @param params
     * @return
     */
    @Override
    public int deleteOkrProjectRoleByUser(Map<String,Long> params) {
        return okrProjectRoleMapper.deleteOkrProjectRoleByUser(params);
    }
}
