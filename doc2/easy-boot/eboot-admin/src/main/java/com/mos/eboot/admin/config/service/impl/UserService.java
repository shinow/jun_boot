package com.mos.eboot.admin.config.service.impl;

import com.mos.eboot.admin.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.shiro.entity.IUser;
import com.mos.eboot.tools.shiro.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService implements IUserService {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public IUser findUserByUsername(String username) {
        return sysUserService.getByUsername(username);
    }

    public ResultModel<String> updateUser(SysUser user){
        return sysUserService.saveOrUpdate(user);
    }
}
