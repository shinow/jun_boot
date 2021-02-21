package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.enums.ConfigValueTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    private String prefix = "system/config";

    @Autowired
    private ISysConfigService configService;

    @RequiresPermissions("system:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询参数配置列表
     */
    @RequiresPermissions("system:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysConfig config)
    {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysConfig config)
    {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysConfig config)
    {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        if(!checkType(config.getConfigValueType(),config.getConfigValue()))
        {
            return error("值与预期类型不符！");
        }
        config.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap)
    {
        mmap.put("config", configService.selectConfigById(configId));
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysConfig config)
    {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        if(!checkType(config.getConfigValueType(),config.getConfigValue()))
        {
            return error("值与预期类型不符！");
        }
        config.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configService.deleteConfigByIds(ids));
    }

    /**
     * 清空缓存
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @GetMapping("/clearCache")
    @ResponseBody
    public AjaxResult clearCache()
    {
        configService.clearCache();
        return success();
    }

    /**
     * 校验参数键名
     */
    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public String checkConfigKeyUnique(SysConfig config)
    {
        return configService.checkConfigKeyUnique(config);
    }

    /**
     * 获取分组列表
     */
    @GetMapping("/configGroup")
    @ResponseBody
    public List<String> configGroup(){
     return  configService.selectConfigGroup();
    }

    /**
     * 修改保存参数配置
     * 通过key批量保存
     * 默认设置为字符串类型
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateByKey")
    @ResponseBody
    public AjaxResult editSaves(@RequestParam Map<String,String> mp){
        int i=0;
        for (Map.Entry<String,String> entry:mp.entrySet()){
            SysConfig sysConfig=new SysConfig();
            sysConfig.setConfigKey(entry.getKey());
            sysConfig.setConfigValue(entry.getValue());
            sysConfig.setConfigName(entry.getKey());
            SysConfig rsConfig=configService.selectConfigByKey(sysConfig.getConfigKey());
            if (StringUtils.isNotNull(rsConfig)) {
                if(!checkType(rsConfig.getConfigValueType(),sysConfig.getConfigValue()))
                {
                    continue;
                }
                sysConfig.setUpdateBy(ShiroUtils.getLoginName());
                configService.updateConfigByKey(sysConfig);
            }
            else {
                sysConfig.setConfigValueType((byte) ConfigValueTypeEnum.STRING.ordinal());
                sysConfig.setCreateBy(ShiroUtils.getLoginName());
                configService.insertConfig(sysConfig);
            }
            i=+1;
        }
        configService.clearCache();
        return toAjax(i);
    }


    /**
     * 判断是否符合预期类型
     * @param type
     * @param object
     * @return
     */
    private boolean checkType(int type,Object object) {

        if (type == 0) {
            return "true".equals(object) || "false".equals(object) || "".equals(object);
        } else if (type == 1) {
            return  NumberUtils.isCreatable(object.toString());
        } else {
            return true;
        }
    }
}
