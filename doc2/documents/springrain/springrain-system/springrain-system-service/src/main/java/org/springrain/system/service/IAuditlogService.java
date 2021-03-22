package org.springrain.system.service;

import org.springrain.frame.entity.AuditLog;
import org.springrain.rpc.annotation.RpcServiceAnnotation;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-04-02 10:17:31
 * @see org.springrain.system.service.IAuditlogService
 */
@RpcServiceAnnotation
public interface IAuditlogService extends IBaseSpringrainService {
    String saveAuditlog(AuditLog entity) throws Exception;


    Integer updateAuditlog(AuditLog entity) throws Exception;

    AuditLog findAuditlogById(Object id) throws Exception;
}
