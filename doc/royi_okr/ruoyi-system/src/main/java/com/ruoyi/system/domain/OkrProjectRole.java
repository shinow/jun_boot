package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目OKR关系对象 okr_project_role
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
public class OkrProjectRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String projectCode;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private Long userId;

    /** OKR主键标识 */
    @Excel(name = "OKR主键标识")
    private Long okrId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOkrId(Long okrId) 
    {
        this.okrId = okrId;
    }

    public Long getOkrId() 
    {
        return okrId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectCode", getProjectCode())
            .append("userId", getUserId())
            .append("okrId", getOkrId())
            .toString();
    }
}
