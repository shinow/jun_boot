package com.ruoyi.web.controller.tool;

import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.OkrAttach;
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.domain.OkrSynergy;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * swagger OKR 临时处理接口
 *
 * @author wes
 */
@Api("临时处理接口")
@RestController
@RequestMapping("/api/okr")
public class ApiOkrInfoController {

    @Autowired
    private IOkrInfoService okrInfoService;

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

    @Autowired
    private DictService dictService;

    @Autowired
    private ISysUserService userService;


    @Autowired
    private IOkrAttachService okrAttachService;


    @ApiOperation("OKR旧数据推送提交")
    @PostMapping("/save")
    public void save(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        String data = params.get("data").toString();
        JSONArray dataArr = JSONArray.fromObject(data);
        if (dataArr.size() > 0) {
            List<OkrInfo> list = new ArrayList<>();

            List<OkrAttach> attaches = new ArrayList<>();
            Map<String,Long> map = getAllUser();
            for (int i = 0; i < dataArr.size(); i++) {
                JSONObject obj = JSONObject.fromObject(dataArr.get(i));
                if(obj!=null)
                {
                    String loginName = (String)obj.get("login_name");
                    if (map.containsKey(loginName)) {
                        OkrInfo info = new OkrInfo();
                        Long cycleId = getCycleId(obj.get("period_name").toString());
                        info.setCycleId(cycleId);
                        info.setUserId(map.get(loginName));
                        info.setRowType(Long.parseLong(obj.get("aim_type").toString()));
                        info.setRowId(obj.get("oId").toString());
                        info.setParentId(obj.get("aim_fid").toString());
                        info.setContent(obj.get("aim_name").toString());
                        info.setRowCode(obj.get("priority").toString());
                        String confidence = obj.get("aim_confidence").toString().equals("null")?"0":obj.get("aim_confidence").toString();
                        info.setConfidenceStar(confidence);
                        info.setPriority(obj.get("aim_confidence").toString());
                        info.setSortId(Long.parseLong(obj.get("priority").toString()));
                        info.setPriority("P" + obj.get("priority"));
                        info.setProcess(obj.get("aim_progress").toString());
                        info.setScoreStar(obj.get("aim_score").toString());
                        list.add(info);

                        //附加字段
                        OkrAttach attach = new OkrAttach();
                        attach.setKeyName("grade_remark");
                        attach.setOkrId(info.getRowId());
                        String remark = obj.get("remark")==null?"":obj.get("remark").toString();
                        attach.setKeyValue(remark);
                        attaches.add(attach);
//                        okrInfoService.insertOkrInfo(info);
                    } else {
                        System.out.println(obj);
                    }
                }
            }
            okrInfoService.insertOkrList(list);
            okrAttachService.insertOkrAttachList(attaches);
        }
    }


    public Map<String,Long> getAllUser()
    {
        Map<String,Long> map = new HashMap<>();
        List<SysUser> list = userService.selectAllUser();
        for(int i=0;i<list.size();i++)
        {
            SysUser user = list.get(i);
            map.put(user.getLoginName(),user.getUserId());
        }
        return map;
    }

    public Map<String,Long> getAllOkr()
    {
        Map<String,Long> map = new HashMap<>();
        List<OkrInfo> list = okrInfoService.selectOkrInfoList(new OkrInfo());
        for(int i=0;i<list.size();i++)
        {
            OkrInfo info = list.get(i);
            map.put(info.getRowId(),info.getId());
        }
        return map;
    }

    public Long getCycleId(String cycleName) {
        Long cycleId = 1L;
        if (cycleName.equals("2019-Q4")) {
            cycleId = 5L;
        }
        if (cycleName.equals("2020-Q2")) {
            cycleId = 3L;
        }
        if (cycleName.equals("2020-Q1")) {
            cycleId = 2L;
        }
        return cycleId;
    }


    @ApiOperation("OKR旧数据推送提交")
    @PostMapping("/saveOkrSynergy")
    public void saveOkrSynergy(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        String data = params.get("data").toString();
        JSONArray dataArr = JSONArray.fromObject(data);
        if (dataArr.size() > 0) {
            List<OkrSynergy> list = new ArrayList<>();
            Map<String,Long> userMap = getAllUser();
            Map<String,Long> okrMap = getAllOkr();
            for (int i = 0; i < dataArr.size(); i++) {
                JSONObject obj = JSONObject.fromObject(dataArr.get(i));
                if(obj!=null)
                {
                    String loginName = (String)obj.get("login_name");
                    if (userMap.containsKey(loginName)) {
                        OkrSynergy okrSynergy = new OkrSynergy();
                        okrSynergy.setUserId(userMap.get(loginName));
                        Long okrId = okrMap.get(obj.get("aim_id").toString());
                        okrSynergy.setOkrId(okrId);
                        list.add(okrSynergy);
                    } else {
                        System.out.println(obj);
                    }
                }
            }
            okrSynergyService.insertOkrSynergyList(list);
        }
    }


}
