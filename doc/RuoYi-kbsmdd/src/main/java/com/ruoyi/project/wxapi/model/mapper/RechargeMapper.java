package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Recharge;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
public interface RechargeMapper extends BaseMapper<Recharge> {

    Recharge getByOrderNo(long orderNo);
}
