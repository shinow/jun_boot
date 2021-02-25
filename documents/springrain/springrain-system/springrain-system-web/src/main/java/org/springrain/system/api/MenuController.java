package org.springrain.system.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.base.BaseController;
import org.springrain.system.entity.Menu;
import org.springrain.system.service.IMenuService;
import org.springrain.system.service.IUserRoleMenuService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * TODO 菜单列表
 *
 * @author springrain<Auto generate>
 * @version 2019-07-26 17:52:10
 */
@RestController
@RequestMapping(value = "/api/system/menu", method = RequestMethod.POST)
public class MenuController extends BaseController {
	@Resource
	private IMenuService menuService;

	@Resource
	private IUserRoleMenuService userRoleMenuServiceImpl;

	/**
	* 所有菜单数据
	*
	* @param menu
	* @param page
	* @return
	* @throws Exception
	* @author 程相羽
	* @version 2020年11月2日 下午2:47:14
	*/
	@GetMapping("/all/list/json")
	public ReturnDatas<List<Menu>> allListJson(Menu menu, Page<Menu> page) throws Exception {
		ReturnDatas<List<Menu>> returnObject = ReturnDatas.getSuccessReturnDatas();
		List<Menu> datas = menuService.findAllMenuListByQueryBean(menu, page);
		returnObject.setPage(page);
		returnObject.setResult(datas);
		return returnObject;
	}

	@RequestMapping(value = "/lists", method = RequestMethod.POST)
	public ReturnDatas<List<Menu>> lists() throws Exception {
		ReturnDatas<List<Menu>> returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		// Page page = newPage(request);
		// ==执行分页查询
		List<Menu> datas = userRoleMenuServiceImpl.findAllMenuTree();
		returnObject.setResult(datas);
		return returnObject;
	}

	/**
	 * 查看的Json格式数据
	 */
	@RequestMapping(value = "/look", method = RequestMethod.POST)
	public ReturnDatas<Menu> look(String id) throws Exception {
		ReturnDatas<Menu> returnObject = ReturnDatas.getSuccessReturnDatas();

		if (StringUtils.isNotBlank(id)) {
			Menu menu = menuService.findMenuById(id);
			returnObject.setResult(menu);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;

	}

	/**
	 * 保存 操作,返回json格式数据
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnDatas<Menu> save(@RequestBody Menu menu) {
		ReturnDatas<Menu> returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
		try {

			String id = menu.getId();
			if (StringUtils.isBlank(id)) {
				menu.setId(null);
			}
			menuService.saveMenu(menu);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.SAVE_ERROR);
		}
		return returnObject;

	}

	/**
	 * 修改 操作,返回json格式数据
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ReturnDatas<Menu> update(@RequestBody Menu menu) {
		ReturnDatas<Menu> returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			String id = menu.getId();
			if (StringUtils.isBlank(id)) {
				return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
			}
			menuService.updateMenu(menu);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ReturnDatas<Menu> delete(@RequestBody Map<String, Object> map) throws Exception {
		// 执行删除
		try {
			String id = (String) map.get("id");
			if (StringUtils.isNotBlank(id)) {
				menuService.deleteMenuById(id);
				return new ReturnDatas<Menu>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas<Menu>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas<Menu>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
	}

}
