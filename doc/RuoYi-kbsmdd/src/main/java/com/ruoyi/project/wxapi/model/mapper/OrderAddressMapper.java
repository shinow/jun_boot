package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.OrderAddress;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface OrderAddressMapper extends BaseMapper<OrderAddress> {

    OrderAddress selectByOrderNoAndUserId(Long orderNo, Integer userId);

    Integer insertOrderAdress(OrderAddress orderAddress);
}
