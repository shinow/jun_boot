package com.ruoyi.project.smdd.cpgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.cpgl.domain.KbsmGoods;

/**
 * 菜品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-09
 */
public interface KbsmGoodsMapper 
{
    /**
     * 查询菜品管理
     * 
     * @param id 菜品管理ID
     * @return 菜品管理
     */
    public KbsmGoods selectKbsmGoodsById(Long id);

    /**
     * 查询菜品管理列表
     * 
     * @param kbsmGoods 菜品管理
     * @return 菜品管理集合
     */
    public List<KbsmGoods> selectKbsmGoodsList(KbsmGoods kbsmGoods);

    /**
     * 新增菜品管理
     * 
     * @param kbsmGoods 菜品管理
     * @return 结果
     */
    public int insertKbsmGoods(KbsmGoods kbsmGoods);

    /**
     * 修改菜品管理
     * 
     * @param kbsmGoods 菜品管理
     * @return 结果
     */
    public int updateKbsmGoods(KbsmGoods kbsmGoods);

    /**
     * 删除菜品管理
     * 
     * @param id 菜品管理ID
     * @return 结果
     */
    public int deleteKbsmGoodsById(Long id);

    /**
     * 批量删除菜品管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmGoodsByIds(String[] ids);

    KbsmGoods getByGoodsNameAndWxappId(String goodsName, Long wxappId);
}
