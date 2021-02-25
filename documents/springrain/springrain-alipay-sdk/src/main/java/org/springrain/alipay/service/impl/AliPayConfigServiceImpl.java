package org.springrain.alipay.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.alipay.entity.AliPayConfig;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.alipay.service.IAliPayConfigService;
import org.springrain.frame.util.GlobalStatic;


@Service("aliPayConfigService")
public class AliPayConfigServiceImpl extends BaseSpringrainAliPayServiceImpl implements IAliPayConfigService {

    private String cacheKeyPrefix = "alipay_config_";


    @Override
    public IAliPayConfig findAliPayConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IAliPayConfig aliPayConfig = null;
        try {
            aliPayConfig = super.getByCache(GlobalStatic.aliPayConfigCacheKey, cacheKeyPrefix + id, AliPayConfig.class);
            if (aliPayConfig == null) {
                aliPayConfig = super.findById(id, AliPayConfig.class);
                if (aliPayConfig == null) {
                    return null;
                }
                super.putByCache(GlobalStatic.aliPayConfigCacheKey, cacheKeyPrefix + id, aliPayConfig);
            }

        } catch (Exception e) {
            aliPayConfig = null;
            logger.error(e.getMessage(), e);
        }

        return aliPayConfig;

    }

    @Override
    public IAliPayConfig updateAliPayConfig(AliPayConfig aliPayConfig) {

        String id = aliPayConfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }
        try {
            super.update(aliPayConfig);
            super.putByCache(GlobalStatic.aliPayConfigCacheKey, cacheKeyPrefix + id, aliPayConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return aliPayConfig;
    }
}
