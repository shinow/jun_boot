package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.UserAddress;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {

    List<UserAddress> selectByUserIdAndWxappId(Integer userId, Integer wxappId);

    UserAddress getByAddressId(Integer addressId);

    UserAddress getByAddressIdAndUserId(Integer addressId, Integer userId);

    Integer updateByUserAdressIdAndUserId(UserAddress userAddress);

    Integer deleteByAddressIdandUserId(Integer addressId, Integer userId);

    Integer insertUserAddress(UserAddress userAddress);
}
