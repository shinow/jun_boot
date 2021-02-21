package com.ruoyi.project.smdd.cpflgl.controller;

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
import com.ruoyi.project.smdd.cpflgl.domain.KbsmCategory;
import com.ruoyi.project.smdd.cpflgl.service.IKbsmCategoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 菜品分类管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/cpflgl")
public class KbsmCategoryController extends BaseController
{
    private String prefix = "smdd/cpflgl";

    @Autowired
    private IKbsmCategoryService kbsmCategoryService;

    @RequiresPermissions("smdd:cpflgl:view")
    @GetMapping()
    public String cpflgl()
    {
        return prefix + "/cpflgl";
    }

    /**
     * 查询菜品分类管理列表
     */
    @RequiresPermissions("smdd:cpflgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmCategory kbsmCategory)
    {
        startPage();
        List<KbsmCategory> list = kbsmCategoryService.selectKbsmCategoryList(kbsmCategory);
        return getDataTable(list);
    }

    /**
     * 导出菜品分类管理列表
     */
    @RequiresPermissions("smdd:cpflgl:export")
    @Log(title = "菜品分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmCategory kbsmCategory)
    {
        List<KbsmCategory> list = kbsmCategoryService.selectKbsmCategoryList(kbsmCategory);
        ExcelUtil<KbsmCategory> util = new ExcelUtil<KbsmCategory>(KbsmCategory.class);
        return util.exportExcel(list, "cpflgl");
    }

    /**
     * 新增菜品分类管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存菜品分类管理
     */
    @RequiresPermissions("smdd:cpflgl:add")
    @Log(title = "菜品分类管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmCategory kbsmCategory)
    {
        return toAjax(kbsmCategoryService.insertKbsmCategory(kbsmCategory));
    }

    /**
     * 修改菜品分类管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmCategory kbsmCategory = kbsmCategoryService.selectKbsmCategoryById(id);
        mmap.put("kbsmCategory", kbsmCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜品分类管理
     */
    @RequiresPermissions("smdd:cpflgl:edit")
    @Log(title = "菜品分类管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmCategory kbsmCategory)
    {
        return toAjax(kbsmCategoryService.updateKbsmCategory(kbsmCategory));
    }

    /**
     * 删除菜品分类管理
     */
    @RequiresPermissions("smdd:cpflgl:remove")
    @Log(title = "菜品分类管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmCategoryService.deleteKbsmCategoryByIds(ids));
    }
}
