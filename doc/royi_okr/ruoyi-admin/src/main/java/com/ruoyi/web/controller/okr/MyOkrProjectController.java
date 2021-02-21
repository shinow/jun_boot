package com.ruoyi.web.controller.okr;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.service.IOkrCycleService;
import com.ruoyi.system.service.IOkrProjectUserService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date 2020/6/15 14:17
 **/
@Controller
@RequestMapping("/okr/projectUser")
public class MyOkrProjectController extends BaseController {

    private String prefix = "okr";

    @Autowired
    private IOkrProjectUserService okrProjectUserService;

    @Autowired
    private IOkrCycleService okrCycleService;

    @Autowired
    private ISysUserService userService;


    @RequiresPermissions("okr:projectUser:view")
    @GetMapping()
    public String user(ModelMap mmap)
    {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        mmap.put("cycleList", list);
        return prefix + "/projectUserView";
    }
}
