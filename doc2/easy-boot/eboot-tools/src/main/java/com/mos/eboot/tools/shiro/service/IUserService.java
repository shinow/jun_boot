package com.mos.eboot.tools.shiro.service;


import com.mos.eboot.tools.shiro.entity.IUser;

public interface IUserService {
	
	IUser findUserByUsername(String username);

}
