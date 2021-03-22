package com.pearadmin.pro.common.secure.services;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;

/**
 * Describe: 基础 UserInfo 公共实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class SecureUser extends BaseDomain implements UserDetails {

    /**
     * 编号
     * */
    @TableId(value="id")
    private String id;

    /**
     * 账号
     * */
    @TableField(value="username")
    private String username;

    /**
     * 密码
     * */
    @TableField(value="password")
    private String password;

    /**
     * 启用
     * */
    @TableField(value="enable")
    private boolean enabled;

    /**
     * 锁定
     * */
    @TableField(value="locked")
    private boolean locked;

    /**
     * 权限
     * */
    @TableField(exist = false)
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    // TODO 目前意义不大, 暂不实现

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

}
