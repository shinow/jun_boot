package com.ruoyi.business.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author PanMeiCheng
 * @version 1.0
 * @date 2018-12-11
 * @company DM
 */
public interface TestMapper {
    @Select("select * from sys_user order by user_id")
    List<Map> getAllUsers();
    @Select("${value}")
    List<Map> getAllUsersBySql(String value);
}
