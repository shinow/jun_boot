package org.springrain.weixin.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.entity.WxMiniappConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;

//@RpcServiceAnnotation(implpackage = "weixin.wxconfig.impl")
@RpcServiceAnnotation
public interface IWxMiniappConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxMiniappConfig findWxMiniappConfigById(String id);


    /**
     * 更新WxMiniappConfig,可以进行缓存处理
     *
     * @param wxminiappconfig
     * @return
     */
    IWxMiniappConfig updateWxMiniappConfig(WxMiniappConfig wxminiappconfig);


}
