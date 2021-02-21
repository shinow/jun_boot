package com.ruoyi.project.smdd.ggzgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.ggzgl.domain.KbsmGoodsSpecGroup;

/**
 * 规格组管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
public interface KbsmGoodsSpecGroupMapper 
{
    /**
     * 查询规格组管理
     * 
     * @param id 规格组管理ID
     * @return 规格组管理
     */
    public KbsmGoodsSpecGroup selectKbsmGoodsSpecGroupById(Integer id);

    /**
     * 查询规格组管理列表
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 规格组管理集合
     */
    public List<KbsmGoodsSpecGroup> selectKbsmGoodsSpecGroupList(KbsmGoodsSpecGroup kbsmGoodsSpecGroup);

    /**
     * 新增规格组管理
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 结果
     */
    public int insertKbsmGoodsSpecGroup(KbsmGoodsSpecGroup kbsmGoodsSpecGroup);

    /**
     * 修改规格组管理
     * 
     * @param kbsmGoodsSpecGroup 规格组管理
     * @return 结果
     */
    public int updateKbsmGoodsSpecGroup(KbsmGoodsSpecGroup kbsmGoodsSpecGroup);

    /**
     * 删除规格组管理
     * 
     * @param id 规格组管理ID
     * @return 结果
     */
    public int deleteKbsmGoodsSpecGroupById(Integer id);

    /**
     * 批量删除规格组管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmGoodsSpecGroupByIds(String[] ids);
}
