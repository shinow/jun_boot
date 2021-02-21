package com.ruoyi.plugs.province.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.plugs.province.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ProvinceController extends BaseController {

    @Autowired
    ProvinceService provinceService;
    @ResponseBody
    @RequestMapping("/plugs/getPositionProvinceList")
    public Object getPositionProvinceList(HttpServletRequest request) {
        Map<String, String> map = ServletUtils.getMap(request);
        String selectId=String.valueOf(map.get("select_id"));
        String selectedProvince=String.valueOf(map.get("selectedProvince"));
        if(StringUtils.isEmpty(selectedProvince)){
            selectedProvince="";
        }
        if (StringUtils.isEmpty(selectId)) {
            logger.error("请求省份列表没有传递select_id参数!");
            selectId="ProvinceList";
        }
        return provinceService.getPositionProvinceList(selectId,selectedProvince);
    }

    @ResponseBody
    @RequestMapping("/plugs/getPositionCityByProvinceId")
    public Object getPositionCityByProvinceId(HttpServletRequest request) {

        Map<String, String> map = ServletUtils.getMap(request);
        String selectId=String.valueOf(map.get("select_id"));
        String provinceId=String.valueOf(map.get("provinceId"));

        String selectedCity=String.valueOf(map.get("selectedCity"));
        if(StringUtils.isEmpty(selectedCity)){
            selectedCity="";
        }

        if (StringUtils.isEmpty(selectId)) {
            logger.warn("请求城市列表没有传递select_id参数!");
            selectId="CityList";
        }
        return provinceService.getPositionCityByProvinceId(selectId,provinceId,selectedCity);
    }
    @ResponseBody
    @RequestMapping("/plugs/getPositionCountryByCityId")
    public Object getPositionCountryByCityId(HttpServletRequest request) {

        Map<String, String> map = ServletUtils.getMap(request);
        String selectId=String.valueOf(map.get("select_id"));
        String cityId=String.valueOf(map.get("cityId"));

        String selectedCountry=String.valueOf(map.get("selectedCountry"));
        if(StringUtils.isEmpty(selectedCountry)){
            selectedCountry="";
        }


        if (StringUtils.isEmpty(selectId)) {
            logger.warn("请求乡镇列表没有传递select_id参数!");
            selectId="PositionList";
        }
        return provinceService.getPositionCountryByCityId(selectId, cityId,selectedCountry);

    }
    @ResponseBody
    @RequestMapping("/plugs/getPositionTownByCountryId")
    public Object getPositionTownByCountryId(HttpServletRequest request) {

        Map<String, String> map = ServletUtils.getMap(request);
        String selectId=String.valueOf(map.get("select_id"));
        String countryId=String.valueOf(map.get("countryId"));

        String selectedTown=String.valueOf(map.get("selectedTown"));
        if(StringUtils.isEmpty(selectedTown)){
            selectedTown="";
        }

        if (StringUtils.isEmpty(selectId)) {
            logger.error("请求村庄列表没有传递select_id参数!");
            selectId="TownList";
        }
        return provinceService.getPositionTownByCountryId(selectId, countryId,selectedTown);
    }

    /**
     *初始化省、市、区、村庄4个Select
     * @return
     */
    @ResponseBody
    @RequestMapping("/plugs/initPositionSelect")
    public Object initPositionSelect(HttpServletRequest request) {
        Map<String, String> map = ServletUtils.getMap(request);
        String provinceId=String.valueOf(map.get("province_id"));
        String cityId=String.valueOf(map.get("city_id"));
        String countryId=String.valueOf(map.get("country_id"));
        String townId=String.valueOf(map.get("city_id"));

        String provinceSelectId=String.valueOf(map.get("province_select_id"));
        String citySelectId=String.valueOf(map.get("city_select_id"));
        String countrySelectId=String.valueOf(map.get("country_select_id"));
        String townSelectId=String.valueOf(map.get("town_select_id"));

        return provinceService.initPositionSelect(provinceId,provinceSelectId, cityId,citySelectId,countryId,countrySelectId,townId,townSelectId);
    }
}
