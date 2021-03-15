package com.mos.eboot.service.platform.mapper;

import com.mos.eboot.platform.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mos.eboot.vo.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 根据角色查询资源信息
	 * @param roleId
	 * @return
	 */
	List<TreeNode> selectResource(@Param("roleId")String roleId);

	/**
	 * 根据用户查询资源信息
	 * @param userId
	 * @param menuType
	 * @return
	 */
	List<SysMenu> selectByUser(@Param("userId")String userId,@Param("menuType")String menuType);

	/**
	 * 查询权限列表
	 * @param userId
	 * @return
	 */
	List<String> selectPermit(@Param("userId")String userId);

	/**
	 * 获取全部权限
	 * @param menuType
	 * @return
	 */
	List<String> selectAllPermit(@Param("menuType") String menuType);

	/**
	 * 获取全部菜单
	 * @param menuType
	 * @return
	 */
	List<SysMenu> selectAllMenu(@Param("menuType") String menuType);
}