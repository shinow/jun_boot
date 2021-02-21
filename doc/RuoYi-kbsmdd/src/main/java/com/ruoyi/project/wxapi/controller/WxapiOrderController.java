package com.ruoyi.project.wxapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.project.wxapi.config.AppPropConfig;
import com.ruoyi.project.wxapi.config.WxPayProperties;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.controller.util.RedisUtil;
import com.ruoyi.project.wxapi.exception.StockException;
import com.ruoyi.project.wxapi.model.bean.*;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.dto.UserAddressDTO;
import com.ruoyi.project.wxapi.model.dto.WxpayMpOrderResutDTO;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.OrderQO;
import com.ruoyi.project.wxapi.model.service.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@Api("order")
@RestController
@RequestMapping("/wxapi/order")
public class WxapiOrderController {
    private Logger log = LoggerFactory.getLogger(WxapiOrderController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsSpecService goodsSpecService;
    @Autowired
    AppPropConfig appPropConfig;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private WxPayService wxService;
    @Autowired
    WxPayProperties wxPayProperties;
    @Autowired
    IUserAddressService userAddressService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public Result cartGet(BaseParamQO baseParamQO) {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        User user = userService.selectByUserId(userId);

        String key = "cart_" + userId + "_" + shopId;
        Map cartCache = redisUtil.hmget(key, Cart.class);
        List<Map.Entry<String, Cart>> list = new ArrayList<Map.Entry<String, Cart>>(cartCache.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Cart>>() {
            @Override
            public int compare(Map.Entry<String, Cart> o1, Map.Entry<String, Cart> o2) {
                return o2.getValue().getGmtCreateTime().compareTo(o1.getValue().getGmtCreateTime());
            }
        });

        BigDecimal totalPrice = new BigDecimal(0);
        Integer totalNum = 0;
        Integer activityPrice = 0;
        Integer payPrice = 0;
        Integer warePrice  = 0;

        JSONObject data = new JSONObject();
        JSONArray goodsList = new JSONArray();
        for (Map.Entry<String, Cart> getCart : list) {
            Cart cart = new Cart();
            BeanUtil.copyProperties(getCart.getValue(), cart);
            Goods goods = goodsService.getByGoodsId(cart.getGoodsId());
            GoodsSpec goodsSpec = goodsSpecService.getNotGroupByGoodsId(cart.getGoodsId());
            if (cart.getAttr() != null) {
                // 多规格
                Set<String> cartAttrKeySet = cart.getAttr().keySet();
                for (String cartAttrKey : cartAttrKeySet) {
                    Integer num = cart.getAttr().get(cartAttrKey);
                    JSONObject goodsObj = new JSONObject();
                    goodsObj.put("goods_id", goods.getId());
                    goodsObj.put("goods_name", goods.getGoodsName());
                    goodsObj.put("thumbnail", appPropConfig.getDomain() + goods.getFileName());
                    goodsObj.put("goods_attr", cartAttrKey);

                    goodsObj.put("total_num", num);
                    totalNum += num;
                    if (baseParamQO.getFood_mode() == 3) {
                        // 外卖价格
                        totalPrice = totalPrice.add(goodsSpec.getOutPrice().multiply(new BigDecimal(num)));
                        goodsObj.put("goods_price", goodsSpec.getOutPrice());
                    } else {
                        totalPrice = totalPrice.add(goodsSpec.getGoodsPrice().multiply(new BigDecimal(num)));
                        goodsObj.put("goods_price", goodsSpec.getGoodsPrice());
                    }

                    goodsList.add(goodsObj);
                }

            } else {
                //单规格
                JSONObject goodsObj = new JSONObject();
                goodsObj.put("goods_id", goods.getId());
                goodsObj.put("goods_name", goods.getGoodsName());
                goodsObj.put("thumbnail", appPropConfig.getDomain() + goods.getFileName());
                goodsObj.put("total_num", cart.getGoodsNum());
                totalNum += cart.getGoodsNum();
                if (baseParamQO.getFood_mode() == 3) {
                    // 外卖价格
                    totalPrice = totalPrice.add(goodsSpec.getOutPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                    goodsObj.put("goods_price", goodsSpec.getOutPrice());
                } else {
                    totalPrice = totalPrice.add(goodsSpec.getGoodsPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                    goodsObj.put("goods_price", goodsSpec.getGoodsPrice());
                }
                goodsList.add(goodsObj);
            }
        }
        data.put("goods_list", goodsList);

        JSONArray flavorList = new JSONArray();
        JSONObject flavorItem = new JSONObject();
        flavorItem.put("name", "多加饭");
        flavorList.add(flavorItem);
        JSONObject flavorItem2 = new JSONObject();
        flavorItem2.put("name", "不要香菜");
        flavorList.add(flavorItem2);

        data.put("flavor_list", flavorList);
        data.put("order_total_price", totalPrice);
        data.put("order_total_num", totalNum);
        data.put("activity_price", 0);
        data.put("order_pay_price", totalPrice); //实付款
        data.put("ware_price", 4);

        JSONObject shop = new JSONObject();
        shop.put("tang_mode", 1);
        data.put("shop", shop);
        data.put("food_mode", baseParamQO.getFood_mode());
        data.put("intra_region", true);// 是否在配送范围
        data.put("express_price", 1);// 配送价格

        List<UserAddress> userAddresses = userAddressService.selectByUserIdAndWxappId(userId, baseParamQO.getWxapp_id());
        if (userAddresses.size() > 0) {
            data.put("exist_address", true);
        } else {
            data.put("exist_address", false);
        }
        for (int i = 0; i < userAddresses.size(); i++) {
            if (user.getAddressId() == userAddresses.get(i).getId()) {
                //是默认地址
                UserAddressDTO userAddressDTO = new UserAddressDTO();
                BeanUtil.copyProperties(userAddresses.get(i), userAddressDTO);
                data.put("address", userAddressDTO);
            }
        }

        return Result.success(data);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public Result cartPost(BaseParamQO baseParamQO, OrderQO orderQO) throws WxPayException {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        User user = userService.selectByUserId(userId);
        String key = "cart_" + userId + "_" + shopId;
        Map cartCache = redisUtil.hmget(key, Cart.class);
        List<Map.Entry<String, Cart>> list = new ArrayList<Map.Entry<String, Cart>>(cartCache.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Cart>>() {
            @Override
            public int compare(Map.Entry<String, Cart> o1, Map.Entry<String, Cart> o2) {
                return o2.getValue().getGmtCreateTime().compareTo(o1.getValue().getGmtCreateTime());
            }
        });

        Order order = null;
        try {
            order = orderService.addOrder(list, baseParamQO, orderQO);
        } catch (StockException e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        } catch (Exception e) {
            return Result.error("未知异常");
        }

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
        JSONObject data = new JSONObject();
        data.put("payment", wxpayMpOrderResutDTO);
        return Result.success(data);
    }
}
