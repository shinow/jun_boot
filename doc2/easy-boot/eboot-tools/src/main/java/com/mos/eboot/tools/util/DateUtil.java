package com.mos.eboot.tools.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtil extends DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd", "yyyy/MM",
            "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd", "yyyy.MM",
            "yyyyMMddHHmmss", "yyyyMMddHHmm", "yyyyMMdd", "yyyyMM"
    };


    public static Date string_date(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date string_date(Object str, String pattern) {
        if (str == null || null == pattern) {
            return null;
        }
        try {
            return parseDate(str.toString(), pattern);
        } catch (Exception e) {
            return null;
        }
    }

    public static String date_string(Date date) {
        for (String pattern : parsePatterns) {
            String d = date_string(date, pattern);
            if (StringUtils.isNoneBlank(d)) {
                return d;
            }
        }
        return null;
    }

    public static String date_string(Date date, String pattern) {
        String formatDate = null;
        if (null != date && pattern != null) {
            formatDate = DateFormatUtils.format(date, pattern);
        }
        return formatDate;
    }

    public static Date date_date(Date date) {
        if (date == null) {
            return null;
        }
        for (String pattern : parsePatterns) {
            Date d = date_date(date, pattern);
            if (null != d) {
                return d;
            }
        }
        return null;
    }

    public static Date date_date(Date date, String pattern) {
        if (date == null || null == pattern) {
            return null;
        }
        try {
            String s = date_string(date, pattern);
            return string_date(s, pattern);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 得到日期年份字符串 格式（yyyy）
     */
    public static String getYear(Date d) {
        return date_string(d, "yyyy");
    }

    /**
     * 得到日期月份字符串 格式（MM）
     */
    public static String getMonth(Date d) {
        return date_string(d, "MM");
    }

    /**
     * 得到日期字符串 格式（dd）
     */
    public static String getDay(Date d) {
        return date_string(d, "dd");
    }

    /**
     * 得到 日期星期字符串 格式（E）星期几
     */
    public static String getWeekStr(Date d) {
        return date_string(d, "E");
    }

    /**
     * 得到 日期星期字符串 格式（E）星期几
     */
    public static int getWeekInt(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        dayOfWeek = dayOfWeek == 0 ? 7 : dayOfWeek;
        return dayOfWeek;
    }


    /**
     * 获取过去的天数
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }


    /**
     * 两个日期之间的天数
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 增加天数
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        c.getTime();
        return c.getTime();
    }

    /**
     * 增加小时数
     */
    public static Date addHours(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        c.getTime();
        return c.getTime();
    }

    /**
     * 增加分钟数
     */
    public static Date addMin(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, min);
        c.getTime();
        return c.getTime();
    }


    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        c.getTime();
        return c.getTime();
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
                * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
                + sss;
    }
}
