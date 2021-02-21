package com.pearframe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearframe.modules.system.domain.SysRole;
import com.pearframe.modules.system.mapper.SysRoleMapper;
import com.pearframe.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
