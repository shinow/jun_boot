package com.ruoyi.project.smdd.gggl.controller;

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
import com.ruoyi.project.smdd.gggl.domain.KbsmGoodsSpec;
import com.ruoyi.project.smdd.gggl.service.IKbsmGoodsSpecService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 规格管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Controller
@RequestMapping("/smdd/gggl")
public class KbsmGoodsSpecController extends BaseController
{
    private String prefix = "smdd/gggl";

    @Autowired
    private IKbsmGoodsSpecService kbsmGoodsSpecService;

    @RequiresPermissions("smdd:gggl:view")
    @GetMapping()
    public String gggl()
    {
        return prefix + "/gggl";
    }

    /**
     * 查询规格管理列表
     */
    @RequiresPermissions("smdd:gggl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmGoodsSpec kbsmGoodsSpec)
    {
        startPage();
        List<KbsmGoodsSpec> list = kbsmGoodsSpecService.selectKbsmGoodsSpecList(kbsmGoodsSpec);
        return getDataTable(list);
    }

    /**
     * 导出规格管理列表
     */
    @RequiresPermissions("smdd:gggl:export")
    @Log(title = "规格管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmGoodsSpec kbsmGoodsSpec)
    {
        List<KbsmGoodsSpec> list = kbsmGoodsSpecService.selectKbsmGoodsSpecList(kbsmGoodsSpec);
        ExcelUtil<KbsmGoodsSpec> util = new ExcelUtil<KbsmGoodsSpec>(KbsmGoodsSpec.class);
        return util.exportExcel(list, "gggl");
    }

    /**
     * 新增规格管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存规格管理
     */
    @RequiresPermissions("smdd:gggl:add")
    @Log(title = "规格管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmGoodsSpec kbsmGoodsSpec)
    {
        return toAjax(kbsmGoodsSpecService.insertKbsmGoodsSpec(kbsmGoodsSpec));
    }

    /**
     * 修改规格管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmGoodsSpec kbsmGoodsSpec = kbsmGoodsSpecService.selectKbsmGoodsSpecById(id);
        mmap.put("kbsmGoodsSpec", kbsmGoodsSpec);
        return prefix + "/edit";
    }

    /**
     * 修改保存规格管理
     */
    @RequiresPermissions("smdd:gggl:edit")
    @Log(title = "规格管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmGoodsSpec kbsmGoodsSpec)
    {
        return toAjax(kbsmGoodsSpecService.updateKbsmGoodsSpec(kbsmGoodsSpec));
    }

    /**
     * 删除规格管理
     */
    @RequiresPermissions("smdd:gggl:remove")
    @Log(title = "规格管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmGoodsSpecService.deleteKbsmGoodsSpecByIds(ids));
    }
}
