package com.ruoyi.web.controller.okr;

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
import com.ruoyi.system.domain.OkrThumbsUp;
import com.ruoyi.system.service.IOkrCommentMetaService;
import com.ruoyi.system.service.IOkrThumbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论点赞，OKR点赞Controller
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
@Controller
@RequestMapping("/thumb/digg")
public class OkrThumbsUpController extends BaseController
{
    private String prefix = "thumb/up";

    @Autowired
    private IOkrThumbsUpService okrThumbsUpService;

    @Autowired
    private IOkrCommentMetaService okrCommentMetaService;

    /**
     * 查询评论点赞，OKR点赞列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OkrThumbsUp okrThumbsUp)
    {
        startPage();
        List<OkrThumbsUp> list = okrThumbsUpService.selectOkrThumbsUpList(okrThumbsUp);
        return getDataTable(list);
    }


    /**
     * 新增评论点赞，OKR点赞
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存评论点赞，OKR点赞
     */
    @Log(title = "评论点赞，OKR点赞", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public AjaxResult addSave(OkrThumbsUp okrThumbsUp)
    {
        okrThumbsUp.setStatus(ConstantsOkr.THUMBER_DEFAULT_STATES);
        okrThumbsUp.setUserId(ShiroUtils.getSysUser().getUserId());
        if( okrThumbsUpService.selectOkrThumbsUpOne(okrThumbsUp) != null ){
            return error(AjaxResult.Type.WARN,"请不要重复点赞!");
        }

        okrThumbsUp.setUserIp(IpUtils.getIpAddr(getRequest()));
        okrThumbsUp.setAddTime(DateUtils.getNowDate());

        if( okrThumbsUpService.insertOkrThumbsUp(okrThumbsUp) > 0  &&
            okrCommentMetaService.increaseOkrCommentMeta(okrThumbsUp.getObjectId(),ConstantsOkr.COMMENT_META_THUMB) > 0 )
        {
            return success("点赞成功！");
        } else {
            return error("点赞失败！");
        }
    }

    /**
     * 修改评论点赞，OKR点赞
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OkrThumbsUp okrThumbsUp = okrThumbsUpService.selectOkrThumbsUpById(id);
        mmap.put("okrThumbsUp", okrThumbsUp);
        return prefix + "/edit";
    }

    /**
     * 修改保存评论点赞，OKR点赞
     */
    @Log(title = "评论点赞，OKR点赞", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OkrThumbsUp okrThumbsUp)
    {
        return toAjax(okrThumbsUpService.updateOkrThumbsUp(okrThumbsUp));
    }

    /**
     * 删除评论点赞，OKR点赞
     */
    @Log(title = "评论点赞，OKR点赞", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(okrThumbsUpService.deleteOkrThumbsUpByIds(ids));
    }


    /**
     * 导出评论点赞，OKR点赞列表
     */
    @Log(title = "评论点赞，OKR点赞", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OkrThumbsUp okrThumbsUp)
    {
        List<OkrThumbsUp> list = okrThumbsUpService.selectOkrThumbsUpList(okrThumbsUp);
        ExcelUtil<OkrThumbsUp> util = new ExcelUtil<OkrThumbsUp>(OkrThumbsUp.class);
        return util.exportExcel(list, "up");
    }
}
