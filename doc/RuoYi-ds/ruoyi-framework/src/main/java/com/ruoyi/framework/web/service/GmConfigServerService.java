package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.GmConfigServer;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.IGmConfigServerService;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 * 
 * @author ruoyi
 */
@Service("configServer")
public class GmConfigServerService
{
    @Autowired
    private IGmConfigServerService gmConfigServerService;

    public List<GmConfigServer> getServerList(String serverId)
    {
        return gmConfigServerService.selectGmConfigServerList(new GmConfigServer());
    }

}
