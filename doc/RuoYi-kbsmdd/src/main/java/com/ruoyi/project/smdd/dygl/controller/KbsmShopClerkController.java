package com.ruoyi.project.smdd.dygl.controller;

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
import com.ruoyi.project.smdd.dygl.domain.KbsmShopClerk;
import com.ruoyi.project.smdd.dygl.service.IKbsmShopClerkService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 店员管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/dygl")
public class KbsmShopClerkController extends BaseController
{
    private String prefix = "smdd/dygl";

    @Autowired
    private IKbsmShopClerkService kbsmShopClerkService;

    @RequiresPermissions("smdd:dygl:view")
    @GetMapping()
    public String dygl()
    {
        return prefix + "/dygl";
    }

    /**
     * 查询店员管理列表
     */
    @RequiresPermissions("smdd:dygl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmShopClerk kbsmShopClerk)
    {
        startPage();
        List<KbsmShopClerk> list = kbsmShopClerkService.selectKbsmShopClerkList(kbsmShopClerk);
        return getDataTable(list);
    }

    /**
     * 导出店员管理列表
     */
    @RequiresPermissions("smdd:dygl:export")
    @Log(title = "店员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmShopClerk kbsmShopClerk)
    {
        List<KbsmShopClerk> list = kbsmShopClerkService.selectKbsmShopClerkList(kbsmShopClerk);
        ExcelUtil<KbsmShopClerk> util = new ExcelUtil<KbsmShopClerk>(KbsmShopClerk.class);
        return util.exportExcel(list, "dygl");
    }

    /**
     * 新增店员管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存店员管理
     */
    @RequiresPermissions("smdd:dygl:add")
    @Log(title = "店员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmShopClerk kbsmShopClerk)
    {
        return toAjax(kbsmShopClerkService.insertKbsmShopClerk(kbsmShopClerk));
    }

    /**
     * 修改店员管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmShopClerk kbsmShopClerk = kbsmShopClerkService.selectKbsmShopClerkById(id);
        mmap.put("kbsmShopClerk", kbsmShopClerk);
        return prefix + "/edit";
    }

    /**
     * 修改保存店员管理
     */
    @RequiresPermissions("smdd:dygl:edit")
    @Log(title = "店员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmShopClerk kbsmShopClerk)
    {
        return toAjax(kbsmShopClerkService.updateKbsmShopClerk(kbsmShopClerk));
    }

    /**
     * 删除店员管理
     */
    @RequiresPermissions("smdd:dygl:remove")
    @Log(title = "店员管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmShopClerkService.deleteKbsmShopClerkByIds(ids));
    }
}
