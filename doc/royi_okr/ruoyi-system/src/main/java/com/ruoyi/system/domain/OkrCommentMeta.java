package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  评论附加
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
public class OkrCommentMeta extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer commentId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String metaKey;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String metaValue;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommentId(Integer commentId)
    {
        this.commentId = commentId;
    }

    public Integer getCommentId()
    {
        return commentId;
    }
    public void setMetaKey(String metaKey) 
    {
        this.metaKey = metaKey;
    }

    public String getMetaKey() 
    {
        return metaKey;
    }
    public void setMetaValue(String metaValue) 
    {
        this.metaValue = metaValue;
    }

    public String getMetaValue() 
    {
        return metaValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("commentId", getCommentId())
            .append("metaKey", getMetaKey())
            .append("metaValue", getMetaValue())
            .toString();
    }
}
