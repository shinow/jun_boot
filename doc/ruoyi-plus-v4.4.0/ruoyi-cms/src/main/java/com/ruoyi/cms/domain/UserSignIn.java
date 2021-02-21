package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户签到对象 cms_user_sign_in
 * 
 * @author markbro
 * @date 2020-02-03
 */
public class UserSignIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 签到月份yyy-MM */
    @Excel(name = "签到月份yyy-MM")
    private String month;

    /** 签到时间 */
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 累计签到次数 */
    @Excel(name = "累计签到次数")
    private Long totalCount;

    /** 本月签到次数 */
    @Excel(name = "本月签到次数")
    private Long monthCount;

    /** 持续签到次数 */
    @Excel(name = "持续签到次数")
    private Long constantCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setMonth(String month) 
    {
        this.month = month;
    }

    public String getMonth() 
    {
        return month;
    }
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }
    public void setTotalCount(Long totalCount) 
    {
        this.totalCount = totalCount;
    }

    public Long getTotalCount() 
    {
        return totalCount;
    }
    public void setMonthCount(Long monthCount) 
    {
        this.monthCount = monthCount;
    }

    public Long getMonthCount() 
    {
        return monthCount;
    }
    public void setConstantCount(Long constantCount) 
    {
        this.constantCount = constantCount;
    }

    public Long getConstantCount() 
    {
        return constantCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("month", getMonth())
            .append("signTime", getSignTime())
            .append("totalCount", getTotalCount())
            .append("monthCount", getMonthCount())
            .append("constantCount", getConstantCount())
            .toString();
    }
}
