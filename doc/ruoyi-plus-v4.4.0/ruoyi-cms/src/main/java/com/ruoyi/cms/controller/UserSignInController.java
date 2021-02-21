package com.ruoyi.cms.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
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
import com.ruoyi.cms.domain.UserSignIn;
import com.ruoyi.cms.service.IUserSignInService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户签到Controller
 * 
 * @author markbro
 * @date 2020-02-03
 */
@Controller
@RequestMapping("/cms/userSignIn")
public class UserSignInController extends BaseController
{
    private String prefix = "cms/userSignIn";

    @Autowired
    private IUserSignInService userSignInService;

    @RequiresPermissions("cms:userSignIn:view")
    @GetMapping()
    public String userSignIn()
    {
        return prefix + "/userSignIn";
    }

    /**
     * 查询用户签到列表
     */
    @RequiresPermissions("cms:userSignIn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserSignIn userSignIn)
    {
        startPage();
        List<UserSignIn> list = userSignInService.selectUserSignInList(userSignIn);
        return getDataTable(list);
    }

    /**
     * 导出用户签到列表
     */
    @RequiresPermissions("cms:userSignIn:export")
    @Log(title = "用户签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserSignIn userSignIn)
    {
        List<UserSignIn> list = userSignInService.selectUserSignInList(userSignIn);
        ExcelUtil<UserSignIn> util = new ExcelUtil<UserSignIn>(UserSignIn.class);
        return util.exportExcel(list, "userSignIn");
    }

    /**
     * 新增用户签到
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户签到
     */
    @RequiresPermissions("cms:userSignIn:add")
    @Log(title = "用户签到", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserSignIn userSignIn)
    {
        return toAjax(userSignInService.insertUserSignIn(userSignIn));
    }

    /**
     * 修改用户签到
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserSignIn userSignIn = userSignInService.selectUserSignInById(id);
        mmap.put("userSignIn", userSignIn);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户签到
     */
    @RequiresPermissions("cms:userSignIn:edit")
    @Log(title = "用户签到", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserSignIn userSignIn)
    {
        return toAjax(userSignInService.updateUserSignIn(userSignIn));
    }

    /**
     * 删除用户签到
     */
    @RequiresPermissions("cms:userSignIn:remove")
    @Log(title = "用户签到", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userSignInService.deleteUserSignInByIds(ids));
    }


    @PostMapping( "/signIn")
    @ResponseBody
    public AjaxResult signIn()
    {
        SysUser user= ShiroUtils.getSysUser();
        return userSignInService.signIn(user.getUserId().toString());
    }

    @PostMapping( "/initSignPage")
    @ResponseBody
    public AjaxResult initSignPage()
    {
        SysUser user= ShiroUtils.getSysUser();
        return userSignInService.initSignPage(user.getUserId().toString());
    }


}
