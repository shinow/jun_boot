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
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品封面图id
     */
    private Integer imageId;

    /**
     * 库存计算方式(1下单减库存 2付款减库存)
     */
    private Integer deductStockType;

    /**
     * 规格类型(1单规格 2多规格)
     */
    private Integer specType;

    /**
     * 商品sku标识
     */
    private String specSkuId;

    /**
     * 商品规格id
     */
    private Integer goodsSpecId;

    /**
     * 商品规格信息
     */
    private String goodsAttr;

    /**
     * 商品详情
     */
    private String content;

    /**
     * 商品编码
     */
    private String goodsNo;

    /**
     * 商品售价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品划线价
     */
    private BigDecimal linePrice;

    /**
     * 购买数量
     */
    private Integer totalNum;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 用户id
     */
    private Integer userId;

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


}
