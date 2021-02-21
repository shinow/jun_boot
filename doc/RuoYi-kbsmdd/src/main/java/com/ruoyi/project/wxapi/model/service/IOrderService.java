package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.Cart;
import com.ruoyi.project.wxapi.model.bean.Order;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.OrderQO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface IOrderService extends IService<Order> {

    Order addOrder(List<Map.Entry<String, Cart>> list, BaseParamQO baseParamQO, OrderQO orderQO);
    Integer updatePayStatusByOrderId(Long orderNo, Integer payStatus);

    List<Order> selectLargeByTableId(Integer tableId);

    List<Order> selectByTableId(Integer tableId);

    List<Order> selectByTableIdAndUserId(Integer tableId, Integer userId);

    List<Order> selectLargeByTableIdAndUserId(Integer tableId, Integer userId);

    Order selectByOrderId(Integer order_id);

    Order selectByOrderIdAndUserId(Integer orderId, Integer userId);
}
