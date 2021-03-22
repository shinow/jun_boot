package org.springrain.system.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.IDicDataService;

/**
 * TODO 在此加入类描述
 *
 * @author 9iu.dicData<Auto generate>
 * @version 2013-07-31 15:56:45
 */
@RestController
@RequestMapping(value = "/api/system/dicdata")
public class DicDataController extends BaseController {
	@Resource
	private IDicDataService dicDataService;

	/**
	 * 查询所有字典类型信息
	 *
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/type/list")
	public ReturnDatas<List<DicData>> list(HttpServletRequest request, Page<DicData> page) {
		ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
		
		String data = request.getParameter("data");
		if(StringUtils.isNotBlank(data)) {
			DicData queryBean = JsonUtils.readValue(data, DicData.class);
			page.setData(queryBean);
		}else {
			page.setData(new DicData());
		}
		
		List<DicData> datas = null;
		try {
			datas = dicDataService.findAllRootList(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(datas);
		returnObject.setPage(page);
		return returnObject;
	}

	

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@PostMapping(value = "/type/update")
	public ReturnDatas<String> typeUpdate(@RequestBody DicData dicData) {
		ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();

		String dicDataId = null;
		try {
			if (StringUtils.isBlank(dicData.getId())) {
				// 保存新的字典类型
				dicDataId = dicDataService.saveDicDataType(dicData);
			} else {
				// 修改字典类型
				dicDataId = dicData.getId();
				dicDataService.update(dicData, true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("保存失败");
		}
		returnObject.setResult(dicDataId);
		return returnObject;
	}

	/**
	 * 根据类型查询类型下的字典列表
	 *
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/type/{typeName}")
	@ResponseBody
	public ReturnDatas<DicData> listjson(@PathVariable String typeName) {
		ReturnDatas<DicData> returnObject = ReturnDatas.getSuccessReturnDatas();
		if(StringUtils.isBlank(typeName)) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数错误");
			return returnObject;
		}
		DicData dicData = null;
		try {
			dicData = dicDataService.findDicDataById(typeName);
		} catch (Exception e) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(dicData);
		return returnObject;
	}
	
	/**
	 *  删除父类型
	 *
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/type/delete/{ids}")
	@ResponseBody
	public ReturnDatas<DicData> deleteType(@PathVariable String ids) {
		ReturnDatas<DicData> returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			List<String> idList = Arrays.asList(StringUtils.split(ids, ","));
			dicDataService.deleteParentDicDataById(idList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("删除失败,请检查当前字典是否有子级元素");
		}
		return returnObject;
	}
	
	@GetMapping("/data/type/{typekey}")
	@ResponseBody
	public ReturnDatas<List<DicData>> dataTypelistjson(@PathVariable String typekey) {
		ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
		if(StringUtils.isBlank(typekey)) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数错误");
			return returnObject;
		}
		List<DicData> dicData = null;
		try {
			dicData = dicDataService.findDicDataListByPid(typekey);
		} catch (Exception e) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(dicData);
		return returnObject;
	}
	
	@GetMapping("/data/list")
	@ResponseBody
	public ReturnDatas<List<DicData>> dataList(HttpServletRequest request, Page<DicData> page) {
		ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
		String pid = request.getParameter("typekey");
		List<DicData> dicDataList = null;
		try {
			dicDataList = dicDataService.findListByPid(pid, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("查询失败");
		}
		returnObject.setResult(dicDataList);
		returnObject.setPage(page);
		return returnObject;
	}

}
