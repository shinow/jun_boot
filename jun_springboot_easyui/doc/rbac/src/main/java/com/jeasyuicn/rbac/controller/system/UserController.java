package com.jeasyuicn.rbac.controller.system;

import com.jeasyuicn.rbac.conmmon.JsonResult;
import com.jeasyuicn.rbac.model.dao.RoleDao;
import com.jeasyuicn.rbac.model.dao.UserDao;
import com.jeasyuicn.rbac.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/system/user")
@Slf4j
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @RequestMapping
    public void index() {
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        PageRequest pr = new PageRequest(page - 1, rows, Sort.Direction.DESC, "id");
        Page<User> pageData = userDao.findAll(pr);
        Map<String, Object> data = new HashMap<>();
        //总记录数
        data.put("total", pageData.getTotalElements());
        //当前页数据
        data.put("rows", pageData.getContent());
        return data;
    }

    @RequestMapping({"/form", "/load"})
    public String form(Long id, Model model) {
        if (id != null) {
            //编辑
            User user = userDao.findOne(id);
            model.addAttribute("user", user);
        }
        //查询角色
        model.addAttribute("roles", roleDao.findAllByEnable(true));

        return "system/user/form";
    }

    @PostMapping({"/save", "/update"})
    @ResponseBody
    @Transactional
    public JsonResult form(@Valid User user, BindingResult br) {
        if (!br.hasErrors()) {
            if (user.getId() == null) {
                //md5加密密码
                user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            } else {
                User org = userDao.findOne(user.getId());
                if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                    user.setPassword(DigestUtils.md5Hex(user.getPassword()));
                } else {
                    //不修改密码
                    user.setPassword(org.getPassword());
                }
            }
            userDao.save(user);
            return JsonResult.success();
        } else {
            return JsonResult.error("校验不通过！");
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @Transactional
    public JsonResult delete(Long id) {
        User user = userDao.findOne(id);
        if (user != null) {
            userDao.delete(user);
            return JsonResult.success();
        }
        return JsonResult.error("数据不存在！");
    }

    @PostMapping("/check")
    @ResponseBody
    public String check(String account) {
        if (userDao.countByAccount(account) == 0) {
            return "true";
        }
        return "false";
    }

}
