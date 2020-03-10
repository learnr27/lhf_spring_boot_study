package com.lhf.springboot.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/27 16:51
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 获取系统当前日期
     * @return
     */
    public static LocalDate getNow(){
        LocalDate date = LocalDate.now();
        return date;
    }

    /**
     * 构造指定日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static LocalDate customTime(Integer year, Integer month, Integer day){
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }




}
