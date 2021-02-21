package com.ruoyi.web.controller.service.log;
import com.ruoyilog.mapper.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    public List<Map<String,String>> selectList(String field, String tableName, String condition, int startRow, int pageSize, String orderStr){
        if(condition.equals("")){
            condition = " 1=1 ";
        }
        Map<String,String> param = new HashMap<>();
        param.put("field", field);
        param.put("condition", condition);
        param.put("table", tableName);
        param.put("startRow", startRow + "");
        param.put("pageSize", pageSize + "");
        param.put("orderStr", orderStr);
        List<Map<String,Object>> maps = userLogMapper.selectList(param);
        List<Map<String,String>> result = new ArrayList<>();
        for(Map<String,Object> entry : maps){
            Map<String,String> map = new HashMap<>();
            for(Map.Entry<String,Object> entry1 : entry.entrySet()){
                map.put(entry1.getKey(), String.valueOf(entry1.getValue()));
            }
            result.add(map);
        }
        return result;
    }
    public int selectCount(String tableName, String condition){
        if(condition.equals("")){
            condition = " 1=1 ";
        }
        int count = userLogMapper.selectCount(tableName, condition);
        return count;
    }

}
