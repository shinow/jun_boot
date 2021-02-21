package com.ruoyi.project.smdd.gggl.service;

import java.util.List;
import com.ruoyi.project.smdd.gggl.domain.KbsmGoodsSpec;

/**
 * 规格管理Service接口
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
public interface IKbsmGoodsSpecService 
{
    /**
     * 查询规格管理
     * 
     * @param id 规格管理ID
     * @return 规格管理
     */
    public KbsmGoodsSpec selectKbsmGoodsSpecById(Long id);

    /**
     * 查询规格管理列表
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 规格管理集合
     */
    public List<KbsmGoodsSpec> selectKbsmGoodsSpecList(KbsmGoodsSpec kbsmGoodsSpec);

    /**
     * 新增规格管理
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 结果
     */
    public int insertKbsmGoodsSpec(KbsmGoodsSpec kbsmGoodsSpec);

    /**
     * 修改规格管理
     * 
     * @param kbsmGoodsSpec 规格管理
     * @return 结果
     */
    public int updateKbsmGoodsSpec(KbsmGoodsSpec kbsmGoodsSpec);

    /**
     * 批量删除规格管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmGoodsSpecByIds(String ids);

    /**
     * 删除规格管理信息
     * 
     * @param id 规格管理ID
     * @return 结果
     */
    public int deleteKbsmGoodsSpecById(Long id);
}
