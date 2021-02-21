package com.ruoyi.project.smdd.gggl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.gggl.mapper.KbsmGoodsSpecMapper;
import com.ruoyi.project.smdd.gggl.domain.KbsmGoodsSpec;
import com.ruoyi.project.smdd.gggl.service.IKbsmGoodsSpecService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 规格管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Service
public class KbsmGoodsSpecServiceImpl implements IKbsmGoodsSpecService 
{
    @Autowired
    private KbsmGoodsSpecMapper kbsmGoodsSpecMapper;

    /**
     * 查询规格管理
     * 
     * @param id 规格管理ID
     * @return 规格管理
     */
    @Override
    public KbsmGoodsSpec selectKbsmGoodsSpecById(Long id)
    {
        return kbsmGoodsSpecMapper.selectKbsmGoodsSpecById(id);
    }

    /**
     * 查询规格管理列表
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 规格管理
     */
    @Override
    public List<KbsmGoodsSpec> selectKbsmGoodsSpecList(KbsmGoodsSpec kbsmGoodsSpec)
    {
        return kbsmGoodsSpecMapper.selectKbsmGoodsSpecList(kbsmGoodsSpec);
    }

    /**
     * 新增规格管理
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 结果
     */
    @Override
    public int insertKbsmGoodsSpec(KbsmGoodsSpec kbsmGoodsSpec)
    {
        return kbsmGoodsSpecMapper.insertKbsmGoodsSpec(kbsmGoodsSpec);
    }

    /**
     * 修改规格管理
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 结果
     */
    @Override
    public int updateKbsmGoodsSpec(KbsmGoodsSpec kbsmGoodsSpec)
    {
        return kbsmGoodsSpecMapper.updateKbsmGoodsSpec(kbsmGoodsSpec);
    }

    /**
     * 删除规格管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsSpecByIds(String ids)
    {
        return kbsmGoodsSpecMapper.deleteKbsmGoodsSpecByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除规格管理信息
     * 
     * @param id 规格管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsSpecById(Long id)
    {
        return kbsmGoodsSpecMapper.deleteKbsmGoodsSpecById(id);
    }
}
