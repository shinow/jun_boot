package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Admin;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-30
 */
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getByLoginName(String userName);

    List<Admin> selectByPageAndAdmin(Integer start, Integer limit, Admin admin);

    Integer selectTotalnum();

    Admin getByAdminId(Integer adminId);

    Integer updateByPrimaryKeySelective(Admin admin);

    Integer insertAdmin(Admin admin);

    Integer deleteByAdminId(Integer adminId);
}
