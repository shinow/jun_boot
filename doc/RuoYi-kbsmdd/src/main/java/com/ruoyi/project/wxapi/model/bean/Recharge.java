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
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 充值id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 充值方式(1自助充值 2后台充值)
     */
    private Integer mode;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 充值金额
     */
    private Integer money;

    /**
     * 赠送金额
     */
    private Integer giftMoney;

    /**
     * 套餐ID
     */
    private Integer rechargePlanId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 支付状态(1待付款 2已付款)
     */
    private Integer payStatus;

    /**
     * 支付时间
     */
    private Integer payTime;

    /**
     * 微信支付交易号
     */
    private String transactionId;

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


}
