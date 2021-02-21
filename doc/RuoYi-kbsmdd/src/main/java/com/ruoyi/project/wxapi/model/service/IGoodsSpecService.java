package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.GoodsSpec;
import com.ruoyi.project.wxapi.model.bean.GoodsSpecGroup;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface IGoodsSpecService extends IService<GoodsSpec> {

    Map<GoodsSpecGroup, List<GoodsSpec>> getMUltiByGoodsId(Integer id);

    GoodsSpec getSingleByGoodsId(Integer id);

    /**
     * 获取未分组的规格
     * @param goodsId
     * @return
     */
    GoodsSpec getNotGroupByGoodsId(Integer goodsId);
}
