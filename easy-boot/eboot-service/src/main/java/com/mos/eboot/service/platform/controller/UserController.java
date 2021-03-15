package com.mos.eboot.service.platform.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.service.platform.service.ISysUserRoleService;
import com.mos.eboot.service.platform.service.ISysUserService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.vo.ChartVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小尘哥
 */
@RestController
@RequestMapping("api/user")
public class UserController  extends BaseController{

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysUserRoleService userRoleService;

    @ApiOperation(value = "获取用户信息",notes = "根据用户名获取用户")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-name",method = RequestMethod.GET)
    public SysUser getByUsername(@RequestParam("username")String username){
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("username",username);
        return userService.selectOne(entityWrapper);
    }

    @ApiOperation(value = "新增|修改用户信息",notes = "新增|修改用户信息")
    @RequestMapping(value = "save-or-update",method = RequestMethod.POST)
    public ResultModel<String> saveOrUpdate(@ApiParam("用户对象") @RequestBody SysUser sysUser){
        return this.basicResult(userService.saveOrUpdate(sysUser));
    }

    @ApiOperation(value = "删除用户",notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public ResultModel<String> deleteById(@RequestParam("id")String id){
        return this.basicResult(userService.deleteById(id));
    }

    @ApiOperation(value = "分页查询",notes = "分页查询用户")
    @RequestMapping(value = "query-page",method = RequestMethod.POST)
    public ResultModel<Page<SysUser>> queryPage(@ApiParam(value = "分页对象") @RequestBody Page<SysUser> page){
        return new ResultModel<>(ResultStatus.SUCCESS,userService.queryPage(page));
    }

    @ApiOperation(value = "获取用户",notes = "根据用户id获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-id",method = RequestMethod.GET)
    public ResultModel<SysUser> getById(@RequestParam("id")String id){
        SysUser user = userService.selectById(id);
        if (user != null){
            user.setRoleIds(userRoleService.getRoleIds(id));
        }
        return new ResultModel<>(ResultStatus.SUCCESS, user);
    }

    @ApiOperation(value = "登录统计",notes = "登录统计")
	@RequestMapping(value = "login-count",method = RequestMethod.GET)
	public ResultModel<List<ChartVO>> loginCount(){
		return new ResultModel<>(ResultStatus.SUCCESS,userService.loginCount());
	}

    @ApiOperation(value = "条件查询用户",notes = "条件查询用户")
	@RequestMapping(value = "all",method = RequestMethod.POST)
	public ResultModel<List<SysUser>> all(@ApiParam @RequestBody SysUser user){
		return new ResultModel<>(ResultStatus.SUCCESS,userService.getAll(user));
	}
}
