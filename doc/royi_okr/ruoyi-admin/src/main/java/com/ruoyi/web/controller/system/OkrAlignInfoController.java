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
import com.ruoyi.system.domain.OkrAlignInfo;
import com.ruoyi.system.service.IOkrAlignInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * OKR对齐关系Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/system/alignInfo")
public class OkrAlignInfoController extends BaseController
{
    private String prefix = "system/alignInfo";

    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;

    @RequiresPermissions("system:alignInfo:view")
    @GetMapping()
    public String alignInfo()
    {
        return prefix + "/alignInfo";
    }

    /**
     * 查询OKR对齐关系列表
     */
    @RequiresPermissions("system:alignInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrAlignInfo okrAlignInfo)
    {
        startPage();
        List<OkrAlignInfo> list = okrAlignInfoService.selectOkrAlignInfoList(okrAlignInfo);
        return getDataTable(list);
    }

    /**
     * 导出OKR对齐关系列表
     */
    @RequiresPermissions("system:alignInfo:export")
    @Log(title = "menus.OKR对齐关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrAlignInfo okrAlignInfo)
    {
        List<OkrAlignInfo> list = okrAlignInfoService.selectOkrAlignInfoList(okrAlignInfo);
        ExcelUtil<OkrAlignInfo> util = new ExcelUtil<OkrAlignInfo>(OkrAlignInfo.class);
        return util.exportExcel(list, "alignInfo");
    }

    /**
     * 新增OKR对齐关系
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存OKR对齐关系
     */
    @RequiresPermissions("system:alignInfo:add")
    @Log(title = "menus.OKR对齐关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OkrAlignInfo okrAlignInfo)
    {
        return toAjax(okrAlignInfoService.insertOkrAlignInfo(okrAlignInfo));
    }

    /**
     * 修改OKR对齐关系
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrAlignInfo okrAlignInfo = okrAlignInfoService.selectOkrAlignInfoById(id);
        mmap.put("okrAlignInfo", okrAlignInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存OKR对齐关系
     */
    @RequiresPermissions("system:alignInfo:edit")
    @Log(title = "menus.OKR对齐关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrAlignInfo okrAlignInfo)
    {
        return toAjax(okrAlignInfoService.updateOkrAlignInfo(okrAlignInfo));
    }

    /**
     * 删除OKR对齐关系
     */
    @RequiresPermissions("system:alignInfo:remove")
    @Log(title = "menus.OKR对齐关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrAlignInfoService.deleteOkrAlignInfoByIds(ids));
    }
}
