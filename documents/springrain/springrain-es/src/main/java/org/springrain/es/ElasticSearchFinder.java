package org.springrain.es;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder.ScriptSortType;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springrain.frame.util.DateUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ElasticSearch查询工具类
 *
 * @author caomei
 */
public class ElasticSearchFinder {

    // 搜索的关键字
    private String keyword = null;
    private List<String> fieldList;
    private List<String> findFields;
    private List<SortField> sortFieldList = new ArrayList<>();
    private String[] fields = null;
    private List<ScriptSortBuilder> scriptSortBuilderList = new ArrayList<>();

    private List<QueryBuilder> listMustCondition = new ArrayList<>();
    private List<QueryBuilder> listShouldCondition = new ArrayList<>();



    private   String DATETIME_FORMAT_ZONE = "yyyy-MM-dd HH:mm:ssZ";

    public ElasticSearchFinder() {
    }

    public ElasticSearchFinder(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 字段值等于某个值
     *
     * @param fieldName 字段名称
     * @param fieldType 字段类型
     * @param value  值
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereCondition(String fieldName, Class fieldType, Object value) throws Exception {
        return addWhereConditionMust(fieldName, fieldType, value);
    }

    /**
     * 字段值在某个值中间
     *
     * @param fieldName
     * @param minValue
     * @param maxValue
     * @return
     * @throws Exception
     */
    /*
     * public List<QueryBuilder> addWhereCondition(String fieldName, Class
     * fieldType, Object minValue, Object maxValue) throws Exception { return
     * addWhereCondition(fieldName, fieldType, minValue, maxValue, true, true,
     * Occur.MUST); }
     */

    /**
     * 添加排序的字段,默认正序
     *
     * @param fieldName
     * @param fieldType
     * @return
     * @throws Exception
     */
    public List<SortField> addSortField(String fieldName, Class fieldType) throws Exception {
        return addSortField(fieldName, fieldType, false);
    }

    public List<String> addFieldList(String field) throws Exception {
        if (this.fieldList == null) {
            fieldList = new ArrayList<>();
        }
        fieldList.add(field);
        return fieldList;
    }

    /**
     * 添加排序的字段
     *
     * @param fieldName
     * @param fieldType
     * @param relevance
     * @return
     * @throws Exception
     */

    public List<SortField> addSortField(String fieldName, Class fieldType, boolean relevance) throws Exception {
        if (StringUtils.isEmpty(fieldName) || fieldType == null) {
            return null;
        }

        // 排序可支持string，但必须是keyword的
        // doc_value的才可以排序和进行聚合
        // 因没有用到注解相关，此处也不再强制 判断，使用的时候注意一些
        Type type = null;

        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            type = Type.INT;
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            type = Type.LONG;
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            type = Type.FLOAT;
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            type = Type.DOUBLE;
        } else if (Date.class == fieldType) {// 日期
            type = Type.LONG;
        } else if (String.class == fieldType) {// 日期
            type = Type.STRING;
        }
        SortField sf = new SortField(fieldName, type, relevance);
        sortFieldList.add(sf);
        return sortFieldList;
    }

    /**
     * 添加自定义排序的字段
     *
     * @param inlineScript
     * @param fieldType
     * @param relevance
     * @return
     * @throws Exception
     */

    public List<ScriptSortBuilder> addScriptSortBuilder(String inlineScript, Map<String, Object> params, Class fieldType, boolean relevance) throws Exception {
        if (StringUtils.isEmpty(inlineScript) || fieldType == null) {
            return null;
        }

        // 排序可支持string，但必须是keyword的
        // doc_value的才可以排序和进行聚合
        // 因没有用到注解相关，此处也不再强制 判断，使用的时候注意一些
        ScriptSortType type = null;

        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            type = ScriptSortType.NUMBER;

        } else if (String.class == fieldType) {// 字符串
            type = ScriptSortType.STRING;
        }
        Script script = null;
        if (params == null || params.isEmpty()) {
            script = new Script(inlineScript);
        } else {
            script = new Script(ScriptType.INLINE, "painless", inlineScript, params);
        }
        ScriptSortBuilder scriptSortBuilder = SortBuilders.scriptSort(script, type).order(relevance ? SortOrder.DESC : SortOrder.ASC);
        scriptSortBuilderList.add(scriptSortBuilder);
        return scriptSortBuilderList;
    }

    /**
     * 字段值等于某个值
     *
     * @param fieldName
     * @param fieldType
     * @param value
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereConditionMust(String fieldName, Class fieldType, Object value)
            throws Exception {

        if (fieldType == null || StringUtils.isEmpty(fieldName) || value == null) {
            return listMustCondition;
        }
        if (value instanceof String) {
            String val = (String) value;
            if (StringUtils.isEmpty(val)) {
                return listMustCondition;
            }

        }
        QueryBuilder query;
        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Integer(value.toString()));
        } else if (BigInteger.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new BigInteger(value.toString()));
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Long(value.toString()));
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Float(value.toString()));
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Double(value.toString()));
        } else if (Date.class == fieldType) {// 日期
            query = QueryBuilders.termQuery(fieldName, Long.valueOf(((Date) value).getTime()));
        } else if (List.class == fieldType) {
            query = QueryBuilders.termsQuery(fieldName, (List) value);
        } else {
            // text 和 keyword
            query = QueryBuilders.matchQuery(fieldName, value.toString());
        }
        listMustCondition.add(query);
        return listMustCondition;
    }

    /**
     * 字段值等于某个值
     *
     * @param fieldName
     * @param fieldType
     * @param value
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereConditionShould(String fieldName, Class fieldType, Object value)
            throws Exception {

        if (fieldType == null || StringUtils.isEmpty(fieldName) || value == null) {
            return getListShouldCondition();
        }
        if (value instanceof String) {
            String val = (String) value;
            if (StringUtils.isEmpty(val)) {
                return getListShouldCondition();
            }

        }
        QueryBuilder query;
        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Integer(value.toString()));
        } else if (BigInteger.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new BigInteger(value.toString()));
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Long(value.toString()));
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Float(value.toString()));
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            query = QueryBuilders.termQuery(fieldName, new Double(value.toString()));
        } else if (Date.class == fieldType) {// 日期
            query = QueryBuilders.termQuery(fieldName, Long.valueOf(((Date) value).getTime()));
        } else if (List.class == fieldType) {
            query = QueryBuilders.termsQuery(fieldName, (List) value);
        } else {
            //text 和 keyword
            query = QueryBuilders.matchQuery(fieldName, value.toString());
        }
        getListShouldCondition().add(query);
        return getListShouldCondition();
    }

    /**
     * 字段值在某个值中间
     *
     * @param fieldName
     * @param fieldType
     * @param minValue
     * @param maxValue
     * @param includeLower
     * @param includeUpper
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereCondition(String fieldName, Class fieldType, Object minValue, Object maxValue,
                                                boolean includeLower, boolean includeUpper) throws Exception {

        if (fieldType == null || StringUtils.isEmpty(fieldName) || minValue == null || maxValue == null) {
            return listMustCondition;
        }
        QueryBuilder query = null;

        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Integer.valueOf(minValue.toString()))
                    .lt(Integer.valueOf(maxValue.toString())).includeLower(includeLower).includeUpper(includeUpper);
        } else if (BigInteger.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(new BigInteger(minValue.toString()))
                    .lt(new BigInteger(maxValue.toString())).includeLower(includeLower).includeUpper(includeUpper);
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Long.valueOf(minValue.toString()))
                    .lt(Long.valueOf(maxValue.toString())).includeLower(includeLower).includeUpper(includeUpper);
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Float.valueOf(minValue.toString()))
                    .lt(Float.valueOf(maxValue.toString())).includeLower(includeLower).includeUpper(includeUpper);
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Double.valueOf(minValue.toString()))
                    .lt(Double.valueOf(maxValue.toString())).includeLower(includeLower).includeUpper(includeUpper);
        } else if (Date.class == fieldType) {// 日期
            query = QueryBuilders.rangeQuery(fieldName).format(DATETIME_FORMAT_ZONE)
                    .gt(DateUtils.formatDate( DATETIME_FORMAT_ZONE,(Date) minValue)).lt(DateUtils.formatDate(DATETIME_FORMAT_ZONE,(Date) maxValue))
                    .includeLower(includeLower).includeUpper(includeUpper);
            ;
            //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(minValue));
        } else {
            // 字符串 按道理说 其实是没有 范围一说的，但考虑到拼音和英语 先行加上
            query = QueryBuilders.rangeQuery(fieldName).gt(minValue.toString()).lt(Double.valueOf(maxValue.toString()))
                    .includeLower(includeLower).includeUpper(includeUpper);
        }
        this.listMustCondition.add(query);
        return listMustCondition;
    }

    /**
     * 字段值大于某个值
     *
     * @param fieldName
     * @param fieldType
     * @param minValue
     * @param includeLower
     * @param occur
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereConditionGT(String fieldName, Class fieldType, Object minValue,
                                                  boolean includeLower, Occur occur) throws Exception {

        if (fieldType == null || StringUtils.isEmpty(fieldName) || minValue == null) {
            return listMustCondition;
        }
        QueryBuilder query = null;

        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Integer.valueOf(minValue.toString()))
                    .includeLower(includeLower);
        } else if (BigInteger.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(new BigInteger(minValue.toString()))
                    .includeLower(includeLower);
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Long.valueOf(minValue.toString()))
                    .includeLower(includeLower);
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Float.valueOf(minValue.toString()))
                    .includeLower(includeLower);
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).gt(Double.valueOf(minValue.toString()))
                    .includeLower(includeLower);
        } else if (Date.class == fieldType) {// 日期
            query = QueryBuilders.rangeQuery(fieldName).format(DATETIME_FORMAT_ZONE)
                    .gt(DateUtils.formatDate(DATETIME_FORMAT_ZONE,(Date) minValue)).includeLower(includeLower);
            //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(minValue));
        } else {
            // 字符串 按道理说 其实是没有 范围一说的，但考虑到拼音和英语 先行加上
            query = QueryBuilders.rangeQuery(fieldName).gt(minValue.toString()).includeLower(includeLower);
        }
        this.listMustCondition.add(query);
        return listMustCondition;
    }

    /**
     * 字段值小于某个值
     *
     * @param fieldName
     * @param fieldType
     * @param minValue
     * @param includeUpper
     * @param includeUpper
     * @param occur
     * @return
     * @throws Exception
     */
    public List<QueryBuilder> addWhereConditionLT(String fieldName, Class fieldType, Object minValue,
                                                  boolean includeUpper, Occur occur) throws Exception {

        if (fieldType == null || StringUtils.isEmpty(fieldName) || minValue == null) {
            return listMustCondition;
        }
        QueryBuilder query = null;

        if (Integer.class == fieldType || int.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).lt(Integer.valueOf(minValue.toString()))
                    .includeUpper(includeUpper);
        } else if (BigInteger.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).lt(new BigInteger(minValue.toString()))
                    .includeUpper(includeUpper);
        } else if (Long.class == fieldType || long.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).lt(Long.valueOf(minValue.toString()))
                    .includeUpper(includeUpper);
        } else if (Float.class == fieldType || float.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).lt(Float.valueOf(minValue.toString()))
                    .includeUpper(includeUpper);
        } else if (Double.class == fieldType || double.class == fieldType) {// 数字
            query = QueryBuilders.rangeQuery(fieldName).lt(Double.valueOf(minValue.toString()))
                    .includeUpper(includeUpper);
        } else if (Date.class == fieldType) {// 日期
            query = QueryBuilders.rangeQuery(fieldName).format(DATETIME_FORMAT_ZONE)
                    .lt(DateUtils.formatDate(DATETIME_FORMAT_ZONE,(Date) minValue)).includeUpper(includeUpper);
            // System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(minValue));
        } else {
            // 字符串 按道理说 其实是没有 范围一说的，但考虑到拼音和英语 先行加上
            query = QueryBuilders.rangeQuery(fieldName).lt(minValue.toString()).includeUpper(includeUpper);
        }
        this.listMustCondition.add(query);
        return listMustCondition;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public String[] getFields() {
        if (fields == null && CollectionUtils.isNotEmpty(fieldList)) {
            fields = fieldList.toArray(new String[fieldList.size()]);
        }

        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public List<SortField> getSortFieldList() {
        return sortFieldList;
    }

    public void setSortFieldList(List<SortField> sortFieldList) {
        this.sortFieldList = sortFieldList;
    }

    public List<QueryBuilder> getListMustCondition() {
        return listMustCondition;
    }

    public void setListMustCondition(List<QueryBuilder> listMustCondition) {
        this.listMustCondition = listMustCondition;
    }

    public List<String> getFindFields() {
        return findFields;
    }

    public void setFindFields(List<String> findFields) {
        this.findFields = findFields;
    }

    public List<QueryBuilder> getListShouldCondition() {
        return listShouldCondition;
    }

    public void setListShouldCondition(List<QueryBuilder> listShouldCondition) {
        this.listShouldCondition = listShouldCondition;
    }

    public List<ScriptSortBuilder> getScriptSortBuilderList() {
        return scriptSortBuilderList;
    }

    public void setScriptSortBuilderList(List<ScriptSortBuilder> scriptSortBuilderList) {
        this.scriptSortBuilderList = scriptSortBuilderList;
    }


}
