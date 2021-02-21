package com.ruoyi.project.smdd.yhgl.domain;

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
 * 用户管理对象 kbsm_user
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmUser implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户id（主键） */
    private Long id;

    /** 进入场景 */
    @Excel(name = "进入场景")
    private Long scene;

    /** 开放平台唯一标示 */
    @Excel(name = "开放平台唯一标示")
    private String unionId;

    /** 微信openid(唯一标示) */
    @Excel(name = "微信openid(唯一标示)")
    private String openId;

    /** 用户类型 1小程序，2公众号 */
    @Excel(name = "用户类型 1小程序，2公众号")
    private Integer type;

    /** 微信昵称 */
    @Excel(name = "微信昵称")
    private String nickName;

    /** 微信头像 */
    @Excel(name = "微信头像")
    private String avatarUrl;

    /** 性别(1男，2女，3保密) */
    @Excel(name = "性别(1男，2女，3保密)")
    private Integer gender;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 是否关注公众号1=关注，0=取消关注 */
    @Excel(name = "是否关注公众号1=关注，0=取消关注")
    private Integer subscribe;

    /** 默认收货地址 */
    @Excel(name = "默认收货地址")
    private Long addressId;

    /** 用户等级id */
    @Excel(name = "用户等级id")
    private Long userGradeId;

    /** 钱包余额 */
    @Excel(name = "钱包余额")
    private BigDecimal wallet;

    /** 消费金额 */
    @Excel(name = "消费金额")
    private BigDecimal pay;

    /** 用户积分 */
    @Excel(name = "用户积分")
    private Long score;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 我的佣金 */
    @Excel(name = "我的佣金")
    private BigDecimal commission;

    /** 推荐人 */
    @Excel(name = "推荐人")
    private Long recommender;

    /** 是否是分销商1=是，0=不是 */
    @Excel(name = "是否是分销商1=是，0=不是")
    private Integer isDealer;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Long loginCount;

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
