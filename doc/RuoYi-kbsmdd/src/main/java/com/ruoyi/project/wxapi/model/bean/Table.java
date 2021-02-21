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
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 餐桌id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 餐桌名称
     */
    private String tableName;

    /**
     * 容纳人数
     */
    private Integer people;

    /**
     * 最低消费
     */
    private BigDecimal consume;

    /**
     * 餐具调料费
     */
    private BigDecimal warePrice;

    /**
     * 状态(1闲 2忙)
     */
    private Integer status;

    /**
     * 排序方式(数字越小越靠前)
     */
    private Integer sort;

    /**
     * 门店ID
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


}
