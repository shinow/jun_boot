package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.mapper.WxapiUserMapper;
import com.ruoyi.project.wxapi.model.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
@Service
public class WxapiUserServiceImpl extends ServiceImpl<WxapiUserMapper, User> implements IUserService {

    @Override
    public User selectByOpenId(String openid) {
        return this.getBaseMapper().selectByOpenId(openid);
    }

    @Override
    public User selectByUserId(Integer userId) {
        return this.getBaseMapper().selectByUserId(userId);
    }

    @Override
    public Integer insertUser(User user) {
        return this.getBaseMapper().insertUser(user);
    }
}
