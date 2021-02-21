package com.pearframe.framework.security.authent;

import com.pearframe.framework.security.subject.CustomUserDetails;
import com.pearframe.framework.security.subject.CustomUserDetailsService;
import com.pearframe.modules.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Describe: 自定义 Security 登陆认证实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    public static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * Describe: 自定义 Security 登陆逻辑
     * Param: Authentication
     * Return Authentication
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.info("账户:"+username+" 密码:"+password);


        // 加载该用户
        CustomUserDetails userInfo = customUserDetailsService.loadUserByUsername(username);

        //判断密码是否正确
        if(!new BCryptPasswordEncoder().matches(password,userInfo.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
    /*    if(userInfo.getStatus()==1){
            throw new LockedException("用户已被冻结");
        }*/

        //角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();

        userInfo.setAuthorities(authorities);

        //进行登陆
        return new UsernamePasswordAuthenticationToken(
                userInfo,password,authorities
        );
    }

    /**
     *
     * */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
