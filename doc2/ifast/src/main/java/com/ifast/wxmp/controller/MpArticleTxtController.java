package com.ifast.wxmp.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.BaseController;
import com.ifast.common.utils.Result;
import com.ifast.wxmp.domain.MpArticleDO;
import com.ifast.wxmp.domain.MpConfigDO;
import com.ifast.wxmp.service.MpArticleService;
import com.ifast.wxmp.service.MpConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2018-10-18 21:21:36 | Aron</small>
 */
@Controller
@RequestMapping("/wxmp/mpArticle/txt")
public class MpArticleTxtController extends BaseController {
	@Autowired
	private MpArticleService mpArticleService;
	@Autowired
	private MpConfigService mpConfigService;

	@GetMapping()
	@RequiresPermissions("wxmp:mpArticleTxt:mpArticle")
	String MpArticle(){
	    return "wxmp/mpArticleTxt/mpArticle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("wxmp:mpArticleTxt:mpArticle")
	public Result<Page<MpArticleDO>> list(MpArticleDO mpArticleDTO, String appId){
        Wrapper<MpArticleDO> wrapper = new EntityWrapper<>(mpArticleDTO).eq("mpId", mpConfigService.findOneByKv("appId", appId).getId()).orderBy("id", false);
        Page<MpArticleDO> page = mpArticleService.selectPage(getPage(MpArticleDO.class), wrapper);
        return success(page);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("wxmp:mpArticleTxt:add")
	String add(){
	    return "wxmp/mpArticleTxt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wxmp:mpArticleTxt:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MpArticleDO mpArticle = mpArticleService.selectById(id);
		model.addAttribute("mpArticle", mpArticle);
	    return "wxmp/mpArticleTxt/edit";
	}
	
	@Log("??????")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wxmp:mpArticleTxt:add")
	public Result<String> save( MpArticleDO mpArticle, String appId){
	    mpArticle.setCreatedate(new Date());
		mpArticle.setMpId(mpConfigService.selectOne(MpConfigDO.builder().appId(appId).build()).getId());
		mpArticleService.insert(mpArticle);
        return success();
	}
	
	@Log("??????")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wxmp:mpArticleTxt:edit")
	public Result<String>  update( MpArticleDO mpArticle){
		mpArticle.setUpdatedate(new Date());
		boolean update = mpArticleService.updateById(mpArticle);
		return update ? success() : Result.fail();
	}
	
	@Log("??????")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpArticleTxt:remove")
	public Result<String>  remove( Long id){
		mpArticleService.deleteById(id);
        return success();
	}
	
	@Log("????????????")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("wxmp:mpArticleTxt:batchRemove")
	public Result<String>  remove(@RequestParam("ids[]") Long[] ids){
		mpArticleService.deleteBatchIds(Arrays.asList(ids));
		return success();
	}
	
}
