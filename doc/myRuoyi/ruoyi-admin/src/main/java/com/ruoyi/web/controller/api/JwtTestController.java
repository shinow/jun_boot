package com.ruoyi.web.controller.api;

import com.github.pagehelper.PageHelper;
import com.ruoyi.business.service.impl.TestServiceImpl;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/jwt/test")
@Api(value="JWT示范接口", description="JWT示范接口")
public class JwtTestController extends BaseController {
    @Autowired
    TestServiceImpl testService;


    @GetMapping(value = "/test")
    @ApiOperation(value="测试Callable", notes="测试 Callable; Callable类型 swagger 不能使用try,故实际开发建议使用 WebAsyncTask ,后者也是要强大的多")
    public Callable test(){
        System.out.println(new Date());
        return ()->{
            PageHelper.startPage(1,1);
            List<Map> allUsers = testService.getAllUsers();
            TableDataInfo dataTable = getDataTable(allUsers);
            System.out.println(new Date());
            return success(dataTable);
        };
    }
    @PostMapping(value = "/test2")
    @ApiOperation(value="测试WebAsyncTask", notes="测试WebAsyncTask")
    public WebAsyncTask test2(HttpServletRequest request){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return "5464";
        });
        return test2;
    }
    @PostMapping(value = "/test3")
    @ApiOperation(value="测试body", notes="测试body")
    public WebAsyncTask test3(HttpServletRequest request,@RequestBody String bodyData){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return bodyData;
        });
        return test2;
    }
    @PostMapping(value = "/test4")
    @ApiOperation(value="测试beanBody", notes="测试beanBody")
    public WebAsyncTask test4(HttpServletRequest request,@RequestBody SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }
    @PostMapping(value = "/test5")
    @ApiOperation(value="测试bean", notes="测试bean")
    public WebAsyncTask test5(HttpServletRequest request, SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }

}