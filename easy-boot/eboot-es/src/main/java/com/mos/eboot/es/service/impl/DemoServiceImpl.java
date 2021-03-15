package com.mos.eboot.es.service.impl;

import com.mos.eboot.es.Entity;
import com.mos.eboot.es.service.IDemoService;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 朱建柯
 * Date: 2019/2/13
 * Time: 10:58
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Resource
    private JestClient jestClient;

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    public ResultModel save(Entity entity) {
        Index index = new Index.Builder(entity).index(Entity.INDEX_NAME).type(Entity.TYPE).build();
        try {
            jestClient.execute(index);
            return ResultModel.defaultSuccess("ES保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultModel.defaultError("ES保存失败---" + e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param searchContent
     * @return
     */
    @Override
    public ResultModel search(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
        //searchSourceBuilder.field("name");
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(Entity.INDEX_NAME).addType(Entity.TYPE).build();
        try {
            JestResult result = jestClient.execute(search);
            List<Entity> sourceAsObjectList = result.getSourceAsObjectList(Entity.class);
            return new ResultModel(ResultStatus.SUCCESS, sourceAsObjectList);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultModel.defaultError("查询失败---" + e.getMessage());
        }
    }
    
}
