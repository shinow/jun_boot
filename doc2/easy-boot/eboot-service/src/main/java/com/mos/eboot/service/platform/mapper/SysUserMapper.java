package com.mos.eboot.service.platform.mapper;

import com.mos.eboot.platform.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mos.eboot.vo.ChartVO;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 小尘哥
 * @since 2018-01-13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	List<ChartVO> loginCount();
}