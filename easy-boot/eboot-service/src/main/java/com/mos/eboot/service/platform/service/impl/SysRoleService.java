package com.mos.eboot.service.platform.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.SysRole;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mos.eboot.platform.entity.SysRoleMenu;
import com.mos.eboot.service.platform.mapper.SysRoleMapper;
import com.mos.eboot.service.platform.service.ISysRoleMenuService;
import com.mos.eboot.service.platform.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

	@Resource
	private ISysRoleMenuService roleMenuService;

    @Override
    public Page<SysRole> queryPage(Page<SysRole> page) {
        return this.selectPage(page,new EntityWrapper<>());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean bindResource(String id, String[] resourceIds) {
    	EntityWrapper<SysRoleMenu> entityWrapper = new EntityWrapper<>();
    	entityWrapper.eq("role_id",id);
		roleMenuService.delete(entityWrapper);

		List<SysRoleMenu> roleMenus = new ArrayList<>();
		SysRoleMenu roleMenu;
		for (int i=0 , length = resourceIds.length;i<length;i++){
			roleMenu = new SysRoleMenu();
			roleMenu.setRoleId(id);
			roleMenu.setMenuId(resourceIds[i]);
			roleMenus.add(roleMenu);
		}
		return roleMenuService.insertBatch(roleMenus);
	}
}
