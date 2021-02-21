package com.ruoyi.project.wxapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.config.AppPropConfig;
import com.ruoyi.project.wxapi.model.bean.Shop;
import com.ruoyi.project.wxapi.model.bean.UploadFile;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.enums.ShopStatusEnum;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.service.IShopService;
import com.ruoyi.project.wxapi.model.service.IUploadFileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api("shop")
@RestController
@RequestMapping("/wxapi/shop")
public class WxapiShopController {
    @Autowired
    IShopService shopService;
    @Autowired
    IUploadFileService uploadFileService;
    @Autowired
    AppPropConfig appPropConfig;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result detail(@Valid BaseParamQO baseParamQO) {
        Shop shop = shopService.getByWxappIdAndShopId(baseParamQO.getWxapp_id(), baseParamQO.getShop_id());
        JSONObject item = new JSONObject();
        item.put("shop_id", shop.getId());
        UploadFile uploadFile = uploadFileService.selectById(shop.getLogoImageId());
        if (uploadFile != null) {
            JSONObject logo = new JSONObject();
            logo.put("file_path", appPropConfig.getDomain() + uploadFile.getFileName());
            item.put("logo", logo);
        }
        item.put("phone", shop.getPhone());
        item.put("shop_hours", shop.getShopHours());
        JSONObject statusObj = new JSONObject();
        switch (shop.getStatus()) {
            case 0:
                statusObj.put("value", shop.getStatus());
                statusObj.put("text", ShopStatusEnum.CLOSE.getText());

                break;
            case 1:
                statusObj.put("value", shop.getStatus());
                statusObj.put("text", ShopStatusEnum.OPEN.getText());
                break;
        }
        item.put("status", statusObj);
        item.put("shop_name", shop.getShopName());
        // 计算当前坐标与门店坐标相差距离
        item.put("location", "1公里");
        item.put("foodMode", JSONArray.parseArray(shop.getFoodMode()));

        item.put("address", shop.getAddress());


        return Result.success(item);
    }

    @RequestMapping("/lists")
    public Result lists(BaseParamQO baseParamQO) {
        List<Shop> shops = shopService.selectByWxappId(baseParamQO.getWxapp_id());
        JSONArray list = new JSONArray();

        for (int i = 0; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            JSONObject item = new JSONObject();
            UploadFile uploadFile = uploadFileService.selectById(shop.getLogoImageId());
            if (uploadFile != null) {
                JSONObject logo = new JSONObject();
                logo.put("file_path", appPropConfig.getDomain() + uploadFile.getFileName());
                item.put("logo", logo);
            }

            item.put("shop_id", shop.getId());
            JSONObject statusObj = new JSONObject();
            switch (shop.getStatus()) {
                case 0:
                    statusObj.put("value", shop.getStatus());
                    statusObj.put("text", ShopStatusEnum.CLOSE.getText());

                break;
                case 1:
                    statusObj.put("value", shop.getStatus());
                    statusObj.put("text", ShopStatusEnum.OPEN.getText());
                break;
            }
            item.put("status", statusObj);
            item.put("shop_name", shop.getShopName());
            // 计算当前坐标与门店坐标相差距离
            item.put("location", "1公里");
            item.put("address", shop.getAddress());
            item.put("foodMode", JSONArray.parseArray(shop.getFoodMode()));

            list.add(item);
        }

        JSONObject data = new JSONObject();
        data.put("list", list);

        return Result.success(data);
    }
}

