package com.ruoyi.project.smdd.dygl.service;

import java.util.List;
import com.ruoyi.project.smdd.dygl.domain.KbsmShopClerk;

/**
 * 店员管理Service接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface IKbsmShopClerkService 
{
    /**
     * 查询店员管理
     * 
     * @param id 店员管理ID
     * @return 店员管理
     */
    public KbsmShopClerk selectKbsmShopClerkById(Long id);

    /**
     * 查询店员管理列表
     * 
     * @param kbsmShopClerk 店员管理
     * @return 店员管理集合
     */
    public List<KbsmShopClerk> selectKbsmShopClerkList(KbsmShopClerk kbsmShopClerk);

    /**
     * 新增店员管理
     * 
     * @param kbsmShopClerk 店员管理
     * @return 结果
     */
    public int insertKbsmShopClerk(KbsmShopClerk kbsmShopClerk);

    /**
     * 修改店员管理
     * 
     * @param kbsmShopClerk 店员管理
     * @return 结果
     */
    public int updateKbsmShopClerk(KbsmShopClerk kbsmShopClerk);

    /**
     * 批量删除店员管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmShopClerkByIds(String ids);

    /**
     * 删除店员管理信息
     * 
     * @param id 店员管理ID
     * @return 结果
     */
    public int deleteKbsmShopClerkById(Long id);
}
