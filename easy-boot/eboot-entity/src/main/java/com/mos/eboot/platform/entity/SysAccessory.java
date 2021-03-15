package com.mos.eboot.platform.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author 小尘哥
 * @since 2018-03-12
 */
@TableName("sys_accessory")
public class SysAccessory extends Model<SysAccessory> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 原文件名

     */
	@TableField("original_name")
	private String originalName;
    /**
     * 文件大小（单位：字节）
     */
	@TableField("file_size")
	private Integer fileSize;
    /**
     * 存储路径
     */
	private String path;
    /**
     * 分类
     */
	private String category;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 是否删除（1：是，0：否）
     */
	@TableField("is_del")
	private String isDel;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysAccessory{" +
			", id=" + id +
			", originalName=" + originalName +
			", fileSize=" + fileSize +
			", path=" + path +
			", category=" + category +
			", createTime=" + createTime +
			", createUser=" + createUser +
			", isDel=" + isDel +
			"}";
	}
}
