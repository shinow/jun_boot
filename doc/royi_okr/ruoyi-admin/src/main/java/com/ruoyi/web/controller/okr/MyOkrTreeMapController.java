package com.ruoyi.web.controller.okr;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.OkrAlignInfo;
import com.ruoyi.system.domain.OkrCycle;
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.resultMapper.MyTreeMapResult;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OKR信息Controller
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/okr/treeMap")
public class MyOkrTreeMapController extends BaseController {
    private String prefix = "okr";

    @Autowired
    private IOkrInfoService okrInfoService;


    @Autowired
    private IOkrCycleService okrCycleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IOkrSynergyService okrSynergyService;

    @Autowired
    private ISysDeptService deptService;


    @Autowired
    private IOkrProjectRoleService okrProjectRoleService;


    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;



    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @RequiresPermissions("okr:tree:view")
    @GetMapping()
    public String info(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        MyTreeMapResult treeMapResult = queryTreeMapData(user,getUseCycleId());
        String result = JsonUtils.bean2json(treeMapResult);
        mmap.put("data",result);
        return prefix + "/treeMap";
    }



    /**
     * 查询用户的OKR 视图
     */
    @GetMapping("/selectUserOkrTreeMap/{userId}/{cycleId}")
    public String selectUserOkrTreeMap(@PathVariable("userId") String userId, @PathVariable("cycleId") String cycleId, ModelMap mmap) {
        SysUser user = userService.selectUserById(Long.parseLong(userId));
        MyTreeMapResult treeMapResult = queryTreeMapData(user,Long.parseLong(cycleId));
        String result = JsonUtils.bean2json(treeMapResult);
        mmap.put("data",result);
        return prefix + "/treeMap";
    }


    /**
     * 查询OKR信息列表
     */
    @RequiresPermissions("okr:tree:list")
    @PostMapping("/list")
    @ResponseBody
    public List<OkrInfo> list(Long id, Long parentId,ModelMap mmap) {
        Long userId = ShiroUtils.getUserId();
        OkrInfo parentInfo = okrInfoService.selectOkrInfoById(id);
        if(parentInfo==null&&StringUtils.isEmpty(parentInfo.getRowId()))
        {
            return null;
        }
        List<OkrInfo> list = new ArrayList<>();
        // if 我的 okr  else 关联的  okr
        if(parentInfo.getUserId().equals(userId)){
            list = okrInfoService.selectOkrInfoListByParentId(parentInfo);
        }
        else
        {
            OkrAlignInfo okrAlignInfo  = new OkrAlignInfo();
            okrAlignInfo.setParentId(parentId);
            okrAlignInfo.setOkrId(id);
            List<OkrAlignInfo> okrAlignInfos = okrAlignInfoService.selectOkrAlignInfoList(okrAlignInfo);
            if(okrAlignInfos.size()>0)
            {
                String[] strArr = okrAlignInfos.get(0).getOkrKeys().split(",");
                List<String> params = Arrays.asList(strArr);
                list = okrInfoService.queryOkrInfoByIds(params);
            }
        }
        return list;
    }

    /**
     * 查询个人OKR信息树
     * @return
     */
    public MyTreeMapResult queryTreeMapData(SysUser user,Long cycleId)
    {
        OkrInfo info = new OkrInfo();
        info.setUserId(user.getUserId());
        info.setCycleId(cycleId);
        MyTreeMapResult treeMapResult = new MyTreeMapResult();
        List<OkrInfo> okrInfos = okrInfoService.selectOkrInfoTreeMapByUserId(info);
        List<MyTreeMapResult>  treeMapResults = okrInfoService.queryTreeMapList(okrInfos);
        long startTime=System.currentTimeMillis();   //获取开始时间
//        String key = "tree_"+user.getUserId()+"_"+cycleId;
//        boolean b = redisTemplate.hasKey(key);
//        if(b){
//            treeMapResults = (List<MyTreeMapResult>)redisTemplate.opsForList().index(key,0);
//        }
//        else
//        {
//            treeMapResults = okrInfoService.querySubTreeMapList(treeMapResults);
//            redisTemplate.opsForList().leftPush(key,treeMapResults);
//            redisTemplate.expire(key, 6, TimeUnit.HOURS);
//        }
        treeMapResults = okrInfoService.querySubTreeMapList(treeMapResults);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        treeMapResult.setChildren(treeMapResults);
        treeMapResult.setName(user.getUserName());
        treeMapResult.setImageUrl(user.getAvatar());
        treeMapResult.setTags("p");
        treeMapResult.setPositionName(user.getDept().getDeptName());
        return treeMapResult;
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




}
