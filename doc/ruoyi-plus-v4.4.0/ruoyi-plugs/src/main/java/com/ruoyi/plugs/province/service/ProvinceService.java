package com.ruoyi.plugs.province.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.plugs.province.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;
    
    public Map<String,Object> getPositionProvinceList(String selectId, String nowProvinceId) {
        Map<String, Object> reurnMap = new HashMap<String, Object>();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list = provinceMapper.getPositionProvinceList();
        Map<String, Object> tmap = null;
        for (Map<String, Object> m : list){
            tmap = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(nowProvinceId)&&nowProvinceId.equals(String.valueOf(m.get("province_id")))){
                tmap.put("selected","selected");
            }
            tmap.put("id",m.get("province_id"));
            tmap.put("name", m.get("province_name"));
            ls.add(tmap);
        }
        reurnMap.put(selectId,ls);
        return reurnMap;
    }

    public Map<String,Object> getPositionCityByProvinceId(String selectId,String provinceId,String nowCityId){
        Map<String, Object> reurnMap = new HashMap<String, Object>();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list =provinceMapper.getPositionCityByProvinceId(provinceId);
        Map<String, Object> tmap = null;
        for (Map<String, Object> m : list){
            tmap = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(nowCityId)&&nowCityId.equals(String.valueOf(m.get("city_id")))){
                tmap.put("selected","selected");
            }
            tmap.put("id",m.get("city_id"));
            tmap.put("name", m.get("city_name"));
            ls.add(tmap);
        }
        reurnMap.put(selectId,ls);
        return reurnMap;
    }

    public Map<String,Object> getPositionCountryByCityId(String selectId,String cityId,String nowCountryId){
        Map<String, Object> reurnMap = new HashMap<String, Object>();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list =provinceMapper.getPositionCountryByCityId(cityId);
        Map<String, Object> tmap = null;
        for (Map<String, Object> m : list){
            tmap = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(nowCountryId)&&nowCountryId.equals(String.valueOf(m.get("country_id")))){
                tmap.put("selected","selected");
            }
            tmap.put("id",m.get("country_id"));
            tmap.put("name", m.get("country_name"));
            ls.add(tmap);
        }
        reurnMap.put(selectId,ls);
        return reurnMap;
    }

    public Map<String,Object> getPositionTownByCountryId(String selectId,String countryId,String nowTownId){
        Map<String, Object> reurnMap = new HashMap<String, Object>();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list = provinceMapper.getPositionTownByCountryId(countryId);
        Map<String, Object> tmap = null;
        for (Map<String, Object> m : list){
            tmap = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(nowTownId)&&nowTownId.equals(String.valueOf(m.get("town_id")))){
                tmap.put("selected","selected");
            }
            tmap.put("id",m.get("town_id"));
            tmap.put("name", m.get("town_name"));
            ls.add(tmap);
        }
        reurnMap.put(selectId,ls);
        return reurnMap;
    }

    public Map<String,Object> initPositionSelect(String provinceId,String provinceSelectId,String cityId,String citySelectId,String countryId,String countrySelectId,String townId,String townSelectId){
        Map<String, Object> reurnMap = new HashMap<String, Object>();
        Map<String, Object> tmap=null;
        if(StringUtils.isNotEmpty(provinceId)&&StringUtils.isNotEmpty(provinceSelectId)){
            tmap=getPositionProvinceList(provinceSelectId,provinceId);
            reurnMap.put("province_select_id",tmap.get("province_select_id"));
            if(StringUtils.isNotEmpty(cityId)){
                tmap=getPositionCityByProvinceId(citySelectId, provinceId,cityId);
                reurnMap.put("city_select_id",tmap.get("city_select_id"));

                if(StringUtils.isNotEmpty(countryId)){
                    tmap=getPositionCountryByCityId(countrySelectId, cityId, countryId);
                    reurnMap.put("country_select_id",tmap.get("country_select_id"));
                    if(StringUtils.isNotEmpty(townId)){
                        tmap=getPositionTownByCountryId(townSelectId, countryId,townId);
                        reurnMap.put("town_select_id",tmap.get("town_select_id"));
                    }else{

                    }
                }else{

                }
            }else{

            }
        }else{
            reurnMap.put("result","false");
            reurnMap.put("msg","province_id或province_select_id不能为空!");
        }
        reurnMap.put("result","true");
        return reurnMap;
    }
}
