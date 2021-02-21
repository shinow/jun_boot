package com.jeasyuicn.rbac.controller.system;

import com.jeasyuicn.rbac.conmmon.JsonResult;
import com.jeasyuicn.rbac.model.dao.PermissionDao;
import com.jeasyuicn.rbac.model.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.controller.system</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年11月13日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Controller
@RequestMapping("/system/permission")
@Slf4j
@Transactional(readOnly = true)
public class PermissionController {

    @Autowired
    private PermissionDao permissionDao;

    @RequestMapping
    public void index() {
    }

    @RequestMapping({"/form", "/load"})
    public String form(Long id, Model model) {
        if (id != null) {
            //编辑
            Permission permission = permissionDao.findOne(id);
            model.addAttribute("permission", permission);
        }
        return "system/permission/form";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<Permission> list() {
        List<Permission> roots = permissionDao.findAllByParentIsNull();
        return roots;
    }

    @PostMapping("/combo")
    @ResponseBody
    public List<Permission> combo() {
        List<Permission> roots = permissionDao.findAllByParentIsNull();
        return roots;
    }

    @PostMapping({"/save", "/update"})
    @ResponseBody
    @Transactional
    public JsonResult form(@Valid Permission permission, BindingResult br) {
        if (!br.hasErrors()) {
            permissionDao.save(permission);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过！");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id) {
        Permission permission = permissionDao.findOne(id);
        if (permission != null) {
            permissionDao.delete(permission);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }
}
