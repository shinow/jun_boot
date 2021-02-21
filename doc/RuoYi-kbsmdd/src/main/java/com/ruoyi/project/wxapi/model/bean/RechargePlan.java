package com.ruoyi.project.wxapi.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RechargePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值套餐id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 套餐名称
     */
    private String planName;

    /**
     * 充值金额
     */
    private Integer money;

    /**
     * 赠送金额
     */
    private Integer giftMoney;

    /**
     * 排序方式(数字越小越靠前)
     */
    private Integer sort;

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
