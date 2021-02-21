package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * OKR附加属性表对象 okr_attach
 * 
 * @author okr
 * @date 2020-06-19
 */
public class OkrAttach extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** OKR标识 */
    @Excel(name = "OKR标识")
    private String okrId;

    /** 附加字段类型 */
    @Excel(name = "附加字段类型")
    private String keyName;

    /** 附加字段值 */
    @Excel(name = "附加字段值")
    private String keyValue;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOkrId(String okrId) 
    {
        this.okrId = okrId;
    }

    public String getOkrId() 
    {
        return okrId;
    }
    public void setKeyName(String keyName) 
    {
        this.keyName = keyName;
    }

    public String getKeyName() 
    {
        return keyName;
    }
    public void setKeyValue(String keyValue) 
    {
        this.keyValue = keyValue;
    }

    public String getKeyValue() 
    {
        return keyValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("okrId", getOkrId())
            .append("keyName", getKeyName())
            .append("keyValue", getKeyValue())
            .toString();
    }
}
