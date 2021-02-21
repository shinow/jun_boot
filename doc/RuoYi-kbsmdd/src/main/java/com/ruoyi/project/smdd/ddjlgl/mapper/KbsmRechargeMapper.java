package com.ruoyi.project.smdd.ddjlgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.ddjlgl.domain.KbsmRecharge;

/**
 * 订单记录管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface KbsmRechargeMapper 
{
    /**
     * 查询订单记录管理
     * 
     * @param id 订单记录管理ID
     * @return 订单记录管理
     */
    public KbsmRecharge selectKbsmRechargeById(Long id);

    /**
     * 查询订单记录管理列表
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 订单记录管理集合
     */
    public List<KbsmRecharge> selectKbsmRechargeList(KbsmRecharge kbsmRecharge);

    /**
     * 新增订单记录管理
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 结果
     */
    public int insertKbsmRecharge(KbsmRecharge kbsmRecharge);

    /**
     * 修改订单记录管理
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 结果
     */
    public int updateKbsmRecharge(KbsmRecharge kbsmRecharge);

    /**
     * 删除订单记录管理
     * 
     * @param id 订单记录管理ID
     * @return 结果
     */
    public int deleteKbsmRechargeById(Long id);

    /**
     * 批量删除订单记录管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmRechargeByIds(String[] ids);
}
