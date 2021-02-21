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
import com.ruoyi.project.wxapi.controller.util.LocalDateTimeUtil;
import com.ruoyi.project.wxapi.model.bean.*;
import com.ruoyi.project.wxapi.model.dto.OrderAddressDTO;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.dto.WxpayMpOrderResutDTO;
import com.ruoyi.project.wxapi.model.enums.*;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wxapi/user/order")
public class WxapiUserOrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderGoodsService orderGoodsService;
    @Autowired
    IOrderAddressService orderAddressService;
    @Autowired
    IShopClerkService shopClerkService;
    @Autowired
    ITableService tableService;

    @Autowired
    AppPropConfig appPropConfig;
    @Autowired
    private IUserService userService;
    @Autowired
    private WxPayService wxService;


    @RequestMapping("/lists")
    public Result list(BaseParamQO baseParamQO) {
        List<Order> orders = null;
        if (baseParamQO.getTableId() == 10001) {
            orders = orderService.selectLargeByTableIdAndUserId(10000, JWTUtil.parseJWT(baseParamQO.getToken()));
        } else {
            orders = orderService.selectByTableIdAndUserId(baseParamQO.getTableId(), JWTUtil.parseJWT(baseParamQO.getToken()));
        }
        JSONObject data =  new JSONObject();
        JSONArray orderObjArray = new JSONArray();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            JSONObject orderObj = new JSONObject();
            orderObj.put("table_id", order.getTableId());
            orderObj.put("order_no", order.getOrderNo());
            orderObj.put("create_time", LocalDateTimeUtil.paserDateTime(order.getGmtCreate()));
            orderObj.put("order_id", order.getId());
            orderObj.put("pay_price", order.getPayPrice());
            orderObj.put("row_no", "1");// 号码


            JSONObject payStatusObj = new JSONObject();
            switch (order.getPayStatus()) {
                case 1:
                    payStatusObj.put("value", PayStatusEnum.WAIT.getValue());
                    payStatusObj.put("text", PayStatusEnum.WAIT.getText());
                break;

                case 2:
                    payStatusObj.put("value", PayStatusEnum.PAY.getValue());
                    payStatusObj.put("text", PayStatusEnum.PAY.getText());
                break;

            }
            orderObj.put("pay_status", payStatusObj);

            JSONObject orderStatusObj = new JSONObject();
            switch (order.getOrderStatus()) {
                case 1:
                    orderStatusObj.put("value", OrderStatusEnum.JXZ.getValue());
                    orderStatusObj.put("text", OrderStatusEnum.JXZ.getText());
                    break;
                case 2:
                    orderStatusObj.put("value", OrderStatusEnum.QX.getValue());
                    orderStatusObj.put("text", OrderStatusEnum.QX.getText());
                    break;
                case 3:
                    orderStatusObj.put("value", OrderStatusEnum.DQX.getValue());
                    orderStatusObj.put("text", OrderStatusEnum.DQX.getText());
                    break;
                case 4:
                    orderStatusObj.put("value", OrderStatusEnum.YWC.getValue());
                    orderStatusObj.put("text", OrderStatusEnum.YWC.getText());
                    break;
            }
            orderObj.put("order_status", orderStatusObj);

            JSONObject receiptStatusObj = new JSONObject();
            switch (order.getReceiptStatus()) {
                case 1:
                    receiptStatusObj.put("value", ReceiptStatusEnum.YCZ.getValue());
                    receiptStatusObj.put("text",  ReceiptStatusEnum.YCZ.getText());
                break;
                case 2:
                    receiptStatusObj.put("value", ReceiptStatusEnum.YCWB.getValue());
                    receiptStatusObj.put("text",  ReceiptStatusEnum.YCWB.getText());
                break;
            }
            orderObj.put("receipt_status", receiptStatusObj);

            JSONObject deliveryStatusObj = new JSONObject();
            switch (order.getDeliveryStatus()) {
                case 1:
                    deliveryStatusObj.put("value", DeliverStatusEnum.PZZ.getValue());
                    deliveryStatusObj.put("text",  DeliverStatusEnum.PZZ.getText());
                break;
                case 2:
                    deliveryStatusObj.put("value", DeliverStatusEnum.SCZ.getValue());
                    deliveryStatusObj.put("text",  DeliverStatusEnum.SCZ.getText());
                break;
            }
            orderObj.put("delivery_status", deliveryStatusObj);

            List<OrderGoods> orderGoods = orderGoodsService.selectByOrderNo(orders.get(i).getOrderNo());
            JSONArray goodsJsonArray = new JSONArray();
            for (int j = 0; j < orderGoods.size(); j++) {
                JSONObject goodsJsonObj = new JSONObject();
                goodsJsonObj.put("thumbnail", appPropConfig.getDomain() + orderGoods.get(j).getFileName());
                goodsJsonArray.add(goodsJsonObj);
            }
            orderObj.put("goods", goodsJsonArray);

            orderObjArray.add(orderObj);


        }
        data.put("list", orderObjArray);


        return Result.success(data);
    }

    @RequestMapping("/detail")
    public Result detail(BaseParamQO baseParamQO) {
        Order order = orderService.selectByOrderIdAndUserId(baseParamQO.getOrder_id(), JWTUtil.parseJWT(baseParamQO.getToken()));
        OrderAddress orderAddress = orderAddressService.selectByOrderNoAndUserId(order.getOrderNo(), JWTUtil.parseJWT(baseParamQO.getToken()));


        List<OrderGoods> orderGoods = orderGoodsService.selectByOrderNo(order.getOrderNo());
        JSONObject orderObj = new JSONObject();
        orderObj.put("order_id", order.getId());
        orderObj.put("order_no", order.getOrderNo());
        orderObj.put("table_id", order.getTableId());
        OrderAddressDTO orderAddressDTO = new OrderAddressDTO();
        BeanUtil.copyProperties(orderAddress, orderAddressDTO);
        orderObj.put("address", orderAddressDTO);

        JSONObject deliveryStatusObj = new JSONObject();
        switch (order.getDeliveryStatus()) {
            case 1:
                deliveryStatusObj.put("value", DeliverStatusEnum.PZZ.getValue());
                deliveryStatusObj.put("text",  DeliverStatusEnum.PZZ.getText());
                break;
            case 2:
                deliveryStatusObj.put("value", DeliverStatusEnum.SCZ.getValue());
                deliveryStatusObj.put("text",  DeliverStatusEnum.SCZ.getText());
                break;
        }
        orderObj.put("delivery_status", deliveryStatusObj);

        JSONObject deliveryModeObj = new JSONObject();
        switch (order.getDeliveryStatus()) {
            case 0:
                deliveryModeObj.put("value", DeliverModeEnum.ZP.getValue());
                deliveryModeObj.put("text",  DeliverModeEnum.ZP.getText());
                break;
            case 1:
                deliveryModeObj.put("value", DeliverModeEnum.SF.getValue());
                deliveryModeObj.put("text",  DeliverModeEnum.SF.getText());
                break;
            case 2:
                deliveryModeObj.put("value", DeliverModeEnum.DD.getValue());
                deliveryModeObj.put("text",  DeliverModeEnum.DD.getText());
                break;
            case 3:
                deliveryModeObj.put("value", DeliverModeEnum.UU.getValue());
                deliveryModeObj.put("text",  DeliverModeEnum.UU.getText());
                break;
        }
        orderObj.put("deliver_mode", deliveryModeObj);
        orderObj.put("deliver_name", order.getDeliverName());
        orderObj.put("deliver_mobile", order.getDeliverMobile());

        if (order.getShopClerkId() != 0) {
            ShopClerk shopClerk = shopClerkService.getByClerkId(order.getShopClerkId());
            JSONObject clerkObj = new JSONObject();
            clerkObj.put("real_name", shopClerk.getRealName());
            clerkObj.put("mobile", shopClerk.getMobile());
            orderObj.put("clerk", clerkObj);
        }


        if (order.getTableId() > 10000) {
            Table table = tableService.getByTableId(order.getTableId());
            JSONObject tableObj = new JSONObject();
            tableObj.put("table_name", table.getTableName());
            orderObj.put("table", tableObj);
        }

        orderObj.put("row_no", "1"); //餐桌、自取、外卖号
        orderObj.put("arrive_time", "到店时间");
        JSONObject orderStatusObj = new JSONObject();
        switch (order.getOrderStatus()) {
            case 1:
                orderStatusObj.put("value", OrderStatusEnum.JXZ.getValue());
                orderStatusObj.put("text", OrderStatusEnum.JXZ.getText());
            break;
            case 2:
                orderStatusObj.put("value", OrderStatusEnum.QX.getValue());
                orderStatusObj.put("text", OrderStatusEnum.QX.getText());
            break;
            case 3:
                orderStatusObj.put("value", OrderStatusEnum.DQX.getValue());
                orderStatusObj.put("text", OrderStatusEnum.DQX.getText());
            break;
            case 4:
                orderStatusObj.put("value", OrderStatusEnum.YWC.getValue());
                orderStatusObj.put("text", OrderStatusEnum.YWC.getText());
            break;
        }
        orderObj.put("order_status", orderStatusObj);

        JSONArray goodsObjArray = new JSONArray();
        for (int i = 0; i < orderGoods.size(); i++) {
            OrderGoods item = orderGoods.get(i);
            JSONObject goodsObj = new JSONObject();
            goodsObj.put("goods_id", item.getGoodsId());
            goodsObj.put("thumbnail", appPropConfig.getDomain() + item.getFileName());
            goodsObj.put("goods_name", item.getGoodsName());
            goodsObj.put("goods_attr", item.getGoodsAttr());
            goodsObj.put("goods_price", item.getGoodsPrice());

            goodsObj.put("total_num", item.getTotalNum());
            goodsObjArray.add(goodsObj);
        }
        orderObj.put("goods", goodsObjArray);
        orderObj.put("total_price", order.getTotalPrice());
        orderObj.put("people", order.getPeople());
        orderObj.put("ware_price", order.getWarePrice());
        orderObj.put("express_price", order.getExpressPrice());
        orderObj.put("pack_price", order.getPackPrice());
        orderObj.put("activity_price", order.getActivityPrice());
        orderObj.put("pay_price", order.getPayPrice());

        JSONObject data = new JSONObject();
        data.put("order", orderObj);
        return Result.success(data);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Result payPost(BaseParamQO baseParamQO) throws WxPayException {
        Order order = orderService.selectByOrderId(baseParamQO.getOrder_id());
        User user = userService.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));

        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody("订单号:" + order.getOrderNo().toString());
        wxPayUnifiedOrderRequest.setOutTradeNo(order.getOrderNo().toString());
        wxPayUnifiedOrderRequest.setNotifyUrl(appPropConfig.getDomain() + "/wxapi/pay/orderNotify");
        wxPayUnifiedOrderRequest.setTotalFee(order.getTotalPrice().multiply(new BigDecimal(100)).intValue());
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setOpenid(user.getOpenId());
        WxPayMpOrderResult wxPayMpOrderResult =  wxService.createOrder(wxPayUnifiedOrderRequest);
        WxpayMpOrderResutDTO wxpayMpOrderResutDTO = new WxpayMpOrderResutDTO();
        BeanUtil.copyProperties(wxPayMpOrderResult, wxpayMpOrderResutDTO);
        return Result.success(wxpayMpOrderResutDTO);

    }
}
