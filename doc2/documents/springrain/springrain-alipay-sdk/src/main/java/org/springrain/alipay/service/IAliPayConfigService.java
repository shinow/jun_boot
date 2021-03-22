package org.springrain.alipay.service;

import org.springrain.alipay.entity.AliPayConfig;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.rpc.annotation.RpcServiceAnnotation;

@RpcServiceAnnotation
public interface IAliPayConfigService {


    /**
     * 根据ID查找,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IAliPayConfig findAliPayConfigById(String id);

    /**
     * 更新aliPayConfig,可以进行缓存处理
     *
     * @param aliPayConfig
     * @return
     */
    IAliPayConfig updateAliPayConfig(AliPayConfig aliPayConfig);
}
