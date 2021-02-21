package com.ruoyi.project.wxapi.controller.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 百度地图
 */
public class TencentMapUtil {
    private static String key = "5ETBZ-GBCH6-CWBSW-MU6LH-PLKRH-PAFJH";

    /**
     * 逆地址解析
     * @param location
     * @return
     */
    public static JSONObject getLocation(String location) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", key);
        paramMap.put("location", location);
        String result = HttpUtil.get("https://apis.map.qq.com/ws/geocoder/v1/", paramMap);
        System.out.println(result);
        return JSONObject.parseObject(result);
    }

    public static void main(String[] args) {
        System.out.println(TencentMapUtil.getLocation("39.984154,116.307490"));
    }

}
