package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Order;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface OrderMapper extends BaseMapper<Order> {

    Integer insertSelective(Order order);

    Integer updatePayStatusByOrderId(Long orderNo, Integer payStatus);

    List<Order> selectLargeByTableId(Integer tableId);

    List<Order> selectByTableId(Integer tableId);

    List<Order> selectByTableIdAndUserId(Integer tableId, Integer userId);

    List<Order> selectLargeByTableIdAndUserId(Integer tableId, Integer userId);

    Order selectByOrderId(Integer orderId);

    Order selectByOrderIdAndUserId(Integer orderId, Integer userId);
}
