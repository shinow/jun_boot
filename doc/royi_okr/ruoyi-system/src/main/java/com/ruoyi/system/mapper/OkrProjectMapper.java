package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrProject;

/**
 * OKR项目管理Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
public interface OkrProjectMapper 
{
    /**
     * 查询OKR项目管理
     * 
     * @param id OKR项目管理ID
     * @return OKR项目管理
     */
    public OkrProject selectOkrProjectById(Long id);

    /**
     * 查询OKR项目管理
     *
     * @param ids OKR项目管理ID
     * @return OKR项目管理
     */
    public List<OkrProject> selectOkrProjectByIds(String[] ids);

    /**
     * 查询OKR项目管理列表
     * 
     * @param okrProject OKR项目管理
     * @return OKR项目管理集合
     */
    public List<OkrProject> selectOkrProjectList(OkrProject okrProject);

    /**
     * 新增OKR项目管理
     * 
     * @param okrProject OKR项目管理
     * @return 结果
     */
    public int insertOkrProject(OkrProject okrProject);

    /**
     * 修改OKR项目管理
     * 
     * @param okrProject OKR项目管理
     * @return 结果
     */
    public int updateOkrProject(OkrProject okrProject);

    /**
     * 删除OKR项目管理
     * 
     * @param id OKR项目管理ID
     * @return 结果
     */
    public int deleteOkrProjectById(Long id);

    /**
     * 批量删除OKR项目管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectByIds(String[] ids);
}
