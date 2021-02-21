package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.OrderGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface IOrderGoodsService extends IService<OrderGoods> {

    List<OrderGoods> selectByOrderNo(Long orderNo);
}
