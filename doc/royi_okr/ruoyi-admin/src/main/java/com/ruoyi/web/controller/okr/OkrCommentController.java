package com.ruoyi.web.controller.okr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.ConstantsOkr;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
    评论的对象 ID，相对评论类型的ID
    比如：
    type=0 针对 o进行评论，
    则object_id 为o的ID
    type=1 针对 o进行评论，
    则object_id 为kr的ID
    type=2 针对 o进行评论，
    则object_id 为被评论（一级评论）的评论ID
    且parent_id 为被评论（二级评论）的评论ID

    注意：只有评论的子集评论会存在
    object_id = parent_id的情况(评论后面不带@)

    @author xiaoshuai2233@sina.com
    @date 2020-10-17
 */
@Controller
@RequestMapping("/comment")
public class OkrCommentController extends BaseController
{
    private String prefix = "comment";

    @Autowired
    private IOkrInfoService okrInfoService;

    @Autowired
    private IOkrCommentService okrCommentService;

    @Autowired
    private IOkrSynergyService okrSynergyService;

    @Autowired
    private IOkrCommentMetaService okrCommentMetaService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:comment:view")
    @GetMapping()
    public String comment()
    {
        return prefix + "/comment";
    }

    /**
     * 查询 评论 列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrComment okrComment)
    {
        startPage();
        List<OkrComment> list = okrCommentService.selectOkrCommentMetaList(okrComment);
        return getDataTable(list);
    }


    /**
     * 新增 评论 
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存 评论
     * @commentFiles  只有一级评论有这个值
     * return 成功返回当前 评论信息，前端异步追加到内容，页面不需要刷新
     */
    @Log(title = " 评论 ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(OkrComment okrComment,@RequestParam(value="commentFiles",required=false) String commentFiles)
    {
        okrComment.setStatus(ConstantsOkr.COMMENT_DEFAULT_STATES);
        okrComment.setUserId(ShiroUtils.getSysUser().getUserId());
        okrComment.setAddTime(DateUtils.getNowDate());
        okrComment.setUserIp(IpUtils.getIpAddr(getRequest()));
        if (okrCommentService.insertOkrComment(okrComment)>0 ){
            Long id = okrComment.getId();
            //插入附加信息：如果存在
            List<OkrCommentMeta> list = JSON.parseObject(commentFiles, new TypeReference<List<OkrCommentMeta>>() {}.getType());
            if (list != null ) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCommentId(id.intValue());
                }
                if (list.size() > 0) {
                    okrCommentMetaService.insertOkrCommentMetaForeach(list);
                }
            }
            OkrComment wholeInfo = okrCommentService.selectOkrCommentById(id);
            return success("评论成功，页面正在刷新！",wholeInfo);
        } else {
            return error("评论失败，请稍后再试……");
        }
    }


    /**
     * 修改 评论 
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrComment okrComment = okrCommentService.selectOkrCommentById(id);
        mmap.put("okrComment", okrComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存 评论 
     */
    @Log(title = " 评论 ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrComment okrComment)
    {
        return toAjax(okrCommentService.updateOkrComment(okrComment));
    }


    /**
     * 评论关联：团队和当前O对应的OK
     */
    @GetMapping("/addAtAttment/{orkid}")
    public String addAtAttment(@PathVariable("orkid") long okrId, ModelMap mmap)
    {
        mmap.put("okrId", okrId);
        return prefix + "/addAtAttment";
    }

    /**
     * 评论关联：O
     */
    @PostMapping("/addAtAttment/Oinfo")
    @ResponseBody
    public TableDataInfo Oinfo(long okrId)
    {
        // 查询OKR信息
        startPage();
        LinkedList<OkrInfo> listOkrInfo = new LinkedList<>();
        listOkrInfo.add(okrInfoService.selectOkrInfoById(okrId));
        return getDataTable(listOkrInfo);
    }

    /**
     * 评论关联：O
     */
    @PostMapping("/addAtAttment/KRinfo")
    @ResponseBody
    public TableDataInfo KRinfo(long okrId) {
        // 查询OKR信息
        startPage();
        OkrInfo okrInfo = okrInfoService.selectOkrInfoById(okrId);
        List<OkrInfo> okrInfos = okrInfoService.selectOkrInfoListByParentId(okrInfo);
        Long userId = okrInfo.getUserId();


        if(!ShiroUtils.getUserId().equals(userId)){
            List<SysUser> list = userService.selectUserList(new SysUser());
            boolean isIn = false;
            //判断当前用户这个 对应的id的OKR的所有者 是什么关系
            for (int i = 0; i < list.size(); i++) {
                if( list.get(i).getUserId().equals( okrInfo.getUserId() )){
                    isIn = true;break;
                }
            }
            //不是领导，如果是领导，就不过滤了
            if (!isIn) {
                //是不是普通协同
                OkrSynergy okrSynergy = new OkrSynergy();
                okrSynergy.setUserId(ShiroUtils.getUserId());
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
            }
        }
        return getDataTable(okrInfos);
    }

    /**
     * 评论关联：项目
     */
    @GetMapping("/addAtProject/{orkid}")
    public String addAtProject(@PathVariable("orkid") long okrId)
    {
        return prefix + "/addAtProject";
    }


    /**
     * 导出 评论列表
     */
    @Log(title = " 评论 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrComment okrComment)
    {
        List<OkrComment> list = okrCommentService.selectOkrCommentList(okrComment);
        ExcelUtil<OkrComment> util = new ExcelUtil<OkrComment>(OkrComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 删除 评论 
     */
    @Log(title = " 评论 ", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrCommentService.deleteOkrCommentByIds(ids));
    }
}
