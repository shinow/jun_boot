package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.OkrProjectUser;
import com.ruoyi.system.service.IOkrProjectUserService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目组用户关系Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Controller
@RequestMapping("/system/projectUser")
public class OkrProjectUserController extends BaseController
{
    private String prefix = "system/projectUser";

    @Autowired
    private IOkrProjectUserService okrProjectUserService;

    @Autowired
    private ISysUserService userService;


    @RequiresPermissions("system:projectUser:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/projectUser";
    }


    /**
     * 查询项目组用户关系列表
     */
    @RequiresPermissions("system:projectUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrProjectUser okrProjectUser)
    {
        startPage();
        List<OkrProjectUser> list = okrProjectUserService.selectOkrProjectUserList(okrProjectUser);
        return getDataTable(list);
    }

    /**
     * 导出项目组用户关系列表
     */
    @RequiresPermissions("system:projectUser:export")
    @Log(title = "项目组用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrProjectUser okrProjectUser)
    {
        List<OkrProjectUser> list = okrProjectUserService.selectOkrProjectUserList(okrProjectUser);
        ExcelUtil<OkrProjectUser> util = new ExcelUtil<OkrProjectUser>(OkrProjectUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增项目组用户关系
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目组用户关系
     */
    @RequiresPermissions("system:projectUser:add")
    @Log(title = "项目组用户关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrProjectUser okrProjectUser)
    {
        return toAjax(okrProjectUserService.insertOkrProjectUser(okrProjectUser));
    }


    /**
     * 组织用户关联
     */
    @Log(title = "组织结构添加用户", businessType = BusinessType.GRANT)
    @PostMapping("/saveGroupUser")
    @ResponseBody
    public AjaxResult saveGroupUser(String groupId, String userId)
    {
        OkrProjectUser groupUser = new OkrProjectUser();
        groupUser.setGroupId(Long.parseLong(groupId));
        groupUser.setUserid(Long.parseLong(userId));
        OkrProjectUser existGroupUser = okrProjectUserService.selectOkrProjectUserExist(groupUser);
        if(existGroupUser!=null)
        {
            return AjaxResult.error("不能重复设置，请检查！");
        }
        return toAjax(okrProjectUserService.insertOkrProjectUser(groupUser));
    }

    /**
     * 修改项目组用户关系
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrProjectUser okrProjectUser = okrProjectUserService.selectOkrProjectUserById(id);
        mmap.put("okrProjectUser", okrProjectUser);
        return prefix + "/edit";
    }


    /**
     * 修改保存项目组用户关系
     */
    @RequiresPermissions("system:projectUser:edit")
    @Log(title = "项目组用户关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrProjectUser okrProjectUser)
    {
        return toAjax(okrProjectUserService.updateOkrProjectUser(okrProjectUser));
    }

    /**
     * 删除项目组用户关系
     */
    @RequiresPermissions("system:projectUser:remove")
    @Log(title = "项目组用户关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrProjectUserService.deleteOkrProjectUserByIds(ids));
    }
}
