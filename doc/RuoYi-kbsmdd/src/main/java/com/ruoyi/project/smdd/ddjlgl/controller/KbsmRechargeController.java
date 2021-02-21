package com.ruoyi.project.smdd.ddjlgl.controller;

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
import com.ruoyi.project.smdd.ddjlgl.domain.KbsmRecharge;
import com.ruoyi.project.smdd.ddjlgl.service.IKbsmRechargeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单记录管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/ddjlgl")
public class KbsmRechargeController extends BaseController
{
    private String prefix = "smdd/ddjlgl";

    @Autowired
    private IKbsmRechargeService kbsmRechargeService;

    @RequiresPermissions("smdd:ddjlgl:view")
    @GetMapping()
    public String ddjlgl()
    {
        return prefix + "/ddjlgl";
    }

    /**
     * 查询订单记录管理列表
     */
    @RequiresPermissions("smdd:ddjlgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmRecharge kbsmRecharge)
    {
        startPage();
        List<KbsmRecharge> list = kbsmRechargeService.selectKbsmRechargeList(kbsmRecharge);
        return getDataTable(list);
    }

    /**
     * 导出订单记录管理列表
     */
    @RequiresPermissions("smdd:ddjlgl:export")
    @Log(title = "订单记录管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmRecharge kbsmRecharge)
    {
        List<KbsmRecharge> list = kbsmRechargeService.selectKbsmRechargeList(kbsmRecharge);
        ExcelUtil<KbsmRecharge> util = new ExcelUtil<KbsmRecharge>(KbsmRecharge.class);
        return util.exportExcel(list, "ddjlgl");
    }

    /**
     * 新增订单记录管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单记录管理
     */
    @RequiresPermissions("smdd:ddjlgl:add")
    @Log(title = "订单记录管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmRecharge kbsmRecharge)
    {
        return toAjax(kbsmRechargeService.insertKbsmRecharge(kbsmRecharge));
    }

    /**
     * 修改订单记录管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmRecharge kbsmRecharge = kbsmRechargeService.selectKbsmRechargeById(id);
        mmap.put("kbsmRecharge", kbsmRecharge);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单记录管理
     */
    @RequiresPermissions("smdd:ddjlgl:edit")
    @Log(title = "订单记录管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmRecharge kbsmRecharge)
    {
        return toAjax(kbsmRechargeService.updateKbsmRecharge(kbsmRecharge));
    }

    /**
     * 删除订单记录管理
     */
    @RequiresPermissions("smdd:ddjlgl:remove")
    @Log(title = "订单记录管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmRechargeService.deleteKbsmRechargeByIds(ids));
    }
}
