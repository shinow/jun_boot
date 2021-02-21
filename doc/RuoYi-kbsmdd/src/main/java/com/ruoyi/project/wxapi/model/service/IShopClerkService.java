package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.ShopClerk;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-19
 */
public interface IShopClerkService extends IService<ShopClerk> {

    ShopClerk getByClerkId(Integer shopClerkId);
}
