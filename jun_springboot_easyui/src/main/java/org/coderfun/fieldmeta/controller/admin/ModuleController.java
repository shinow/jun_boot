package org.coderfun.fieldmeta.controller.admin;



import java.util.List;

import org.coderfun.common.exception.AppException;
import org.coderfun.common.exception.ErrorCodeEnum;
import org.coderfun.fieldmeta.entity.Module;
import org.coderfun.fieldmeta.entity.Module_;
import org.coderfun.fieldmeta.entity.Project;
import org.coderfun.fieldmeta.entity.Project_;
import org.coderfun.fieldmeta.service.ModuleService;
import org.coderfun.fieldmeta.service.ProjectService;
import org.coderfun.sys.dict.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import klg.j2ee.common.model.EasyUIPage;
import klg.j2ee.common.model.JsonData;
import klg.j2ee.query.jpa.expr.AExpr;


@Controller("adminModuleController")
@RequestMapping("/admin/action/module")
public class ModuleController {
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	ProjectService projectService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonData add(
			@ModelAttribute Module module){
		
		Long projectId=module.getProject().getId();
		module.setProject(projectService.getById(projectId));
		moduleService.save(module);
		return JsonData.success();
	}
	
	
	@ResponseBody
	@RequestMapping("/edit")
	public JsonData edit(
			@ModelAttribute Module module){
		
		Long projectId=module.getProject().getId();
		module.setProject(projectService.getById(projectId));
		moduleService.update(module);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JsonData delete(
			@RequestParam Long id){
		
		if(!moduleService.delete(id,true)){
			throw new AppException(ErrorCodeEnum.ENTITY_HAS_RELATED_DATA);
		}
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/findpage")
	public EasyUIPage findpage(
			@ModelAttribute Module module,
			@RequestParam int page,
			@RequestParam int rows){
		Pageable pageable=new PageRequest(page<1?0:page-1, rows, new Sort(Direction.DESC,"id"));
		Page<Module> pageData=moduleService.findPage(module, pageable);
		return new EasyUIPage(pageData);
	}
	
	@ResponseBody
	@RequestMapping("/findlist")
	public JsonData findlist(
			@ModelAttribute Module module){
		
		List<Module> listData=moduleService.findList(module, new Sort(Direction.DESC,"id"));
		return JsonData.success(listData);
	}
	
	@ResponseBody
	@RequestMapping("/defalut_project")
	public JsonData defaultProject(){
		Project project = projectService.getDefaultProject();
		List<Module> listData=moduleService.findList( new Sort(Direction.DESC,"id"),
				AExpr.eq(Module_.project, project));
		return JsonData.success(listData);
	}
}
