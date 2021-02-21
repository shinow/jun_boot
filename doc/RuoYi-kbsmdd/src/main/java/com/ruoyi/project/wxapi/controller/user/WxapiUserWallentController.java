package com.ruoyi.project.wxapi.controller.user;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;

import com.ruoyi.project.wxapi.config.AppPropConfig;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.model.bean.Recharge;
import com.ruoyi.project.wxapi.model.bean.RechargePlan;
import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.dto.WxpayMpOrderResutDTO;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.RechargeQO;
import com.ruoyi.project.wxapi.model.service.IRechargePlanService;
import com.ruoyi.project.wxapi.model.service.IRechargeService;
import com.ruoyi.project.wxapi.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/wxapi/user/wallent")
@RestController
public class WxapiUserWallentController {
    @Autowired
    IRechargeService rechargeService;
    @Autowired
    IRechargePlanService rechargePlanService;
    @Autowired
    AppPropConfig appPropConfig;
    @Autowired
    private IUserService userService;
    @Autowired
    private WxPayService wxService;

    @RequestMapping("/plan")
    public Result plan(BaseParamQO baseParamQO) {
        List<RechargePlan> rechargePlans = rechargePlanService.selectByWxAppId(baseParamQO.getWxapp_id());
        JSONArray array = new JSONArray();
        for (int i = 0; i < rechargePlans.size(); i++) {
            RechargePlan rechargePlan = rechargePlans.get(i);
            JSONObject item = new JSONObject();
            item.put("money", rechargePlan.getMoney());
            item.put("recharge_plan_id", rechargePlan.getId());
            item.put("gift_money", rechargePlan.getGiftMoney());
            array.add(item);
        }
        return Result.success(array);
    }

    @RequestMapping("/recharge/pay")
    public Result rechargePay(BaseParamQO baseParamQO, RechargeQO rechargeQO) throws WxPayException {
        Recharge recharge = rechargeService.addRecharge(baseParamQO, rechargeQO);
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        User user = userService.selectByUserId(userId);

        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody("订单号:" + recharge.getOrderNo());
        wxPayUnifiedOrderRequest.setOutTradeNo(recharge.getOrderNo().toString());
        wxPayUnifiedOrderRequest.setNotifyUrl(appPropConfig.getDomain() + "/wxapi/pay/rechargeNotify");
        wxPayUnifiedOrderRequest.setTotalFee(recharge.getMoney() * 100);
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setOpenid(user.getOpenId());
        WxPayMpOrderResult wxPayMpOrderResult =  wxService.createOrder(wxPayUnifiedOrderRequest);
        WxpayMpOrderResutDTO wxpayMpOrderResutDTO = new WxpayMpOrderResutDTO();
        BeanUtil.copyProperties(wxPayMpOrderResult, wxpayMpOrderResutDTO);

        return Result.success(wxpayMpOrderResutDTO);
    }

}
