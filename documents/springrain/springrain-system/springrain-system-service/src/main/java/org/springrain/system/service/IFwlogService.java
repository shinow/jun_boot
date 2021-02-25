package org.springrain.system.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Fwlog;

/**
 * TODO 在此加入类描述
 *
 * @author weicms.net<Auto generate>
 * @version 2013-07-29 11:36:44
 * @copyright {@link springrain}
 * @see org.springrain.springrain.service.Fwlog
 */
@RpcServiceAnnotation
public interface IFwlogService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Fwlog findFwlogById(Object id) throws Exception;

}
