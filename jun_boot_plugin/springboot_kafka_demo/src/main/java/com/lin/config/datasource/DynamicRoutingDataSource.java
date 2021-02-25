package com.lin.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: LinZhaoguan
 * Date: 2018/3/16
 * Time: 16:05
 * Description:
 */
@Slf4j
@Order(99)
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {


    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        /*if (Math.random()*10 > 5) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
        }else {
            DynamicDataSourceContextHolder.useMasterDataSource();
        }*/

        log.debug("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
