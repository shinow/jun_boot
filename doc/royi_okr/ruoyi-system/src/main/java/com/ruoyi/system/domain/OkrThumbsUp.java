package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 点赞对象 okr_thumbs_up
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-19
 */
public class OkrThumbsUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 点赞类型（O,KR,评论，等等） */
    @Excel(name = "点赞类型", readConverterExp = "O=,KR,评论，等等")
    private Long type;

    /** 点赞对象ID（O,KR,评论，对应的主键ID） */
    @Excel(name = "点赞对象ID", readConverterExp = "O=,KR,评论，对应的主键ID")
    private Integer objectId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 点赞IP */
    @Excel(name = "点赞IP")
    private String userIp;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 添加时间 */
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setObjectId(Integer objectId)
    {
        this.objectId = objectId;
    }

    public Integer getObjectId()
    {
        return objectId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserIp(String userIp)
    {
        this.userIp = userIp;
    }

    public String getUserIp()
    {
        return userIp;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("objectId", getObjectId())
                .append("userId", getUserId())
                .append("userIp", getUserIp())
                .append("status", getStatus())
                .append("addTime", getAddTime())
                .toString();
    }
}