package com.mos.eboot.service.platform.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mos.eboot.enumeration.MenuType;
import com.mos.eboot.platform.entity.SysMenu;
import com.mos.eboot.service.platform.mapper.SysMenuMapper;
import com.mos.eboot.service.platform.service.ISysMenuService;
import com.mos.eboot.tools.util.StringUtil;
import com.mos.eboot.vo.TreeNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<TreeNode> selectByRoleId(String roleId) {
        return sysMenuMapper.selectResource(roleId);
    }

    @Override
    @Cacheable(cacheNames="userMenus",key = "#p0")
    public List<SysMenu> selectByUser(String userId) {
        System.out.println("进来开始获取~~");
        if (StringUtil.isNotBlank(userId)) {
            return sysMenuMapper.selectByUser(userId, MenuType.MENU.getCode());
        }
        return sysMenuMapper.selectAllMenu(SysMenu.MENU_TYPE_RESOURCE);
    }

    @Override
    public List<String> selectPermit(String userId) {
        if (StringUtil.isNotBlank(userId)) {
            return sysMenuMapper.selectPermit(userId);
        }
        return sysMenuMapper.selectAllPermit(SysMenu.MENU_TYPE_PERMIT);
    }
}
