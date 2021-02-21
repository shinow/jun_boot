package com.ruoyi.ip.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ip.util.HttpUtils;
import com.ruoyi.ip.util.IpUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BaiduHandler implements IpHandler
{
    public static final String IP_URL = "http://api.map.baidu.com/location/ip";

    @Override
    public Map<String, Object> getRegion(String ip)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("address", getRealAddressByIP(ip));
        return map;
    }

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        if (IpUtil.internalIp(ip))
        {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendPost(IP_URL, "ak=i9Kg0hwPEsSABe8RojvACtieGhZR0r37&coor=bd09ll&ip=" + ip);
        try
        {
            JSONObject data = JSON.parseObject(rspStr);
            if (data.getInteger("status") == 0)
            {
                address = data.getJSONObject("content").getString("address");
            }
            else
            {
                log.error("获取地理位置异常 {}", data.get("message"));
            }
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }
        return address;
    }
}
