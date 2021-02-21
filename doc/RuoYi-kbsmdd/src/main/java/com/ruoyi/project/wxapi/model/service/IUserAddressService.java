package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.UserAddress;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
public interface IUserAddressService extends IService<UserAddress> {

    List<UserAddress> selectByUserIdAndWxappId(Integer userId, Integer wxappId);

    UserAddress getByAddressId(Integer addressId);

    UserAddress getByAddressIdAndUserId(Integer address_id, Integer parseJWT);

    Integer updateByUserAdressIdAndUserId(UserAddress userAddress);

    Integer deleteByAddressIdandUserId(Integer address_id, Integer parseJWT);

    Integer insertUserAddress(UserAddress userAddress);
}
