package com.ruoyi.front.zcool.mapper;

import com.ruoyi.front.zcool.domain.ZcoolUser;



public interface ZcoolUserMapper {
    public ZcoolUser selectByUserId(Long userId);
    public int updateUserInfo(ZcoolUser user);
}
