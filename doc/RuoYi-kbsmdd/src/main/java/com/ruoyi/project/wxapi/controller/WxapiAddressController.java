package com.ruoyi.project.wxapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.controller.util.TencentMapUtil;
import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.bean.UserAddress;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.dto.UserAddressDTO;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.UserAddressQO;
import com.ruoyi.project.wxapi.model.service.IUserAddressService;
import com.ruoyi.project.wxapi.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/wxapi/address")
@RestController
public class WxapiAddressController {

    @Autowired
    IUserAddressService userAddressService;
    @Autowired
    IUserService userService;

    @RequestMapping("/getLocation")
    public Result getLocation(BaseParamQO baseParamQO) {
        JSONObject locationObj = TencentMapUtil.getLocation(baseParamQO.getLocation());
        System.out.println(locationObj.getJSONObject("result"));
        String recommend = locationObj.getJSONObject("result").getJSONObject("formatted_addresses").getString("recommend");
        JSONObject location = locationObj.getJSONObject("result").getJSONObject("address_component");
        JSONObject data = new JSONObject();
        data.put("location", location);
        data.put("recommend", recommend);
        return Result.success(data);

    }

    @RequestMapping("/lists")
    public Result lists(BaseParamQO baseParamQO) {
        User user = userService.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        List<UserAddress> userAddresses = userAddressService.selectByUserIdAndWxappId(JWTUtil.parseJWT(baseParamQO.getToken()), baseParamQO.getWxapp_id());
        List<UserAddressDTO> userAddressDTOS = new ArrayList<>();
        for (int i = 0; i < userAddresses.size(); i++) {
            UserAddressDTO userAddressDTO = new UserAddressDTO();
            BeanUtil.copyProperties(userAddresses.get(i), userAddressDTO);
            userAddressDTOS.add(userAddressDTO);
        }
        JSONObject data = new JSONObject();
        data.put("list", userAddressDTOS);
        data.put("default_id", user.getAddressId());

        return Result.success(data);
    }

    @RequestMapping("/detail")
    public Result detail(BaseParamQO baseParamQO, Integer address_id) {
        UserAddress userAddress = userAddressService.getByAddressIdAndUserId(address_id, JWTUtil.parseJWT(baseParamQO.getToken()));
        UserAddressDTO userAddressDTO = new UserAddressDTO();
        BeanUtil.copyProperties(userAddress, userAddressDTO);
        JSONObject data = new JSONObject();
        data.put("detail", userAddressDTO);
        data.put("disabled", false);
        data.put("nav_select", false);
        return Result.success(data);
    }

    @RequestMapping("/edit")
    public Result edit(BaseParamQO baseParamQO, Integer address_id, UserAddressQO userAddressQO) {
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(userAddressQO, userAddress);
        userAddress.setUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        userAddress.setId(address_id);
        Integer flag = userAddressService.updateByUserAdressIdAndUserId(userAddress);

        return Result.success();
    }

    @RequestMapping("/delete")
    public Result delete(BaseParamQO baseParamQO, Integer address_id) {
        Integer flag = userAddressService.deleteByAddressIdandUserId(address_id, JWTUtil.parseJWT(baseParamQO.getToken()));
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @RequestMapping("/add")
    public Result add(BaseParamQO baseParamQO, UserAddressQO userAddressQO) {
        UserAddress userAddress = new UserAddress();
        BeanUtil.copyProperties(userAddressQO, userAddress);
        userAddress.setUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        userAddress.setWxappId(baseParamQO.getWxapp_id());
        Integer flag = userAddressService.insertUserAddress(userAddress);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error("添加失败");
        }
    }

    @RequestMapping("/setDefault")
    public Result setDefault(BaseParamQO baseParamQO, Integer address_id) {
        User user = userService.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        user.setAddressId(address_id);
        Boolean flag = userService.updateById(user);
        if (flag) {
            return Result.success();
        } else {
            return Result.error("设置默认地址失败");
        }
    }

}
