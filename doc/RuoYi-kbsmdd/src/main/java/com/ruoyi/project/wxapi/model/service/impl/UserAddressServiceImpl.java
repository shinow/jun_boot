package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.UserAddress;
import com.ruoyi.project.wxapi.model.mapper.UserAddressMapper;
import com.ruoyi.project.wxapi.model.service.IUserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

    @Override
    public List<UserAddress> selectByUserIdAndWxappId(Integer userId, Integer wxappId) {
        return this.getBaseMapper().selectByUserIdAndWxappId(userId, wxappId);
    }

    @Override
    public UserAddress getByAddressId(Integer addressId) {
        return this.getBaseMapper().getByAddressId(addressId);
    }

    @Override
    public UserAddress getByAddressIdAndUserId(Integer addressId, Integer userId) {
        return this.getBaseMapper().getByAddressIdAndUserId(addressId, userId);
    }

    @Override
    public Integer updateByUserAdressIdAndUserId(UserAddress userAddress) {
        return this.getBaseMapper().updateByUserAdressIdAndUserId(userAddress);
    }

    @Override
    public Integer deleteByAddressIdandUserId(Integer addressId, Integer userId) {
        return this.getBaseMapper().deleteByAddressIdandUserId(addressId, userId);
    }

    @Override
    public Integer insertUserAddress(UserAddress userAddress) {
        return this.getBaseMapper().insertUserAddress(userAddress);
    }
}
