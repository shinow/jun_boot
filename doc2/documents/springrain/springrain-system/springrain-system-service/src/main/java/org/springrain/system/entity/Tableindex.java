package org.springrain.system.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2016-11-10 14:16:38
 */
@Table(name = "t_tableindex")
public class Tableindex extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // alias
    /*
     * public static final String TABLE_ALIAS = "Tableindex"; public static final
     * String ALIAS_ID = "编号"; public static final String ALIAS_TABLENAME = "表名";
     * public static final String ALIAS_MAXINDEX = "表记录最大的行,一直累加"; public static
     * final String ALIAS_PREFIX = "前缀 单个字母加 _";
     */
    // date formats

    // columns START
    /**
     * 表名
     */
    private java.lang.String id;

    /**
     * 表记录最大的行,一直累加
     */
    private java.lang.Integer maxIndex;
    /**
     * 前缀 单个字母加 _
     */
    private java.lang.String prefix;
    /**
     * bak1
     */
    private java.lang.String bak1;
    /**
     * bak2
     */
    private java.lang.String bak2;
    /**
     * bak3
     */
    private java.lang.String bak3;
    /**
     * bak4
     */
    private java.lang.String bak4;
    /**
     * bak5
     */
    private java.lang.String bak5;
    // columns END 数据库字段结束

    // concstructor

    public Tableindex() {
    }

    public Tableindex(java.lang.String id) {
        this.id = id;
    }

    @Id
    @WhereSQL(sql = "id=:Tableindex_id")
    public java.lang.String getId() {
        return this.id;
    }

    // get and set
    public void setId(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    @WhereSQL(sql = "maxIndex=:Tableindex_maxIndex")
    public java.lang.Integer getMaxIndex() {
        return this.maxIndex;
    }

    public void setMaxIndex(java.lang.Integer value) {
        this.maxIndex = value;
    }

    @WhereSQL(sql = "prefix=:Tableindex_prefix")
    public java.lang.String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.prefix = value;
    }

    /**
     * bak1
     */
    @WhereSQL(sql = "bak1=:Tableindex_bak1")
    public java.lang.String getBak1() {
        return this.bak1;
    }

    public void setBak1(java.lang.String bak1) {
        this.bak1 = bak1;
    }

    /**
     * bak2
     */
    @WhereSQL(sql = "bak2=:Tableindex_bak2")
    public java.lang.String getBak2() {
        return this.bak2;
    }

    /**
     * bak2
     */
    public void setBak2(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak2 = value;
    }

    /**
     * bak3
     */
    @WhereSQL(sql = "bak3=:Tableindex_bak3")
    public java.lang.String getBak3() {
        return this.bak3;
    }

    /**
     * bak3
     */
    public void setBak3(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak3 = value;
    }

    /**
     * bak4
     */
    @WhereSQL(sql = "bak4=:Tableindex_bak4")
    public java.lang.String getBak4() {
        return this.bak4;
    }

    /**
     * bak4
     */
    public void setBak4(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak4 = value;
    }

    /**
     * bak5
     */
    @WhereSQL(sql = "bak5=:Tableindex_bak5")
    public java.lang.String getBak5() {
        return this.bak5;
    }

    /**
     * bak5
     */
    public void setBak5(java.lang.String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.bak5 = value;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("表名[").append(getId()).append("],").append("表记录最大的行,一直累加[")
                .append(getMaxIndex()).append("],").append("前缀 单个字母加 _[").append(getPrefix()).append("],")
                .append("bak1[").append(getBak1()).append("],").append("bak2[").append(getBak2()).append("],")
                .append("bak3[").append(getBak3()).append("],").append("bak4[").append(getBak4()).append("],")
                .append("bak5[").append(getBak5()).append("],").toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tableindex == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Tableindex other = (Tableindex) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

}
