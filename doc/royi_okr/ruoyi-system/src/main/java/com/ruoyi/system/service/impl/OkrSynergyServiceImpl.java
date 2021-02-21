package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrSynergyMapper;
import com.ruoyi.system.domain.OkrSynergy;
import com.ruoyi.system.service.IOkrSynergyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 协同关系Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-07
 */
@Service
public class OkrSynergyServiceImpl implements IOkrSynergyService 
{
    @Autowired
    private OkrSynergyMapper okrSynergyMapper;

    /**
     * 查询协同关系
     * 
     * @param id 协同关系ID
     * @return 协同关系
     */
    @Override
    public OkrSynergy selectOkrSynergyById(Long id)
    {
        return okrSynergyMapper.selectOkrSynergyById(id);
    }


    @Override
    public List<OkrSynergy> selectOkrSynergyByOkrId(Long okrId) {
        return okrSynergyMapper.selectOkrSynergyByOkrId(okrId);
    }

    /**
     * 查询协同关系列表
     * 
     * @param okrSynergy 协同关系
     * @return 协同关系
     */
    @Override
    public List<OkrSynergy> selectOkrSynergyList(OkrSynergy okrSynergy)
    {
        return okrSynergyMapper.selectOkrSynergyList(okrSynergy);
    }

    /**
     * 查询协同关系列表 按照okr_id 排序
     *
     * @param okrSynergy 协同关系
     * @return 协同关系集合
     */
    public List<OkrSynergy> selectOkrSynergyListByOkrId(OkrSynergy okrSynergy){
        return okrSynergyMapper.selectOkrSynergyListByOkrId(okrSynergy);
    };

    /**
     * 新增协同关系
     * 
     * @param okrSynergy 协同关系
     * @return 结果
     */
    @Override
    public int insertOkrSynergy(OkrSynergy okrSynergy)
    {
        return okrSynergyMapper.insertOkrSynergy(okrSynergy);
    }

    @Override
    public int insertOkrSynergyList(List<OkrSynergy> list) {
        return okrSynergyMapper.insertOkrSynergyList(list);
    }

    /**
     * 修改协同关系
     * 
     * @param okrSynergy 协同关系
     * @return 结果
     */
    @Override
    public int updateOkrSynergy(OkrSynergy okrSynergy)
    {
        return okrSynergyMapper.updateOkrSynergy(okrSynergy);
    }

    /**
     * 删除协同关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrSynergyByIds(String ids)
    {
        return okrSynergyMapper.deleteOkrSynergyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除协同关系信息
     * 
     * @param id 协同关系ID
     * @return 结果
     */
    @Override
    public int deleteOkrSynergyById(Long id)
    {
        return okrSynergyMapper.deleteOkrSynergyById(id);
    }

    @Override
    public int deleteOkrSynergyByOkrId(Long okrId) {
        return okrSynergyMapper.deleteOkrSynergyByOkrId(okrId);
    }
}
