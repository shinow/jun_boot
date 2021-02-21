package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrThumbsUpMapper;
import com.ruoyi.system.domain.OkrThumbsUp;
import com.ruoyi.system.service.IOkrThumbsUpService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
@Service
public class OkrThumbsUpServiceImpl implements IOkrThumbsUpService 
{
    @Autowired
    private OkrThumbsUpMapper okrThumbsUpMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public OkrThumbsUp selectOkrThumbsUpById(Long id)
    {
        return okrThumbsUpMapper.selectOkrThumbsUpById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<OkrThumbsUp> selectOkrThumbsUpList(OkrThumbsUp okrThumbsUp)
    {
        return okrThumbsUpMapper.selectOkrThumbsUpList(okrThumbsUp);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param okrThumbsUp 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public OkrThumbsUp selectOkrThumbsUpOne(OkrThumbsUp okrThumbsUp)
    {
        return okrThumbsUpMapper.selectOkrThumbsUpOne(okrThumbsUp);
    }



    /**
     * 新增【请填写功能名称】
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertOkrThumbsUp(OkrThumbsUp okrThumbsUp)
    {
        return okrThumbsUpMapper.insertOkrThumbsUp(okrThumbsUp);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param okrThumbsUp 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateOkrThumbsUp(OkrThumbsUp okrThumbsUp)
    {
        return okrThumbsUpMapper.updateOkrThumbsUp(okrThumbsUp);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrThumbsUpByIds(String ids)
    {
        return okrThumbsUpMapper.deleteOkrThumbsUpByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteOkrThumbsUpById(Long id)
    {
        return okrThumbsUpMapper.deleteOkrThumbsUpById(id);
    }
}
