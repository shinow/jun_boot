package com.pearframe.framework.security.subject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    @TableId(value="user_id",type= IdType.ID_WORKER_STR)
    private String userId;

    @TableField(value="user_name",exist = true)
    private String userName;

    @TableField(value="password",exist=true)
    private String password;

    @TableField(value="salt",exist = true)
    private String salt;

    @TableField(value="status",exist = true)
    private Integer status;

    @TableField(exist = false)
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.authorities;
    }

    /**
     * Describe: 返回用于验证用户身份的密码
     * */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Describe:返回验证用户身份的用户名
     * */
    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 指示用户是否已经过期
     * */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Describe: 只是用户的账号是否锁定
     * */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Describe: 用户密码是否过期
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Describe: 该账户是否启用
     * */
    @Override
    public boolean isEnabled() {
        return false;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
