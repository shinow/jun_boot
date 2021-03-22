package com.mos.eboot.service.platform.service;

import com.mos.eboot.platform.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.mos.eboot.vo.TreeNode;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
public interface ISysMenuService extends IService<SysMenu> {

	List<TreeNode> selectByRoleId(String roleId);

	List<SysMenu> selectByUser(String userId);

	List<String> selectPermit(String userId);


}
