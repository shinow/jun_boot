package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Goods;
import com.ruoyi.project.wxapi.model.bean.GoodsSpec;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface GoodsSpecMapper extends BaseMapper<GoodsSpec> {

    List<GoodsSpec> selectByGoodsId(Integer goodsId);

    List<GoodsSpec> selectByGroupId(Integer groupId);

    GoodsSpec getByGoodsId(Integer goodsId);

    GoodsSpec getNotGroupByGoodsId(Integer goodsId);

    Integer reduceStockByGoods(Goods goods);
}
