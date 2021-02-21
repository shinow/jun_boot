package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.ShopClerk;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-19
 */
public interface ShopClerkMapper extends BaseMapper<ShopClerk> {

    ShopClerk getByClerkId(Integer shopClerkId);
}
