package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.OrderGoods;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-14
 */
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {

    List<OrderGoods> selectByOrderNo(Long orderNo);

    Integer insertOrderGoods(OrderGoods orderGoods);
}
