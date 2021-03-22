package org.springrain.system.api.mp;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.system.base.BaseController;
import org.springrain.system.service.IUserService;
import org.springrain.weixin.service.IWxMpConfigService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/mp/auth", method = RequestMethod.POST)
public class AuthController extends BaseController {

    @Resource
    private IWxMpConfigService wxMpConfigService;

    @Resource
    private IUserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnDatas<?> login(@RequestBody Map<?, ?> map) throws Exception {
        ReturnDatas<?> returnObject = ReturnDatas.getSuccessReturnDatas();

        return returnObject;
    }
}