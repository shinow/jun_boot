package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrSynergy;

/**
 * 协同关系Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-07
 */
public interface OkrSynergyMapper 
{
    /**
     * 查询协同关系
     * 
     * @param id 协同关系ID
     * @return 协同关系
     */
    public OkrSynergy selectOkrSynergyById(Long id);

    /**
     * 查询协同关系
     *
     * @param okrId 协同关系ID
     * @return 协同关系
     */
    public List<OkrSynergy> selectOkrSynergyByOkrId(Long okrId);

    /**
     * 查询协同关系列表
     * 
     * @param okrSynergy 协同关系
     * @return 协同关系集合
     */
    public List<OkrSynergy> selectOkrSynergyList(OkrSynergy okrSynergy);


    /**
     * 查询协同关系列表按照okr_id 排序
     *
     * @param okrSynergy 协同关系
     * @return 协同关系集合
     */
    public List<OkrSynergy> selectOkrSynergyListByOkrId(OkrSynergy okrSynergy);


    /**
     * 新增协同关系
     * 
     * @param okrSynergy 协同关系
     * @return 结果
     */
    public int insertOkrSynergy(OkrSynergy okrSynergy);


    /**
     * 批量插入协同数据
     * @param list
     * @return
     */
    public int insertOkrSynergyList(List<OkrSynergy> list);


    /**
     * 修改协同关系
     * 
     * @param okrSynergy 协同关系
     * @return 结果
     */
    public int updateOkrSynergy(OkrSynergy okrSynergy);

    /**
     * 删除协同关系
     * 
     * @param id 协同关系ID
     * @return 结果
     */
    public int deleteOkrSynergyById(Long id);

    /**
     * 批量删除协同关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrSynergyByIds(String[] ids);

    /**
     * 批量删除协同关系
     *
     * @param okrId 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrSynergyByOkrId(Long okrId);
}
