package com.mos.eboot.service.platform.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.SysRole;
import com.mos.eboot.platform.entity.SysUserRole;
import com.mos.eboot.service.platform.service.ISysRoleService;
import com.mos.eboot.service.platform.service.ISysUserRoleService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小尘哥
 */
@RestController
@RequestMapping("sys/role")
public class RoleController extends BaseController{

    @Resource
    private ISysUserRoleService userRoleService;

    @Resource
    private ISysRoleService roleService;

    @ApiOperation(value = "获取角色id集合",notes = "根据用户id获取角色id集合")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-user",method = RequestMethod.GET)
    public ResultModel<List<String>> getByUserId(@RequestParam("userId")String userId){
        EntityWrapper<SysUserRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id",userId);
        List<SysUserRole> userRoles =userRoleService.selectList(entityWrapper);
        List<String> roles = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return new ResultModel<>(ResultStatus.SUCCESS,roles);
    }

    @ApiOperation(value = "获取角色详情",notes = "根据角色id获取详情")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-id",method = RequestMethod.GET)
    public ResultModel<SysRole> getById(@RequestParam("id")String id){
        return new ResultModel<>(ResultStatus.SUCCESS,roleService.selectById(id));
    }

    @ApiOperation(value = "删除角色",notes = "根据角色id删除角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "del-by-id",method = RequestMethod.POST)
    public ResultModel<String> delById(@RequestParam("id")String id){
        return this.basicResult(roleService.deleteById(id));
    }

    @ApiOperation(value = "新增|修改角色",notes = "新增|修改角色")
    @RequestMapping(value = "save-or-update",method = RequestMethod.POST)
    public ResultModel<String> saveOrUpdate(@ApiParam @RequestBody SysRole role){
        Boolean r ;
        if (StringUtil.isBlank(role.getId())){
            r = roleService.insert(role);
        } else{
            r = roleService.updateById(role);
        }
        return this.basicResult(r);
    }

    @ApiOperation(value = "分页查询角色",notes = "分页查询角色")
    @RequestMapping(value = "query-page",method = RequestMethod.POST)
    public ResultModel<Page<SysRole>> queryPage(@ApiParam @RequestBody Page<SysRole> page){
        return new ResultModel<>(ResultStatus.SUCCESS,roleService.queryPage(page));
    }

    @ApiOperation(value = "条件查询角色",notes = "条件查询角色")
    @RequestMapping(value = "query-list",method = RequestMethod.POST)
    public ResultModel<List<SysRole>> queryList(@ApiParam @RequestBody SysRole sysRole){
        EntityWrapper<SysRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(sysRole);
        return new ResultModel<>(ResultStatus.SUCCESS,roleService.selectList(entityWrapper));
    }
    @ApiOperation(value = "角色绑定资源",notes = "角色绑定资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ids", value = "角色ID集合", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "bind-resource",method = RequestMethod.POST)
    public ResultModel<String> bindResource(@RequestParam(name = "id")String id,@RequestBody String[] ids){
        return this.basicResult(roleService.bindResource(id,ids));
    }
}
