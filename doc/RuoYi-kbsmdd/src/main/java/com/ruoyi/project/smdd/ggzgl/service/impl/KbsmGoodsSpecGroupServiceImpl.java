package com.ruoyi.project.smdd.ggzgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.ggzgl.mapper.KbsmGoodsSpecGroupMapper;
import com.ruoyi.project.smdd.ggzgl.domain.KbsmGoodsSpecGroup;
import com.ruoyi.project.smdd.ggzgl.service.IKbsmGoodsSpecGroupService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 规格组管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Service
public class KbsmGoodsSpecGroupServiceImpl implements IKbsmGoodsSpecGroupService 
{
    @Autowired
    private KbsmGoodsSpecGroupMapper kbsmGoodsSpecGroupMapper;

    /**
     * 查询规格组管理
     * 
     * @param id 规格组管理ID
     * @return 规格组管理
     */
    @Override
    public KbsmGoodsSpecGroup selectKbsmGoodsSpecGroupById(Integer id)
    {
        return kbsmGoodsSpecGroupMapper.selectKbsmGoodsSpecGroupById(id);
    }

    /**
     * 查询规格组管理列表
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 规格组管理
     */
    @Override
    public List<KbsmGoodsSpecGroup> selectKbsmGoodsSpecGroupList(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        return kbsmGoodsSpecGroupMapper.selectKbsmGoodsSpecGroupList(kbsmGoodsSpecGroup);
    }

    /**
     * 新增规格组管理
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 结果
     */
    @Override
    public int insertKbsmGoodsSpecGroup(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        return kbsmGoodsSpecGroupMapper.insertKbsmGoodsSpecGroup(kbsmGoodsSpecGroup);
    }

    /**
     * 修改规格组管理
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 结果
     */
    @Override
    public int updateKbsmGoodsSpecGroup(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        return kbsmGoodsSpecGroupMapper.updateKbsmGoodsSpecGroup(kbsmGoodsSpecGroup);
    }

    /**
     * 删除规格组管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsSpecGroupByIds(String ids)
    {
        return kbsmGoodsSpecGroupMapper.deleteKbsmGoodsSpecGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除规格组管理信息
     * 
     * @param id 规格组管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsSpecGroupById(Integer id)
    {
        return kbsmGoodsSpecGroupMapper.deleteKbsmGoodsSpecGroupById(id);
    }
}
