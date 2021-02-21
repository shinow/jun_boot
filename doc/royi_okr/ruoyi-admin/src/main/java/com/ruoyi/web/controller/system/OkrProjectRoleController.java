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
import com.ruoyi.system.domain.OkrProjectRole;
import com.ruoyi.system.service.IOkrProjectRoleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目OKR关系Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/system/projectRole")
public class OkrProjectRoleController extends BaseController
{
    private String prefix = "system/projectRole";

    @Autowired
    private IOkrProjectRoleService okrProjectRoleService;

    @RequiresPermissions("system:projectRole:view")
    @GetMapping()
    public String projectRole()
    {
        return prefix + "/projectRole";
    }

    /**
     * 查询项目OKR关系列表
     */
    @RequiresPermissions("system:projectRole:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrProjectRole okrProjectRole)
    {
        startPage();
        List<OkrProjectRole> list = okrProjectRoleService.selectOkrProjectRoleList(okrProjectRole);
        return getDataTable(list);
    }

    /**
     * 导出项目OKR关系列表
     */
    @RequiresPermissions("system:projectRole:export")
    @Log(title = "项目OKR关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrProjectRole okrProjectRole)
    {
        List<OkrProjectRole> list = okrProjectRoleService.selectOkrProjectRoleList(okrProjectRole);
        ExcelUtil<OkrProjectRole> util = new ExcelUtil<OkrProjectRole>(OkrProjectRole.class);
        return util.exportExcel(list, "projectRole");
    }

    /**
     * 新增项目OKR关系
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目OKR关系
     */
    @RequiresPermissions("system:projectRole:add")
    @Log(title = "项目OKR关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrProjectRole okrProjectRole)
    {
        return toAjax(okrProjectRoleService.insertOkrProjectRole(okrProjectRole));
    }

    /**
     * 修改项目OKR关系
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrProjectRole okrProjectRole = okrProjectRoleService.selectOkrProjectRoleById(id);
        mmap.put("okrProjectRole", okrProjectRole);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目OKR关系
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "项目OKR关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrProjectRole okrProjectRole)
    {
        return toAjax(okrProjectRoleService.updateOkrProjectRole(okrProjectRole));
    }

    /**
     * 删除项目OKR关系
     */
    @RequiresPermissions("system:role:remove")
    @Log(title = "项目OKR关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrProjectRoleService.deleteOkrProjectRoleByIds(ids));
    }
}
