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
import com.ruoyi.system.domain.OkrProjectUserRole;
import com.ruoyi.system.service.IOkrProjectUserRoleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目权限Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-30
 */
@Controller
@RequestMapping("/system/projectUserRole")
public class OkrProjectUserRoleController extends BaseController
{
    private String prefix = "system/projectRole";

    @Autowired
    private IOkrProjectUserRoleService okrProjectUserRoleService;

    @RequiresPermissions("system:projectRole:view")
    @GetMapping()
    public String projectRole()
    {
        return prefix + "/projectRole";
    }

    /**
     * 查询项目权限列表
     */
    @RequiresPermissions("system:projectRole:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrProjectUserRole okrProjectUserRole)
    {
        startPage();
        List<OkrProjectUserRole> list = okrProjectUserRoleService.selectOkrProjectUserRoleList(okrProjectUserRole);
        return getDataTable(list);
    }

    /**
     * 导出项目权限列表
     */
    @RequiresPermissions("system:projectRole:export")
    @Log(title = "项目权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrProjectUserRole okrProjectUserRole)
    {
        List<OkrProjectUserRole> list = okrProjectUserRoleService.selectOkrProjectUserRoleList(okrProjectUserRole);
        ExcelUtil<OkrProjectUserRole> util = new ExcelUtil<OkrProjectUserRole>(OkrProjectUserRole.class);
        return util.exportExcel(list, "projectRole");
    }

    /**
     * 新增项目权限
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目权限
     */
    @RequiresPermissions("system:projectRole:add")
    @Log(title = "项目权限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrProjectUserRole okrProjectUserRole)
    {
        return toAjax(okrProjectUserRoleService.insertOkrProjectUserRole(okrProjectUserRole));
    }

    /**
     * 修改项目权限
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        OkrProjectUserRole okrProjectUserRole = okrProjectUserRoleService.selectOkrProjectUserRoleById(userId);
        mmap.put("okrProjectUserRole", okrProjectUserRole);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目权限
     */
    @RequiresPermissions("system:projectRole:edit")
    @Log(title = "项目权限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrProjectUserRole okrProjectUserRole)
    {
        return toAjax(okrProjectUserRoleService.updateOkrProjectUserRole(okrProjectUserRole));
    }

    /**
     * 删除项目权限
     */
    @RequiresPermissions("system:projectRole:remove")
    @Log(title = "项目权限", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrProjectUserRoleService.deleteOkrProjectUserRoleByIds(ids));
    }
}
