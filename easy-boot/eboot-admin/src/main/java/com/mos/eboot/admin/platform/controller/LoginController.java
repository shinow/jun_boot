package com.mos.eboot.admin.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 小尘哥
 */
@Controller
public class LoginController {

    @GetMapping("toLogin")
    public String toLogin(){
        return "login";
    }

}
