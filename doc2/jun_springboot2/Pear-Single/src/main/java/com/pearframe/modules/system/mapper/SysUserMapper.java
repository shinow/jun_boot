package com.pearframe.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearframe.framework.security.subject.CustomUserDetails;
import com.pearframe.modules.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{

    public CustomUserDetails loadUserByUsername(@Param("username") String username);

}
