package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.Recharge;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.RechargeQO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
public interface IRechargeService extends IService<Recharge> {

    Recharge addRecharge(BaseParamQO baseParamQO, RechargeQO rechargeQO);

    Recharge getByOrderNo(long orderNo);
}
