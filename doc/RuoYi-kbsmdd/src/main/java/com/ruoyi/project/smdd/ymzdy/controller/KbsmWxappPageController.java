package com.ruoyi.project.smdd.ymzdy.controller;

import java.util.List;

import com.ruoyi.project.wxapi.model.bean.WxappPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.smdd.ymzdy.domain.KbsmWxappPage;
import com.ruoyi.project.smdd.ymzdy.service.IKbsmWxappPageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 页面自定义Controller
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@Controller
@RequestMapping("/smdd/ymzdy")
public class KbsmWxappPageController extends BaseController
{
    private String prefix = "smdd/ymzdy";


    @Autowired
    private IKbsmWxappPageService kbsmWxappPageService;

    @RequiresPermissions("smdd:ymzdy:view")
    @GetMapping()
    public String ymzdy(Integer id, Model model)
    {
        if (id == null) {
            id = 1;
        }
        model.addAttribute("id", id);
        return prefix + "/ymzdy";
    }

    /**
     * 查询页面自定义详情
     */
    @RequiresPermissions("smdd:ymzdy:detail")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(KbsmWxappPage kbsmWxappPage)
    {
       KbsmWxappPage wxappPage = kbsmWxappPageService.selectKbsmWxappPageByWxappId(kbsmWxappPage.getWxappId());
       if (wxappPage == null) {
           // 生成默认模板。
           KbsmWxappPage wxappPageDefault = kbsmWxappPageService.selectKbsmWxappTypeId(3);
           KbsmWxappPage insertWxappPage = new KbsmWxappPage();
           insertWxappPage.setWxappId(kbsmWxappPage.getWxappId());
           insertWxappPage.setPageType(1);
           insertWxappPage.setPageData(wxappPageDefault.getPageData());
           kbsmWxappPageService.insertKbsmWxappPage(insertWxappPage);
           return AjaxResult.success(insertWxappPage);
       }
        return AjaxResult.success(wxappPage);
    }

    /**
     * 修改保存页面自定义
     */
    @RequiresPermissions("smdd:ymzdy:edit")
    @Log(title = "页面自定义", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KbsmWxappPage kbsmWxappPage)
    {
        return toAjax(kbsmWxappPageService.updateKbsmWxappPageByWxappId(kbsmWxappPage));
    }

}
