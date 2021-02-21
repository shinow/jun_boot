package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrProjectMapper;
import com.ruoyi.system.domain.OkrProject;
import com.ruoyi.system.service.IOkrProjectService;
import com.ruoyi.common.core.text.Convert;

/**
 * OKR项目管理Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Service
public class OkrProjectServiceImpl implements IOkrProjectService 
{
    @Autowired
    private OkrProjectMapper okrProjectMapper;

    /**
     * 查询OKR项目管理
     * 
     * @param id OKR项目管理ID
     * @return OKR项目管理
     */
    @Override
    public OkrProject selectOkrProjectById(Long id)
    {
        return okrProjectMapper.selectOkrProjectById(id);
    }

    @Override
    public List<OkrProject> selectOkrProjectByIds(String[] ids) {
        return okrProjectMapper.selectOkrProjectByIds(ids);
    }

    /**
     * 查询OKR项目管理列表
     * 
     * @param okrProject OKR项目管理
     * @return OKR项目管理
     */
    @Override
    public List<OkrProject> selectOkrProjectList(OkrProject okrProject)
    {
        return okrProjectMapper.selectOkrProjectList(okrProject);
    }

    /**
     * 新增OKR项目管理
     * 
     * @param okrProject OKR项目管理
     * @return 结果
     */
    @Override
    public int insertOkrProject(OkrProject okrProject)
    {
        okrProject.setCreateTime(DateUtils.getNowDate());
        return okrProjectMapper.insertOkrProject(okrProject);
    }

    /**
     * 修改OKR项目管理
     * 
     * @param okrProject OKR项目管理
     * @return 结果
     */
    @Override
    public int updateOkrProject(OkrProject okrProject)
    {
        return okrProjectMapper.updateOkrProject(okrProject);
    }

    /**
     * 删除OKR项目管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectByIds(String ids)
    {
        return okrProjectMapper.deleteOkrProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除OKR项目管理信息
     * 
     * @param id OKR项目管理ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectById(Long id)
    {
        return okrProjectMapper.deleteOkrProjectById(id);
    }
}
