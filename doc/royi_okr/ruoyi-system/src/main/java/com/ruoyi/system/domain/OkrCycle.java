package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * OKR周期对象 okr_cycle
 * 
 * @author ruoyi
 * @date 2020-04-28
 */
public class OkrCycle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 周期名称 */
    @Excel(name = "周期名称")
    private String cycleName;

    /** 起始时间 */
    @Excel(name = "起始时间")
    private String beginTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String endTime;

    /** 周期类型（年度1 季度0） */
    @Excel(name = "周期类型", readConverterExp = "年=度1,季=度0")
    private Long cycleType;

    /** 状态（0进行中、1已失效） */
    @Excel(name = "状态", readConverterExp = "0=进行中、1已失效")
    private Long status;

    /** 是否删除（0默认、1删除） */
    @Excel(name = "是否删除", readConverterExp = "0=默认、1删除")
    private Long isDel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCycleName(String cycleName) 
    {
        this.cycleName = cycleName;
    }

    public String getCycleName() 
    {
        return cycleName;
    }
    public void setBeginTime(String beginTime) 
    {
        this.beginTime = beginTime;
    }

    public String getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setCycleType(Long cycleType) 
    {
        this.cycleType = cycleType;
    }

    public Long getCycleType() 
    {
        return cycleType;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIsDel(Long isDel) 
    {
        this.isDel = isDel;
    }

    public Long getIsDel() 
    {
        return isDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cycleName", getCycleName())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("cycleType", getCycleType())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("isDel", getIsDel())
            .toString();
    }
}
