package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.Goods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
public interface IGoodsService extends IService<Goods> {

    List<Goods> listByCategoryId(Integer id);

    Goods getByGoodsId(Integer goodsId);


}
