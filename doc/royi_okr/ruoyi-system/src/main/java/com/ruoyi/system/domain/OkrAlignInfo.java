package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * OKR对齐关系对象 okr_align_info
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
public class OkrAlignInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** OKR主键 */
    @Excel(name = "OKR主键")
    private Long okrId;

    /** 父OKR主键 */
    @Excel(name = "父OKR主键")
    private Long parentId;

    /** KR集合 */
    @Excel(name = "KR集合")
    private String okrKeys;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOkrId(Long okrId) 
    {
        this.okrId = okrId;
    }

    public Long getOkrId() 
    {
        return okrId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setOkrKeys(String okrKeys) 
    {
        this.okrKeys = okrKeys;
    }

    public String getOkrKeys() 
    {
        return okrKeys;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("okrId", getOkrId())
            .append("parentId", getParentId())
            .append("okrKeys", getOkrKeys())
            .toString();
    }
}
