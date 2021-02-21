package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IOkrProjectGroupService;
import com.ruoyi.system.service.IOkrProjectUserRoleService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.system.service.IOkrProjectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * OKR项目管理Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Controller
@RequestMapping("/system/project")
public class OkrProjectController extends BaseController
{
    private String prefix = "system/project";

    @Autowired
    private IOkrProjectService okrProjectService;

    @Autowired
    private IOkrProjectGroupService okrProjectGroupService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IOkrProjectUserRoleService okrProjectUserRoleService;


    @RequiresPermissions("system:project:view")
    @GetMapping()
    public String project()
    {
        return prefix + "/project";
    }

    /**
     * 查询OKR项目管理列表
     */
    @RequiresPermissions("system:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrProject okrProject)
    {
        startPage();
        List<OkrProject> list = okrProjectService.selectOkrProjectList(okrProject);
        return getDataTable(list);
    }

    /**
     * 查询OKR项目管理列表
     */
    @RequiresPermissions("system:project:projectList")
    @PostMapping("/projectList")
    @ResponseBody
    public  List<OkrProject> projectList(OkrProject okrProject)
    {
        List<OkrProject> list = new ArrayList<>();
        SysUser user = ShiroUtils.getSysUser();
        OkrProjectUserRole okrProjectUserRole = new OkrProjectUserRole();
        okrProjectUserRole.setUserId(user.getUserId());
        List<OkrProjectUserRole>  okrProjectUserRoles = okrProjectUserRoleService.selectOkrProjectUserRoleList(okrProjectUserRole);
        if(okrProjectUserRoles.size()>0)
        {
            String[] ids = getProjectIds(okrProjectUserRoles);
            list = okrProjectService.selectOkrProjectByIds(ids);
        }
        return list;
    }

    public String[] getProjectIds(List<OkrProjectUserRole> okrProjectUserRoles) {
        String[] ids = new String[okrProjectUserRoles.size()];
        for (int i = 0; i < okrProjectUserRoles.size(); i++) {
            ids[i] = okrProjectUserRoles.get(i).getProjectId().toString();
        }
        return ids;
    }

    /**
     * 导出OKR项目管理列表
     */
    @RequiresPermissions("system:project:export")
    @Log(title = "menus.OKR项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrProject okrProject)
    {
        List<OkrProject> list = okrProjectService.selectOkrProjectList(okrProject);
        ExcelUtil<OkrProject> util = new ExcelUtil<OkrProject>(OkrProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 新增OKR项目管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存OKR项目管理
     */
    @RequiresPermissions("system:project:add")
    @Log(title = "menus.OKR项目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrProject okrProject)
    {
        //创建默认项目组-根节点
        OkrProjectGroup okrProjectGroup  = new OkrProjectGroup();
        okrProjectGroup.setCreateBy(ShiroUtils.getLoginName());
        okrProjectGroup.setCreateTime(DateUtils.getNowDate());
        okrProjectGroup.setAncestors("0");
        okrProjectGroup.setDelFlag("0");
        okrProjectGroup.setGroupName(okrProject.getName()+"项目组");
        SysUser user = userService.selectUserById(okrProject.getProductManagerId());
        okrProjectGroup.setLeader(user.getUserName());
        okrProjectGroup.setEmail(user.getEmail());
        okrProjectGroup.setOrderNum(0);
        okrProjectGroup.setParentId(Long.parseLong("0"));
        okrProjectGroup.setPhone(user.getPhonenumber());
        okrProjectGroup.setProjectCode(okrProject.getCode());
        okrProjectGroupService.insertOkrProjectGroup(okrProjectGroup);
        return toAjax(okrProjectService.insertOkrProject(okrProject));
    }

    /**
     * 修改OKR项目管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrProject okrProject = okrProjectService.selectOkrProjectById(id);
        mmap.put("okrProject", okrProject);
        return prefix + "/edit";
    }

    /**
     * 修改保存OKR项目管理
     */
    @RequiresPermissions("system:project:edit")
    @Log(title = "menus.OKR项目管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrProject okrProject)
    {
        return toAjax(okrProjectService.updateOkrProject(okrProject));
    }

    /**
     * 删除OKR项目管理
     */
    @RequiresPermissions("system:project:remove")
    @Log(title = "menus.OKR项目管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrProjectService.deleteOkrProjectByIds(ids));
    }
}
