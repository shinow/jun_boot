package com.ruoyi.project.wxapi.model.service;

import java.util.Map;

public interface IShiroService {

    /**
     * 加载初始的菜单权限
     * @return
     */
    public Map<String, String> loadFilterChainDefinitions();
}
