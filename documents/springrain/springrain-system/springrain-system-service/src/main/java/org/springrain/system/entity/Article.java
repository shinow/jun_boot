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
 * @version 2019-07-24 14:55:40
 */
@Table(name = "t_article")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "内容表";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_MINTITLE = "标题";
	public static final String ALIAS_SUMMARY = "摘要";
	public static final String ALIAS_KEYWORDS = "关键字";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_LOOKCOUNT = "打开次数";
	public static final String ALIAS_CREATEPERSON = "创建人";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_STATUS = "状态  0未审核  1审核通过";
    */
    //date formats
    //public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;

    //columns START

    // ID
    private String id;

    // 标题
    private String mintitle;

    // 摘要
    private String summary;

    // 关键字
    private String keywords;

    // 描述
    private String description;

    // 打开次数
    private Integer lookcount;

    // 创建人
    private String createPerson;

    // 创建时间
    private java.util.Date createDate;

    // 状态  0未审核  1审核通过
    private Integer status;
    //columns END 数据库字段结束

    //concstructor
    public Article() {
    }


    //get and set

    /**
     * ID
     */
    @Id
    @WhereSQL(sql = "id=:Article_id")
    public String getId() {
        return this.id;
    }

    /**
     * ID
     *
     * @param value
     */
    public void setId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 标题
     */
    @WhereSQL(sql = "mintitle like :%Article_mintitle%")
    public String getMintitle() {
        return this.mintitle;
    }

    /**
     * 标题
     *
     * @param value
     */
    public void setMintitle(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.mintitle = value;
    }

    /**
     * 摘要
     */
    @WhereSQL(sql = "summary=:Article_summary")
    public String getSummary() {
        return this.summary;
    }

    /**
     * 摘要
     *
     * @param value
     */
    public void setSummary(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.summary = value;
    }

    /**
     * 关键字
     */
    @WhereSQL(sql = "keywords=:Article_keywords")
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * 关键字
     *
     * @param value
     */
    public void setKeywords(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.keywords = value;
    }

    /**
     * 描述
     */
    @WhereSQL(sql = "description=:Article_description")
    public String getDescription() {
        return this.description;
    }

    /**
     * 描述
     *
     * @param value
     */
    public void setDescription(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.description = value;
    }

    /**
     * 打开次数
     */
    @WhereSQL(sql = "lookcount=:Article_lookcount")
    public Integer getLookcount() {
        return this.lookcount;
    }

    /**
     * 打开次数
     *
     * @param value
     */
    public void setLookcount(Integer value) {
        this.lookcount = value;
    }

    /**
     * 创建人
     */
    @WhereSQL(sql = "createPerson=:Article_createPerson")
    public String getCreatePerson() {
        return this.createPerson;
    }

    /**
     * 创建人
     *
     * @param value
     */
    public void setCreatePerson(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.createPerson = value;
    }
		/*
	public String getcreateDateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreateDate());
	}
	public void setcreateDateString(String value) throws ParseException{
		setcreateDate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/

    /**
     * 创建时间
     */
    @WhereSQL(sql = "createDate=:Article_createDate")
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 创建时间
     *
     * @param value
     */
    public void setCreateDate(java.util.Date value) {
        this.createDate = value;
    }

    /**
     * 状态  0未审核  1审核通过
     */
    @WhereSQL(sql = "status=:Article_status")
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 状态  0未审核  1审核通过
     *
     * @param value
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ID[").append(getId()).append("],")
                .append("标题[").append(getMintitle()).append("],")
                .append("摘要[").append(getSummary()).append("],")
                .append("关键字[").append(getKeywords()).append("],")
                .append("描述[").append(getDescription()).append("],")
                .append("打开次数[").append(getLookcount()).append("],")
                .append("创建人[").append(getCreatePerson()).append("],")
                .append("创建时间[").append(getCreateDate()).append("],")
                .append("状态  0未审核  1审核通过[").append(getStatus()).append("],")
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Article other = (Article) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
