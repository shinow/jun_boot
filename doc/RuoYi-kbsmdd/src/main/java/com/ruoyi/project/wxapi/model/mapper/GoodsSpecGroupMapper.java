package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.GoodsSpecGroup;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface GoodsSpecGroupMapper extends BaseMapper<GoodsSpecGroup> {

    List<GoodsSpecGroup> selectByGoodsId(Integer goodsId);
}
