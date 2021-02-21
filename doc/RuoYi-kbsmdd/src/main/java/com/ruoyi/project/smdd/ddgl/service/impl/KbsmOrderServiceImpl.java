package com.ruoyi.project.smdd.ddgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.ddgl.mapper.KbsmOrderMapper;
import com.ruoyi.project.smdd.ddgl.domain.KbsmOrder;
import com.ruoyi.project.smdd.ddgl.service.IKbsmOrderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmOrderServiceImpl implements IKbsmOrderService 
{
    @Autowired
    private KbsmOrderMapper kbsmOrderMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理ID
     * @return 订单管理
     */
    @Override
    public KbsmOrder selectKbsmOrderById(Long id)
    {
        return kbsmOrderMapper.selectKbsmOrderById(id);
    }

    /**
     * 查询订单管理列表
     * 
     * @param kbsmOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<KbsmOrder> selectKbsmOrderList(KbsmOrder kbsmOrder)
    {
        return kbsmOrderMapper.selectKbsmOrderList(kbsmOrder);
    }

    /**
     * 新增订单管理
     * 
     * @param kbsmOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertKbsmOrder(KbsmOrder kbsmOrder)
    {
        return kbsmOrderMapper.insertKbsmOrder(kbsmOrder);
    }

    /**
     * 修改订单管理
     * 
     * @param kbsmOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateKbsmOrder(KbsmOrder kbsmOrder)
    {
        return kbsmOrderMapper.updateKbsmOrder(kbsmOrder);
    }

    /**
     * 删除订单管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmOrderByIds(String ids)
    {
        return kbsmOrderMapper.deleteKbsmOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmOrderById(Long id)
    {
        return kbsmOrderMapper.deleteKbsmOrderById(id);
    }
}
