package com.nbclass.modules.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbclass.common.constant.CoreConst;
import com.nbclass.common.util.ResultUtil;
import com.nbclass.common.vo.ResponseVo;
import com.nbclass.modules.sys.service.ConfigService;
import com.nbclass.modules.sys.vo.ConfigStorageVo;

@Controller
@RequestMapping("/config")
public class ConfigController {
	
	@Autowired
    private ConfigService configService;
	
	@PostMapping(value = "/saveStorage")
	@ResponseBody
    public ResponseVo<?> saveConfig(ConfigStorageVo config){
		config.setSetFlag(CoreConst.STATUS_VALID);
        configService.saveStorageConfig(config);
        return ResultUtil.success("存储设置成功！");
    }
	
}
