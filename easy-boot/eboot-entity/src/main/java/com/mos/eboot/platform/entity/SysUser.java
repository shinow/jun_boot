package com.mos.eboot.platform.entity;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mos.eboot.tools.shiro.entity.IUser;
import com.mos.eboot.tools.util.Constants;
import com.mos.eboot.tools.util.StringUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 * <p>
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-13
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements IUser,UserDetails {

	public static final String DEFAULT_PWD = "123456";

	public static String SUPER_ID = "1";

	private static final long serialVersionUID = 1L;

	private String id;

	@Excel(name = "用户名",width = 40D)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Length(min = 6, max = 20, message = "密码长度不少于6个字符，不超过20个字符")
	private String password;

	@TableField("is_del")
	@TableLogic
	private String isDel;

	@Excel(name = "昵称", orderNum = "1",width = 20D)
	private String nickname;

	@Excel(name = "手机号", orderNum = "2", width = 30D)
	private String mobile;

	private String photo;

	@Excel(name = "创建时间", orderNum = "3", format = "yyyy-MM-dd HH:mm:ss",width = 40D)
	@TableField("create_time")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@TableField("create_user")
	private String createUser;

	@Excel(name = "最后登录时间", orderNum = "4", format = "yyyy-MM-dd HH:mm:ss",width = 40D)
	@TableField("last_login_time")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	@Excel(name = "最后登录IP", orderNum = "5",width = 40D)
	private String ip;

	private String disabled;

	private String locked;

	@TableField(exist = false)
	private List<String> roleIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getDisabled() {
		return Constants.POSITIVE.equals(disabled);
	}

	@Override
	public Integer getDeleted() {
		return StringUtil.isBlank(isDel) ? 0 : Integer.valueOf(isDel);
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public Boolean getLocked() {
		return Constants.POSITIVE.equals(locked);
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				", id=" + id +
				", username=" + username +
				", password=" + password +
				", isDel=" + isDel +
				", nickname=" + nickname +
				", mobile=" + mobile +
				", photo=" + photo +
				", createTime=" + createTime +
				", createUser=" + createUser +
				", lastLoginTime=" + lastLoginTime +
				", ip=" + ip +
				", disabled=" + disabled +
				", locked=" + locked +
				"}";
	}
}
