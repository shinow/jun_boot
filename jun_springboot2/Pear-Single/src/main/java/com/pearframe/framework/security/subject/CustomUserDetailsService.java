package com.pearframe.framework.security.subject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pearframe.modules.system.domain.SysUser;
import com.pearframe.modules.system.mapper.SysUserMapper;
import com.pearframe.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Describe: 自定义 Security 用户服务实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * Describe: 自定义用户加载策略
     * Param: username
     * */
    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        CustomUserDetails customUserDetails = sysUserMapper.loadUserByUsername(s);

        if(customUserDetails==null)

            throw new UsernameNotFoundException("用户名不存在");

        return customUserDetails;
    }
}
