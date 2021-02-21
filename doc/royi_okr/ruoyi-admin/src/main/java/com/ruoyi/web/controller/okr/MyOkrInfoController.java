package com.ruoyi.web.controller.okr;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.EnumComment;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.resultMapper.OkrAlignResult;
import com.ruoyi.system.domain.resultMapper.OkrProjectRoleResult;
import com.ruoyi.system.domain.resultMapper.SynergyOkrInfo;
import com.ruoyi.system.service.*;
import com.ruoyi.system.view.UserZtree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * OKR信息Controller
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/okr/info")
public class MyOkrInfoController extends BaseController {
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
    private IOkrProjectRoleService okrProjectRoleService;

    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;

    @Autowired
    private DictService dictService;

    @Autowired
    private IOkrAttachService okrAttachService;

    @Autowired
    private IOkrProjectUserRoleService okrProjectUserRoleService;

    @Autowired
    private IOkrCommentService okrCommentService;

    @Autowired
    private ISysUserService userService;

    /**
     * 加载协同树
     */
    @GetMapping("/okrSynergyTreeData")
    @ResponseBody
    public List<UserZtree> okrSynergyTreeData(OkrInfo okrInfo) {
        List<OkrSynergy> okrSynergyist = okrSynergyService.selectOkrSynergyByOkrId(okrInfo.getId());
        List<UserZtree> ztrees = deptService.queryOkrDeptTree(getUserIds(okrSynergyist));
        return ztrees;
    }


    /**
     * 获取用户userid清单
     * @param okrSynergyist
     * @return
     */
    public List<String> getUserIds(List<OkrSynergy> okrSynergyist) {
        List<String> list = new ArrayList<>();
        for (OkrSynergy synergy : okrSynergyist) {
            list.add(synergy.getUserId().toString());
        }
        return list;
    }


    /**
     * 选择OKR
     */
    @GetMapping("/selectOkr/{okrId}")
    public String selectOkr(@PathVariable("okrId") String okrId, ModelMap mmap) {
        List<OkrCycle> list = okrCycleService.selectOkrUseCycleList();
        mmap.put("parentOkrId", okrId);
        return prefix + "/selectOkr";
    }

    /**
     * 查看协同界面
     */
    @GetMapping("/synergyUserView/{okrId}")
    public String synergyUserView(@PathVariable("okrId") Long okrId, ModelMap mmap)
    {
        mmap.put("okrId", okrId);
        return prefix + "/synergyOkrUser";
    }

    /**
     * 查询OKR协同用户列表
     * @param okrId
     * @return
     */
    @RequiresPermissions("okr:info:synergyOkrUsers")
    @PostMapping("/synergyOkrUsers")
    @ResponseBody
    public TableDataInfo synergyOkrUsers(String okrId)
    {
        startPage();
        List<SysUser> list = userService.selectUserListForOkrId(Long.parseLong(okrId));
        return getDataTable(list);
    }


    /**
     * 追加KR
     */
    @GetMapping("/addKResult/{okrId}")
    public String addKResult(@PathVariable("okrId") String okrId, ModelMap mmap) {
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(Long.parseLong(okrId));
        mmap.put("okrInfo", okrInfo);
        return prefix + "/addKResult";
    }


    /**
     * 待改善-在service层进行数据封装
     * 保存OKR对齐关系
     */
    @Log(title = "保存OKR对齐关系", businessType = BusinessType.GRANT)
    @PostMapping("/saveAlignOkr")
    @ResponseBody
    public AjaxResult saveAlignOkr(String ids, String parentId) {
        //查询当前OKR是否和协同OKR已经进行过对齐？if（对齐）更新 else（不对齐）添加
        String[] strArr = ids.split(",");
        List<String> params = Arrays.asList(strArr);
        List<OkrInfo> okrInfoList = okrInfoService.queryOkrInfoByIds(params);
        Map<String, String> resultMap = groupOkrInfo(okrInfoList);

        //检测是否对齐过,主动对齐和被动对齐,如果没有对齐过直接添加，如果对齐过，进行数据更新操作
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            OkrAlignInfo okrAlignInfo = new OkrAlignInfo();
            okrAlignInfo.setOkrId(Long.parseLong(parentId));
            okrAlignInfo.setParentId(Long.parseLong(entry.getKey()));
            List<OkrAlignInfo> okrAlignInfos = okrAlignInfoService.selectOkrAlignInfoList(okrAlignInfo);
            if (okrAlignInfos != null && okrAlignInfos.size() > 0) {
                return AjaxResult.error("已被对齐无法反向对齐！");
            }
        }

        //查询当前用户对齐当前OKR的信息，清空
        OkrInfo okrInfo = new OkrInfo();
        Long userId = ShiroUtils.getUserId();
        okrInfo.setUserId(userId);
        okrInfo.setParentOkrId(parentId);
        List<OkrAlignResult> alignResults = okrInfoService.queryMyOkrAlignListByUserId(okrInfo);
        String alignIds = StringUtils.join(getAlignArr(alignResults), ",");
        if (alignIds != null) {
            okrAlignInfoService.deleteOkrAlignInfoByIds(alignIds);
        }
        if (!"".equals(strArr[0])) {
            //数据验证通过之后，进行数据增加和修改操作
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                OkrAlignInfo okrAlignInfo = new OkrAlignInfo();
                okrAlignInfo.setOkrId(Long.parseLong(entry.getKey()));
                okrAlignInfo.setParentId(Long.parseLong(parentId));
                okrAlignInfo.setOkrKeys(entry.getValue());
                List<OkrAlignInfo> okrAlignInfos = okrAlignInfoService.selectOkrAlignInfoList(okrAlignInfo);
                if (okrAlignInfos == null || okrAlignInfos.size() == 0) {
                    okrAlignInfoService.insertOkrAlignInfo(okrAlignInfo);
                }
            }
        }
        return AjaxResult.success();
    }

    /**
     * 获取 OKR对齐关系的数据清空
     *
     * @param alignResults
     * @return
     */
    public List<String> getAlignArr(List<OkrAlignResult> alignResults) {
        if (alignResults != null && alignResults.size() > 0) {
            List<String> resultList = new ArrayList<>();
            int len = alignResults.size();
            String[] ids = new String[len];
            for (int i = 0; i < len; i++) {
                if (alignResults.get(i).getrId() != null) {
                    resultList.add(alignResults.get(i).getrId());
                }
            }
            return resultList;
        }
        return null;
    }

    /**
     * 对齐OKR数据进行分组
     * O对O，假如是K找到K对应的O进行分组
     *
     * @param okrInfoList
     * @return
     */
    public Map<String, String> groupOkrInfo(List<OkrInfo> okrInfoList) {
        Map<String, List<String>> map = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();
        if (okrInfoList != null && okrInfoList.size() > 0) {
            List<String> sunList = new ArrayList<>();
            for (int i = 0; i < okrInfoList.size(); i++) {
                OkrInfo okrInfo = okrInfoList.get(i);
                if (okrInfo.getRowType().equals(0L))  //如果是目标
                {
                    map.put(okrInfo.getId().toString(), sunList);
                } else {
                    OkrInfo parentOkrInfo = okrInfoService.selectOkrInfoByParentId(okrInfo.getParentId());
                    if (map.containsKey(parentOkrInfo.getId().toString())) {
                        sunList = map.get(parentOkrInfo.getId().toString());
                        sunList.add(okrInfo.getId().toString());
                    } else {
                        sunList = new ArrayList<>();
                        sunList.add(okrInfo.getId().toString());
                        map.put(parentOkrInfo.getId().toString(), sunList);
                    }
                }
            }

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                resultMap.put(entry.getKey(), String.join(",", entry.getValue()));
            }
        }
        return resultMap;
    }


    @RequiresPermissions("okr:info:view")
    @GetMapping()
    public String info(ModelMap mmap) {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        mmap.put("cycleList", list);
        return prefix + "/info";
    }

    /**
     * 查询OKR信息列表
     */
    @RequiresPermissions("okr:info:list")
    @PostMapping("/list")
    @ResponseBody
    public List<OkrInfo> list(OkrInfo okrInfo, ModelMap mmap) {
        List<OkrInfo> list = getMyUseOkrList(okrInfo);
        return list;
    }

    /**
     * 查询OKR信息列表
     */
    @RequiresPermissions("okr:info:alignList")
    @PostMapping("/alignList")
    @ResponseBody
    public List<OkrInfo> alignList(OkrInfo okrInfo, ModelMap mmap) {
        Long userId = ShiroUtils.getUserId();
//        if(okrInfo.getCycleId()==null) //如果没有选中OKR区间，默认使用当前再用的
//        {
//            List<OkrCycle> useCycleList = okrCycleService.selectOkrUseCycleList();
//            okrInfo.setCycleId(useCycleList.get(0).getId());  //默认使用的
//        }
//        List<OkrInfo> list = okrInfoService.selectOkrInfoListByUserId(okrInfo, userId);
        List<OkrInfo> list = getMyUseOkrList(okrInfo);
        okrInfo.setUserId(userId);
        List<OkrAlignResult> okrInfoList = okrInfoService.queryMyOkrAlignListByUserId(okrInfo);
        Map<String, String> okrMap = okrInfoService.getMyOkrIds(okrInfoList);
        list = okrInfoService.changeSelectOkr(list, okrMap);
        return list;
    }


    public List<OkrInfo> getMyUseOkrList(OkrInfo okrInfo) {
        Long userId = ShiroUtils.getUserId();
        if (okrInfo.getCycleId() == null) //如果没有选中OKR区间，默认使用当前再用的
        {
            List<OkrCycle> useCycleList = okrCycleService.selectOkrUseCycleList();
            okrInfo.setCycleId(useCycleList.get(0).getId());  //默认使用的
        }
        List<OkrInfo> list = okrInfoService.selectOkrInfoListByUserId(okrInfo, userId);
        return list;
    }

    /**
     * 查询OKR生效的周期列表
     */
    @RequiresPermissions("okr:cycle:useList")
    @PostMapping("/useList")
    @ResponseBody
    public List<OkrCycle> useList(OkrCycle okrCycle) {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(okrCycle);
        return list;
    }

    /**
     * 导出OKR信息列表
     */
    @RequiresPermissions("okr:info:export")
    @Log(title = "OKR信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrInfo okrInfo) {
        List<OkrInfo> list = okrInfoService.selectOkrInfoList(okrInfo);
        ExcelUtil<OkrInfo> util = new ExcelUtil<OkrInfo>(OkrInfo.class);
        return util.exportExcel(list, "info");
    }


    /**
     * 新增OKR信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        String language = ShiroUtils.getSysUser().getLanguage();
        mmap.put("okr_quar_info", transInfo(dictService.getType("okr_quarterly_info"), language));
        mmap.put("okr_year_info", transInfo(dictService.getType("okr_year_info"), language));
        mmap.put("okr_kr_placeholder", transInfo(dictService.getType("okr_kr_placeholder"), language));
        mmap.put("okr_o_placeholder", transInfo(dictService.getType("okr_o_placeholder"), language));
        return prefix + "/add";
    }


    /**
     * 过滤字符
     */
    public String transInfo(List<SysDictData> info, String language) {
        String[] languageArr = language.replace("-", "_").split("_");
        if (languageArr.length > 1) language = languageArr[0];
        for (int i = 0; i < info.size(); i++) {
            String[] labels = info.get(i).getDictLabel().replace("-", "_").split("_");
            if (labels.length == 3 && language.equals(labels[2])) {
                return info.get(i).getDictValue();
            }
        }
        return info.get(0).getDictValue();
    }


    /**
     * 新增保存OKR信息
     */
    @Transactional
    @RequiresPermissions("okr:info:add")
    @Log(title = "OKR信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam Map<String, Object> params, @RequestParam(required = false, value = "seasonItem[]") List<String> seasonItem, @RequestParam(required = false, value = "yearItem[]") List<String> yearItem) {
        SysUser user = ShiroUtils.getSysUser();
        String seasonObjective = (String) params.get("seasonObjective");
        String yearObjective = (String) params.get("yearObjective");
        List<OkrCycle> cycleList = okrCycleService.selectOkrUseCycleList();
        Long yearCycleId = getCycleId(cycleList, 1);
        Long seasonCycleId = getCycleId(cycleList, 0);
        if (!StringUtils.isEmpty(seasonObjective))   //判断季度OKR是否为空
        {
            saveOkrInfo(user.getUserId(), seasonObjective, seasonCycleId, seasonItem);
        }
        if (!StringUtils.isEmpty(yearObjective))   //判断年度OKR是否为空
        {
            saveOkrInfo(user.getUserId(), yearObjective, yearCycleId, yearItem);
        }
        return AjaxResult.success();
    }


    /**
     * 批量选择用户
     */
    @GetMapping("/selectUser/{okrIds}")
    public String selectUser(@PathVariable("okrIds") String okrIds, ModelMap mmap) {
        mmap.put("okrIds", okrIds);
        return prefix + "/selectOkrUsers";
    }


    /**
     * 批量选择项目管理员
     */
    @GetMapping("/selectProjectUser/{projectIds}")
    public String selectProjectUser(@PathVariable("projectIds") String projectIds, ModelMap mmap) {
        mmap.put("projectIds", projectIds);
        return prefix + "/selectProjectUsers";
    }


    /**
     * 查询用户的OKR清单
     */
    @GetMapping("/selectUserOkrList/{userId}/{cycleId}")
    public String selectUserOkrList(@PathVariable("userId") String userId, @PathVariable("cycleId") String cycleId, ModelMap mmap) {
        OkrInfo okrInfo = new OkrInfo();
        okrInfo.setUserId(Long.parseLong(userId));
        okrInfo.setCycleId(Long.parseLong(cycleId));
        okrInfo.setRowType(0L);
        List<OkrInfo> list = okrInfoService.selectOkrInfoList(okrInfo);
        List<ModelMap> okrMap = getOkrMapListDetail(list);
        mmap.put("okrMapList", okrMap);
        if (okrMap.size() > 0) {
            return prefix + "/viewMyOkrList";
        }
        return prefix + "/none";
    }

    /**
     * 查询用户的OKR清单
     */
    @GetMapping("/viewUserOkrList/{userId}/{cycleId}")
    public String viewUserOkrList(@PathVariable("userId") String userId, @PathVariable("cycleId") String cycleId, ModelMap mmap) {
        List<OkrCycle> list = okrCycleService.selectOkrCycleList(new OkrCycle());
        mmap.put("cycleList", list);
        mmap.put("userId", userId);
        return prefix + "/viewMyOkrInfo";
    }


    /**
     * 查询个人OKR信息列表
     */
    @PostMapping("/viewList")
    @ResponseBody
    public List<OkrInfo> viewList(OkrInfo okrInfo, ModelMap mmap) {
        List<OkrInfo> list = okrInfoService.selectOkrInfoListByUserId(okrInfo, okrInfo.getUserId());
        return list;
    }


    /**
     * @desc 返回携带详情的OKR
     */
    public List<ModelMap> getOkrMapListDetail(List<OkrInfo> list)
    {

        List<ModelMap> parentList = new ArrayList<>();
        if (list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                ModelMap mmap = new ModelMap();
                OkrInfo info = list.get(j);
                mmap = getOkrDetail(info.getId(), mmap);
                parentList.add(mmap);
            }
        }
        return parentList;
    }

    /**
     * 追加Key result 信息
     */
    @Log(title = "OKR保存备注信息", businessType = BusinessType.INSERT)
    @PostMapping("/saveOkrAttach")
    @ResponseBody
    public AjaxResult saveOkrAttach(@RequestParam Map<String, String> params) {
        Integer val = saveAttach(params);
        return toAjax(val);
    }


    /**
     *  添加 meta 字段信息
     * @return 成功与否标识符
     */
    public Integer saveAttach( Map<String, String> params){
        Integer val = 0;
        String okrId    = params.get("okrId");
        String keyName  = params.get("keyName");
        String keyValue = params.get("keyValue");
        if (StringUtils.isEmpty(okrId)) {
            return val;
        }
        OkrAttach okrAttach = new OkrAttach();
        okrAttach.setKeyName(keyName);
        okrAttach.setKeyValue(keyValue);
        okrAttach.setOkrId(okrId);
        List<OkrAttach> okrAttaches = okrAttachService.selectOkrAttachList(okrAttach);
        if (okrAttaches.size() > 0) {
            OkrAttach okrExistAttach = okrAttaches.get(0);
            if(StringUtils.isNotEmpty(keyValue)){
                okrExistAttach.setKeyValue(keyValue);
                val = okrAttachService.updateOkrAttach(okrExistAttach);
            }else{
                val = okrAttachService.deleteOkrAttachById(okrExistAttach.getId());
            }
        } else {
            val = okrAttachService.insertOkrAttach(okrAttach);
        }
        return val;
    }


    /**
     * 追加Key result 信息
     */
    @RequiresPermissions("okr:info:saveKeyResult")
    @Log(title = "追加Key result 信息", businessType = BusinessType.INSERT)
    @PostMapping("/saveKeyResult")
    @ResponseBody
    public AjaxResult saveKeyResult(OkrInfo okrInfo) {
        SysUser user = ShiroUtils.getSysUser();
        String objRowId = new MakeOrderNumUtil().makeOrderNum();
        String content = okrInfo.getContent();
        okrInfo = okrInfoService.selectOkrInfoById(okrInfo.getId());
        //获取最大的OKR--设置查询条件
        okrInfo.setParentId(okrInfo.getRowId());
        OkrInfo lastOkrInfo = okrInfoService.queryMaxKeyResultByUserId(okrInfo);
        OkrInfo keyResult = new OkrInfo();
        int rowIndex = 0;
        if (lastOkrInfo != null) {
            rowIndex = Integer.parseInt(lastOkrInfo.getRowCode());
        }
        int rowCode = rowIndex + 1;
        keyResult.setRowCode(rowCode + "");
        keyResult.setRowId(objRowId);
        keyResult.setContent(content);
        keyResult.setUserId(user.getUserId());
        keyResult.setCycleId(okrInfo.getCycleId());
        keyResult.setParentId(okrInfo.getRowId());
        keyResult.setRowType(1L);

        //更新自身信息 如果更新的是KR侧同步相关的O信息
        Integer flag = okrInfoService.insertOkrInfo(keyResult);
        if (flag > 0 && keyResult.getRowType() == 1) {
            flag = okrInfoService.updateOInfo(keyResult);
        }
        return toAjax(flag);
    }

    /**
     * 加工数据
     *
     * @param userId
     * @param content
     * @param cycleId
     * @param item
     */
    @Transactional
    public void saveOkrInfo(Long userId, String content, Long cycleId, List<String> item) {
        String objRowId = new MakeOrderNumUtil().makeOrderNum();
        OkrInfo yearOkrInfo = returnObjective(objRowId, userId, content, cycleId);
        //获取最大的OKR
        OkrInfo lastOkrInfo = okrInfoService.queryMaxOkrInfoByUserId(yearOkrInfo);
        int rowIndex = 0;
        if (lastOkrInfo != null) {
            rowIndex = Integer.parseInt(lastOkrInfo.getRowCode());
        }
        int rowCode = rowIndex + 1;
        yearOkrInfo.setRowCode(rowCode + "");
        //存储年度目标
        okrInfoService.insertOkrInfo(yearOkrInfo);
        //检查是否存在KR--如果存在--》存储对应的KR
        for (int i = 0; i < item.size(); i++) {
            int row = 1 + i;
            OkrInfo yearKeyResult = returnKeyResult(objRowId, userId, item.get(i), cycleId);
            yearKeyResult.setRowCode(row + "");
            okrInfoService.insertOkrInfo(yearKeyResult);
        }
    }

    /**
     * 构造Objective
     *
     * @param objRowId
     * @param userId
     * @param objective
     * @param cycleId
     * @return
     */
    public OkrInfo returnObjective(String objRowId, Long userId, String objective, Long cycleId) {
        OkrInfo okrInfo = new OkrInfo();
        okrInfo.setRowId(objRowId);
        okrInfo.setUserId(userId);
        okrInfo.setContent(objective);
        okrInfo.setCycleId(cycleId);
        return okrInfo;
    }

    /**
     * 构造KeyResult
     *
     * @param parentId
     * @param userId
     * @param objective
     * @param cycleId
     * @return
     */
    public OkrInfo returnKeyResult(String parentId, Long userId, String objective, Long cycleId) {
        OkrInfo okrInfo = new OkrInfo();
        String rowId = new MakeOrderNumUtil().makeOrderNum();
        okrInfo.setRowId(rowId);
        okrInfo.setParentId(parentId);
        okrInfo.setUserId(userId);
        okrInfo.setContent(objective);
        okrInfo.setCycleId(cycleId);
        okrInfo.setRowType(1L);
        return okrInfo;
    }

    /**
     * 获取生效的OKR类型
     *
     * @param cycleList 生效数据
     * @param type      1为年度，0为季度
     * @return
     */
    public Long getCycleId(List<OkrCycle> cycleList, int type) {

        for(int i=0;i<cycleList.size();i++)
        {
            OkrCycle cycle = cycleList.get(i);
            if(cycle.getCycleType().equals(0L))
            {
                return cycle.getId();
            }
        }
        return null;
    }


    /**
     * 查询OKR 项目列表
     */
    protected List<OkrProjectRoleResult> okrInfoProjectList(Long id) {
        //查询当前用户是否存在于某个项目组
        SysUser user = ShiroUtils.getSysUser();
        Map<String, Long> params = new HashMap<>();
        params.put("userId", user.getUserId());
        params.put("okrId", id);
        //检查是否需要勾选
        return selectedCheck(id, okrInfoService.queryProjectListForUserId(params));
    }

    /**
     * 查看OKR详情
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
        // 查询OKR信息
        mmap = getOkrDetail(id, mmap);

        mmap.put("user", ShiroUtils.getSysUser());

        mmap.put("comments",getObjectComment(id, EnumComment.COMMENT_TYPE_FOR_O.getValue()));

        //返回模板
        return prefix + "/viewEditObject";
    }


    /**
     * 1
     * 查看OKR详情
     * 和 查看别人的OKR 详情页
     * 这个查看的不是自己的OKR,
     * 是别人的OKR,所以不能修操作。
     * 本处的模板文件和管理者使用的是一个模板文件，
     * 所以管理员和 "我的协同OKR都可以看到相关的信息"
     * 2
     * 当前用户信息
     * 3
     * 截取默认数量的评论
     */
    @GetMapping("/managerView/{id}")
    public String managerView(@PathVariable("id") Long id, ModelMap mmap) {

        //表示符
        boolean isIn=false;
        //当前用户信息
        mmap.put("user", ShiroUtils.getSysUser());
        //当前OKR信息
        OkrInfo okrInfo  = okrInfoService.selectOkrInfoById(id);
        //判断当前需要查询的id是O还是KR
        mmap.put("isO",getOkrType(okrInfo));

        List<SysUser> list = userService.selectUserList(new SysUser());

        //判断当前用户这个 对应的id的OKR的所有者 是什么关系
        for (int i = 0; i < list.size(); i++) {
            if( list.get(i).getUserId().equals( okrInfo.getUserId() )){
                isIn = true;break;
            }
        }

        //只有判断了是 O 才有必要进行下面的查询
        if(getOkrType(okrInfo)){
            if (isIn) {
                mmap = getOkrDetail(id, mmap);
            } else {
                mmap = getOkrOtherDetail(id, mmap);
            }
        } else {
            mmap.put("okrInfo",okrInfo);
        }
        mmap.put("comments",getObjectComment(id, EnumComment.COMMENT_TYPE_FOR_O.getValue()));
        return prefix + "/viewObject";
    }

    /**
     * @param id
     * @desc 某个对象下面的评论
     * @return 返回当前对象的一级评级评论(分页数据)
     */
    public TableDataInfo getObjectComment(@PathVariable("id") Long id,Integer type){
        if (!EnumComment.isHave(type)) throw new RuntimeException("不存在的评论类型！");
        startPage();
        OkrComment okrComment =  new OkrComment();
        okrComment.setObjectId(id);
        okrComment.setType(type);
        okrComment.setParentId(0L);
        List<OkrComment> okrCommentLists = okrCommentService.selectOkrCommentMetaList(okrComment);
        return getDataTable(okrCommentLists);
    }

    /**
     *
     * @desc 获得别人的ORK 列表详情页
     * @param id OKR 的O的ID
     * @param mmap
     * @return
     */
    public ModelMap getOkrOtherDetail(Long id, ModelMap mmap) {
        //查询的
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(id);
        mmap.put("okrInfo", okrInfo);
        List<OkrInfo> okrInfos = okrInfoService.selectOkrInfoListByParentId(okrInfo);

        //被协同的用户id
        Long userId = ShiroUtils.getSysUser().getUserId();

        //过滤上面的krInfos
        if (okrInfos.size() > 0 ){
            OkrSynergy okrSynergy = new OkrSynergy();
            okrSynergy.setUserId(userId);
            List<OkrSynergy> okrSynergies = okrSynergyService.selectOkrSynergyListByOkrId(okrSynergy);
            Long[] longArr = new Long[okrSynergies.size()];
            for ( int i = 0;i < okrSynergies.size();i++) {
                longArr[i] = okrSynergies.get(i).getOkrId();
            }

            //迭代删除
            Iterator<OkrInfo> iterator = okrInfos.iterator();
            while(iterator.hasNext()){
                OkrInfo okr = iterator.next();
                //不存在
                if ( Arrays.binarySearch(longArr,okr.getId()) < 0 ){
                    iterator.remove();
                }
            }

            for (int i = 0; i < okrInfos.size(); i++) {

                //KR项目回显
                List<OkrProjectRoleResult> projectKRList = okrInfoProjectList(okrInfos.get(i).getId());
                if (projectKRList != null) {
                    okrInfos.get(i).setProjectList(projectKRList);
                }

                //备注查询
                String okr_remark_html_content = getOkrAttach(okrInfos.get(i).getRowId(), "okr_remark_content");
                String okr_remark_text_content = HtmlUtils.Html2Text(okr_remark_html_content);

                //评分标准
                String okr_grade_remark_content = getOkrAttach(okrInfos.get(i).getRowId(), "grade_remark");
                String okr_grade_remark_text_content = HtmlUtils.Html2Text(okr_grade_remark_content);

                //赋值
                okrInfos.get(i).setRemarkContent(okr_remark_html_content);
                okrInfos.get(i).setRemarkTextContent(okr_remark_text_content);
                okrInfos.get(i).setGradeRemark(okr_grade_remark_content);
                okrInfos.get(i).setGradeTextRemark(okr_grade_remark_text_content);
            }
            mmap.put("okrKReultInfo", okrInfos);
            //查询 当前O 对应的周期
            OkrCycle okrCycle = okrCycleService.selectOkrCycleById(okrInfo.getCycleId());
            mmap.put("okrCycle", okrCycle);

            return mmap;
        }else{
            return mmap;
        }
    }

    /**
     * @desc  获得自己的(或者是登录用户的部门领导的)ORK列表详情页信息
     * @desc  说白的就是 自己或者领导查看的路由地址查看接口
     * @param id OKR的O的Id
     * @param mmap 返回的mmap 接收器
     * @return
     */
    public ModelMap getOkrDetail(Long id, ModelMap mmap) {
        // 查询OKR信息
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(id);
        // 查询当前用户是否存在于某个项目组
        List<OkrProjectRoleResult> projectList = okrInfoProjectList(id);
        if (projectList != null) {
            okrInfo.setProjectList(projectList);
        }
        mmap.put("okrInfo", okrInfo);
        //O对应的 KR 信息
        List<OkrInfo> krInfos = okrInfoService.selectOkrInfoListByParentId(okrInfo);
        for (int i = 0; i < krInfos.size(); i++) {

            //KR项目回显
            List<OkrProjectRoleResult> projectKRList = okrInfoProjectList(krInfos.get(i).getId());
            if (projectKRList != null) {
                krInfos.get(i).setProjectList(projectKRList);
            }

            //备注查询
            String okr_remark_html_content = getOkrAttach(krInfos.get(i).getRowId(), "okr_remark_content");
            String okr_remark_text_content = HtmlUtils.Html2Text(okr_remark_html_content);

            //评分标准
            String okr_grade_remark_content = getOkrAttach(krInfos.get(i).getRowId(), "grade_remark");
            String okr_grade_remark_text_content = HtmlUtils.Html2Text(okr_grade_remark_content);

            //赋值
            krInfos.get(i).setRemarkContent(okr_remark_html_content);
            krInfos.get(i).setRemarkTextContent(okr_remark_text_content);
            krInfos.get(i).setGradeRemark(okr_grade_remark_content);
            krInfos.get(i).setGradeTextRemark(okr_grade_remark_text_content);
        }
        mmap.put("okrKReultInfo", krInfos);

        //查询 当前O 对应的周期
        OkrCycle okrCycle = okrCycleService.selectOkrCycleById(okrInfo.getCycleId());
        mmap.put("okrCycle", okrCycle);
        return mmap;
    }


    /**
     * @desc  返回OKR是不是 O的类型
     * @param okrInfo OKR详情
     * @return
     */
    public boolean getOkrType(OkrInfo okrInfo) {
        boolean isO = false;
        if (okrInfo.getRowType() == 0L) {
            isO = true;
        }
        return isO;
    }


    /**
     * 修改OKR信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        // 查询OKR信息
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(id);

        // OKR 应该展示的行数
        Integer rowLine = HtmlUtils.Text2line(okrInfo.getContent());
        mmap.put("rowLine", rowLine);

        // 查询KR备注信息
        String okrRemarkContent = getOkrAttach(okrInfo.getRowId(), "okr_remark_content");
        if (StringUtils.isNotEmpty(okrRemarkContent)) {
            okrInfo.setRemarkContent(getOkrAttach(okrInfo.getRowId(), "okr_remark_content"));
            okrInfo.setRemarkTextContent(HtmlUtils.Html2Text(okrRemarkContent));
            mmap.put("placeholder", 0);
        } else {
            String language = ShiroUtils.getSysUser().getLanguage();
            String okrRemarkPlaceholder = transInfo(dictService.getType("okr_remark_placeholder"), language);
            okrInfo.setRemarkContent("");
            okrInfo.setRemarkTextContent(okrRemarkPlaceholder);
            mmap.put("placeholder", 1);
        }

        // 查询KR评分标准
        String  okrGradeRemark = getOkrAttach(okrInfo.getRowId(),"grade_remark");
        if(StringUtils.isNotEmpty(okrGradeRemark)){
            okrInfo.setGradeRemark(okrGradeRemark);
            okrInfo.setGradeTextRemark(HtmlUtils.Html2Text(okrGradeRemark));
            mmap.put("placeholderGrade",0);
        } else {
            String language = ShiroUtils.getSysUser().getLanguage();
            String okrRemarkPlaceholder = transInfo(dictService.getType("okr_grade_remark"), language);
            okrInfo.setGradeRemark("");
            okrInfo.setGradeTextRemark(okrRemarkPlaceholder);
            mmap.put("placeholderGrade",1);
        }

        // 查询当前用户是否存在于某个项目组
        List<OkrProjectRoleResult> projectList = okrInfoProjectList(id);
        if (projectList != null) {
            okrInfo.setProjectList(projectList);
        }
        mmap.put("okrInfo", okrInfo);
        if (okrInfo.getRowType() == 0) {
            return prefix + "/editObject";
        } else {
            return prefix + "/editKResult";
        }
    }

    /**
     * 获取OKR 附加属性   备注
     * @param okrRowId
     * @param attachKey
     * @return
     */
    public String getOkrAttach(String okrRowId, String attachKey) {
        OkrAttach okrAttach = new OkrAttach();
        okrAttach.setKeyName(attachKey);
        okrAttach.setOkrId(okrRowId);
        List<OkrAttach> okrAttaches = okrAttachService.selectOkrAttachList(okrAttach);
        if (okrAttaches.size() > 0) {
            return okrAttaches.get(0).getKeyValue();
        }
        return null;
    }

    /**
     * 加工OKR和项目的对应关系
     *
     * @param okrId
     * @param projectList
     * @return
     */
    public List<OkrProjectRoleResult> selectedCheck(Long okrId, List<OkrProjectRoleResult> projectList) {
        if (projectList != null && projectList.size() > 0) {
            List<OkrProjectRoleResult> okrProjectRoleResults = new ArrayList<>();
            for (int i = 0; i < projectList.size(); i++) {
                OkrProjectRoleResult okrProjectRoleResult = projectList.get(i);
                if (okrProjectRoleResult.getOkrId() != null) {
                    okrProjectRoleResult.setSelected(true);
                } else {
                    okrProjectRoleResult.setSelected(false);
                }
                okrProjectRoleResults.add(okrProjectRoleResult);
            }
            return okrProjectRoleResults;
        }
        return null;
    }

    /**
     * 修改保存OKR信息
     * 待改善-剥离到服务层，添加事务管理器,执行效率在1秒以上，需要优化
     */
    @RequiresPermissions("okr:info:edit")
    @Log(title = "OKR信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrInfo okrInfo, KrInfos krInfos) {

        long startTime = System.currentTimeMillis();

        //处理okrInfo没有的字段
        if (okrInfo.getUpdateTime() == null) {
            okrInfo.setUpdateTime(DateUtils.getNowDate());
        }

        //检测更新OKR项目对应关系(删除或者更新)
        updateProject(okrInfo);

        //OKR 协同关系 处理
        if (StringUtils.isNotEmpty(okrInfo.getSynergyUsers())) {
            String userIds = okrInfo.getSynergyUsers();
            updateSynergy(userIds,okrInfo.getId());
        }


        //更新评分规则
        Map<String,String> paramsGrade = new HashMap<String,String>();
        String gradeRemarkText = okrInfo.getGradeTextRemark();
        paramsGrade.put("okrId",okrInfo.getRowId());
        paramsGrade.put("keyName","grade_remark");
        paramsGrade.put("keyValue",gradeRemarkText);
        saveAttach(paramsGrade);

        //更新进度备注
        Map<String,String> paramsRemark = new HashMap<String,String>();
        String okrRemarkContent = okrInfo.getRemarkTextContent();
        paramsRemark.put("okrId",okrInfo.getRowId());
        paramsRemark.put("keyName","okr_remark_content");
        paramsRemark.put("keyValue",okrRemarkContent);
        saveAttach(paramsRemark);


        //携带的KRs 修改数据 ,进行更新操作
        if(krInfos != null && krInfos.getKrInfos() != null && krInfos.getKrInfos().size() > 0 ){
            int size = krInfos.getKrInfos().size();
            OkrInfo  krinfo = null;
            for(int i = 0 ; i < size ; i++ ){
                krinfo = krInfos.getKrInfos().get(i);
                updateProject(krinfo);
                okrInfoService.updateOkrInfo(krinfo);
            }
            krinfo.setParentId(okrInfo.getRowId());
            //开始更新O的数据,取最后一个
            okrInfoService.updateOInfo( krinfo );
        }

        //更新自身信息 如果更新的是KR,则同步相关的O信息
        Integer flag = okrInfoService.updateOkrInfo(okrInfo);
        if (flag > 0 && okrInfo.getRowType() == 1) {
            flag = okrInfoService.updateOInfo(okrInfo);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("editSave 程序运行时间： " + (endTime - startTime) + "ms");
        return toAjax(flag);
    }

    /**
     * 待改善-剥离到服务层，添加事务管理器
     *
     * @param okrInfo
     */
    public void updateProject(OkrInfo okrInfo) {
        //检查历时数据是否和现在数据对应--查询
        SysUser user = ShiroUtils.getSysUser();
        //清除历史数据对应关系
        Map<String, Long> params = new HashMap<>();
        params.put("userId", user.getUserId());
        params.put("okrId", okrInfo.getId());
        okrProjectRoleService.deleteOkrProjectRoleByUser(params);
        if( okrInfo.getProjects() != null){
            for (int i = 0; i < okrInfo.getProjects().length; i++) {
                OkrProjectRole okrProjectRole = new OkrProjectRole();
                okrProjectRole.setUserId(user.getUserId());
                okrProjectRole.setOkrId(okrInfo.getId());
                okrProjectRole.setProjectCode(okrInfo.getProjects()[i]);
                okrProjectRoleService.insertOkrProjectRole(okrProjectRole);
            }
        }
    }

    /**
     * 更新项目管理员关系
     * 待改善-剥离到服务层，添加事务管理器
     *
     * @param userIds
     */
    public void updateProjectUser(String userIds, Long projectId) {
        if (!StringUtils.isEmpty(userIds)) {
            String[] userArr = userIds.split(",");
            //清理OKR对应关系，后续程序进行插入--后期可以改成redis存储关系
            okrProjectUserRoleService.deleteOkrProjectUserRoleById(projectId);
            List<OkrProjectUserRole> okrProjectUserRoles = new ArrayList<>();
            for (int i = 0; i < userArr.length; i++) {
                Long userId = Long.parseLong(userArr[i]);
                OkrProjectUserRole okrProjectUserRole = new OkrProjectUserRole();
                okrProjectUserRole.setUserId(userId);
                okrProjectUserRole.setProjectId(projectId);
                okrProjectUserRoles.add(okrProjectUserRole);
            }
            //批量插入协同数据
            okrProjectUserRoleService.insertOkrProjectUserRoleList(okrProjectUserRoles);
        } else {
            if ("".equals(userIds)) //取消最后一条进行数据清空
            {
                //清理OKR对应关系，后续程序进行插入--后期可以改成redis存储关系
                okrProjectUserRoleService.deleteOkrProjectUserRoleById(projectId);
            }
        }

    }


    /**
     * 更新协同关系
     * 待改善-剥离到服务层，添加事务管理器
     *
     * @param userIds
     */
    public void updateSynergy(String userIds, Long okrId) {

        //检测是否有勾选协同关系
        SysUser user = ShiroUtils.getSysUser();
        if (!StringUtils.isEmpty(userIds)) {
            String[] userArr = userIds.split(",");
            //清理OKR对应关系，后续程序进行插入--后期可以改成redis存储关系
            okrSynergyService.deleteOkrSynergyByOkrId(okrId);
            List<OkrSynergy> okrSynergies = new ArrayList<>();
            for (int i = 0; i < userArr.length; i++) {
                Long userId = Long.parseLong(userArr[i]);
                //自己不可以协同给自己
                if (!user.getUserId().equals(userId)) {
                    OkrSynergy okrSynergy = new OkrSynergy();
                    okrSynergy.setOkrId(okrId);
                    okrSynergy.setUserId(userId);
                    okrSynergies.add(okrSynergy);
                }
                //正式上线前需要改成批量插入《待修改标记位》
//                okrSynergyService.insertOkrSynergy(okrSynergy);
            }
            //批量插入协同数据
            okrSynergyService.insertOkrSynergyList(okrSynergies);
        } else {
            if ("".equals(userIds)) //取消最后一条进行数据清空
            {
                //清理OKR对应关系，后续程序进行插入--后期可以改成redis存储关系
                okrSynergyService.deleteOkrSynergyByOkrId(okrId);
            }
        }

    }

    /**
     * OKR批量协同功能
     */
    @RequiresPermissions("okr:info:batchCollaboration")
    @Log(title = "OKR批量协同功能", businessType = BusinessType.UPDATE)
    @PostMapping("/batchCollaboration")
    @ResponseBody
    public AjaxResult batchCollaboration(@RequestParam Map<String, String> params) {
        String okrIds = params.get("okrIds");
        String userIds = params.get("userIds");
        if (okrIds != null && okrIds.length() > 0) {
            String[] okrIdArr = okrIds.split(",");
            for (int i = 0; i < okrIdArr.length; i++) {
                Long okrId = Long.parseLong(okrIdArr[i]);
                updateSynergy(userIds, okrId);
            }
        }
        return AjaxResult.success();
    }


    /**
     * OKR批量项目赋权
     */
    @RequiresPermissions("okr:info:batchProjectUsers")
    @Log(title = "OKR批量协同功能", businessType = BusinessType.UPDATE)
    @PostMapping("/batchProjectUsers")
    @ResponseBody
    public AjaxResult batchProjectUsers(@RequestParam Map<String, String> params) {
        String projectIds = params.get("projectIds");
        String userIds = params.get("userIds");
        if (projectIds != null && projectIds.length() > 0) {
            String[] okrIdArr = projectIds.split(",");
            for (int i = 0; i < okrIdArr.length; i++) {
                Long okrId = Long.parseLong(okrIdArr[i]);
                updateProjectUser(userIds, okrId);
            }
        }
        return AjaxResult.success();
    }

    /**
     * 删除OKR信息
     */
    @RequiresPermissions("okr:info:remove")
    @Log(title = "OKR信息", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("ids") String ids) {
        OkrInfo info = okrInfoService.selectOkrInfoById(Long.parseLong(ids));
        if (info.getRowType().equals(1L)) {
            //更更新Object 信息更新自身信息 如果更新的是KR侧同步相关的O信息
            Integer flag = okrInfoService.deleteOkrInfoByIds(ids);
            if (flag > 0) {
                flag = okrInfoService.updateOInfo(info);
            }
            return toAjax(flag);
        } else {
            info.setParentId(ids);
            List<OkrInfo> infoList = okrInfoService.selectOkrInfoListByParentId(info);
            infoList.add(info);
            String idArr = getIds(infoList);
            return toAjax(okrInfoService.deleteOkrInfoByIds(idArr));
        }
    }


    public String getIds(List<OkrInfo> infoList) {
        String[] ids = new String[infoList.size()];
        for (int i = 0; i < infoList.size(); i++) {
            ids[i] = infoList.get(i).getId().toString();
        }
        return String.join(",", ids);
    }

}
