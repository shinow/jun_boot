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
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Wxapp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序id（主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 应用类型(1餐饮 2商城)
     */
    private Integer appType;

    /**
     * 门店模式（1单门店 2多门店）
     */
    private Integer shopMode;

    /**
     * 账号来源，1=自助注册，2=平台注册
     */
    private Integer source;

    /**
     * 是否授权(0否 1是)
     */
    private Integer isEmpower;

    /**
     * 小程序名称
     */
    private String appName;

    /**
     * 小程序头像
     */
    private String headImg;

    /**
     * 原始ID
     */
    private String userName;

    /**
     * 小程序AppID
     */
    private String appId;

    /**
     * 主体名称
     */
    private String principalName;

    /**
     * 服务器域名
     */
    private String apiDomain;

    /**
     * 功能介绍
     */
    private String signature;

    /**
     * 微信商户号id
     */
    private String mchid;

    /**
     * 微信支付密钥
     */
    private String apikey;

    /**
     * 微信支付证书
     */
    private String certPem;

    /**
     * 微信支付证书
     */
    private String keyPem;

    /**
     * 收藏提醒(0关闭 1开启)
     */
    private Integer isCollection;

    /**
     * 关注公众号提醒(0关闭 1开启)
     */
    private Integer isWechat;

    /**
     * 商家客服电话
     */
    private String phone;

    /**
     * 令牌凭证
     */
    private String accessToken;

    /**
     * 令牌到期时间
     */
    private Integer expiresIn;

    /**
     * 刷新令牌
     */
    private String authorizerRefreshToken;

    /**
     * 模板ID
     */
    private Integer template;

    /**
     * 自定义版权(0关闭 1开启)
     */
    private Integer isCopyright;

    /**
     * 小程序底部版权
     */
    private String copyright;

    /**
     * 商户ID
     */
    private Integer storeUserId;

    /**
     * 小程序到期时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
