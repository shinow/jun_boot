package com.ruoyi.project.smdd.gggl.domain;

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
 * 规格管理对象 kbsm_goods_spec
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmGoodsSpec implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品规格id */
    @Excel(name = "商品规格id")
    private Long id;

    /** 规格名称 */
    @Excel(name = "规格名称")
    private String specName;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsNo;

    /** 店内售价 */
    @Excel(name = "店内售价")
    private BigDecimal goodsPrice;

    /** 外卖售价 */
    @Excel(name = "外卖售价")
    private BigDecimal outPrice;

    /** 商品划线价 */
    @Excel(name = "商品划线价")
    private BigDecimal linePrice;

    /** 当前库存数量 */
    @Excel(name = "当前库存数量")
    private Long stockNum;

    /** 商品销量 */
    @Excel(name = "商品销量")
    private Long goodsSales;

    /** 小程序id */
    @Excel(name = "小程序id")
    private Long wxappId;

    /** 商品sku标识(0代表spu属性) */
    @Excel(name = "商品sku标识(0代表spu属性)")
    private String specSkuId;

    /** 所属规格组（0为单规格， 大于0为多规格） */
    @Excel(name = "所属规格组", readConverterExp = "0=为单规格，,大=于0为多规格")
    private Long groupId;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModified;

    /** 用于乐观锁的版本号 */
    private Long version;



}
