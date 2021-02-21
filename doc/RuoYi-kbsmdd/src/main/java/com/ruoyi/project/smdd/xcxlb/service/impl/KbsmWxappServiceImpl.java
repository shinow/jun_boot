package com.ruoyi.project.smdd.xcxlb.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.xcxlb.mapper.KbsmWxappMapper;
import com.ruoyi.project.smdd.xcxlb.domain.KbsmWxapp;
import com.ruoyi.project.smdd.xcxlb.service.IKbsmWxappService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 小程序列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-08
 */
@Service
public class KbsmWxappServiceImpl implements IKbsmWxappService 
{
    @Autowired
    private KbsmWxappMapper kbsmWxappMapper;

    /**
     * 查询小程序列表
     * 
     * @param id 小程序列表ID
     * @return 小程序列表
     */
    @Override
    public KbsmWxapp selectKbsmWxappById(Long id)
    {
        return kbsmWxappMapper.selectKbsmWxappById(id);
    }

    /**
     * 查询小程序列表列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 小程序列表
     */
    @Override
    public List<KbsmWxapp> selectKbsmWxappList(KbsmWxapp kbsmWxapp)
    {
        return kbsmWxappMapper.selectKbsmWxappList(kbsmWxapp);
    }

    /**
     * 新增小程序列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 结果
     */
    @Override
    public int insertKbsmWxapp(KbsmWxapp kbsmWxapp)
    {
        return kbsmWxappMapper.insertKbsmWxapp(kbsmWxapp);
    }

    /**
     * 修改小程序列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 结果
     */
    @Override
    public int updateKbsmWxapp(KbsmWxapp kbsmWxapp)
    {
        return kbsmWxappMapper.updateKbsmWxapp(kbsmWxapp);
    }

    /**
     * 删除小程序列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmWxappByIds(String ids)
    {
        return kbsmWxappMapper.deleteKbsmWxappByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小程序列表信息
     * 
     * @param id 小程序列表ID
     * @return 结果
     */
    @Override
    public int deleteKbsmWxappById(Long id)
    {
        return kbsmWxappMapper.deleteKbsmWxappById(id);
    }
}
