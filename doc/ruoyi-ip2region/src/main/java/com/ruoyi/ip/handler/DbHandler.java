package com.ruoyi.ip.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.ip.config.RegionConfig;
import com.ruoyi.ip.util.DbUtil;
import com.ruoyi.ip.util.IpUtil;

@Component
public class DbHandler implements IpHandler
{
    @Autowired
    private RegionConfig regionConfig;

    @Override
    public Map<String, Object> getRegion(String ip)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if (IpUtil.internalIp(ip))
        {
            map.put("address", "内网IP");
        }
        else
        {
            String region = DbUtil.getCityInfo(ip, regionConfig);
            String[] reg = region.split("\\|");
            map.put("country", reg[0]);
            map.put("area", reg[1]);
            map.put("province", reg[2]);
            map.put("city", reg[3]);
            map.put("isp", reg[4]);
            map.put("address", region.replaceAll("\\|", "").replaceAll("0", ""));
        }
        return map;
    }
}
