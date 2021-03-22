package com.mos.eboot.service.platform.controller;


import com.mos.eboot.platform.entity.SysAccessory;
import com.mos.eboot.service.platform.service.ISysAccessoryService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.IDGen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author 小尘哥
 * @since 2018-03-12
 */
@RestController
@RequestMapping("/sys/accessory")
public class SysAccessoryController extends BaseController{

    @Resource
    private ISysAccessoryService accessoryService;

    @ApiOperation(value = "附件上传",notes = "附件上传")
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public ResultModel<String> upload(@ApiParam(value = "附件对象") @RequestBody SysAccessory accessory){
        accessory.setId(IDGen.genId());
        accessoryService.insert(accessory);
        return new ResultModel<>(ResultStatus.SUCCESS,accessory.getId());
    }

    @ApiOperation(value = "获取附件",notes = "根据id获取附件")
    @ApiImplicitParam(name = "id", value = "附件id", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "open",method = RequestMethod.GET)
    public ResultModel<SysAccessory> open(@RequestParam("id")String id){
        return new ResultModel<>(ResultStatus.SUCCESS,accessoryService.selectById(id));
    }
	
}
