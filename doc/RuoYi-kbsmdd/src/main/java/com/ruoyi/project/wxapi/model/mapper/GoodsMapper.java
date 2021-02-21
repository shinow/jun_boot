package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Goods;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> listByCategoryId(Integer categoryId);

    Goods getByGoodsId(Integer goodsId);
}
