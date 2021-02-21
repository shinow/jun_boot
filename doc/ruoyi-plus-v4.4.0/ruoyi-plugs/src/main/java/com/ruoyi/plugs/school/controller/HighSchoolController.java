package com.ruoyi.plugs.school.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.plugs.school.service.HighSchoolService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HighSchoolController extends BaseController {

    @Autowired
    HighSchoolService highSchoolService;

    @ResponseBody
    @RequestMapping("/plugs/highSchool")
    public AjaxResult selectLikeWords(HttpServletRequest request) {
        String words=request.getParameter("words");
        if(StringUtils.isNotEmpty(words)){
            List<Map<String,Object>> list= highSchoolService.selectSchoolLikeWords(words);
            if(CollectionUtils.isNotEmpty(list)){
                return AjaxResult.success("查询成功",list);
            }
            return AjaxResult.error("没有查询到相关记录!");
        }
        return error("查询失败!");
    }
}
