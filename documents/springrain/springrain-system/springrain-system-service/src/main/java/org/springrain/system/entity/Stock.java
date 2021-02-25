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
 * @version 2019-08-19 15:36:24
 */
@Table(name = "t_stock")
public class Stock extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
	/*
	public static final String TABLE_ALIAS = "库存";
	public static final String ALIAS_ID = " ";
	public static final String ALIAS_GOOD_ID = "商品id";
	public static final String ALIAS_NUM = "数量";
    */
    //date formats

    //columns START

    //
    private String id;

    // 商品id
    private Integer good_id;

    // 数量
    private Integer num;
    //columns END 数据库字段结束

    //concstructor
    public Stock() {
    }


    //get and set

    /**
     *
     */
    @Id
    @WhereSQL(sql = "id=:Stock_id")
    public String getId() {
        return this.id;
    }

    /**
     * @param value
     */
    public void setId(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.trim();
        }
        this.id = value;
    }

    /**
     * 商品id
     */
    @WhereSQL(sql = "good_id=:Stock_good_id")
    public Integer getGood_id() {
        return this.good_id;
    }

    /**
     * 商品id
     *
     * @param value
     */
    public void setGood_id(Integer value) {
        this.good_id = value;
    }

    /**
     * 数量
     */
    @WhereSQL(sql = "num=:Stock_num")
    public Integer getNum() {
        return this.num;
    }

    /**
     * 数量
     *
     * @param value
     */
    public void setNum(Integer value) {
        this.num = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(" [").append(getId()).append("],")
                .append("商品id[").append(getGood_id()).append("],")
                .append("数量[").append(getNum()).append("],")
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

        if (obj == null) {
            return false;
        }

        if (obj instanceof Stock == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Stock other = (Stock) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
