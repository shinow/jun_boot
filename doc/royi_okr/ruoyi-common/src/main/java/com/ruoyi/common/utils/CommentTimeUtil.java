package com.ruoyi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentTimeUtil {
    private static final long ONEDATE = 86400000; // 一天的时间差
    private static final long ONEHOUR = 3600000; // 一小时的时间差
    private static final long ONEMINUTE = 60000; // 一分钟的时间差
    private static final long ZONE = 0; // 一分钟的时间差

    /**
     * time类型 是 yyyy-MM-dd HH:mm:ss.s 不是的请转化成这个
     * time 未与当前时间相比的时间  time  就是未来的时间
     * @param time
     * @return
     */
    public static String putDate(String time) {
        try {
            SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date otheTime = parse.parse(time);
            Date nowTime  = new Date();
            Long time10   = nowTime.getTime();
            Long time11   = otheTime.getTime();
            long timeDiff =  time10 - time11;
            if (timeDiff >= ONEDATE) {
                String someDate = someDate(timeDiff);
                return someDate == null ? time : someDate;
            }
            if (timeDiff >= ONEHOUR) {
                return someHour(timeDiff);
            }
            if (timeDiff >= ONEMINUTE) {
                return someMinute(timeDiff);
            }
            if (timeDiff >= ZONE){
                return someZone();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String putDate(Date time) {
        Date nowTime = new Date();
        Long time10 = nowTime.getTime();
        Long time11 = time.getTime();
        long timeDiff = time10 - time11;
        if (timeDiff >= ONEDATE) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String someDate = someDate(timeDiff);
            return someDate == null ? format.format(time) : someDate;
        }
        if (timeDiff >= ONEHOUR) {
            return someHour(timeDiff);
        }
        if (timeDiff >= ONEMINUTE) {
            return someMinute(timeDiff);
        }
        return null;
    }


    private static String someZone() {
        return "刚刚";
    }


    private static String someMinute(long timeDiff) {
        int dateNum = (int) (timeDiff / ONEMINUTE);
        String dateStr;
        if (dateNum < 10) {
            dateStr = "刚刚";
        } else if (dateNum < 30) {
            dateStr = "半小时前";
        } else {
            dateStr = "1个小时以内";
        }
        return dateStr;

    }

    /**
     *
     * 判断相差小时
     *
     * @param timeDiff
     * @return
     */

    private static String someHour(long timeDiff) {
        int dateNum = (int) (timeDiff / ONEHOUR);
        String dateStr;
        if (dateNum < 12) {
            dateStr = getDate(dateNum, "小时前");
        } else {
            dateStr = "一天以内";
        }
        return dateStr;
    }

    /**
     * 用于判断相差月数
     * @param timeDiff
     * @return
     */
    private static String someDate(long timeDiff) {
        int dateNum = (int) (timeDiff / ONEDATE);
        String dateStr;
        if (dateNum <= 15) {
            dateStr = getDate(dateNum, "天前内");
        } else if (dateNum <= 30 && dateNum > 15) {
            dateStr = "1个月内";
        } else if (dateNum < 60) {
            dateStr = "2个月内";
        } else {
            dateStr = "很久很久以前";
        }
        return dateStr;
    }

    /**
     * 组装提示
     *
     * @param index
     * @param str
     * @return
     */
    private static String getDate(int index, String str) {
        return String.format("%s%s", enNum2CnNum(String.valueOf(index)), str);
    }

    /**
     * 数字转化成中国的一二三
     * @param num
     * @return
     */
    public static String enNum2CnNum(String num) {
        String[] CnNum = new String[] { "零", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] Cn10Num = new String[] { "十", "百", "千","万" };
        String CnStr = "";

        try {
            char[] strArray = num.toCharArray();
            int length = strArray.length;
            for (int i = 0; i < strArray.length; i++) {
                if (length > 1 && ((char2Int(strArray[i])) != 0)) {
                    CnStr += (CnNum[char2Int(strArray[i])] + Cn10Num[length - 2]);
                } else {
                    CnStr += CnNum[char2Int(strArray[i])];
                }
                length--;
            }
            // 处理多个零字段
            CnStr = CnStr.replaceAll("零零*", "零");
            // 处理最后一个是零
            if (CnStr.lastIndexOf("零") + 1 == CnStr.length()) {
                CnStr = CnStr.replaceAll("零$", "");
            }
            // 处理十几
            if (CnStr.contains("十") && (CnStr.length() == 3 || CnStr.length() == 2)) {
                CnStr = CnStr.replaceAll("^一", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CnStr;

    }
    /**
     * 获取单个的String
     * @param cha
     * @return
     */
    private static Integer char2Int(char cha) {
        return Integer.valueOf(cha + "");
    }
}
