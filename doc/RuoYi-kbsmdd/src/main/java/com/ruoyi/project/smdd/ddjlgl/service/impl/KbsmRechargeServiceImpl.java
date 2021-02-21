package com.ruoyi.project.smdd.ddjlgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.ddjlgl.mapper.KbsmRechargeMapper;
import com.ruoyi.project.smdd.ddjlgl.domain.KbsmRecharge;
import com.ruoyi.project.smdd.ddjlgl.service.IKbsmRechargeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 订单记录管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmRechargeServiceImpl implements IKbsmRechargeService 
{
    @Autowired
    private KbsmRechargeMapper kbsmRechargeMapper;

    /**
     * 查询订单记录管理
     * 
     * @param id 订单记录管理ID
     * @return 订单记录管理
     */
    @Override
    public KbsmRecharge selectKbsmRechargeById(Long id)
    {
        return kbsmRechargeMapper.selectKbsmRechargeById(id);
    }

    /**
     * 查询订单记录管理列表
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 订单记录管理
     */
    @Override
    public List<KbsmRecharge> selectKbsmRechargeList(KbsmRecharge kbsmRecharge)
    {
        return kbsmRechargeMapper.selectKbsmRechargeList(kbsmRecharge);
    }

    /**
     * 新增订单记录管理
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 结果
     */
    @Override
    public int insertKbsmRecharge(KbsmRecharge kbsmRecharge)
    {
        return kbsmRechargeMapper.insertKbsmRecharge(kbsmRecharge);
    }

    /**
     * 修改订单记录管理
     * 
     * @param kbsmRecharge 订单记录管理
     * @return 结果
     */
    @Override
    public int updateKbsmRecharge(KbsmRecharge kbsmRecharge)
    {
        return kbsmRechargeMapper.updateKbsmRecharge(kbsmRecharge);
    }

    /**
     * 删除订单记录管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmRechargeByIds(String ids)
    {
        return kbsmRechargeMapper.deleteKbsmRechargeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单记录管理信息
     * 
     * @param id 订单记录管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmRechargeById(Long id)
    {
        return kbsmRechargeMapper.deleteKbsmRechargeById(id);
    }
}
