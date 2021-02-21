package com.ruoyi.web.controller.okr;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * OKR信息Controller
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/okr/orgUser")
public class MyOkrOrgUserController extends BaseController {
    private String prefix = "okr";

    @Autowired
    private IOkrInfoService okrInfoService;

    @Autowired
    private ISysUserService userService;


    @Autowired
    private IOkrCycleService okrCycleService;


    @Autowired
    private IOkrSynergyService okrSynergyService;

    @Autowired
    private ISysDeptService deptService;


    @Autowired
    private IOkrProjectRoleService okrProjectRoleService;


    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;

    @RequiresPermissions("okr:orgUser:userView")
    @GetMapping()
    public String userView(ModelMap mmap) {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        List<OkrCycle> useCycleList = okrCycleService.selectOkrUseCycleList();
        mmap.put("cycleList", list);
        mmap.put("cycleId", useCycleList.get(0).getId());
        return prefix + "/okrUserManager";
    }

    @RequiresPermissions("okr:orgUser:userList")
    @PostMapping("/userList")
    @ResponseBody
    public TableDataInfo userList(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
