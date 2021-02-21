package com.ruoyi.project.wxapi.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.ruoyi.project.wxapi.model.bean.GoodsImage;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
@Data
public class GoodsDTO implements Serializable {


    /**
     * 商品id
     */
    @JsonProperty("goods_id")
    private Integer id;

    /**
     * 商品名称
     */
    @JsonProperty("goods_name")
    private String goodsName;

    /**
     * 商品分类id
     */
    @JsonProperty("category_id")
    private Integer categoryId;

    /**
     * 商品规格(1单规格 2多规格)
     */
    @JsonProperty("spec_type")
    private Integer specType;

    /**
     * 库存计算方式(1下单减库存 2付款减库存)
     */
    @JsonProperty("deduct_stock_type")
    private Integer deductStockType;

    /**
     * 商品详情
     */
    @JsonProperty("content")
    private String content;


    /**
     * 商品排序(数字越小越靠前)
     */
    @JsonProperty("goods_sort")
    private Integer goodsSort;

    /**
     * 商品状态(1上架 2下架)
     */
    @JsonProperty("goods_status")
    private Integer goodsStatus;

    /**
     * 餐盒费用
     */
    @JsonProperty("pack_price")
    private BigDecimal packPrice;


    /**
     * 门店id
     */
    @JsonProperty("shop_id")
    private Integer shopId;

    private String thumbnail;

    private Integer total_num = 0;

    private Integer goods_sales = 0;

    private Integer goods_price = 0;

    private JSONObject specData;

    private List<GoodsImage> image;

    private List<GoodsSpecDTO> spec;

}
