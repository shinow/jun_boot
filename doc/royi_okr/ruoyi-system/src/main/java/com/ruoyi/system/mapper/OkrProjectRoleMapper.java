package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.OkrProjectRole;

/**
 * 项目OKR关系Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
public interface OkrProjectRoleMapper 
{
    /**
     * 查询项目OKR关系
     * 
     * @param id 项目OKR关系ID
     * @return 项目OKR关系
     */
    public OkrProjectRole selectOkrProjectRoleById(Long id);

    /**
     * 查询项目OKR关系列表
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 项目OKR关系集合
     */
    public List<OkrProjectRole> selectOkrProjectRoleList(OkrProjectRole okrProjectRole);

    /**
     * 新增项目OKR关系
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 结果
     */
    public int insertOkrProjectRole(OkrProjectRole okrProjectRole);

    /**
     * 修改项目OKR关系
     * 
     * @param okrProjectRole 项目OKR关系
     * @return 结果
     */
    public int updateOkrProjectRole(OkrProjectRole okrProjectRole);

    /**
     * 删除项目OKR关系
     * 
     * @param id 项目OKR关系ID
     * @return 结果
     */
    public int deleteOkrProjectRoleById(Long id);

    /**
     * 批量删除项目OKR关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectRoleByIds(String[] ids);

    /**
     * 删除当前用户当前OKR的所有项目关联
     * @param params
     * @return
     */
    public int deleteOkrProjectRoleByUser(Map<String,Long> params);
}
