package com.mos.eboot.service.platform.controller;

import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.service.platform.service.ISysMenuService;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("sys/role/permission")
public class RolePermissionController {

    @Resource
	private ISysMenuService menuService;

    @ApiOperation(value = "获取权限集合",notes = "根据用户id获取权限集合")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-all",method = RequestMethod.GET)
    public ResultModel<List<String>> getAll( @RequestParam(value = "userId",required = false) String userId){
    	//超级管理员查询全部权限
    	if (StringUtil.isNotEmpty(userId) && SysUser.SUPER_ID.equals(userId)){
    		userId = null;
		}
        return new ResultModel<>(ResultStatus.SUCCESS,menuService.selectPermit(userId));
    }
}
