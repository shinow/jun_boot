package org.springrain.weixin.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.entity.WxPayConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxPayConfig;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxPayConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxPayConfig findWxPayConfigById(String id);

    /**
     * 更新WxPayConfig,可以进行缓存处理
     *
     * @param wxpayconfig
     * @return
     */
    IWxPayConfig updateWxPayConfig(WxPayConfig wxpayconfig);


}
