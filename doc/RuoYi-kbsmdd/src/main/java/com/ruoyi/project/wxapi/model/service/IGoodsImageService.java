package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.GoodsImage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface IGoodsImageService extends IService<GoodsImage> {

    GoodsImage getByGoodsId(Integer id);

    List<GoodsImage> selectByGoodsId(Integer goods_id);
}
