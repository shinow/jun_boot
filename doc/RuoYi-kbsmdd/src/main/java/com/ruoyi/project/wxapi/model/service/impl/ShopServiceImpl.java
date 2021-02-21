package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.Shop;
import com.ruoyi.project.wxapi.model.mapper.ShopMapper;
import com.ruoyi.project.wxapi.model.service.IShopService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-04
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public Shop getByWxappIdAndShopId(Integer wxapp_id, Integer shop_id) {
        return this.getBaseMapper().getByWxappIdAndShopId(wxapp_id, shop_id);
    }

    @Override
    public List<Shop> selectByWxappId(Integer wxappId) {
        return this.getBaseMapper().selectByWxappId(wxappId);
    }
}
