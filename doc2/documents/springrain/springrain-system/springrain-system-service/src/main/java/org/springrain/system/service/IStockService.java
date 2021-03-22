package org.springrain.system.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Stock;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-08-19 15:36:24
 */
@RpcServiceAnnotation
public interface IStockService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Stock findStockById(String id) throws Exception;


}
