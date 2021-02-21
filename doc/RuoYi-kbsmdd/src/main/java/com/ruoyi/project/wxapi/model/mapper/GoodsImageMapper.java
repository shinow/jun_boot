package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.GoodsImage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface GoodsImageMapper extends BaseMapper<GoodsImage> {

    GoodsImage getByGoodsId(Integer goodsId);

    List<GoodsImage> selectByGoodsId(Integer goodsId);

    Integer insertOnDublicateKey(Long goodsId, Integer imgId, Long wxappId);
}
