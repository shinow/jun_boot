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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    /**
     * 商品规格(1单规格 2多规格)
     */
    private Integer specType;

    /**
     * 库存计算方式(1下单减库存 2付款减库存)
     */
    private Integer deductStockType;

    /**
     * 商品详情
     */
    private String content;

    /**
     * 初始销量
     */
    private Integer salesInitial;

    /**
     * 实际销量
     */
    private Integer salesActual;

    /**
     * 商品排序(数字越小越靠前)
     */
    private Integer goodsSort;

    /**
     * 商品状态(1上架 2下架)
     */
    private Integer goodsStatus;

    /**
     * 餐盒费用
     */
    private BigDecimal packPrice;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 门店id
     */
    private Integer shopId;

    /**
     * 小程序id
     */
    private Integer wxappId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

    private String fileUrl;

    private String fileName;

    private Integer imageId;

    private String specSkuId;

    private Integer goodsSpecId;

    private BigDecimal goodsPrice;

    private BigDecimal outPrice;

    private BigDecimal linePrice;

    private Integer stockNum;

    private Long version;


}
