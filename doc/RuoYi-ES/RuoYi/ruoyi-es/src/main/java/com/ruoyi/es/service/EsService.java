package com.ruoyi.es.service;

import com.ruoyi.es.bean.es.EsUserBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author japhet_jiu
 * @version 1.0
 */
public interface EsService extends ElasticsearchRepository<EsUserBean,Integer> {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    EsUserBean queryEmployeeById(String id);

}
