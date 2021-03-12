package com.jun.plugin.modules.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long menuId;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限描述
	 */
	private String description;

	/**
	 * 权限访问路径
	 */
	private String url;

	/**
	 * 权限标识
	 */
	private String perms;

	/**
	 * 父级权限id
	 */
	private Long parentId;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer type;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 状态：1有效; 0无效
	 */
	private Integer status;

	private Date createTime;

	private Date updateTime;

	/** 子菜单 */
	private List<SysMenu> children = new ArrayList<SysMenu>();

	/**
	 * 获取权限id
	 *
	 * @return menu_id - 权限id
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 设置权限id
	 *
	 * @param menuId
	 *            权限id
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId == null ? null : menuId;
	}

	/**
	 * 获取权限名称
	 *
	 * @return name - 权限名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置权限名称
	 *
	 * @param name
	 *            权限名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 获取权限描述
	 *
	 * @return description - 权限描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置权限描述
	 *
	 * @param description
	 *            权限描述
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * 获取权限访问路径
	 *
	 * @return url - 权限访问路径
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置权限访问路径
	 *
	 * @param url
	 *            权限访问路径
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * 获取父级权限id
	 *
	 * @return parent_id - 父级权限id
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置父级权限id
	 *
	 * @param parentId
	 *            父级权限id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取状态：1有效；0删除
	 *
	 * @return status - 状态：1有效；0删除
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态：1有效；0删除
	 *
	 * @param status
	 *            状态：1有效；0删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

}