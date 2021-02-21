package com.ruoyi.project.smdd.yhgl.controller;

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
import com.ruoyi.project.smdd.yhgl.domain.KbsmUser;
import com.ruoyi.project.smdd.yhgl.service.IKbsmUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Controller
@RequestMapping("/smdd/yhgl")
public class KbsmUserController extends BaseController
{
    private String prefix = "smdd/yhgl";

    @Autowired
    private IKbsmUserService kbsmUserService;

    @RequiresPermissions("smdd:yhgl:view")
    @GetMapping()
    public String yhgl()
    {
        return prefix + "/yhgl";
    }

    /**
     * 查询用户管理列表
     */
    @RequiresPermissions("smdd:yhgl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KbsmUser kbsmUser)
    {
        startPage();
        List<KbsmUser> list = kbsmUserService.selectKbsmUserList(kbsmUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @RequiresPermissions("smdd:yhgl:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KbsmUser kbsmUser)
    {
        List<KbsmUser> list = kbsmUserService.selectKbsmUserList(kbsmUser);
        ExcelUtil<KbsmUser> util = new ExcelUtil<KbsmUser>(KbsmUser.class);
        return util.exportExcel(list, "yhgl");
    }

    /**
     * 新增用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户管理
     */
    @RequiresPermissions("smdd:yhgl:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KbsmUser kbsmUser)
    {
        return toAjax(kbsmUserService.insertKbsmUser(kbsmUser));
    }

    /**
     * 修改用户管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KbsmUser kbsmUser = kbsmUserService.selectKbsmUserById(id);
        mmap.put("kbsmUser", kbsmUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户管理
     */
    @RequiresPermissions("smdd:yhgl:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmUser kbsmUser)
    {
        return toAjax(kbsmUserService.updateKbsmUser(kbsmUser));
    }

    /**
     * 删除用户管理
     */
    @RequiresPermissions("smdd:yhgl:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kbsmUserService.deleteKbsmUserByIds(ids));
    }
}
