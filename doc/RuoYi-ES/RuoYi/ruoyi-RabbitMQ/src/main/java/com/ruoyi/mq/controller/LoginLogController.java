package com.ruoyi.mq.controller;

import java.util.List;

import com.ruoyi.mq.bean.LoginLog;
import com.ruoyi.mq.service.ILoginLogService;
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
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登录日志Controller
 * 
 * @author japhet_jiu
 * @date 2020-05-27
 */
@Controller
@RequestMapping("/system/log")
public class LoginLogController extends BaseController
{
    private String prefix = "system/log";

    @Autowired
    private ILoginLogService loginLogService;

    @RequiresPermissions("system:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询登录日志列表
     */
    @RequiresPermissions("system:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LoginLog loginLog)
    {
        startPage();
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    /**
     * 导出登录日志列表
     */
    @RequiresPermissions("system:log:export")
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LoginLog loginLog)
    {
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        ExcelUtil<LoginLog> util = new ExcelUtil<LoginLog>(LoginLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增登录日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存登录日志
     */
    @RequiresPermissions("system:log:add")
    @Log(title = "登录日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LoginLog loginLog)
    {
        return toAjax(loginLogService.insertLoginLog(loginLog));
    }

    /**
     * 修改登录日志
     */
    @GetMapping("/edit/{loginId}")
    public String edit(@PathVariable("loginId") Long loginId, ModelMap mmap)
    {
        LoginLog loginLog = loginLogService.selectLoginLogById(loginId);
        mmap.put("loginLog", loginLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存登录日志
     */
    @RequiresPermissions("system:log:edit")
    @Log(title = "登录日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LoginLog loginLog)
    {
        return toAjax(loginLogService.updateLoginLog(loginLog));
    }

    /**
     * 删除登录日志
     */
    @RequiresPermissions("system:log:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(loginLogService.deleteLoginLogByIds(ids));
    }
}
