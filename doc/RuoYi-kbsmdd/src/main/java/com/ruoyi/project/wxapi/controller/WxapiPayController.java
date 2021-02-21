package com.ruoyi.project.wxapi.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.project.wxapi.model.bean.Recharge;
import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.service.IOrderService;
import com.ruoyi.project.wxapi.model.service.IRechargeService;
import com.ruoyi.project.wxapi.model.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wxapi/pay")
public class WxapiPayController {
    private static Logger log = LoggerFactory.getLogger(WxapiPayController.class);

    @Autowired
    private WxPayService wxService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    IRechargeService rechargeService;
    @Autowired
    private IUserService userService;

    /***
     * 订单支付回调
     * @param xmlData
     * @return
     */
    @RequestMapping(value = "/orderNotify")
    public String orderNotify(@RequestBody String xmlData) {
        try {
            final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
            String orderNo = notifyResult.getOutTradeNo();
            log.info("订单支付回调orderNo:" + orderNo);
            Integer flag = orderService.updatePayStatusByOrderId(Long.parseLong(orderNo), 2);
            if (flag > 0) {
                 return WxPayNotifyResponse.success("成功");
            } else {
                return WxPayNotifyResponse.fail("失败");
            }

        } catch (WxPayException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail("可能是伪造的请求");
        }
    }

    /**
     * 余额充值回调
     * @return
     */
    @RequestMapping("/rechargeNotify")
    public String rechargeNotify(@RequestBody String xmlData) {
        try {
            final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
            String orderNo = notifyResult.getOutTradeNo();
            log.info("余额充值回调orderNo:" + orderNo);
            Recharge recharge = rechargeService.getByOrderNo(Long.parseLong(orderNo));
            recharge.setPayStatus(2);
            rechargeService.updateById(recharge);

            User user = userService.selectByUserId(recharge.getUserId());
            user.setWallet(user.getWallet().add(new BigDecimal(recharge.getMoney() + recharge.getGiftMoney())));
            Boolean flag = userService.updateById(user);
            if (flag) {
                return WxPayNotifyResponse.success("成功");
            } else {
                return WxPayNotifyResponse.fail("失败");
            }

        } catch (WxPayException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail("可能是伪造的请求");
        }

    }


}
