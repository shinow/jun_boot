package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.OrderGoods;
import com.ruoyi.project.wxapi.model.mapper.OrderGoodsMapper;
import com.ruoyi.project.wxapi.model.service.IOrderGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements IOrderGoodsService {

    @Override
    public List<OrderGoods> selectByOrderNo(Long orderNo) {
        return this.getBaseMapper().selectByOrderNo(orderNo);
    }
}
