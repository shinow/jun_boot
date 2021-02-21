package com.ruoyi.project.smdd.ggzgl.controller;

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
import com.ruoyi.project.smdd.ggzgl.domain.KbsmGoodsSpecGroup;
import com.ruoyi.project.smdd.ggzgl.service.IKbsmGoodsSpecGroupService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 规格组管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Controller
@RequestMapping("/smdd/ggzgl")
public class KbsmGoodsSpecGroupController extends BaseController
{
    private String prefix = "smdd/ggzgl";

    @Autowired
    private IKbsmGoodsSpecGroupService kbsmGoodsSpecGroupService;

    @RequiresPermissions("smdd:ggzgl:view")
    @GetMapping()
    public String ggzgl()
    {
        return prefix + "/ggzgl";
    }

    /**
     * 查询规格组管理列表
     */
    @RequiresPermissions("smdd:ggzgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        startPage();
        List<KbsmGoodsSpecGroup> list = kbsmGoodsSpecGroupService.selectKbsmGoodsSpecGroupList(kbsmGoodsSpecGroup);
        return getDataTable(list);
    }

    /**
     * 导出规格组管理列表
     */
    @RequiresPermissions("smdd:ggzgl:export")
    @Log(title = "规格组管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        List<KbsmGoodsSpecGroup> list = kbsmGoodsSpecGroupService.selectKbsmGoodsSpecGroupList(kbsmGoodsSpecGroup);
        ExcelUtil<KbsmGoodsSpecGroup> util = new ExcelUtil<KbsmGoodsSpecGroup>(KbsmGoodsSpecGroup.class);
        return util.exportExcel(list, "ggzgl");
    }

    /**
     * 新增规格组管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存规格组管理
     */
    @RequiresPermissions("smdd:ggzgl:add")
    @Log(title = "规格组管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        return toAjax(kbsmGoodsSpecGroupService.insertKbsmGoodsSpecGroup(kbsmGoodsSpecGroup));
    }

    /**
     * 修改规格组管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        KbsmGoodsSpecGroup kbsmGoodsSpecGroup = kbsmGoodsSpecGroupService.selectKbsmGoodsSpecGroupById(id);
        mmap.put("kbsmGoodsSpecGroup", kbsmGoodsSpecGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存规格组管理
     */
    @RequiresPermissions("smdd:ggzgl:edit")
    @Log(title = "规格组管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmGoodsSpecGroup kbsmGoodsSpecGroup)
    {
        return toAjax(kbsmGoodsSpecGroupService.updateKbsmGoodsSpecGroup(kbsmGoodsSpecGroup));
    }

    /**
     * 删除规格组管理
     */
    @RequiresPermissions("smdd:ggzgl:remove")
    @Log(title = "规格组管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmGoodsSpecGroupService.deleteKbsmGoodsSpecGroupByIds(ids));
    }
}
