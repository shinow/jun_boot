package org.springrain.weixin.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.sdk.common.WxAccessToken;
import org.springrain.weixin.sdk.common.WxCardTicket;
import org.springrain.weixin.sdk.common.WxJsTicket;
import org.springrain.weixin.sdk.common.wxconfig.IWxConfig;

@RpcServiceAnnotation
public interface IWxAccessTokenService {

    /**
     * 获取AccessToken
     *
     * @param wxConfig
     * @return
     */
    WxAccessToken findWxAccessToken(IWxConfig wxConfig) throws Exception;

    /**
     * 会员卡 ticket
     *
     * @param wxConfig
     * @return
     * @throws Exception
     */
    WxCardTicket findWxCardTicket(IWxConfig wxConfig) throws Exception;

    /**
     * js ticket
     *
     * @param wxConfig
     * @return
     * @throws Exception
     */
    WxJsTicket findWxJsTicket(IWxConfig wxConfig) throws Exception;

}
