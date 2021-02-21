package com.ruoyi.project.wxapi.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class GoodsSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品规格id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品编码
     */
    private String goodsNo;

    /**
     * 店内售价
     */
    private BigDecimal goodsPrice;

    /**
     * 外卖售价
     */
    private BigDecimal outPrice;

    /**
     * 商品划线价
     */
    private BigDecimal linePrice;

    /**
     * 当前库存数量
     */
    private Integer stockNum;

    /**
     * 商品销量
     */
    private Integer goodsSales;

    /**
     * 小程序id
     */
    private Integer wxappId;

    /**
     * 商品spu标识
     */
    private String specSkuId;

    private Integer groupId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
