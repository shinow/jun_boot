package org.springrain.system.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.system.entity.Stock;
import org.springrain.system.service.IStockService;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-08-19 15:36:24
 */

@Service("stockService")
public class StockServiceImpl extends BaseSpringrainServiceImpl implements IStockService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        Stock stock = (Stock) entity;
        return super.save(stock).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Stock stock = (Stock) entity;
        return super.update(stock);
    }

    @Override
    public Stock findStockById(String id) throws Exception {
        return super.findById(id, Stock.class);
    }


}
