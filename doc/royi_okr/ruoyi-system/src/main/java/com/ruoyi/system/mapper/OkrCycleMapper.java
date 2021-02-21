package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrCycle;

/**
 * OKR周期Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-28
 */
public interface OkrCycleMapper 
{
    /**
     * 查询OKR周期
     * 
     * @param id OKR周期ID
     * @return OKR周期
     */
    public OkrCycle selectOkrCycleById(Long id);


    /**
     * 查询OKR生效周期列表
     *
     * @return OKR周期集合
     */
    public List<OkrCycle> selectOkrUseCycleList();

    /**
     * 查询OKR周期列表
     * 
     * @param okrCycle OKR周期
     * @return OKR周期集合
     */
    public List<OkrCycle> selectOkrCycleList(OkrCycle okrCycle);

    /**
     * 新增OKR周期
     * 
     * @param okrCycle OKR周期
     * @return 结果
     */
    public int insertOkrCycle(OkrCycle okrCycle);

    /**
     * 修改OKR周期
     * 
     * @param okrCycle OKR周期
     * @return 结果
     */
    public int updateOkrCycle(OkrCycle okrCycle);

    /**
     * 删除OKR周期
     * 
     * @param id OKR周期ID
     * @return 结果
     */
    public int deleteOkrCycleById(Long id);

    /**
     * 批量删除OKR周期
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrCycleByIds(String[] ids);
}
