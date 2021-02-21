package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OkrAttach;
import com.ruoyi.system.service.IOkrAttachService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * OKR附加属性表Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/system/attach")
public class OkrAttachController extends BaseController
{
    private String prefix = "system/attach";

    @Autowired
    private IOkrAttachService okrAttachService;

    @RequiresPermissions("system:attach:view")
    @GetMapping()
    public String attach()
    {
        return prefix + "/attach";
    }

    /**
     * 查询OKR附加属性表列表
     */
    @RequiresPermissions("system:attach:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrAttach okrAttach)
    {
        startPage();
        List<OkrAttach> list = okrAttachService.selectOkrAttachList(okrAttach);
        return getDataTable(list);
    }

    /**
     * 导出OKR附加属性表列表
     */
    @RequiresPermissions("system:attach:export")
    @Log(title = "OKR附加属性表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrAttach okrAttach)
    {
        List<OkrAttach> list = okrAttachService.selectOkrAttachList(okrAttach);
        ExcelUtil<OkrAttach> util = new ExcelUtil<OkrAttach>(OkrAttach.class);
        return util.exportExcel(list, "attach");
    }

    /**
     * 新增OKR附加属性表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存OKR附加属性表
     */
    @RequiresPermissions("system:attach:add")
    @Log(title = "OKR附加属性表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrAttach okrAttach)
    {
        return toAjax(okrAttachService.insertOkrAttach(okrAttach));
    }

    /**
     * 修改OKR附加属性表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrAttach okrAttach = okrAttachService.selectOkrAttachById(id);
        mmap.put("okrAttach", okrAttach);
        return prefix + "/edit";
    }

    /**
     * 修改保存OKR附加属性表
     */
    @RequiresPermissions("system:attach:edit")
    @Log(title = "OKR附加属性表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrAttach okrAttach)
    {
        return toAjax(okrAttachService.updateOkrAttach(okrAttach));
    }

    /**
     * 删除OKR附加属性表
     */
    @RequiresPermissions("system:attach:remove")
    @Log(title = "OKR附加属性表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrAttachService.deleteOkrAttachByIds(ids));
    }
}
