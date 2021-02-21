package com.ruoyi.project.smdd.ddgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.ddgl.domain.KbsmOrder;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface KbsmOrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 订单管理ID
     * @return 订单管理
     */
    public KbsmOrder selectKbsmOrderById(Long id);

    /**
     * 查询订单管理列表
     * 
     * @param kbsmOrder 订单管理
     * @return 订单管理集合
     */
    public List<KbsmOrder> selectKbsmOrderList(KbsmOrder kbsmOrder);

    /**
     * 新增订单管理
     * 
     * @param kbsmOrder 订单管理
     * @return 结果
     */
    public int insertKbsmOrder(KbsmOrder kbsmOrder);

    /**
     * 修改订单管理
     * 
     * @param kbsmOrder 订单管理
     * @return 结果
     */
    public int updateKbsmOrder(KbsmOrder kbsmOrder);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理ID
     * @return 结果
     */
    public int deleteKbsmOrderById(Long id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmOrderByIds(String[] ids);
}
