package com.ruoyi.web.controller.okr;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.domain.resultMapper.OkrAlignResult;
import com.ruoyi.system.domain.resultMapper.SynergyOkrInfo;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 协同关系Controller
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-07
 */
@Controller
@RequestMapping("/okr/synergy")
public class MyOkrSynergyController extends BaseController {
    private String prefix = "okr";

    @Autowired
    private IOkrInfoService okrInfoService;


    @Autowired
    private IOkrCycleService okrCycleService;


    @Autowired
    private IOkrSynergyService okrSynergyService;

    @Autowired
    private ISysDeptService deptService;


    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;

    @RequiresPermissions("okr:synergy:view")
    @GetMapping()
    public String synergy(ModelMap mmap) {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        mmap.put("cycleList", list);
        return prefix + "/synergyInfo";
    }

    /**
     * 查询OKR信息列表
     */
    @RequiresPermissions("okr:synergy:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SynergyOkrInfo> list(OkrInfo okrInfo) {
        Long userId = ShiroUtils.getUserId();
        //设置默认周期
        if (okrInfo.getCycleId()==null) {
            okrInfo.setCycleId(getUseCycleId());
        }
        List<SynergyOkrInfo> list = okrInfoService.querySynergyOkrInfoListByUserId(okrInfo, userId);
        list = changeSynergyOkr(list);
        return list;
    }

    public Long getUseCycleId()
    {
        List<OkrCycle> okrCycles = okrCycleService.selectOkrUseCycleList();
        for(int i=0;i<okrCycles.size();i++)
        {
            OkrCycle cycle = okrCycles.get(i);
            if(cycle.getCycleType().equals(0L))
            {
                return cycle.getId();
            }
        }
        return null;
    }

    /**
     * 检测OKR是否被对齐，设置对齐状态
     *
     * @param list
     * @return
     */
    public List<SynergyOkrInfo> changeSynergyOkr(List<SynergyOkrInfo> list) {
        List<SynergyOkrInfo> resutlList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            Long userId = ShiroUtils.getUserId();
            OkrInfo okrInfo = new OkrInfo();
            okrInfo.setUserId(userId);
            List<OkrAlignResult> okrInfoList = okrInfoService.queryMyOkrAlignListByUserId(okrInfo);
            Map<String,String> map = okrInfoService.getMyOkrParentIds(okrInfoList);
            for (int i = 0; i < list.size(); i++) {
                SynergyOkrInfo synergyOkrInfo = list.get(i);
                if (map.containsKey(synergyOkrInfo.getId().toString())) {
                    synergyOkrInfo.setAlign(true);
                } else {
                    synergyOkrInfo.setAlign(false);
                }
                resutlList.add(synergyOkrInfo);
            }
        }
        return resutlList;
    }


}
