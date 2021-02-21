package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IOkrCycleService;
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
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.service.IOkrInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * OKR信息Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/system/info")
public class OkrInfoController extends BaseController
{
    private String prefix = "system/info";

    @Autowired
    private IOkrInfoService okrInfoService;

    @Autowired
    private IOkrCycleService okrCycleService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info(ModelMap mmap)
    {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        mmap.put("cycleList", list);
        return prefix + "/info";
    }

//    /**
//     * 查询OKR信息列表
//     */
//    @RequiresPermissions("system:info:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(OkrInfo okrInfo)
//    {
//        startPage();
//        List<OkrInfo> list = okrInfoService.selectOkrInfoList(okrInfo);
//        return getDataTable(list);
//    }

    /**
     * 查询OKR信息列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public List<OkrInfo> list(OkrInfo okrInfo)
    {
        SysUser user = ShiroUtils.getSysUser();
        if(okrInfo.getCycleId()==null) //如果没有选中OKR区间，默认使用当前再用的
        {
            List<OkrCycle> useCycleList = okrCycleService.selectOkrUseCycleList();
            okrInfo.setCycleId(useCycleList.get(0).getId());  //默认使用的
        }
        if(okrInfo.getDeptId()==null)
        {
            okrInfo.setDeptId(user.getDeptId().toString());
        }
        List<OkrInfo> list = okrInfoService.selectOkrInfoList(okrInfo);
        return list;
    }


    /**
     * 导出OKR信息列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "OKR信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrInfo okrInfo)
    {
        List<OkrInfo> list = okrInfoService.selectOkrInfoList(okrInfo);
        ExcelUtil<OkrInfo> util = new ExcelUtil<OkrInfo>(OkrInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增OKR信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存OKR信息
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "OKR信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrInfo okrInfo)
    {
        return toAjax(okrInfoService.insertOkrInfo(okrInfo));
    }

    /**
     * 修改OKR信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(id);
        mmap.put("okrInfo", okrInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存OKR信息
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "OKR信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrInfo okrInfo)
    {
        return toAjax(okrInfoService.updateOkrInfo(okrInfo));
    }

    /**
     * 删除OKR信息
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "OKR信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrInfoService.deleteOkrInfoByIds(ids));
    }
}
