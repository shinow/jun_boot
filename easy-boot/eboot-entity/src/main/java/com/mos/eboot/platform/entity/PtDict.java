package com.mos.eboot.platform.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小尘哥
 * @since 2018-05-06
 */
@ApiModel(value = "字典对象")
@TableName("pt_dict")
public class PtDict extends Model<PtDict> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id",allowEmptyValue = true)
	private String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称",required = true)
	@NotBlank(message = "名称不能为空")
	private String name;
    /**
     * 类型code
     */
    @ApiModelProperty(value = "类型code",required = true)
	@TableField("type_code")
	@Length(min = 1, max = 64, message = "类型长度不少于1个字符，不超过64个字符")
	private String typeCode;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号",required = true)
	@TableField("sort_no")
	private Integer sortNo;
    /**
     * 是否可编辑
     */
    @ApiModelProperty(value = "是否可编辑",required = false,allowEmptyValue = true)
	@NotBlank(message = "是否可编辑不能为空")
	@TableField("is_edit")
	private String isEdit;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除",required = false,allowEmptyValue = true)
    @TableLogic
	@TableField("is_del")
	private String isDel;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
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
		return "PtDict{" +
			", id=" + id +
			", name=" + name +
			", typeCode=" + typeCode +
			", sortNo=" + sortNo +
			", isEdit=" + isEdit +
			", isDel=" + isDel +
			"}";
	}
}
