package com.mos.eboot.platform.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
@ApiModel(value = "菜单对象")
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {
    private static final long serialVersionUID = 1L;

    public static final String MENU_TYPE_RESOURCE = "1";

    public static final String MENU_TYPE_PERMIT = "2";

    public static final String ROOT_ID = "1";

    public static final String ROOT_Text = "根";

    @ApiModelProperty(value="主键id",allowEmptyValue = true)
    private String id;

    @ApiModelProperty(value="菜单名称",required = true)
    private String name;

    @ApiModelProperty(value="path")
    private String path;

    @ApiModelProperty(value="icon")
    private String icon;

    @ApiModelProperty(value="父节点id",required = true)
    @TableField("parent_id")
    private String parentId;
    /**
     * 1:资源菜单，2：权限
     */
    @ApiModelProperty(value="1:资源菜单，2：权限",required = true)
    @TableField("menu_type")
    private String menuType;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user")
    private String createUser;

    @TableField("modify_time")
    private Date modifyTime;

    @TableField("modify_user")
    private String modifyUser;

    @ApiModelProperty(value="排序号",required = true)
    @TableField("sort_no")
    private int sortNo;

    private String code;

	@TableField(exist = false)
    private String menuTypeText;

    @TableField(exist = false)
    private String parentNodeName;

	@TableField(exist = false)
	private SysMenu parentNode;


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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMenuTypeText() {
		return menuTypeText;
	}

	public void setMenuTypeText(String menuTypeText) {
		this.menuTypeText = menuTypeText;
	}

	public String getParentNodeName() {
		return parentNodeName;
	}

	public SysMenu getParentNode() {
		return parentNode;
	}

	public void setParentNode(SysMenu parentNode) {
		this.parentNode = parentNode;
	}

	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;


	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                ", id=" + id +
                ", name=" + name +
                ", path=" + path +
                ", icon=" + icon +
                ", parentId=" + parentId +
                ", menuType=" + menuType +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", modifyTime=" + modifyTime +
                ", modifyUser=" + modifyUser +
                ", sortNo=" + sortNo +
                ", code=" + code +
                "}";
    }
}
