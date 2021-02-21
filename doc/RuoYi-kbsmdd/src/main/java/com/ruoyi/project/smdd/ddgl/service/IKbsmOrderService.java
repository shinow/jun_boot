package com.ruoyi.project.smdd.ddgl.service;

import java.util.List;
import com.ruoyi.project.smdd.ddgl.domain.KbsmOrder;

/**
 * 订单管理Service接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface IKbsmOrderService 
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
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmOrderByIds(String ids);

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理ID
     * @return 结果
     */
    public int deleteKbsmOrderById(Long id);
}
