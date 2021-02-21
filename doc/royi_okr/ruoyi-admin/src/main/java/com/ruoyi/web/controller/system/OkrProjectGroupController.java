package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IOkrCycleService;
import com.ruoyi.system.service.IOkrInfoService;
import com.ruoyi.system.service.IOkrProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IOkrProjectGroupService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品项目组Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Controller
@RequestMapping("/system/group")
public class OkrProjectGroupController extends BaseController
{
    private String prefix = "system/group";

    @Autowired
    private IOkrProjectGroupService okrProjectGroupService;


    @Autowired
    private IOkrProjectService okrProjectService;


    @Autowired
    private IOkrInfoService okrInfoService;

    @Autowired
    private IOkrCycleService okrCycleService;


    @RequiresPermissions("system:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }



    /**
     * 查询产品项目组列表
     */
    @RequiresPermissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrProjectGroup okrProjectGroup)
    {
        startPage();
        List<OkrProjectGroup> list = okrProjectGroupService.selectOkrProjectGroupList(okrProjectGroup);
        return getDataTable(list);
    }


    /**
     * 通过项目关系查询OKR
     */
    @RequiresPermissions("system:projectUser:okrList")
    @PostMapping("/okrList")
    @ResponseBody
    public List<OkrInfo> okrList(OkrProjectUser okrProjectUser)
    {
        if (okrProjectUser.getCycleId() == null) //如果没有选中OKR区间，默认使用当前再用的
        {
            List<OkrCycle> useCycleList = okrCycleService.selectOkrUseCycleList();
            okrProjectUser.setCycleId(useCycleList.get(0).getId());  //默认使用的
        }
        List<OkrInfo> list = okrInfoService.selectOkrInfoListForProject(okrProjectUser);
        return list;
    }


    /**
     * 加载项目组列表树
     */
    @GetMapping("/treeData/{projectCode}")
    @ResponseBody
    public List<Ztree> treeData(@PathVariable("projectCode") String projectCode)
    {
        OkrProjectGroup projectGroup = new OkrProjectGroup();
        projectGroup.setProjectCode(projectCode);
        List<Ztree> ztrees = okrProjectGroupService.selectProjectGroupTree(projectGroup);
        return ztrees;
    }

    /**
     * 选择用户
     */
    @GetMapping("/selectUser/{groupId}")
    public String selectUser(@PathVariable("groupId") String groupId, ModelMap mmap)
    {
        mmap.put("group", okrProjectGroupService.selectOkrProjectGroupById(Long.parseLong(groupId)));
        return prefix + "/selectUser";
    }


    /**
     * 选择项目组树
     */
    @GetMapping("/selectGroupTree/{projectCode}")
    public String selectGroupTree(@PathVariable("projectCode") String projectCode, ModelMap mmap)
    {
        mmap.put("group", okrProjectGroupService.selectOkrProjectGroupByCode(projectCode));
        return prefix + "/tree";
    }

    /**
     * 查询产品项目组树形列表
     */
    @RequiresPermissions("system:group:treeList")
    @PostMapping("/treeList")
    @ResponseBody
    public List<OkrProjectGroup> treeList(OkrProjectGroup okrProjectGroup)
    {
        List<OkrProjectGroup> list = okrProjectGroupService.selectOkrProjectGroupList(okrProjectGroup);
        return list;
    }


    /**
     * 导出产品项目组列表
     */
    @RequiresPermissions("system:group:export")
    @Log(title = "产品项目组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrProjectGroup okrProjectGroup)
    {
        List<OkrProjectGroup> list = okrProjectGroupService.selectOkrProjectGroupList(okrProjectGroup);
        ExcelUtil<OkrProjectGroup> util = new ExcelUtil<OkrProjectGroup>(OkrProjectGroup.class);
        return util.exportExcel(list, "group");
    }


    /**
     * 校验项目组名称
     */
    @PostMapping("/checkGroupNameUnique")
    @ResponseBody
    public String checkGroupNameUnique(OkrProjectGroup okrProjectGroup)
    {
        return okrProjectGroupService.checkGroupNameUnique(okrProjectGroup);
    }

    /**
     * 新增产品项目组
     */
    @GetMapping("/add/{projectCode}")
    public String add(@PathVariable("projectCode") String projectCode, ModelMap mmap)
    {
        mmap.put("group", okrProjectGroupService.selectOkrProjectGroupByCode(projectCode));
        return prefix + "/add";
    }

    /**
     * 新增保存产品项目组
     */
    @RequiresPermissions("system:group:add")
    @Log(title = "产品项目组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrProjectGroup okrProjectGroup)
    {
        okrProjectGroup.setCreateTime(DateUtils.getNowDate());
        okrProjectGroup.setUpdateTime(DateUtils.getNowDate());
        okrProjectGroup.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(okrProjectGroupService.insertOkrProjectGroup(okrProjectGroup));
    }

    /**
     * 修改产品项目组
     */
    @GetMapping("/edit/{groupId}")
    public String edit(@PathVariable("groupId") Integer groupId, ModelMap mmap)
    {
        OkrProject okrProject = okrProjectService.selectOkrProjectById(groupId.longValue());
        OkrProjectGroup okrProjectGroup = okrProjectGroupService.selectOkrProjectGroupByCode(okrProject.getCode());
        mmap.put("okrProjectGroup", okrProjectGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品项目组
     */
    @RequiresPermissions("system:group:edit")
    @Log(title = "产品项目组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrProjectGroup okrProjectGroup)
    {
        return toAjax(okrProjectGroupService.updateOkrProjectGroup(okrProjectGroup));
    }

    /**
     * 删除产品项目组
     */
    @RequiresPermissions("system:group:remove")
    @Log(title = "产品项目组", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrProjectGroupService.deleteOkrProjectGroupByIds(ids));
    }
}
