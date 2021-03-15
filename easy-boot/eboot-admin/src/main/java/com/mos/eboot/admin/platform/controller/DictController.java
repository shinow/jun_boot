package com.mos.eboot.admin.platform.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.admin.platform.api.IDictService;
import com.mos.eboot.platform.entity.PtDict;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.LayPage;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.util.Constants;
import com.mos.eboot.tools.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 小尘哥
 * @Time 2018/5/6 21:01
 */
@Controller
@RequestMapping("pt/dict")
public class DictController extends BaseController{

    @Resource
    private IDictService dictService;

    @RequestMapping(value = "to-page",method = RequestMethod.GET)
    public String toPage() {
        return "platform/dict/list";
    }

    @RequestMapping(value = "to-edit",method = RequestMethod.GET)
    @RequiresPermissions("sys:dict:basic:op")
    public String toEdit(@RequestParam(name = "id", required = false) String id, Model model) {
        PtDict dict = dictService.getById(id).getData();
        if (dict == null){
            dict = new PtDict();
        }
        model.addAttribute("dict", dict);
        return "platform/dict/edit";
    }

    @ResponseBody
    @RequestMapping(value = "save-or-update",method = RequestMethod.POST)
    @RequiresPermissions("sys:dict:basic:op")
    public ResultModel<String> save(@Valid PtDict dict, BindingResult result) {
        this.validError(result);
        if (StringUtil.isBlank(dict.getId())) {
            dict.setIsDel(Constants.NEGATIVE);
            dict.setIsEdit(Constants.POSITIVE);
        }
        return dictService.saveOrUpdate(dict);
    }

    @ResponseBody
    @RequestMapping(value = "query-page",method = RequestMethod.POST)
    public LayPage<PtDict> queryPage(Page<PtDict> page) {
        return this.getLayPage(dictService.queryPage(page));
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @RequiresPermissions("sys:dict:basic:del")
    public ResultModel<String> del(@RequestParam("id") String id){
        return dictService.delById(id);
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam("id")String id, Model model){
        PtDict dict = dictService.getById(id).getData();
        model.addAttribute("dict", dict);
        return "platform/dict/detail";
    }
}
