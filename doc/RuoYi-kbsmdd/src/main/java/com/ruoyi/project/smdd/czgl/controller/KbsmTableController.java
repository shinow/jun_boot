package com.ruoyi.project.smdd.czgl.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.smdd.czgl.domain.KbsmTable;
import com.ruoyi.project.smdd.czgl.service.IKbsmTableService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 餐桌管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/czgl")
public class KbsmTableController extends BaseController
{
    private String prefix = "smdd/czgl";

    @Autowired
    private IKbsmTableService kbsmTableService;

    @RequiresPermissions("smdd:czgl:view")
    @GetMapping()
    public String czgl()
    {
        return prefix + "/czgl";
    }

    /**
     * 查询餐桌管理列表
     */
    @RequiresPermissions("smdd:czgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmTable kbsmTable)
    {
        startPage();
        List<KbsmTable> list = kbsmTableService.selectKbsmTableList(kbsmTable);
        return getDataTable(list);
    }

    /**
     * 导出餐桌管理列表
     */
    @RequiresPermissions("smdd:czgl:export")
    @Log(title = "餐桌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmTable kbsmTable)
    {
        List<KbsmTable> list = kbsmTableService.selectKbsmTableList(kbsmTable);
        ExcelUtil<KbsmTable> util = new ExcelUtil<KbsmTable>(KbsmTable.class);
        return util.exportExcel(list, "czgl");
    }

    /**
     * 新增餐桌管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存餐桌管理
     */
    @RequiresPermissions("smdd:czgl:add")
    @Log(title = "餐桌管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmTable kbsmTable)
    {
        return toAjax(kbsmTableService.insertKbsmTable(kbsmTable));
    }

    /**
     * 修改餐桌管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmTable kbsmTable = kbsmTableService.selectKbsmTableById(id);
        mmap.put("kbsmTable", kbsmTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存餐桌管理
     */
    @RequiresPermissions("smdd:czgl:edit")
    @Log(title = "餐桌管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmTable kbsmTable)
    {
        return toAjax(kbsmTableService.updateKbsmTable(kbsmTable));
    }

    /**
     * 删除餐桌管理
     */
    @RequiresPermissions("smdd:czgl:remove")
    @Log(title = "餐桌管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmTableService.deleteKbsmTableByIds(ids));
    }
}
