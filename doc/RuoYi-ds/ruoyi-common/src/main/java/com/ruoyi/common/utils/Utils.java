package com.ruoyi.common.utils;
import java.util.HashMap;
import java.util.Map;
public class Utils {

    public static Map<String, String> condition(Map<String, String> params, String[] heads) {
        Map<String, String> maps = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" 1=1 ");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            if (key.contains("tiaojian")) {
                String[] arr1 = key.split("#");
                for (String str : heads) {
                    String arr2[] = str.split("#");
                    if (!arr2[0].equals(arr1[1])) {
                        continue;
                    }
                    if (arr2.length >= 3) {
                        String start = params.get("input#start#" + arr1[1]);
                        String end = params.get("input#end#" + arr1[1]);
                        if (start == null || start.equals("") || end == null || end.equals("")) {
                            break;
                        }
                        //时间戳int
                        if (arr2[2].equals("timestamp")) {
                            int startTime = DateUtil.pasreStringToInt(start, DateUtil.DATE_TIME_FORMAT);
                            int endTime = DateUtil.pasreStringToInt(end, DateUtil.DATE_TIME_FORMAT);
                            sb.append(" and ").append(arr1[1]).append(">=").append(startTime).append(" and ").append(arr1[1]).append("<=").append(endTime);
                        }
                        //时间戳long
                        if (arr2[2].equals("timestamplong")) {
                            long startTime = DateUtil.pasreStringToLong(start, DateUtil.DATE_TIME_FORMAT);
                            long endTime = DateUtil.pasreStringToLong(end, DateUtil.DATE_TIME_FORMAT);
                            sb.append(" and ").append(arr1[1]).append(">=").append(startTime).append(" and ").append(arr1[1]).append("<=").append(endTime);
                        }
                        //时间字符串
                        if (arr2[2].equals("datetime")) {
                            sb.append(" and ").append(arr1[1]).append(">=").append("'").append(start).append("'").
                                    append(" and ").append(arr1[1]).append("<=").append("'").append(end).append("'");
                        }
                    } else {
                        String value = params.get("input#" + arr1[1]);
                        if (value == null || value.equals("")) {
                            break;
                        }
                        if (entry.getValue().equals("1")) {
                            sb.append(" and ").append(arr1[1]).append(">=").append("'").append(value).append("'");
                        }
                        if (entry.getValue().equals("2")) {
                            sb.append(" and ").append(arr1[1]).append("<=").append("'").append(value).append("'");
                        }
                        if (entry.getValue().equals("3")) {
                            sb.append(" and ").append(arr1[1]).append("=").append("'").append(value).append("'");
                        }
                        if (entry.getValue().equals("4")) {
                            sb.append(" and ").append(arr1[1]).append(" like ").append("'%").append(value).append("%'");
                        }
                        if (entry.getValue().equals("6")) {
                            String[] arr = value.split("&");
                            if (arr.length < 2) {
                                break;
                            }
                            sb.append(" and ").append(arr1[1]).append(">=").append("'").append(arr[0]).append("'");
                            sb.append(" and ").append(arr1[1]).append("<=").append("'").append(arr[1]).append("'");
                        }
                        //分组
                        if (entry.getValue().equals("7")) {
                            maps.put("addGroup", " group by " + arr1[1] + " ");
                        }
                        //求和
                        if (entry.getValue().equals("8")) {
                            maps.put("addField", "sum(" + arr1[1] + ")" + "#" + "求和统计");
                            maps.put("addWhere", "order by sum(" + arr1[1] + ") desc ");
                        }
                        //统计次数
                        if (entry.getValue().equals("9")) {
                            maps.put("addField", "count(" + arr1[1] + ")" + "#" + "次数统计");
                            maps.put("addWhere", "order by count(" + arr1[1] + ") desc ");
                        }
                    }
                    break;
                }
            }
        }
        sb.append(" ");
        maps.put("where", sb.toString());
        return maps;
    }
}
