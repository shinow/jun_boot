package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.OrderAddress;
import com.ruoyi.project.wxapi.model.mapper.OrderAddressMapper;
import com.ruoyi.project.wxapi.model.service.IOrderAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
@Service
public class OrderAddressServiceImpl extends ServiceImpl<OrderAddressMapper, OrderAddress> implements IOrderAddressService {

    @Override
    public OrderAddress selectByOrderNoAndUserId(Long orderNo, Integer userId) {
        return this.getBaseMapper().selectByOrderNoAndUserId(orderNo, userId);
    }
}
