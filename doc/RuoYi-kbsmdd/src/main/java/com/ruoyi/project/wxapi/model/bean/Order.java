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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 订单排号
     */
    private Integer rowNo;

    /**
     * 门店id
     */
    private Integer shopId;

    /**
     * 餐桌/包间id
     */
    private Integer tableId;

    /**
     * 商品总金额
     */
    private BigDecimal totalPrice;

    /**
     * 优惠价格
     */
    private BigDecimal activityPrice;

    /**
     * 订单实付款金额
     */
    private BigDecimal payPrice;

    /**
     * 付款状态(1未付款 2已付款)
     */
    private Integer payStatus;

    /**
     * 付款时间
     */
    private Integer payTime;

    /**
     * 配送费用
     */
    private BigDecimal expressPrice;

    /**
     * 餐盒费用
     */
    private BigDecimal packPrice;

    /**
     * 餐具调料费
     */
    private BigDecimal warePrice;

    /**
     * 就餐人数
     */
    private Integer people;

    /**
     * 配送员
     */
    private Integer shopClerkId;

    /**
     * 口味选项
     */
    private String flavor;

    /**
     * 买家留言
     */
    private String message;

    /**
     * 自取到店时间
     */
    private LocalDateTime arriveTime;

    /**
     * 上餐状态(1烹制中 2上餐中)
     */
    private Integer deliveryStatus;

    /**
     * 上餐时间
     */
    private Integer deliveryTime;

    /**
     * 用餐状态(1用餐中 2用餐完毕)
     */
    private Integer receiptStatus;

    /**
     * 用餐完毕时间
     */
    private Integer receiptTime;

    /**
     * 订单状态(1进行中 2取消 3待取消 4已完成)
     */
    private Integer orderStatus;

    /**
     * 微信支付交易号
     */
    private String transactionId;

    /**
     * 我的佣金
     */
    private BigDecimal commission;

    /**
     * 配送模式(0自配 1顺丰 2达达 2UU)
     */
    private Integer deliverMode;

    /**
     * 第三方配送单号
     */
    private String deliverId;

    /**
     * 配送距离
     */
    private Integer deliveryDistance;

    /**
     * 第三方配送费用
     */
    private BigDecimal deliverPrice;

    /**
     * 骑手姓名
     */
    private String deliverName;

    /**
     * 骑手电话
     */
    private String deliverMobile;

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


}
