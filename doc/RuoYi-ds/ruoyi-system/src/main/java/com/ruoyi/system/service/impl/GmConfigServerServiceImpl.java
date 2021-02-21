package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.GmConfigServer;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.mapper.GmConfigServerMapper;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.IGmConfigServerService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmConfigServerServiceImpl implements IGmConfigServerService {
    @Autowired
    private GmConfigServerMapper configServerMapper;

    @Override
    public List<GmConfigServer> selectGmConfigServerList(GmConfigServer configServer) {
        return configServerMapper.selectGmConfigServerList(configServer);
    }


}
