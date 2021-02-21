package com.pearframe.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearframe.framework.security.subject.CustomUserDetails;

import java.io.Serializable;
import java.util.Date;

/**
 * Describe: 用户领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@TableName(value="sys_user")
public class SysUser extends CustomUserDetails implements Serializable {

    @TableField(value="real_name",exist = true)
    private String realName;

    @TableField(value="birthday",exist = true)
    private Date birthday;

    @TableField(value="avatar",exist = true)
    private String avatar;

    @TableField(value="sex",exist = true)
    private Integer sex;

    @TableField(value="email",exist = true)
    private String email;

    @TableField(value="phone",exist = true)
    private String phone;

    @TableField(value="delFlag",exist = true)
    private Integer delFlag;


}
