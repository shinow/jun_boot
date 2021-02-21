package com.ruoyi.project.smdd.ddgl.domain;

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
 * 订单管理对象 kbsm_order
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmOrder implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long orderNo;

    /** 订单排号 */
    @Excel(name = "订单排号")
    private Long rowNo;

    /** 门店id */
    @Excel(name = "门店id")
    private Long shopId;

    /** 餐桌/包间id( 1店内就餐 2打包带走 3外卖直达 4到店自取) */
    @Excel(name = "餐桌/包间id( 1店内就餐 2打包带走 3外卖直达 4到店自取)")
    private Long tableId;

    /** 商品总金额 */
    @Excel(name = "商品总金额")
    private BigDecimal totalPrice;

    /** 优惠价格 */
    @Excel(name = "优惠价格")
    private BigDecimal activityPrice;

    /** 订单实付款金额 */
    @Excel(name = "订单实付款金额")
    private BigDecimal payPrice;

    /** 付款状态(1未付款 2已付款) */
    @Excel(name = "付款状态(1未付款 2已付款)")
    private Integer payStatus;

    /** 付款时间 */
    @Excel(name = "付款时间")
    private Long payTime;

    /** 配送费用 */
    @Excel(name = "配送费用")
    private BigDecimal expressPrice;

    /** 餐盒费用 */
    @Excel(name = "餐盒费用")
    private BigDecimal packPrice;

    /** 餐具调料费 */
    @Excel(name = "餐具调料费")
    private BigDecimal warePrice;

    /** 就餐人数 */
    @Excel(name = "就餐人数")
    private Integer people;

    /** 配送员 */
    @Excel(name = "配送员")
    private Long shopClerkId;

    /** 口味选项 */
    @Excel(name = "口味选项")
    private String flavor;

    /** 买家留言 */
    @Excel(name = "买家留言")
    private String message;

    /** 自取到店时间 */
    @Excel(name = "自取到店时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arriveTime;

    /** 上餐状态(1烹制中 2上餐中) */
    @Excel(name = "上餐状态(1烹制中 2上餐中)")
    private Integer deliveryStatus;

    /** 上餐时间 */
    @Excel(name = "上餐时间")
    private Long deliveryTime;

    /** 用餐状态(1用餐中 2用餐完毕) */
    @Excel(name = "用餐状态(1用餐中 2用餐完毕)")
    private Integer receiptStatus;

    /** 用餐完毕时间 */
    @Excel(name = "用餐完毕时间")
    private Long receiptTime;

    /** 订单状态(1进行中 2取消 3待取消 4已完成) */
    @Excel(name = "订单状态(1进行中 2取消 3待取消 4已完成)")
    private Integer orderStatus;

    /** 微信支付交易号 */
    @Excel(name = "微信支付交易号")
    private String transactionId;

    /** 我的佣金 */
    @Excel(name = "我的佣金")
    private BigDecimal commission;

    /** 配送模式(0自配 1顺丰 2达达 2UU) */
    @Excel(name = "配送模式(0自配 1顺丰 2达达 2UU)")
    private Integer deliverMode;

    /** 第三方配送单号 */
    @Excel(name = "第三方配送单号")
    private String deliverId;

    /** 配送距离 */
    @Excel(name = "配送距离")
    private Long deliveryDistance;

    /** 第三方配送费用 */
    @Excel(name = "第三方配送费用")
    private BigDecimal deliverPrice;

    /** 骑手姓名 */
    @Excel(name = "骑手姓名")
    private String deliverName;

    /** 骑手电话 */
    @Excel(name = "骑手电话")
    private String deliverMobile;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

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
