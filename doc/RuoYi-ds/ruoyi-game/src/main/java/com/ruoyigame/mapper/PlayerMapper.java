package com.ruoyigame.mapper;
import com.ruoyi.common.provider.QueryProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 数据层
 * 
 * @author ruoyi
 */
public interface PlayerMapper
{
    @SelectProvider(type = QueryProvider.class, method = "queryByParam")
    @ResultType(Map.class)
    public List<Map<String, Object>> selectList(Map<String, String> param);

    @Select({"select count(*) from ${tableName} where ${condition}"})
    public int selectCount(@Param("tableName") String tableName, @Param("condition") String condition);

}