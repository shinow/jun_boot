package org.springrain.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期,时间工具类
 */
public class DateUtils {

    /**
     * 默认时区
     */
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * 日期
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间
     */
    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);


    private DateUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 转换日期字符串得到指定格式的日期类型
     *
     * @param formatString 需要转换的格式字符串
     * @param targetDate   需要转换的时间
     * @return
     * @throws ParseException
     */
    public static final Date convertString2Date(String formatString, String targetDate) throws ParseException {
        if (StringUtils.isBlank(targetDate))
            return null;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Date result = null;
        try {
            result = format.parse(targetDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return result;
    }

    /**
     * 转换字符串得到默认格式的日期类型
     *
     * @param dataStr 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date convertString2Date(String dataStr) {
        if (StringUtils.isBlank(dataStr)) {
            return null;
        }
        Date date = null;
        try {
            if (dataStr.length() <= 4) {
                date = new SimpleDateFormat("yyyy").parse(dataStr);
            } else if (dataStr.length() <= 7) {
                date = new SimpleDateFormat("yyyy-MM").parse(dataStr);
            } else if (dataStr.length() <= 10) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);
            } else if (dataStr.length() <= 19 && dataStr.contains("T")) {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dataStr);
            } else if (dataStr.length() <= 19) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataStr);
            } else if (dataStr.length() <= 23 && dataStr.contains("T")) {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss").parse(dataStr);
            } else if (dataStr.length() <= 23) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").parse(dataStr);
            } else if (dataStr.length() <= 24) {//2020-08-09T06:57:20.078Z
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'").parse(dataStr);
            }
        } catch (ParseException ex) {
            logger.error(ex.getMessage(), ex);
        }
        if (date != null) {
            return date;
        }
        return null;

    }

    /**
     * 转换日期得到指定格式的日期字符串
     *
     * @param formatString 需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
     * @param targetDate   目标日期
     * @return
     */
    public static String convertDate2String(String formatString, Date targetDate) {
        SimpleDateFormat format = null;
        String result = null;
        if (targetDate != null) {
            format = new SimpleDateFormat(formatString);
            result = format.format(targetDate);
        } else {
            return null;
        }
        return result;
    }

    /**
     * 转换日期,得到默认日期格式字符串
     *
     * @param targetDate 需要转化的日期
     * @return
     */
    public static String convertDate2String(Date targetDate) {
        return convertDate2String(DATE_FORMAT, targetDate);
    }


    /**
     * 格式化日期
     *
     * @param formatString 需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
     * @param targetDate   目标日期
     * @throws ParseException 例: DateUtils.formatDate("yyyy-MM-dd HH",new Date())
     *                        "yyyy-MM-dd HH:00:00"
     */
    public static Date formatDate(String formatString, Date targetDate) throws ParseException {
        if (targetDate == null) {
            return null;
        }
        if (StringUtils.isBlank(formatString)) {
            formatString = DateUtils.DATE_FORMAT;
        }

        targetDate = DateUtils.convertString2Date(formatString, DateUtils.convertDate2String(formatString, targetDate));

        return targetDate;
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
     * @param targetDate 目标日期
     * @return
     * @throws ParseException 例： DateUtils.formatDate(new Date()) "yyyy-MM-dd
     *                        00:00:00"
     */
    public static Date formatDate(Date targetDate) throws ParseException {
        targetDate = formatDate(DateUtils.DATE_FORMAT, targetDate);
        return targetDate;
    }


}
