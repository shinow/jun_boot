package com.ruoyi.project.smdd.cpgl.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 菜品管理对象 kbsm_goods
 * 
 * @author ruoyi
 * @date 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmGoods implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品分类id */
    @Excel(name = "商品分类id")
    private Long categoryId;

    /** 商品规格(1单规格 2多规格) */
    @Excel(name = "商品规格(1单规格 2多规格)")
    private Integer specType;

    /** 库存计算方式(1下单减库存 2付款减库存) */
    @Excel(name = "库存计算方式(1下单减库存 2付款减库存)")
    private Integer deductStockType;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String content;

    /** 初始销量 */
    @Excel(name = "初始销量")
    private Long salesInitial;

    /** 实际销量 */
    @Excel(name = "实际销量")
    private Long salesActual;

    /** 商品排序(数字越小越靠前) */
    @Excel(name = "商品排序(数字越小越靠前)")
    private Long goodsSort;

    /** 商品状态(1上架 2下架) */
    @Excel(name = "商品状态(1上架 2下架)")
    private Integer goodsStatus;

    /** 餐盒费用 */
    @Excel(name = "餐盒费用")
    private BigDecimal packPrice;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 门店id */
    @Excel(name = "门店id")
    private Long shopId;

    /** 小程序id */
    @Excel(name = "小程序id")
    private Long wxappId;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModified;



}
