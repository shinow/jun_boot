package com.ruoyi.framework.web.service;

import com.ruoyi.common.config.Global;
import com.ruoyi.framework.util.FileUploadUtils;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 * 
 * @author ruoyi
 */
@Service("config")
public class ConfigService
{
    @Autowired
    private ISysConfigService configService;

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey)
    {
        return configService.selectConfigByKey(configKey);
    }

    /**
     * 获取文件web根路径
     * @param dir 子文件目录
     * @return
     */
    public String getWebProfile(String dir) {
        String webProfile = FileUploadUtils.getWebProfile();
        if (!Global.isNetFile()) webProfile += dir;
        return webProfile;
    }
}
