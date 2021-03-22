package org.springrain.system.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.RoleMenu;
import org.springrain.system.service.IMenuService;

import java.util.List;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */

@Service("menuService")
public class MenuServiceImpl extends BaseSpringrainServiceImpl implements IMenuService {


    @Override
    public String saveMenu(Menu entity) throws Exception {


        //String id= SecUtils.getUUID();
        String id = null;
        if (StringUtils.isNotBlank(entity.getId())) {
            id = entity.getId();
        } else {
            id = SecUtils.getTimeNO();
            //id = tableindexService.updateNewId(Menu.class);
        }
        entity.setId(id);

        String comcode = findMenuNewComcode(id, entity.getPid());

        entity.setComcode(comcode);

        super.save(entity);

        // updateMenuManager(id,entity.getManagerRoleId());
        // 清除缓存
        super.evictByKey(GlobalStatic.qxCacheKey, "findAllMenuTree");

        return id;


    }


    public Integer updateMenu(Menu entity) throws Exception {
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findMenuById_" + entity.getId());


        String pid = entity.getPid();
        entity.setComcode(null);

        String id = entity.getId();
        if (StringUtils.isEmpty(id)) {
            return null;
        }


        Menu old_menu = findMenuById(id);
        if (old_menu == null) {
            return null;
        }

        // 清除缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findMenuById_" + id);
        super.evictByKey(GlobalStatic.qxCacheKey, "findAllMenuTree");

        String old_c = old_menu.getComcode();

        String new_c = findMenuNewComcode(id, pid);

        if (new_c.equalsIgnoreCase(old_c)) {// 编码没有改变
            return super.update(entity, true);
        }
        // 编码改变
        entity.setComcode(new_c);
        Integer update = super.update(entity, true);
        // 级联更新
        Finder f_s_list = Finder.getSelectFinder(Menu.class, "id,comcode")
                .append(" WHERE comcode like :comcode and id<>:id ").setParam("comcode", old_c + "%")
                .setParam("id", id);
        List<Menu> list = super.queryForList(f_s_list, Menu.class);
        if (CollectionUtils.isEmpty(list)) {
            return update;
        }

        for (Menu menu : list) {
            String _id = menu.getId();
            String _c = findMenuNewComcode(_id, id);
            menu.setComcode(_c);
            menu.setPid(id);
            // 清理缓存
            // super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findMenuById_" + _id);
        }

        super.update(list, true);
        // 清除缓存
        // super.evictByKey(GlobalStatic.qxCacheKey,"findAllMenuTree");
        return update;
    }

    @Override
    public Menu findMenuById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findMenuById_" + id;
        Menu menu = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, Menu.class);
        if (menu != null) {
            return menu;
        }
        menu = super.findById(id, Menu.class);
        if (menu == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, menu);
        return menu;
    }

    @Override
    public String deleteMenuById(String id) throws Exception {

        List<String> menuIds = findMenuBypid(id);
        if (CollectionUtils.isEmpty(menuIds)) {
            return null;
        }

        Finder f_delete_re = Finder.getDeleteFinder(RoleMenu.class).append(" WHERE menuId in (:menuIds)");
        f_delete_re.setParam("menuIds", menuIds);
        super.update(f_delete_re);

        Finder f_delete = Finder.getDeleteFinder(Menu.class).append(" WHERE id in (:menuIds)");
        f_delete.setParam("menuIds", menuIds);
        super.update(f_delete);

        super.cleanCache(GlobalStatic.qxCacheKey);

        return null;
    }

    @Override
    public List<String> findMenuBypid(String pid) throws Exception {


        if (pid == null) {
            return null;
        }

        Finder f_select = Finder.getSelectFinder(Menu.class, "id").append(" WHERE active=1  ");

        if (StringUtils.isNotBlank(pid)) {// pid 不是根节点
            Menu menu = this.findMenuById(pid);
            if (menu == null) {
                return null;
            }
            String comcode = menu.getComcode();
            if (StringUtils.isBlank(comcode)) {
                return null;
            }
            f_select.append(" and comcode like :comcode ").setParam("comcode", comcode + "%");

        }

        f_select.append(" order by sortno desc ");
        List<String> menuIds = super.queryForList(f_select, String.class);

        return menuIds;
    }


    @Override
    public String findMenuNewComcode(String id, String pid) throws Exception {

        if (StringUtils.isEmpty(id)) {
            return null;
        }

        String comcode = null;
        Finder f_p_c = Finder.getSelectFinder(Menu.class, "comcode").append(" WHERE id=:pid ").setParam("pid", pid);
        String p_c = super.queryForObject(f_p_c, String.class);
        // 如果没有上级
        if (StringUtils.isEmpty(p_c)) {
            p_c = ",";
        }

        comcode = p_c + id + ",";

        return comcode;
    }


	@Override
	public List<Menu> findAllMenuListByQueryBean(Menu menu, Page<Menu> page) throws Exception {
		Finder finder = Finder.getSelectFinder(Menu.class)
				.append(" WHERE active=:active");
		finder.setParam("active", 1);
		return this.queryForList(finder, Menu.class);
	}

}
