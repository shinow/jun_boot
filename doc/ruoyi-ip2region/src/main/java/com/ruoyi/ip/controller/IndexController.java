package com.ruoyi.ip.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.ip.handler.IpHandler;
import com.ruoyi.ip.util.IpUtil;
import com.ruoyi.ip.util.R;
import com.ruoyi.ip.util.SpringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IndexController
{
    private static final Map<String, String> map;
    static
    {
        map = new ConcurrentHashMap<String, String>();
        map.put("db", "dbHandler");
        map.put("baidu", "baiduHandler");
        map.put("qqwry", "qqwryHandler");
    }

    @RequestMapping("/")
    public R region(HttpServletRequest request, String ip,
            @RequestParam(name = "type", defaultValue = "db") String type)
    {
        if (null == ip) ip = IpUtil.getIpAddr(request);
        if (isIp(ip))
        {
            log.info("开始查询ip:{}", ip);
            String handlername = map.get(type);
            if (null == handlername) return R.error("参数错误");
            IpHandler handler = SpringUtils.getBean(handlername);
            return R.ok().put("data", handler.getRegion(ip)).put("ip", ip);
        }
        return R.error("参数错误");
    }

    /**
     * 是否ip格式
     * @author zmr
     */
    private boolean isIp(String str)
    {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }
}
