package com.ruoyi.system.service;

import com.ruoyi.system.domain.GmConfigServer;
import com.ruoyi.system.domain.SysConfig;

import java.util.List;

public interface IGmConfigServerService {

    public List<GmConfigServer> selectGmConfigServerList(GmConfigServer configServer);

}
