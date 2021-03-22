package org.springrain.system.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Role;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */
@RpcServiceAnnotation
public interface IRoleService extends IBaseSpringrainService {

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Role findRoleById(String id) throws Exception;


}
