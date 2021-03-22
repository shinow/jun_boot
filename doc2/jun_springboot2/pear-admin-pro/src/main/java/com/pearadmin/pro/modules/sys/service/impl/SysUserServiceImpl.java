package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.mapper.SysUserMapper;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserService sysUserService;

}
