package com.ruoyi.project.wxapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.controller.util.RedisUtil;
import com.ruoyi.project.wxapi.model.bean.Cart;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.GoodsQO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Api("card")
@RestController
@RequestMapping("/wxapi/cart")
public class WxapiCartController {
    private static Logger log = LoggerFactory.getLogger(WxapiCartController.class);
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(BaseParamQO baseParamQO, GoodsQO goodsQO) {
        Cart cart = new Cart();
        Map<String, Integer> attr = new HashMap<>();

        cart.setGoodsId(goodsQO.getGoods_id());
        cart.setGoodsNum(goodsQO.getGoods_num());

        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        String key = "cart_" + userId + "_" + shopId;
        String item = goodsQO.getGoods_id() + "_" + goodsQO.getGoods_sku_id();

        JSONObject getCartObj = (JSONObject) redisUtil.hget(key, item);
        Cart getCart = null;
        if (getCartObj != null) {
            getCart = new Cart();
            BeanUtil.copyProperties(getCartObj, getCart);
        }
        Boolean flag = false;

        JSONArray jsonArray = JSONArray.parseArray(goodsQO.getGoods_spec_arr());


        if (jsonArray != null) {// 多规格
            Map<String, Integer> attrMap = null;
           // 处理不影响库存属性
           for (int i = 0; i < jsonArray.size(); i++) {
               JSONObject item2 = (JSONObject) jsonArray.get(i);
               Integer itemId = Integer.parseInt((String) item2.get("item_id"));
               String specValue = (String) item2.get("spec_value");
               if (itemId == 0) {

                   if (getCart != null) {// 商品存在购物车中
                       attrMap = getCart.getAttr();
                        if (attrMap.get(specValue) != null) {// 有这个规格
                            attrMap.put(specValue, goodsQO.getGoods_num() + attrMap.get(specValue));
                       } else {
                            attrMap.put(specValue, goodsQO.getGoods_num());
                        }
                   } else {
                       attrMap = new HashMap<>();
                       attrMap.put(specValue, goodsQO.getGoods_num());
                   }

               }
           }
            cart.setAttr(attrMap);
       }
       cart.setGoodsNum(goodsQO.getGoods_num());
       cart.setGoodsId(goodsQO.getGoods_id());
       cart.setGoodsSkuId(goodsQO.getGoods_sku_id());
       cart.setGmtCreateTime(LocalDateTime.now());


       if (getCart != null) { // 购物车中存在此商品
           Integer goodsNum = getCart.getGoodsNum();
           cart.setGoodsNum(cart.getGoodsNum() + goodsNum);

       }
       flag = redisUtil.hset(key, item, cart, 2*60*60);


        if (flag) {
            return Result.success();
        } else {
            return Result.error("添加购物车失败");
        }

    }

    @RequestMapping(value = "sub", method = RequestMethod.POST)
    public Result sub(BaseParamQO baseParamQO, GoodsQO goodsQO) {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        String key = "cart_" + userId + "_" + shopId;
        String item = goodsQO.getGoods_id() + "_" + goodsQO.getGoods_sku_id();
        Cart cart = new Cart();
        BeanUtil.copyProperties(redisUtil.hget(key, item), cart);
        cart.setGmtCreateTime(LocalDateTime.now());
        Integer goodsNum = cart.getGoodsNum();
        if (goodsNum == 1) {// 移除购物车
            redisUtil.hdel(key, item);
        } else {
            cart.setGoodsNum(goodsNum - 1);
            redisUtil.hset(key, item, cart);
        }

        return Result.success();
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(BaseParamQO baseParamQO, GoodsQO goodsQO) {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        String key = "cart_" + userId + "_" + shopId;
        String item = goodsQO.getGoods_id() + "_" + goodsQO.getGoods_sku_id();
        redisUtil.hdel(key, item);
        return Result.success();
    }
}
