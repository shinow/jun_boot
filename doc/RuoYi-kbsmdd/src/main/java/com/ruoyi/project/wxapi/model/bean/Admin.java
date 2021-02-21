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
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPassword;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密钥
     */
    private String securityKey;

    /**
     * 登陆次数
     */
    private Integer loginTimes;

    /**
     * 失败登陆次数
     */
    private Integer loginErrorTimes;

    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登陆ip
     */
    private String lastLoginIp;

    /**
     * 1为超级管理员0为非超级管理员
     */
    private Boolean isSuper;

    /**
     * 0未删除，1已删除，默认0
     */
    private Boolean isDelete;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;

    private Integer roleId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
