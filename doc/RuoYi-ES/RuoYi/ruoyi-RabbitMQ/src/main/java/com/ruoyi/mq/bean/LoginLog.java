package com.ruoyi.mq.bean;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 登录日志对象 login_log
 * 
 * @author japhet_jiu
 * @date 2020-05-27
 */
@Data
public class LoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long loginId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long loginUserId;

    /** 用户操作 */
    @Excel(name = "用户操作")
    private String loginOperation;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String loginIp;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginTime;
}
