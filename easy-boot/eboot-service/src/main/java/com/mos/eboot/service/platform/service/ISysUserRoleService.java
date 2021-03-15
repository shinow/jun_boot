package com.mos.eboot.service.platform.service;

import com.mos.eboot.platform.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-14
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    List<String> getRoleIds(String userId);
}
