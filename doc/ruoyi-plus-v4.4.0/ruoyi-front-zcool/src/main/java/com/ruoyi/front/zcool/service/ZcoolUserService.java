package com.ruoyi.front.zcool.service;

import com.ruoyi.front.zcool.domain.ZcoolUser;
import com.ruoyi.front.zcool.mapper.ZcoolUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZcoolUserService {
    @Autowired
    ZcoolUserMapper zcoolUserMapper;

    public ZcoolUser selectByUserId(Long userId){
        return  zcoolUserMapper.selectByUserId(userId);
    }

    public int updateUserInfo(ZcoolUser user){
        return  zcoolUserMapper.updateUserInfo(user);
    }

}
