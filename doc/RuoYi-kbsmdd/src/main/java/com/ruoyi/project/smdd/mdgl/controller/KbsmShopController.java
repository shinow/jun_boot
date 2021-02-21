package com.ruoyi.project.smdd.mdgl.controller;

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
import com.ruoyi.project.smdd.mdgl.domain.KbsmShop;
import com.ruoyi.project.smdd.mdgl.service.IKbsmShopService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 门店管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/mdgl")
public class KbsmShopController extends BaseController
{
    private String prefix = "smdd/mdgl";

    @Autowired
    private IKbsmShopService kbsmShopService;

    @RequiresPermissions("smdd:mdgl:view")
    @GetMapping()
    public String mdgl()
    {
        return prefix + "/mdgl";
    }

    /**
     * 查询门店管理列表
     */
    @RequiresPermissions("smdd:mdgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmShop kbsmShop)
    {
        startPage();
        List<KbsmShop> list = kbsmShopService.selectKbsmShopList(kbsmShop);
        return getDataTable(list);
    }

    /**
     * 导出门店管理列表
     */
    @RequiresPermissions("smdd:mdgl:export")
    @Log(title = "门店管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmShop kbsmShop)
    {
        List<KbsmShop> list = kbsmShopService.selectKbsmShopList(kbsmShop);
        ExcelUtil<KbsmShop> util = new ExcelUtil<KbsmShop>(KbsmShop.class);
        return util.exportExcel(list, "mdgl");
    }

    /**
     * 新增门店管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门店管理
     */
    @RequiresPermissions("smdd:mdgl:add")
    @Log(title = "门店管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmShop kbsmShop)
    {
        return toAjax(kbsmShopService.insertKbsmShop(kbsmShop));
    }

    /**
     * 修改门店管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmShop kbsmShop = kbsmShopService.selectKbsmShopById(id);
        mmap.put("kbsmShop", kbsmShop);
        return prefix + "/edit";
    }

    /**
     * 修改保存门店管理
     */
    @RequiresPermissions("smdd:mdgl:edit")
    @Log(title = "门店管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmShop kbsmShop)
    {
        return toAjax(kbsmShopService.updateKbsmShop(kbsmShop));
    }

    /**
     * 删除门店管理
     */
    @RequiresPermissions("smdd:mdgl:remove")
    @Log(title = "门店管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmShopService.deleteKbsmShopByIds(ids));
    }
}
