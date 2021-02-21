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
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.service.IOkrCycleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * OKR周期Controller
 * 
 * @author ruoyi
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/system/cycle")
public class OkrCycleController extends BaseController
{
    private String prefix = "system/cycle";

    @Autowired
    private IOkrCycleService okrCycleService;

    @RequiresPermissions("system:cycle:view")
    @GetMapping()
    public String cycle()
    {
        return prefix + "/cycle";
    }

    /**
     * 查询OKR周期列表
     */
    @RequiresPermissions("system:cycle:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrCycle okrCycle)
    {
        startPage();
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(okrCycle);
        return getDataTable(list);
    }

    /**
     * 导出OKR周期列表
     */
    @RequiresPermissions("system:cycle:export")
    @Log(title = "OKR周期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrCycle okrCycle)
    {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(okrCycle);
        ExcelUtil<OkrCycle> util = new ExcelUtil<OkrCycle>(OkrCycle.class);
        return util.exportExcel(list, "cycle");
    }

    /**
     * 新增OKR周期
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存OKR周期
     */
    @RequiresPermissions("system:cycle:add")
    @Log(title = "OKR周期", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrCycle okrCycle)
    {
        return toAjax(okrCycleService.insertOkrCycle(okrCycle));
    }

    /**
     * 修改OKR周期
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrCycle okrCycle = okrCycleService.selectOkrCycleById(id);
        mmap.put("okrCycle", okrCycle);
        return prefix + "/edit";
    }

    /**
     * 修改保存OKR周期
     */
    @RequiresPermissions("system:cycle:edit")
    @Log(title = "OKR周期", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrCycle okrCycle)
    {
        return toAjax(okrCycleService.updateOkrCycle(okrCycle));
    }

    /**
     * 删除OKR周期
     */
    @RequiresPermissions("system:cycle:remove")
    @Log(title = "OKR周期", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrCycleService.deleteOkrCycleByIds(ids));
    }
}
