package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.OrderAddress;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface IOrderAddressService extends IService<OrderAddress> {

    OrderAddress selectByOrderNoAndUserId(Long orderNo, Integer userId);
}
