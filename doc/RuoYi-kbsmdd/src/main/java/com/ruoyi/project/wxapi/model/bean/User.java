package com.ruoyi.project.wxapi.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id（主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 进入场景
     */
    private Integer scene;

    /**
     * 开放平台唯一标示
     */
    private String unionId;

    /**
     * 微信openid(唯一标示)
     */
    private String openId;

    /**
     * 用户类型 1小程序，2公众号
     */
    private Integer type;

    /**
     * 微信昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 微信头像
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 性别(1男，2女，3保密)
     */
    private Integer gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 是否关注公众号1=关注，0=取消关注
     */
    private Integer subscribe;

    /**
     * 默认收货地址
     */
    private Integer addressId;

    /**
     * 用户等级id
     */
    private Integer userGradeId;

    /**
     * 钱包余额
     */
    private BigDecimal wallet;

    /**
     * 消费金额
     */
    private BigDecimal pay;

    /**
     * 用户积分
     */
    private Integer score;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 我的佣金
     */
    private BigDecimal commission;

    /**
     * 推荐人
     */
    private Integer recommender;

    /**
     * 是否是分销商1=是，0=不是
     */
    private Integer isDealer;

    /**
     * 登录次数
     */
    private Integer loginCount;

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
