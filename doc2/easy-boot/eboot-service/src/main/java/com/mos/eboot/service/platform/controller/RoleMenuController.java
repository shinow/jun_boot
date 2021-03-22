package com.mos.eboot.service.platform.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mos.eboot.platform.entity.SysRoleMenu;
import com.mos.eboot.service.platform.service.ISysRoleMenuService;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sys/role/menu")
public class RoleMenuController {

    @Resource
    private ISysRoleMenuService sysRoleMenuService;

    @ApiOperation(value = "获取资源集合",notes = "根据角色ids获取资源集合")
    @RequestMapping(value = "get-menus",method = RequestMethod.GET)
    public ResultModel<List<String>> getMenus(@ApiParam(value = "角色集合") @RequestBody List<String> roleIds){
        List<String> menuIds = new ArrayList<>();
        roleIds.forEach(item->{
            EntityWrapper<SysRoleMenu> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("role_id",item);
            List<SysRoleMenu> roleMenus = sysRoleMenuService.selectList(entityWrapper);
            menuIds.addAll(roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
        });
        return new ResultModel<>(ResultStatus.SUCCESS,menuIds.stream().distinct().collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("test1");
        list1.add("test2");
        list1.add("test3");
        List<String> list2 = new ArrayList<>();
        list2.add("test1");
        list2.add("test4");
        list2.add("test5");
        List<String> list3 = new ArrayList<>();
        list3.addAll(list1);
        list3.addAll(list2);

        list3.stream().distinct().forEach(System.out::println);

    }
}
