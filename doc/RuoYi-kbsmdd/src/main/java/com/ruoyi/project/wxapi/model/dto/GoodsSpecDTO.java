package com.ruoyi.project.wxapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsSpecDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格id
     */
    @JsonProperty("goods_spec_id")
    private Integer id;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 商品id
     */
    @JsonProperty("goods_id")
    private Integer goodsId;

    /**
     * 商品编码
     */
    @JsonProperty("goods_no")
    private String goodsNo;

    /**
     * 店内售价
     */
    @JsonProperty("goods_price")
    private BigDecimal goodsPrice;

    /**
     * 外卖售价
     */
    @JsonProperty("out_price")
    private BigDecimal outPrice;

    /**
     * 商品划线价
     */
    @JsonProperty("line_price")
    private BigDecimal linePrice;

    /**
     * 当前库存数量
     */
    @JsonProperty("stock_num")
    private Integer stockNum;

    /**
     * 商品销量
     */
    @JsonProperty("goods_sales")
    private Integer goodsSales;

    /**
     * 商品spu标识
     */
    @JsonProperty("spec_sku_id")
    private String specSkuId;

    private Integer sell_num = 0;


}
