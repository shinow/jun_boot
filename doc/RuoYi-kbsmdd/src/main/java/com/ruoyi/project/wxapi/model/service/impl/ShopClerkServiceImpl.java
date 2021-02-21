package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.ShopClerk;
import com.ruoyi.project.wxapi.model.mapper.ShopClerkMapper;
import com.ruoyi.project.wxapi.model.service.IShopClerkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-19
 */
@Service
public class ShopClerkServiceImpl extends ServiceImpl<ShopClerkMapper, ShopClerk> implements IShopClerkService {

    @Override
    public ShopClerk getByClerkId(Integer shopClerkId) {
        return this.getBaseMapper().getByClerkId(shopClerkId);
    }
}
