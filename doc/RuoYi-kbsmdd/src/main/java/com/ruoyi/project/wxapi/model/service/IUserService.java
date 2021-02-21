package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
public interface IUserService extends IService<User> {

    User selectByOpenId(String openid);

    User selectByUserId(Integer userId);

    Integer insertUser(User user);
}
