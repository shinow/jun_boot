package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OkrSynergy;
import com.ruoyi.system.service.IOkrSynergyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 协同关系Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-07
 */
@Controller
@RequestMapping("/system/synergy")
public class OkrSynergyController extends BaseController
{
    private String prefix = "system/synergy";

    @Autowired
    private IOkrSynergyService okrSynergyService;

    @RequiresPermissions("system:synergy:view")
    @GetMapping()
    public String synergy()
    {
        return prefix + "/synergy";
    }

    /**
     * 查询协同关系列表
     */
    @RequiresPermissions("system:synergy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrSynergy okrSynergy)
    {
        startPage();
        List<OkrSynergy> list = okrSynergyService.selectOkrSynergyList(okrSynergy);
        return getDataTable(list);
    }

    /**
     * 导出协同关系列表
     */
    @RequiresPermissions("system:synergy:export")
    @Log(title = "协同关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrSynergy okrSynergy)
    {
        List<OkrSynergy> list = okrSynergyService.selectOkrSynergyList(okrSynergy);
        ExcelUtil<OkrSynergy> util = new ExcelUtil<OkrSynergy>(OkrSynergy.class);
        return util.exportExcel(list, "synergy");
    }

    /**
     * 新增协同关系
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存协同关系
     */
    @RequiresPermissions("system:synergy:add")
    @Log(title = "协同关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrSynergy okrSynergy)
    {
        return toAjax(okrSynergyService.insertOkrSynergy(okrSynergy));
    }

    /**
     * 修改协同关系
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrSynergy okrSynergy = okrSynergyService.selectOkrSynergyById(id);
        mmap.put("okrSynergy", okrSynergy);
        return prefix + "/edit";
    }

    /**
     * 修改保存协同关系
     */
    @RequiresPermissions("system:synergy:edit")
    @Log(title = "协同关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrSynergy okrSynergy)
    {
        return toAjax(okrSynergyService.updateOkrSynergy(okrSynergy));
    }

    /**
     * 删除协同关系
     */
    @RequiresPermissions("system:synergy:remove")
    @Log(title = "协同关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrSynergyService.deleteOkrSynergyByIds(ids));
    }
}
