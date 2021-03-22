package com.mos.eboot.service.platform.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.PtDict;
import com.mos.eboot.service.platform.service.IPtDictService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 小尘哥
 * @since 2018-05-06
 */
@RestController
@RequestMapping("/pt/dict")
public class PtDictController extends BaseController {

    @Resource
    private IPtDictService dictService;

    @ApiOperation(value = "获取字典详情", notes = "根据id获取字典")
    @ApiImplicitParam(name = "id", value = "字典ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "get-by-id", method = RequestMethod.GET)
    @Cacheable(value = "dict", key = "#p0")
    public ResultModel<PtDict> getById(@RequestParam("id") String id) {
        System.out.println("开始获取id为【" + id + "】的字典");
        return new ResultModel<>(ResultStatus.SUCCESS, dictService.selectById(id));
    }

    @ApiOperation(value = "删除字典", notes = "根据id删除字典")
    @ApiImplicitParam(name = "id", value = "字典ID", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "del-by-id", method = RequestMethod.POST)
    @CacheEvict(value = "dict", key = "#p0")
    public ResultModel<String> delById(@RequestParam("id") String id) {
        return this.basicResult(dictService.deleteById(id));
    }

    @ApiOperation(value = "新增|修改字典", notes = "新增|修改字典")
    @RequestMapping(value = "save-or-update", method = RequestMethod.POST)
    @CachePut(value = "dict", key = "#p0.id")
    public ResultModel<String> saveOrUpdate(@ApiParam @RequestBody PtDict dict) {
        Boolean r;
        if (StringUtil.isBlank(dict.getId())) {
            r = dictService.insert(dict);
        } else {
            r = dictService.updateById(dict);
        }
        return this.basicResult(r);
    }

    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @RequestMapping(value = "query-page", method = RequestMethod.POST)
    public ResultModel<Page<PtDict>> queryPage(@ApiParam @RequestBody Page<PtDict> page) {
        EntityWrapper<PtDict> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort_no", true);
        return new ResultModel<>(ResultStatus.SUCCESS, dictService.selectPage(page, wrapper));
    }

}
