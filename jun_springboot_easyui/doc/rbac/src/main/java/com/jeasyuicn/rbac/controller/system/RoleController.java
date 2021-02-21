package com.jeasyuicn.rbac.controller.system;

import com.jeasyuicn.rbac.conmmon.JsonResult;
import com.jeasyuicn.rbac.model.dao.PermissionDao;
import com.jeasyuicn.rbac.model.dao.RoleDao;
import com.jeasyuicn.rbac.model.entity.Permission;
import com.jeasyuicn.rbac.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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
@RequestMapping("/system/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @RequestMapping
    public void index() {
    }

    @RequestMapping("/list")
    @ResponseBody
    public Iterable list() {
        return roleDao.findAll();
    }

    @RequestMapping({"/form", "/load"})
    public String form(Long id, Model model) {
        if (id != null) {
            //编辑
            Role role = roleDao.findOne(id);
            model.addAttribute("role", role);
        }
        return "system/role/form";
    }

    @PostMapping({"/save", "/update"})
    @ResponseBody
    @Transactional
    public JsonResult form(@Valid Role role, BindingResult br) {
        if (!br.hasErrors()) {
            roleDao.save(role);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过！");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id) {
        Role role = roleDao.findOne(id);
        if (role != null) {
            roleDao.delete(role);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }

    @RequestMapping("permission/tree")
    @ResponseBody
    public List<Permission> permissionTree() {
        return permissionDao.findAllByParentIsNull();
    }

    /**
     * 获取角色对应的权限列表
     *
     * @param id
     * @return
     */
    @RequestMapping("permission/{id}")
    @ResponseBody
    public Set<Permission> permission(@PathVariable("id") Long id) {
        Role role = roleDao.findOne(id);
        return role.getPermissions();
    }

    @RequestMapping("permission/save")
    @Transactional
    @ResponseBody
    public JsonResult permissionSave(Long roleId, Long[] permissionId) {
        Role role = roleDao.findOne(roleId);
        //先清除已有角色
        role.getPermissions().clear();
        for (Long pid : permissionId) {
            role.getPermissions().add(permissionDao.findOne(pid));
        }

        roleDao.save(role);

        return JsonResult.success("授权成功！");
    }
}
