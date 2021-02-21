package com.ruoyi.system.domain.resultMapper;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * OKR信息对象 SynergyOkrInfo
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
public class SynergyOkrInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 数据标识 */
    @Excel(name = "数据标识")
    private String rowId;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long sortId;

    /** 编号 */
    @Excel(name = "编号")
    private String rowCode;

    /** 内容描述 */
    @Excel(name = "内容描述")
    private String content;

    /** 类型（0为目标、1为KR） */
    @Excel(name = "类型", readConverterExp = "0=为目标、1为KR")
    private Long rowType;

    /** 信心指数 */
    @Excel(name = "信心指数")
    private String confidenceStar;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private Long userId;

    /** 目标评分 */
    @Excel(name = "目标评分")
    private String scoreStar;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String parentId;

    /** 周期ID */
    @Excel(name = "周期ID")
    private Long cycleId;

    /** 目标进度 */
    @Excel(name = "目标进度")
    private String process;

    /** 状态（默认为0、删除为1） */
    @Excel(name = "状态", readConverterExp = "默=认为0、删除为1")
    private Long status;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    private String cycleType;

    private String loginName;

    private String userName;

    private String deptName;

    private boolean isAlign;

    public boolean isAlign() {
        return isAlign;
    }

    public void setAlign(boolean align) {
        isAlign = align;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 用于新增提交Json字符串
     */
    private String cycleItem;

    /**
     * 用于新增提交Json字符串
     */
    private String synergyUsers;

    public String getSynergyUsers() {
        return synergyUsers;
    }

    public void setSynergyUsers(String synergyUsers) {
        this.synergyUsers = synergyUsers;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getCycleItem() {
        return cycleItem;
    }

    public void setCycleItem(String cycleItem) {
        this.cycleItem = cycleItem;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSortId(Long sortId) 
    {
        this.sortId = sortId;
    }

    public Long getSortId() 
    {
        return sortId;
    }
    public void setRowCode(String rowCode) 
    {
        this.rowCode = rowCode;
    }

    public String getRowCode() 
    {
        return rowCode;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setRowType(Long rowType) 
    {
        this.rowType = rowType;
    }

    public Long getRowType() 
    {
        return rowType;
    }
    public void setConfidenceStar(String confidenceStar) 
    {
        this.confidenceStar = confidenceStar;
    }

    public String getConfidenceStar() 
    {
        return confidenceStar;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setScoreStar(String scoreStar) 
    {
        this.scoreStar = scoreStar;
    }

    public String getScoreStar() 
    {
        return scoreStar;
    }
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getParentId()
    {
        return parentId;
    }
    public void setCycleId(Long cycleId) 
    {
        this.cycleId = cycleId;
    }

    public Long getCycleId() 
    {
        return cycleId;
    }
    public void setProcess(String process) 
    {
        this.process = process;
    }

    public String getProcess() 
    {
        return process;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setPriority(String priority) 
    {
        this.priority = priority;
    }

    public String getPriority() 
    {
        return priority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sortId", getSortId())
            .append("rowCode", getRowCode())
            .append("content", getContent())
            .append("rowType", getRowType())
            .append("confidenceStar", getConfidenceStar())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("scoreStar", getScoreStar())
            .append("parentId", getParentId())
            .append("cycleId", getCycleId())
            .append("process", getProcess())
            .append("status", getStatus())
            .append("priority", getPriority())
            .toString();
    }
}
