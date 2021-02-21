package com.ruoyi.project.wxapi.model.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.model.bean.Recharge;
import com.ruoyi.project.wxapi.model.bean.RechargePlan;
import com.ruoyi.project.wxapi.model.mapper.RechargeMapper;
import com.ruoyi.project.wxapi.model.mapper.RechargePlanMapper;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.RechargeQO;
import com.ruoyi.project.wxapi.model.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements IRechargeService {
    @Autowired
    RechargePlanMapper rechargePlanMapper;

    @Override
    public Recharge addRecharge(BaseParamQO baseParamQO, RechargeQO rechargeQO) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long orderNo = snowflake.nextId();
        Recharge recharge = null;
        if (rechargeQO.getRecharge_plan_id() > 0) {
            // 套餐充值
            RechargePlan rechargePlan = rechargePlanMapper.getByRechargePlanId(rechargeQO.getRecharge_plan_id());
            recharge = new Recharge();
            recharge.setOrderNo(orderNo);
            recharge.setMoney(rechargePlan.getMoney());
            recharge.setGiftMoney(rechargePlan.getGiftMoney());
            recharge.setUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
            recharge.setShopId(baseParamQO.getShop_id());
            recharge.setWxappId(baseParamQO.getWxapp_id());
            recharge.setRechargePlanId(rechargePlan.getId());

        } else {
            // 直接充值
            recharge = new Recharge();
            recharge.setOrderNo(orderNo);
            recharge.setMoney(rechargeQO.getMoney());
            recharge.setUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
            recharge.setShopId(baseParamQO.getShop_id());
            recharge.setWxappId(baseParamQO.getWxapp_id());

        }
        Integer flag = this.getBaseMapper().insert(recharge);
        if (flag > 0) {
            return recharge;
        }
        return null;
    }

    @Override
    public Recharge getByOrderNo(long orderNo) {
        return this.getBaseMapper().getByOrderNo(orderNo);
    }
}
