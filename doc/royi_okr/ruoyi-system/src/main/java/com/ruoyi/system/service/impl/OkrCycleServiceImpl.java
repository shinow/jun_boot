package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrCycleMapper;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.service.IOkrCycleService;
import com.ruoyi.common.core.text.Convert;

/**
 * OKR周期Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-28
 */
@Service
public class OkrCycleServiceImpl implements IOkrCycleService 
{
    @Autowired
    private OkrCycleMapper okrCycleMapper;

    /**
     * 查询OKR周期
     * 
     * @param id OKR周期ID
     * @return OKR周期
     */
    @Override
    public OkrCycle selectOkrCycleById(Long id)
    {
        return okrCycleMapper.selectOkrCycleById(id);
    }

    @Override
    public List<OkrCycle> selectOkrUseCycleList() {
        return okrCycleMapper.selectOkrUseCycleList();
    }

    /**
     * 查询OKR周期列表
     * 
     * @param okrCycle OKR周期
     * @return OKR周期
     */
    @Override
    public List<OkrCycle> selectOkrCycleList(OkrCycle okrCycle)
    {
        return okrCycleMapper.selectOkrCycleList(okrCycle);
    }

    /**
     * 新增OKR周期
     * 
     * @param okrCycle OKR周期
     * @return 结果
     */
    @Override
    public int insertOkrCycle(OkrCycle okrCycle)
    {
        okrCycle.setCreateTime(DateUtils.getNowDate());
        return okrCycleMapper.insertOkrCycle(okrCycle);
    }

    /**
     * 修改OKR周期
     * 
     * @param okrCycle OKR周期
     * @return 结果
     */
    @Override
    public int updateOkrCycle(OkrCycle okrCycle)
    {
        return okrCycleMapper.updateOkrCycle(okrCycle);
    }

    /**
     * 删除OKR周期对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrCycleByIds(String ids)
    {
        return okrCycleMapper.deleteOkrCycleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除OKR周期信息
     * 
     * @param id OKR周期ID
     * @return 结果
     */
    @Override
    public int deleteOkrCycleById(Long id)
    {
        return okrCycleMapper.deleteOkrCycleById(id);
    }
}
