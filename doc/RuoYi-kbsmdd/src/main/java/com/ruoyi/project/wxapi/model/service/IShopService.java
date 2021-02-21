package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.Shop;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-04
 */
public interface IShopService extends IService<Shop> {

    Shop getByWxappIdAndShopId(Integer wxapp_id, Integer shop_id);

    List<Shop> selectByWxappId(Integer wxapp_id);
}
