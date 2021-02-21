package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * OKR项目管理对象 okr_project
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
public class OkrProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String code;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 公司主体 */
    @Excel(name = "公司主体")
    private String company;

    /** 项目阶段 */
    private String stage;

    /** 产品经理 */
    private Long productManagerId;

    /** 项目经理 */
    private Long projectManagerId;

    /** 数据状态（0正常、非正常） */
    @Excel(name = "数据状态", readConverterExp = "0=正常、非正常")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setStage(String stage) 
    {
        this.stage = stage;
    }

    public String getStage() 
    {
        return stage;
    }
    public void setProductManagerId(Long productManagerId) 
    {
        this.productManagerId = productManagerId;
    }

    public Long getProductManagerId() 
    {
        return productManagerId;
    }
    public void setProjectManagerId(Long projectManagerId) 
    {
        this.projectManagerId = projectManagerId;
    }

    public Long getProjectManagerId() 
    {
        return projectManagerId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("company", getCompany())
            .append("stage", getStage())
            .append("productManagerId", getProductManagerId())
            .append("projectManagerId", getProjectManagerId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
