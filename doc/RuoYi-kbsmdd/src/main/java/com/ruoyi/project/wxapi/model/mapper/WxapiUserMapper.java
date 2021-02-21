package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
public interface WxapiUserMapper extends BaseMapper<User> {

    User selectByOpenId(String openid);

    User selectByUserId(Integer userId);

    Integer insertUser(User user);
}
