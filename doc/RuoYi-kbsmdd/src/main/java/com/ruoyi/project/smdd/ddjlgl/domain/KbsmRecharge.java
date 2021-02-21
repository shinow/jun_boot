package com.ruoyi.project.smdd.ddjlgl.domain;

import java.util.List;
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
 * 订单记录管理对象 kbsm_recharge
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmRecharge implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 充值id */
    private Long id;

    /** 充值方式(1自助充值 2后台充值) */
    @Excel(name = "充值方式(1自助充值 2后台充值)")
    private Integer mode;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long orderNo;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private Long money;

    /** 赠送金额 */
    @Excel(name = "赠送金额")
    private Long giftMoney;

    /** 套餐ID */
    @Excel(name = "套餐ID")
    private Long rechargePlanId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 支付状态(1待付款 2已付款) */
    @Excel(name = "支付状态(1待付款 2已付款)")
    private Integer payStatus;

    /** 支付时间 */
    @Excel(name = "支付时间")
    private Long payTime;

    /** 微信支付交易号 */
    @Excel(name = "微信支付交易号")
    private String transactionId;

    /** 门店id */
    @Excel(name = "门店id")
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
