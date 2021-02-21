package com.ruoyi.project.smdd.mdgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.mdgl.domain.KbsmShop;

/**
 * 门店管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface KbsmShopMapper 
{
    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    public KbsmShop selectKbsmShopById(Long id);

    /**
     * 查询门店管理列表
     * 
     * @param kbsmShop 门店管理
     * @return 门店管理集合
     */
    public List<KbsmShop> selectKbsmShopList(KbsmShop kbsmShop);

    /**
     * 新增门店管理
     * 
     * @param kbsmShop 门店管理
     * @return 结果
     */
    public int insertKbsmShop(KbsmShop kbsmShop);

    /**
     * 修改门店管理
     * 
     * @param kbsmShop 门店管理
     * @return 结果
     */
    public int updateKbsmShop(KbsmShop kbsmShop);

    /**
     * 删除门店管理
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    public int deleteKbsmShopById(Long id);

    /**
     * 批量删除门店管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmShopByIds(String[] ids);
}
