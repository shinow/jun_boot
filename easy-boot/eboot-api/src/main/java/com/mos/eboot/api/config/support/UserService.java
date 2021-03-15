package com.mos.eboot.api.config.support;

import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小尘哥
 */
@Service("userService")
public class UserService implements IUserService {

    @Resource
    private ISysUserService sysUserService;

    public ResultModel<String> updateUser(SysUser user){
        return sysUserService.saveOrUpdate(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return sysUserService.getByUsername(username);
	}
}
