package com.ruoyi.project.smdd.czgl.domain;

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
 * 餐桌管理对象 kbsm_table
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmTable implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 餐桌id */
    private Long id;

    /** 餐桌名称 */
    @Excel(name = "餐桌名称")
    private String tableName;

    /** 容纳人数 */
    @Excel(name = "容纳人数")
    private Long people;

    /** 最低消费 */
    @Excel(name = "最低消费")
    private BigDecimal consume;

    /** 餐具调料费 */
    @Excel(name = "餐具调料费")
    private BigDecimal warePrice;

    /** 状态(1闲 2忙) */
    @Excel(name = "状态(1闲 2忙)")
    private Integer status;

    /** 排序方式(数字越小越靠前) */
    @Excel(name = "排序方式(数字越小越靠前)")
    private Long sort;

    /** 门店ID */
    @Excel(name = "门店ID")
    private Long shopId;

    /** 小程序id */
    @Excel(name = "小程序id")
    private Long wxappId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;



}
