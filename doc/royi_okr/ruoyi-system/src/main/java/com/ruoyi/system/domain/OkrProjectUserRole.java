package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目权限对象 okr_project_user_role
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-30
 */
public class OkrProjectUserRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户标识 */
    private Long userId;

    /** 项目标识 */
    private Long projectId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("projectId", getProjectId())
            .toString();
    }
}
