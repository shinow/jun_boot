package com.project.framework.web.service;

import com.project.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.system.service.ISysConfigService;

import java.util.HashMap;
import java.util.Map;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 *
 * @author lws
 */
@Service("config")
public class ConfigService {
    private static Map<String,String> keyValueS = new HashMap<String,String>();
    @Autowired
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        String value = keyValueS.get(configKey);
        if(!StringUtils.isEmpty(value)){
            return value;
        }
        value = configService.selectConfigByKey(configKey);
        value = value==null?"":value;
        keyValueS.put(configKey,value);
        return value;
    }

    /**
     * 刷新内存中的key
     * @param key
     */
    public void refreshKey(String key){
        keyValueS.put(key,"");
    }
    /**
     * 根据键名查询参数配置信息
     * @param configKeys 参数名称
     * @param splitBy 键的分隔符（也是返回值的分隔符）
     * @return 以分隔符连接的键值
     */
    public String getKeys(String configKeys,String splitBy,String returnSplitBy){
        StringBuilder sb = new StringBuilder();
        String[] keys = configKeys.split(splitBy);
        if(keys.length==0){
            return "";
        }
        for(String key : keys){
            sb.append(returnSplitBy).append(getKey(key));
        }
        return sb.toString().substring(returnSplitBy.length());
    }
}
