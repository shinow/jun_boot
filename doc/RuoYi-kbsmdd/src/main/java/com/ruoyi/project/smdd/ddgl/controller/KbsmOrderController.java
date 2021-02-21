package com.ruoyi.project.smdd.ddgl.controller;

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
import com.ruoyi.project.smdd.ddgl.domain.KbsmOrder;
import com.ruoyi.project.smdd.ddgl.service.IKbsmOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/ddgl")
public class KbsmOrderController extends BaseController
{
    private String prefix = "smdd/ddgl";

    @Autowired
    private IKbsmOrderService kbsmOrderService;

    @RequiresPermissions("smdd:ddgl:view")
    @GetMapping()
    public String ddgl()
    {
        return prefix + "/ddgl";
    }

    /**
     * 查询订单管理列表
     */
    @RequiresPermissions("smdd:ddgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmOrder kbsmOrder)
    {
        startPage();
        List<KbsmOrder> list = kbsmOrderService.selectKbsmOrderList(kbsmOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @RequiresPermissions("smdd:ddgl:export")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmOrder kbsmOrder)
    {
        List<KbsmOrder> list = kbsmOrderService.selectKbsmOrderList(kbsmOrder);
        ExcelUtil<KbsmOrder> util = new ExcelUtil<KbsmOrder>(KbsmOrder.class);
        return util.exportExcel(list, "ddgl");
    }

    /**
     * 新增订单管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单管理
     */
    @RequiresPermissions("smdd:ddgl:add")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmOrder kbsmOrder)
    {
        return toAjax(kbsmOrderService.insertKbsmOrder(kbsmOrder));
    }

    /**
     * 修改订单管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmOrder kbsmOrder = kbsmOrderService.selectKbsmOrderById(id);
        mmap.put("kbsmOrder", kbsmOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单管理
     */
    @RequiresPermissions("smdd:ddgl:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmOrder kbsmOrder)
    {
        return toAjax(kbsmOrderService.updateKbsmOrder(kbsmOrder));
    }

    /**
     * 删除订单管理
     */
    @RequiresPermissions("smdd:ddgl:remove")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmOrderService.deleteKbsmOrderByIds(ids));
    }
}
