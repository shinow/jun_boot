package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxCpConfig;
import org.springrain.weixin.sdk.common.WxAccessToken;
import org.springrain.weixin.sdk.common.wxconfig.IWxCpConfig;
import org.springrain.weixin.service.IWxAccessTokenService;
import org.springrain.weixin.service.IWxCpConfigService;

import javax.annotation.Resource;

@Service("wxCpConfigService")
public class WxCpConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxCpConfigService {

    private String cacheKeyPrefix = "wxminiapp_config_";

    @Resource
    private IWxAccessTokenService wxAccessTokenService;


    public WxCpConfigServiceImpl() {
    }


    @Override
    public IWxCpConfig findWxCpConfigById(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxCpConfig wxcpConfig = null;
        try {
            wxcpConfig = super.getByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, WxCpConfig.class);
            if (wxcpConfig == null) {
                wxcpConfig = super.findById(id, WxCpConfig.class);
                if (wxcpConfig == null) {
                    return null;
                }
                super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxcpConfig);
            }

            WxAccessToken wxAccessToken = wxAccessTokenService.findWxAccessToken(wxcpConfig);
            wxcpConfig.setAccessToken(wxAccessToken.getAccessToken());

        } catch (Exception e) {
            wxcpConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxcpConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新
     */
    @Override
    public IWxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig) {

        String id = wxcpconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.putByCache(GlobalStatic.wxConfigCacheKey, cacheKeyPrefix + id, wxcpconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxcpconfig;
    }

}
