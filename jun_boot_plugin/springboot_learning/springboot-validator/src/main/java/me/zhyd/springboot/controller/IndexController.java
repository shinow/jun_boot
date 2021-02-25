package me.zhyd.springboot.controller;

import me.zhyd.springboot.model.Other;
import me.zhyd.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springboot
 * Created by yadong.zhang on com.zyd.controller
 *
 * @Author: yadong.zhang
 * @Date: 2017/10/10 13:41
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index() {

        return new ModelAndView("index");
    }

    @RequestMapping("/saveUser")
    public ModelAndView saveUser(@Validated User user, BindingResult userResult, @Validated Other other, BindingResult otherResult, Model model) {
        List<Map<String, String>> errorList = new ArrayList<>();

        loadErrorList(userResult, errorList);
        loadErrorList(otherResult, errorList);

        model.addAttribute("user", user);
        model.addAttribute("other", other);
        model.addAttribute("errorList", errorList);
        return new ModelAndView("index");
    }

    private void loadErrorList(BindingResult userResult, List<Map<String, String>> errorList) {
        Map<String, String> map = null;
        if (userResult.hasErrors()) {
            List<FieldError> errors = userResult.getFieldErrors();
            for (FieldError error : errors) {
                map = new HashMap<String, String>();
                map.put("field", error.getField());
                map.put("message", error.getDefaultMessage());
                errorList.add(map);
            }
        }
    }
}
