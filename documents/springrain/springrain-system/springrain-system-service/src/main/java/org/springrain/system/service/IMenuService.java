package org.springrain.system.service;

import org.springrain.frame.util.Page;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.entity.Menu;

import java.util.List;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */
@RpcServiceAnnotation
public interface IMenuService extends IBaseSpringrainService {


    /**
     * 根据ID查找
     *
     * @param id
     * @return
     * @throws Exception
     */
    Menu findMenuById(String id) throws Exception;


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    String saveMenu(Menu entity) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @return
     * @throws Exception
     */
    Integer updateMenu(Menu entity) throws Exception;


    /**
     * 根据Id删除菜单和子菜单
     *
     * @param id
     * @return
     * @throws Exception
     */
    String deleteMenuById(String id) throws Exception;


    /**
     * 根据pid,查询菜单id包括子菜单id
     *
     * @param pid
     * @return
     * @throws Exception
     */
    List<String> findMenuBypid(String pid) throws Exception;


    /**
     * 根据id和pid生成菜单的Comcode
     *
     * @param id
     * @param pid
     * @return
     * @throws Exception
     */
    String findMenuNewComcode(String id, String pid) throws Exception;


	/**
	* 根据查询条件查询所有可用菜单
	*
	* @param menu
	* @param page
	* @return
	* @author 程相羽
	* @version 2020年11月2日 下午2:50:23
	 * @throws Exception 
	*/
	List<Menu> findAllMenuListByQueryBean(Menu menu, Page<Menu> page) throws Exception;


}
