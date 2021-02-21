package com.ruoyi.plugs.province.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ProvinceMapper {


    public List<Map<String,Object>> getPositionProvinceList();

    public List<Map<String,Object>> getPositionCityByProvinceId(@Param("province_id") String provinceId);

    public List<Map<String,Object>> getPositionCountryByCityId(@Param("city_id") String cityId);

    public List<Map<String,Object>> getPositionTownByCountryId(@Param("country_id") String countryId);
}
