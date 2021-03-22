package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxPayConfig;
import org.springrain.weixin.sdk.common.WxAccessToken;
import org.springrain.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.springrain.weixin.service.IWxAccessTokenService;
import org.springrain.weixin.service.IWxPayConfigService;

import javax.annotation.Resource;

@Service("wxPayConfigService")
public class WxPayConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxPayConfigService {

    private String cacheKeyPrefix = "wxpay_config_";
    @Resource
    private IWxAccessTokenService wxAccessTokenService;

    @Override
    public IWxPayConfig findWxPayConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxPayConfig wxPayConfig = null;
        try {
            wxPayConfig = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, WxPayConfig.class);
            if (wxPayConfig == null) {
                wxPayConfig = super.findById(id, WxPayConfig.class);
                if (wxPayConfig == null) {
                    return null;
                }
                super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxPayConfig);
            }

            WxAccessToken wxAccessToken = wxAccessTokenService.findWxAccessToken(wxPayConfig);
            wxPayConfig.setAccessToken(wxAccessToken.getAccessToken());


        } catch (Exception e) {
            wxPayConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxPayConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxPayConfig updateWxPayConfig(WxPayConfig wxpayappconfig) {

        String id = wxpayappconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }
        try {
            super.update(wxpayappconfig);
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxpayappconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxpayappconfig;
    }


}
