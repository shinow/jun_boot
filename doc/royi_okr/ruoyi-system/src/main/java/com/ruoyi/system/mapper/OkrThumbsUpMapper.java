package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrThumbsUp;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
public interface OkrThumbsUpMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public OkrThumbsUp selectOkrThumbsUpById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<OkrThumbsUp> selectOkrThumbsUpList(OkrThumbsUp okrThumbsUp);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param okrThumbsUp 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public OkrThumbsUp selectOkrThumbsUpOne(OkrThumbsUp okrThumbsUp);

    /**
     * 新增【请填写功能名称】
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 结果
     */
    public int insertOkrThumbsUp(OkrThumbsUp okrThumbsUp);

    /**
     * 修改【请填写功能名称】
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 结果
     */
    public int updateOkrThumbsUp(OkrThumbsUp okrThumbsUp);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteOkrThumbsUpById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrThumbsUpByIds(String[] ids);
}
