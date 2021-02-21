package com.ruoyi.project.wxapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.config.AppPropConfig;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.controller.util.RedisUtil;
import com.ruoyi.project.wxapi.model.bean.*;
import com.ruoyi.project.wxapi.model.dto.CategoryDTO;
import com.ruoyi.project.wxapi.model.dto.GoodsDTO;
import com.ruoyi.project.wxapi.model.dto.GoodsSpecDTO;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.service.ICategoryService;
import com.ruoyi.project.wxapi.model.service.IGoodsImageService;
import com.ruoyi.project.wxapi.model.service.IGoodsService;
import com.ruoyi.project.wxapi.model.service.IGoodsSpecService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api("goods")
@RestController
@RequestMapping("/wxapi/goods")
public class WxapiGoodsController {
    private final Logger log = LoggerFactory.getLogger(WxapiGoodsController.class);

    @Autowired
    IGoodsService goodsService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IGoodsImageService goodsImageService;
    @Autowired
    IGoodsSpecService goodsSpecService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    AppPropConfig appPropConfig;

    @RequestMapping("/lists")
    public Result lists(BaseParamQO baseParamQO) {
        Integer userId = JWTUtil.parseJWT(baseParamQO.getToken());
        Integer shopId = baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1;
        String key = "cart_" + userId + "_" + shopId;
        Map cartCache = redisUtil.hmget(key);

        Integer totalNum = 0;
        BigDecimal totalPrice = new BigDecimal(0);

        baseParamQO.setShop_id(baseParamQO.getShop_id() != null ? baseParamQO.getShop_id() : 1);
        List<Category> categories = categoryService.listByShopId(baseParamQO.getShop_id());
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        JSONObject goodsObj = new JSONObject();
        JSONObject data = new JSONObject();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);

            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtil.copyProperties(category, categoryDTO);
            categoryDTOS.add(categoryDTO);

            List<GoodsDTO> goodsDTOS = new ArrayList<>();
            Integer categoryId = categories.get(i).getId();
            List<Goods> goodsList = goodsService.listByCategoryId(categoryId);
            for (int j = 0; j < goodsList.size(); j++) {
                List<GoodsSpecDTO> goodsSpecDTOS = new ArrayList<>();
                Goods goods = goodsList.get(j);
                GoodsSpec goodsSpec = goodsSpecService.getSingleByGoodsId(goods.getId());
                GoodsSpecDTO goodsSpecDTO = new GoodsSpecDTO();
                BeanUtil.copyProperties(goodsSpec, goodsSpecDTO);
                goodsSpecDTOS.add(goodsSpecDTO);
                String item = goodsSpec.getGoodsId() + "_" + goodsSpec.getSpecSkuId();

                GoodsImage goodsImage = goodsImageService.getByGoodsId(goods.getId());
                goodsImage.setFilePath(appPropConfig.getDomain() + goodsImage.getFileName());
                List<GoodsImage> goodsImages = new ArrayList<>();
                goodsImages.add(goodsImage);


                GoodsDTO goodsDTO = new GoodsDTO();
                goodsDTO.setThumbnail(goodsImage.getFilePath());
                goodsDTO.setImage(goodsImages);
                if (goods.getSpecType() == 2) {// 多规格
                    Map<GoodsSpecGroup, List<GoodsSpec>> goodsSpecMap = goodsSpecService.getMUltiByGoodsId(goods.getId());
                    JSONObject specData = initSpecData(goodsSpecMap, goodsSpecDTOS, key, item);
                    goodsDTO.setSpecData(specData);
                } else {
                    goodsDTO.setSpecData(null);
                }


                // 与购物车数据比较
                if (cartCache.get(item) != null) {// 购物车有这个商品
                    Cart cart = new Cart();
                    BeanUtil.copyProperties(cartCache.get(item), cart);
                    goodsDTO.setTotal_num(cart.getGoodsNum());
                    totalNum = totalNum + cart.getGoodsNum();

                    if (baseParamQO.getFood_mode() == 3) {
                        // 外卖价格
                        totalPrice = totalPrice.add(goodsSpec.getOutPrice().multiply(new BigDecimal(cart.getGoodsNum())));

                    } else {
                        totalPrice = totalPrice.add(goodsSpec.getGoodsPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                    }


                }

                BeanUtil.copyProperties(goods, goodsDTO);
                goodsDTO.setSpec(goodsSpecDTOS);
                goodsDTOS.add(goodsDTO);
            }
            goodsObj.put(categoryId.toString(), goodsDTOS);
        }
        data.put("category", categoryDTOS);
        data.put("goods", goodsObj);
        data.put("order_total_num", totalNum);
        data.put("order_total_price", totalPrice);
        data.put("min_price", 0);

        return Result.success(data);
    }

    @RequestMapping("/detail")
    public Result detail(BaseParamQO baseParamQO, Integer goods_id) {
        Goods goods = goodsService.getByGoodsId(goods_id);
        GoodsSpec goodsSpec = goodsSpecService.getSingleByGoodsId(goods_id);
        List<GoodsImage> goodsImages = goodsImageService.selectByGoodsId(goods_id);

        JSONObject data = new JSONObject();
        data.put("nav_select", false);// 快捷导航
        data.put("indicatorDots", true);// 是否显示面板指示点
        data.put("autoplay", true);// 是否自动切换
        data.put("interval", true);// 自动切换时间间隔
        data.put("duration", true);// 滑动动画时长
        data.put("currentIndex", 1);// 轮播图指针
        data.put("floorstatus", false);// 返回顶部
        data.put("showView", true);// 显示商品规格
        data.put("goods_price", goods.getGoodsPrice());
        data.put("line_price", goods.getLinePrice());
        data.put("stock_num", goodsSpec.getStockNum());
        data.put("goods_sales", goodsSpec.getGoodsSales());
        data.put("goods_sku_id", goods.getSpecSkuId());

        JSONObject detail = new JSONObject();
        detail.put("goods_id", goods.getId());
        detail.put("goods_name", goods.getGoodsName());
        detail.put("content", goods.getContent());
        JSONArray imageJsonArray = new JSONArray();
        for (int i = 0; i < goodsImages.size(); i++) {
            JSONObject item = new JSONObject();
            GoodsImage goodsImage = goodsImages.get(i);
            item.put("file_path", appPropConfig.getDomain() + goodsImage.getFileName());
            imageJsonArray.add(item);
        }
        detail.put("image", imageJsonArray);
        data.put("detail", detail);
        return Result.success(data);

    }



    private JSONObject initSpecData(Map<GoodsSpecGroup, List<GoodsSpec>> goodsSpecMap, List<GoodsSpecDTO> goodsSpecDTOS, String key, String item) {
        JSONObject specData = new JSONObject();

        JSONArray spec_attr = new JSONArray();
        JSONArray spec_list = new JSONArray();

        for (GoodsSpecGroup goodsSpecGroup : goodsSpecMap.keySet()) {
            JSONObject specAttrItem = new JSONObject();
            specAttrItem.put("group_id", goodsSpecGroup.getId());
            specAttrItem.put("group_name", goodsSpecGroup.getGroupName());
            JSONArray spec_items = new JSONArray();
            List<GoodsSpec> goodsSpecs = goodsSpecMap.get(goodsSpecGroup);

            JSONObject cartCache = (JSONObject) redisUtil.hget(key, item);
            Cart cart = null;
            if (cartCache != null) {
                cart = new Cart();
                BeanUtil.copyProperties(cartCache, cart);
            }

            for (int i = 0; i < goodsSpecs.size(); i++) {
                JSONObject spec_item = new JSONObject();
                GoodsSpec goodsSpec = goodsSpecs.get(i);
                spec_item.put("item_id", goodsSpec.getSpecSkuId());
                spec_item.put("spec_value", goodsSpec.getSpecName());
                if (cart != null && cart.getAttr().get(goodsSpec.getSpecName()) != null) {
                    spec_item.put("num", cart.getAttr().get(goodsSpec.getSpecName()));
                } else {
                    spec_item.put("num", 0);
                }
                spec_items.add(spec_item);

                if (!goodsSpec.getSpecSkuId().equals("0")) {// 不是无关联库存属性
                    JSONObject specListItem = new JSONObject();
                    specListItem.put("goods_spec_id", goodsSpec.getId());
                    specListItem.put("spec_sku_id", goodsSpec.getSpecSkuId());
                    specListItem.put("goods_no", goodsSpec.getGoodsNo());
                    specListItem.put("out_price", goodsSpec.getOutPrice());
                    specListItem.put("goods_price", goodsSpec.getGoodsPrice());
                    specListItem.put("line_price", goodsSpec.getLinePrice());
                    specListItem.put("stock_num", goodsSpec.getStockNum());
                    spec_list.add(specListItem);

                    GoodsSpecDTO goodsSpecDTO = new GoodsSpecDTO();
                    BeanUtil.copyProperties(goodsSpec, goodsSpecDTO);
                    goodsSpecDTOS.add(goodsSpecDTO);
                }

            }
            specAttrItem.put("spec_items", spec_items);
            spec_attr.add(specAttrItem);
        }
        specData.put("spec_attr", spec_attr);
        specData.put("spec_list", spec_list);
        return specData;
    }
}
