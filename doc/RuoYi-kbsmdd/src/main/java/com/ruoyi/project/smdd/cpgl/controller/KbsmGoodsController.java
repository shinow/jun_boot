package com.ruoyi.project.smdd.cpgl.controller;

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
import com.ruoyi.project.smdd.cpgl.domain.KbsmGoods;
import com.ruoyi.project.smdd.cpgl.service.IKbsmGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 菜品管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-09
 */
@Controller
@RequestMapping("/smdd/cpgl")
public class KbsmGoodsController extends BaseController
{
    private String prefix = "smdd/cpgl";

    @Autowired
    private IKbsmGoodsService kbsmGoodsService;

    @RequiresPermissions("smdd:cpgl:view")
    @GetMapping()
    public String cpgl()
    {
        return prefix + "/cpgl";
    }

    /**
     * 查询菜品管理列表
     */
    @RequiresPermissions("smdd:cpgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmGoods kbsmGoods)
    {
        startPage();
        List<KbsmGoods> list = kbsmGoodsService.selectKbsmGoodsList(kbsmGoods);
        return getDataTable(list);
    }

    /**
     * 导出菜品管理列表
     */
    @RequiresPermissions("smdd:cpgl:export")
    @Log(title = "菜品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmGoods kbsmGoods)
    {
        List<KbsmGoods> list = kbsmGoodsService.selectKbsmGoodsList(kbsmGoods);
        ExcelUtil<KbsmGoods> util = new ExcelUtil<KbsmGoods>(KbsmGoods.class);
        return util.exportExcel(list, "cpgl");
    }

    /**
     * 新增菜品管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存菜品管理
     */
    @RequiresPermissions("smdd:cpgl:add")
    @Log(title = "菜品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmGoods kbsmGoods, Integer imgId)

    {
        return toAjax(kbsmGoodsService.insertKbsmGoods(kbsmGoods, imgId));
    }

    /**
     * 修改菜品管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmGoods kbsmGoods = kbsmGoodsService.selectKbsmGoodsById(id);
        mmap.put("kbsmGoods", kbsmGoods);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜品管理
     */
    @RequiresPermissions("smdd:cpgl:edit")
    @Log(title = "菜品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmGoods kbsmGoods)
    {
        return toAjax(kbsmGoodsService.updateKbsmGoods(kbsmGoods));
    }

    /**
     * 删除菜品管理
     */
    @RequiresPermissions("smdd:cpgl:remove")
    @Log(title = "菜品管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmGoodsService.deleteKbsmGoodsByIds(ids));
    }
}
