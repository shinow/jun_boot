package org.springrain.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.SortField;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.*;
import org.springrain.lucene.IK.lucene.IKAnalyzer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ElasticSearch 工具类
 *
 * @author caomei
 */
public class ElasticSearchOperation {
    public String IK_ANALYER_TOKENIZER = "ik_max_word";
    public String ANALYER_NAME = "ik";
    public boolean DEBUGGER = false;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private RestHighLevelClient client = null;
    private ObjectMapper mapper = null;

    // private final String type = "_doc";


    // 分词器,使用IK的精确匹配
    private Analyzer analyzer = new IKAnalyzer();
    // private  Analyzer analyzer = new SmartChineseAnalyzer();

    private String DATETIME_FORMAT_ZONE = "yyyy-MM-dd HH:mm:ssZ";
    public ElasticSearchOperation() {
        mapper = new FrameObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(DATETIME_FORMAT_ZONE));
    }


    /**
     * 根据关键词查询某个实体类的索引
     *
     * @param clazz
     * @param page
     * @param searchkeyword
     * @return
     * @throws Exception
     */
    public List searchDocument(String rootdir, Class clazz, Page page, String searchkeyword) throws Exception {
        ElasticSearchFinder luceneFinder = new ElasticSearchFinder(searchkeyword);
        return searchDocument(rootdir, clazz, page, luceneFinder);
    }

    /**
     * 根据LuceneSearchClause查询关键字,可以添加where条件和数值类型排序,最常用的接口
     *
     * @param rootdir
     * @param clazz
     * @param page
     * @param luceneFinder
     * @return
     * @throws Exception
     */
    public <T> List<T> searchDocument(String rootdir, Class<T> clazz, Page page, ElasticSearchFinder luceneFinder)
            throws Exception {

        if (clazz == null || luceneFinder == null) {
            return null;
        }
        QueryBuilder queryBuilder;
        if (CollectionUtils.isNotEmpty(luceneFinder.getListMustCondition()) || CollectionUtils.isNotEmpty(luceneFinder.getListShouldCondition())) {

            queryBuilder = getBuilderByLuceneFinder(clazz, luceneFinder, luceneFinder.getListMustCondition());
        } else {
            queryBuilder = getBuilderByLuceneFinder(clazz, luceneFinder);
        }
        return searchDocument(rootdir, clazz, page, queryBuilder, luceneFinder);
    }

    /**
     * 根据entityId 查询一个对象
     *
     * @param rootdir
     * @param clazz
     * @param value
     * @return
     * @throws Exception
     */
    public <T> T searchDocumentById(String rootdir, Class<T> clazz, String value) throws Exception {

        if (clazz == null || StringUtils.isBlank(value)) {
            return null;
        }

        EntityInfo info = ClassUtils.getEntityInfoByClass(clazz);
        if (info == null) {
            return null;
        }

        Page page = new Page(1);
        page.setPageSize(1);

        ElasticSearchFinder esFinder = new ElasticSearchFinder(null);
        esFinder.addWhereCondition(info.getPkName(), info.getPkReturnType(), value);

        List<T> list = searchDocument(rootdir, clazz, page, esFinder);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);

    }

    /**
     * 根据实体类保存到索引,结合 LuceneSearch和LuceneField注解
     *
     * @param rootdir 索引库
     * @param entity  会根据实体获取 table注解名，和主键值
     * @return
     */
    public String saveDocument(String rootdir, Object entity) {
        String result = ReturnDatas.SUCCESS;
        try {
            EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
            String id = (String) ClassUtils.getPropertieValue(entityInfo.getPkName(), entity);// 获取主键ID
            if (StringUtils.isEmpty(id)) {
                id = SecUtils.getUUID();
                ClassUtils.setPropertieValue("id", entity, id);
            }
			/*String dataId = (String) ClassUtils.getPropertieValue("id", entity);// 获取主键ID
			if(StringUtils.isEmpty(dataId)) {
				
			}*/

            XContentBuilder builder = Bean2Builder(entity);
            if (builder == null) {
                result = "此实体没有要保存到ES中的数据字段";
            }

            // 封装保存对象
            IndexRequest indexRequest = new IndexRequest(rootdir);
            indexRequest.id(id);
            indexRequest.source(builder);
            // 执行保存
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            RestStatus status = indexResponse.status();
            if (DEBUGGER) {
                System.out.println(status);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 索引文档转builer
     *
     * @param entity
     * @return
     * @throws Exception
     */
    private XContentBuilder Bean2Builder(Object entity) throws Exception {
        List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(entity.getClass());
        if (CollectionUtils.isEmpty(luceneFields)) {
            return null;
        }
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        Date date = null;
        for (FieldInfo finfo : luceneFields) {

            if (!finfo.getLuceneField()) {
                continue;
            }
            if (finfo.getFieldType() == Date.class) {
                date = (Date) ClassUtils.getPropertieValue(finfo.getFieldName(), entity);
                if (date != null) {
                    builder.field(finfo.getFieldName(), DateUtils.formatDate(DATETIME_FORMAT_ZONE,date));
                }
            } else {
                builder.field(finfo.getFieldName(), ClassUtils.getPropertieValue(finfo.getFieldName(), entity));
            }

        }
        builder.endObject();
        return builder;
    }

    /**
     * 根据实体类批量保存到索引,结合 LuceneSearch和LuceneField注解
     *
     * @param rootdir
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String saveListDocument(String rootdir, List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String result = ReturnDatas.SUCCESS;
        BulkRequest request = new BulkRequest();
        EntityInfo entityInfo;
        String id;
        String tableName;
        XContentBuilder builder;
        boolean hasData = false;
        for (T entity : list) {
            entityInfo = ClassUtils.getEntityInfoByEntity(entity);
            id = (String) ClassUtils.getPropertieValue(entityInfo.getPkName(), entity);// 获取主键ID
            if (StringUtils.isEmpty(id)) {
                id = SecUtils.getUUID();
                ClassUtils.setPropertieValue("id", entity, id);
            }

            builder = Bean2Builder(entity);
            if (builder == null) {
                continue;
            }

            if (DEBUGGER) {
                System.out.println(Strings.toString(builder));
            }
            // System.out.println(Strings.toString(builder));
            tableName = entityInfo.getTableName();

            hasData = true;
            request.add(new IndexRequest(rootdir).id(id).source(builder));
        }
        if (hasData) {
            BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                result = "有错误" + bulkResponse.buildFailureMessage();
            }
        }
        return result;
    }

    /**
     * 根据Id删除索引
     *
     * @param rootdir
     * @param clazz
     * @param id
     * @return
     * @throws Exception
     */
    public String deleteDocumentById(String rootdir, Class clazz, String id) throws Exception {
        List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
        if (CollectionUtils.isEmpty(luceneFields)) {
            return null;
        }
        String result = ReturnDatas.SUCCESS;
        // EntityInfo entityInfo = ClassUtils.getEntityInfoByClass(clazz);
        try {
            // 封装删除对象
            DeleteRequest deleteRequest = new DeleteRequest(rootdir);
            deleteRequest.id(id);
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;

    }

    /**
     * 批量删除对象的索引
     *
     * @param rootdir
     * @param list
     * @return
     * @throws Exception
     */
    public <T> String deleteListDocument(String rootdir, List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String result = ReturnDatas.SUCCESS;
        BulkRequest request = new BulkRequest();
        EntityInfo entityInfo;
        for (T entity : list) {
            if (CollectionUtils.isEmpty(ClassUtils.getLuceneFields(entity.getClass()))) {
                continue;
            }
            entityInfo = ClassUtils.getEntityInfoByEntity(entity);

            request.add(new DeleteRequest(rootdir)
                    .id((String) ClassUtils.getPropertieValue(entityInfo.getPkName(), entity)));
        }
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;

    }

    /**
     * 根据LuceneFinder,批量删除索引
     *
     * @param rootdir
     * @param clazz
     * @param luceneFinder
     * @return
     * @throws Exception
     */
    public String deleteListDocument(String rootdir, Class clazz, ElasticSearchFinder luceneFinder) throws Exception {
        List datas = searchDocument(rootdir, clazz, null, luceneFinder);
        return deleteListDocument(rootdir, datas);
    }

    /**
     * 根据Id列表,批量删除索引
     *
     * @param rootdir
     * @param clazz
     * @param ids
     * @return
     * @throws Exception
     */
    public String deleteListDocument(String rootdir, Class clazz, List<String> ids) throws Exception {
        List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
        if (CollectionUtils.isEmpty(luceneFields)) {
            return null;
        }
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }

        String result = ReturnDatas.SUCCESS;
        BulkRequest bulkRequest = new BulkRequest();
        for (String id : ids) {
            bulkRequest.add(new DeleteRequest(rootdir).id(id));
        }
        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 删除一个实体类的所有索引
     *
     * @param rootdir
     * @param clazz
     * @return
     * @throws Exception
     */
    public String deleteAllDocument(String rootdir, Class clazz) throws Exception {
        List<FieldInfo> luceneFields = ClassUtils.getLuceneFields(clazz);
        if (CollectionUtils.isEmpty(luceneFields)) {
            return null;
        }
        String result = ReturnDatas.SUCCESS;
        EntityInfo entityInfo = ClassUtils.getEntityInfoByClass(clazz);
        try {
//			BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
//					.filter(QueryBuilders.typeQuery(entityInfo.getTableName())).source(rootdir).get();
//
//			long deleted = response.getDeleted();
//			result+=":"+deleted;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 修改一个对象的索引
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public String updateDocument(String rootdir, Object entity) throws Exception {

        String result = ReturnDatas.SUCCESS;
        try {
            XContentBuilder builder = Bean2Builder(entity);
            if (builder == null) {
                result = "此实体没有要修改保存到ES中的数据字段";
            }
            EntityInfo entityInfo = ClassUtils.getEntityInfoByEntity(entity);
            String id = (String) ClassUtils.getPropertieValue(entityInfo.getPkName(), entity);// 获取主键ID

            UpdateRequest updateRequest = new UpdateRequest(rootdir, id).doc(builder);
            UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
            RestStatus status = updateResponse.status();
            if (DEBUGGER) {
                System.out.println(status);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 批量修改索引
     *
     * @param rootdir
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String updateListDocument(String rootdir, List<T> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String result = ReturnDatas.SUCCESS;

        BulkRequest bulkRequest = new BulkRequest();
        EntityInfo entityInfo;
        String id;
        XContentBuilder builder;
        boolean hasData = false;
        for (T entity : list) {
            builder = Bean2Builder(entity);
            if (builder == null) {
                continue;
            }
            if (DEBUGGER) {
                System.out.println(Strings.toString(builder));
            }
            entityInfo = ClassUtils.getEntityInfoByEntity(entity);
            id = (String) ClassUtils.getPropertieValue(entityInfo.getPkName(), entity);// 获取主键ID

            hasData = true;
            bulkRequest.add(new UpdateRequest(rootdir, id).doc(builder));
        }
        if (hasData) {
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                result = ReturnDatas.ERROR;
                BulkItemResponse[] items = bulkResponse.getItems();
                for (BulkItemResponse e : items) {
                    logger.error(e.getFailureMessage());
                }
                return result;
            }
        }
        return result;
    }

    private QueryBuilder getBuilderByLuceneFinder(Class clazz, ElasticSearchFinder luceneFinder) throws Exception {
        String[] fields = luceneFinder.getFields();
        QueryBuilder queryBuilder = null;
        if (StringUtils.isNotBlank(luceneFinder.getKeyword())) {
            if (luceneFinder.getFields() == null) {
                List<FieldInfo> luceneTokenizedFields = ClassUtils.getLuceneTokenizedFields(clazz);
                List<String> fieldList = luceneFinder.getFieldList();
                for (FieldInfo finfo : luceneTokenizedFields) {
                    fieldList.add(finfo.getFieldName());
                }
                // luceneFinder.setFieldList(fieldList);
                fields = luceneFinder.getFields();
            }
            queryBuilder = QueryBuilders.multiMatchQuery(luceneFinder.getKeyword(), fields);
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        return queryBuilder;
    }

    /**
     * 封装获取查询的BooleanQuery.Builder
     *
     * @param clazz
     * @param luceneFinder
     * @return
     * @throws Exception
     */
    public BoolQueryBuilder getBuilderByLuceneFinder(Class clazz, ElasticSearchFinder luceneFinder,
                                                     List<QueryBuilder> listMustQueryBuilder) throws Exception {

        String[] fields = luceneFinder.getFields();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(luceneFinder.getKeyword())) {
            if (luceneFinder.getFields() == null) {
                List<FieldInfo> luceneTokenizedFields = ClassUtils.getLuceneTokenizedFields(clazz);
                List<String> fieldList = luceneFinder.getFieldList();
                for (FieldInfo finfo : luceneTokenizedFields) {
                    fieldList.add(finfo.getFieldName());
                }
            }
            fields = luceneFinder.getFields();
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(luceneFinder.getKeyword(), fields));

        } else {
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        }
        if (CollectionUtils.isNotEmpty(listMustQueryBuilder)) {
            for (QueryBuilder qb : listMustQueryBuilder) {
                boolQueryBuilder.must(qb);
            }
        }
        if (CollectionUtils.isNotEmpty(luceneFinder.getListShouldCondition())) {
            boolQueryBuilder.minimumShouldMatch(1);
            for (QueryBuilder qb : luceneFinder.getListShouldCondition()) {
                boolQueryBuilder.should(qb);
            }
        }
        return boolQueryBuilder;

    }

    /**
     * 根据 Query 和 Sort查询数据列表,暂不对外开放.
     *
     * @param rootdir
     * @param clazz
     * @param page
     * @param queryBuilder
     * @param finder
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> List<T> searchDocument(String rootdir, Class<T> clazz, Page page, QueryBuilder queryBuilder, ElasticSearchFinder finder)
            throws Exception {
        SearchRequest searchRequest = new SearchRequest(rootdir);
        // 构造查询语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        // 字段查询
        List<String> findFields = finder.getFindFields();
        if (CollectionUtils.isNotEmpty(findFields)) {
            for (String e : findFields) {
                sourceBuilder.docValueField(e);
            }
        }
        // 字段排序封装
        List<SortField> sortFieldList = finder.getSortFieldList();
        if (CollectionUtils.isNotEmpty(sortFieldList)) {
            for (SortField e : sortFieldList) {
                sourceBuilder.sort(
                        new FieldSortBuilder(e.getField()).order(e.getReverse() ? SortOrder.DESC : SortOrder.ASC));
            }
        }
        // 脚本排序封装
        List<ScriptSortBuilder> scriptSortBuilderList = finder.getScriptSortBuilderList();
        if (CollectionUtils.isNotEmpty(scriptSortBuilderList)) {
            for (ScriptSortBuilder e : scriptSortBuilderList) {
                sourceBuilder.sort(e);
            }
        }

        if (DEBUGGER) {
            System.out.println(queryBuilder.toString());
        }

        sourceBuilder.size(page == null ? 20 : page.getPageSize());
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        // 查询数据
        SearchResponse scrollResp = client.search(searchRequest, RequestOptions.DEFAULT);
        Long totalCount = scrollResp.getHits().getTotalHits().value;
        //Long totalCount = scrollResp.getHits().getTotalHits();
        if (DEBUGGER) {
            System.out.println(totalCount);
        }

        List<T> list = new ArrayList<>();
        T o = null;
        if (page == null) {
            do {
                for (SearchHit hit : scrollResp.getHits().getHits()) {
                    if (DEBUGGER) {
                        System.out.println(hit.getSourceAsString());
                    }
                    if (CollectionUtils.isNotEmpty(findFields)) {
                        for (String e : findFields) {
                            o = clazz.newInstance();
                            ClassUtils.setPropertieValue(e, o, hit.getSourceAsMap().get(e));
                            list.add(o);
                        }
                    } else {
                        o = mapper.readValue(hit.getSourceAsString(), clazz);
                        list.add(o);
                    }
                }
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollResp.getScrollId());
                scrollRequest.scroll(TimeValue.timeValueSeconds(8));
                scrollResp = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } while (scrollResp.getHits().getHits().length != 0);
        } else {
            page.setTotalCount(totalCount.intValue());
            int start = page.getPageSize() * (page.getPageNo() - 1);
            if (start >= totalCount) {
                return null;
            }
            int end = page.getPageSize() * page.getPageNo();
            if (end > totalCount) {
                end = totalCount.intValue();
            }
            if (start >= end) {
                return null;
            }
            int sum = 0;
            do {
                if (sum == start) {
                    for (SearchHit hit : scrollResp.getHits().getHits()) {
                        // System.out.println(hit.getSourceAsString());
                        list.add(mapper.readValue(hit.getSourceAsString(), clazz));
                    }
                    break;
                }
                sum += scrollResp.getHits().getHits().length;
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollResp.getScrollId());
                scrollRequest.scroll(TimeValue.timeValueSeconds(8));
                scrollResp = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } while (scrollResp.getHits().getHits().length != 0);
        }
        return list;
    }


    public RestHighLevelClient getClient() {
        return client;
    }

    public void setClient(RestHighLevelClient client) {
        this.client = client;
    }

}