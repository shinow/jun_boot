package com.ruoyi.project.smdd.xcxlb.controller;

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
import com.ruoyi.project.smdd.xcxlb.domain.KbsmWxapp;
import com.ruoyi.project.smdd.xcxlb.service.IKbsmWxappService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 小程序列表Controller
 * 
 * @author ruoyi
 * @date 2020-08-08
 */
@Controller
@RequestMapping("/smdd/xcxlb")
public class KbsmWxappController extends BaseController
{
    private String prefix = "smdd/xcxlb";

    @Autowired
    private IKbsmWxappService kbsmWxappService;

    @RequiresPermissions("smdd:xcxlb:view")
    @GetMapping()
    public String xcxlb()
    {
        return prefix + "/xcxlb";
    }

    /**
     * 查询小程序列表列表
     */
    @RequiresPermissions("smdd:xcxlb:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmWxapp kbsmWxapp)
    {
        startPage();
        List<KbsmWxapp> list = kbsmWxappService.selectKbsmWxappList(kbsmWxapp);
        return getDataTable(list);
    }

    /**
     * 导出小程序列表列表
     */
    @RequiresPermissions("smdd:xcxlb:export")
    @Log(title = "小程序列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmWxapp kbsmWxapp)
    {
        List<KbsmWxapp> list = kbsmWxappService.selectKbsmWxappList(kbsmWxapp);
        ExcelUtil<KbsmWxapp> util = new ExcelUtil<KbsmWxapp>(KbsmWxapp.class);
        return util.exportExcel(list, "xcxlb");
    }

    /**
     * 新增小程序列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小程序列表
     */
    @RequiresPermissions("smdd:xcxlb:add")
    @Log(title = "小程序列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmWxapp kbsmWxapp)
    {
        return toAjax(kbsmWxappService.insertKbsmWxapp(kbsmWxapp));
    }

    /**
     * 修改小程序列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmWxapp kbsmWxapp = kbsmWxappService.selectKbsmWxappById(id);
        mmap.put("kbsmWxapp", kbsmWxapp);
        return prefix + "/edit";
    }

    /**
     * 修改保存小程序列表
     */
    @RequiresPermissions("smdd:xcxlb:edit")
    @Log(title = "小程序列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmWxapp kbsmWxapp)
    {
        return toAjax(kbsmWxappService.updateKbsmWxapp(kbsmWxapp));
    }

    /**
     * 删除小程序列表
     */
    @RequiresPermissions("smdd:xcxlb:remove")
    @Log(title = "小程序列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmWxappService.deleteKbsmWxappByIds(ids));
    }
}
