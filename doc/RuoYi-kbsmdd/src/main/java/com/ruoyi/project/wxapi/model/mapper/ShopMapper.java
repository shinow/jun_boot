package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Shop;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-04
 */
public interface ShopMapper extends BaseMapper<Shop> {

    Shop getByWxappIdAndShopId(Integer wxapp_id, Integer shop_id);

    List<Shop> selectByWxappId(Integer wxappId);
}
