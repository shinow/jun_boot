package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.controller.util.Page;
import com.ruoyi.project.wxapi.model.bean.Admin;
import com.ruoyi.project.wxapi.model.dto.AdminDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-30
 */
public interface IAdminService extends IService<Admin> {

    Admin getByLoginName(String userName);

    Page<Admin> selectByPageAndAdmin(Integer page, Integer limit, Admin admin);

    AdminDTO getByAdminId(Integer id);

    Integer updateAdmin(Admin admin);

    Integer insertAdmin(Admin admin);

    Integer deleteByAdminId(Integer id);
}
