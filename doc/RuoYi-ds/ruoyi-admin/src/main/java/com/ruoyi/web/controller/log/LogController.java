package com.ruoyi.web.controller.log;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.*;
import com.ruoyi.framework.datasource.DataSourceHolder;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.quartz.util.SpringContextUtil;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.web.controller.service.game.PlayerService;
import com.ruoyi.web.controller.service.log.UserLogService;
import com.ruoyi.web.controller.tool.PropertiesUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 游戏日志管理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
    private String prefix = "log";
    @Autowired
    private UserLogService userLogService;

    @RequiresPermissions("log:view")
    @GetMapping()
    public String list() {
        return prefix + "/list";
    }

    @RequestMapping(value = "/logInfo")
    @ResponseBody
    public String logInfo(HttpServletRequest request) {
        HashMap<String, String> params = ParamMapUtil.getParameterMap(request);
        int start_row = Integer.valueOf(params.get("start_row"));
        String menuType = params.get("menuType");
        String source = params.get("server");
        if (menuType.equals("-1") || source.equals("-1")) {
            return "";
        }
        Properties properties = PropertiesUtil.getInstance().loadPropertiesBySource("classpath:log.properties");
        String table = properties.getProperty(menuType);
        String[] arr = table.split(",");
        Map<String, String> maps = Utils.condition(params, arr);
        String where = "";
        if (maps.get("where") != null) {
            where = maps.get("where");
        }
        DataSourceHolder.setDataSource(source);
        int startRowNum = (start_row - 1) * 50;
        int pageSize = 50;
        DataModel data = new DataModel();
        StringBuilder sb = new StringBuilder();
        LinkedHashMap<String, String> heads = new LinkedHashMap<>();
        String orderStr = "";
        for (String str : arr) {
            String[] arr1 = str.split("#");
            String field = arr1[0];
            String text = arr1[1];
            heads.put(field, text);
            //如果是时间戳类型就转为时间字符串
            if (arr1.length >= 3) {
                if (arr1[2].equals("timestamp")) {
                    sb.append("FROM_UNIXTIME(" + field + ", '%Y-%m-%d %H:%i:%s') as " + field + "").append(",");
                    orderStr = " order by " + field + " desc ";
                }
                if (arr1[2].equals("timestamplong")) {
                    sb.append("FROM_UNIXTIME(" + field + "/1000, '%Y-%m-%d %H:%i:%s') as " + field + "").append(",");
                    orderStr = " order by " + field + " desc ";
                }
                if (arr1[2].equals("datetime")) {
                    sb.append(field).append(",");
                    orderStr = " order by " + field + " desc ";
                }
            } else {
                sb.append(field).append(",");
            }
        }
        if (maps.get("addGroup") != null) {
            orderStr = "";
            orderStr += maps.get("addGroup");
        }
        if (maps.get("addWhere") != null) {
            orderStr += maps.get("addWhere");
        }
        if (maps.get("addField") != null) {
            String[] arr1 = maps.get("addField").split("#");
            String field = arr1[0];
            String text = arr1[1];
            heads.put(field, text);
            sb.append(field).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        data.setHead(heads);
        List<Map<String, String>> resultList = userLogService.selectList(sb.toString(), menuType, where, startRowNum, pageSize, orderStr);
        int count = userLogService.selectCount(menuType, where);

        Map<Integer, Map<String, String>> bodys = new TreeMap<>();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, String> map = resultList.get(i);
            for (String key : sb.toString().split(",")) {
                String object = map.get(key);
                map.put(key, object);
            }
            bodys.put(i, map);
        }
        data.setBody(bodys);
        data.setCount(count == 0 ? 1 : count);
        String str = JSON.toJson(data);
        return str;
    }
}
