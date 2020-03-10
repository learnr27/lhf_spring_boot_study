package com.lhf.springboot.date;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DateUtil {
    private static String defaultPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 锁对象
     */
    private static final Object LOCKOBJ = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap =
            new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    public static String getCurrentDate(String aFormat) {
        String tDate = new SimpleDateFormat(aFormat).format(new Date(System.currentTimeMillis()));
        return tDate;
    }

    public static String getCurrentDate() {
        return DateUtil.getCurrentDate("yyyyMMdd");
    }

    public static String getCurrentDates() {
        return DateUtil.getCurrentDate("MMddHHmm");
    }

    public static String getCurrentTime() {
        return DateUtil.getCurrentDate("HHmmss");
    }

    public static String getCurrentDateAndTime() {
        return DateUtil.getCurrentDate("yyyyMMddHHmmss");
    }

    public static String getCurrentDateAndTimeFormat() {
        return DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args){
        System.out.println(getCurrentDates());
    }
    /**
     * 日期格式化
     *
     * @param date    日期
     * @param formate 格式，以本类定义的格式为准，可自行添加。
     * @return
     */
    public static String dateToString(Date date, String formate) {
        if (date == null) {
            return "";
        }

        return new SimpleDateFormat(formate).format(date);
    }

    /**
     * 日期减少n天
     *
     * @param date 日期
     * @param day  天数
     * @return
     */
    public static Date minusDay(Date date, int day) {
        return addDate(date, Calendar.DAY_OF_YEAR, -day);
    }

    /**
     * 日期增加n天
     *
     * @param date 日期
     * @param day  天数
     * @return
     */
    public static Date addDay(Date date, int day) {
        return addDate(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期增加
     *
     * @param date          日期
     * @param calendarType  增加类型，如：Calendar.YEAR，Calendar.DAY_OF_YEAR，Calendar.MONTH
     * @param calendarValue
     * @return
     */
    public static Date addDate(Date date, int calendarType, int calendarValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarType, calendarValue);
        return calendar.getTime();
    }

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> sdfLocal = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (sdfLocal == null) {
            synchronized (LOCKOBJ) {
                sdfLocal = sdfMap.get(pattern);
                if (sdfLocal == null) {

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    sdfLocal = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, sdfLocal);
                }
            }
        }

        return sdfLocal.get();
    }

    public static String time2str(Date date, String pattern) {
        SimpleDateFormat sdf = null;
        if (StringUtils.isNotEmpty(pattern)) {
            sdf = getSdf(pattern);
        } else {
            sdf = getSdf(defaultPattern);
        }

        return sdf.format(date);
    }

    public static Date str2Date(String dateStr, String pattern)
            throws Exception {
        SimpleDateFormat sdf = null;
        if (StringUtils.isNotEmpty(pattern)) {
            sdf = getSdf(pattern);
        } else {
            sdf = getSdf(defaultPattern);
        }
        return sdf.parse(dateStr);
    }

    public static String format(Date date, String pattern) {
        if(date == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date str2Date(String dateStr)
            throws Exception {
        String[] date = dateStr.split(" ");
        dateStr = date[0];
        String regExp = "^\\d{4}(\\-|\\/)?\\d{1,2}\\1\\d{1,2}$";
        if (!Pattern.matches(regExp, dateStr) && !Pattern.matches("^\\d{8}$", dateStr)) {
            throw new Exception();
        }
        String pattern = "yyyyMMdd";
        if (StringUtils.isNotBlank(dateStr)) {
            if (dateStr.split("/").length > 1) {
                pattern = "yyyy/MM/dd";
            }
            if (dateStr.split("-").length > 1) {
                pattern = "yyyy-MM-dd";
            }
        }
        SimpleDateFormat sdf = getSdf(pattern);
        return sdf.parse(dateStr);
    }

    public static String date2Str(Date date)
            throws Exception {
        SimpleDateFormat sdf = getSdf(defaultPattern);
        return sdf.format(date);
    }

    /**
     * added by chris.zhou
     */
    public static int dayAfterMonth() {
        Calendar calendar = Calendar.getInstance();
        int nowMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int maxMonth = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        return maxMonth - nowMonth;
    }

    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    //判断选择的日期是否是今天
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    private static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    public static boolean isServing(Date start, Date end) {
        Date now = new Date();

        if (start == null) {
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String formattedStart = dateFormat.format(start);
        String formattedNow = dateFormat.format(now);

        boolean startBeforeToday = formattedStart.compareTo(formattedNow) <= 0;

        if (end == null) {
            return startBeforeToday;
        }

        String formattedEnd = dateFormat.format(end);

        boolean endAfterToday = formattedEnd.compareTo(formattedNow) >= 0;

        return startBeforeToday && endAfterToday;
    }

    /**
     * 日期添加
     *
     * @param date
     * @param type ： Calendar.YEAR,Calendar.MONTH
     * @param n    数字
     * @return
     */
    public static Date add(Date date, int type, int n) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, n);

        return calendar.getTime();
    }

    /*
     * 设置一天内最大时间
     * @param openDate
     * @return
     */
    public static Date setTime(Date openDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(openDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static int calculateDaysBetween(Date start, Date end) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(start);
        caled.setTime(end);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (calst.getTime().getTime() / 1000) - (int) (caled.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    public static List<Date> getBeginAndEndOfDay(Date date) {
        List<Date> ret = new ArrayList<Date>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        ret.add(getStartTime(cal.getTime()));
        ret.add(getEndTime(cal.getTime()));

        return ret;
    }

    public static List<Date> getBeginAndEndOfWeek(Date date) {
        List<Date> ret = new ArrayList<Date>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        ret.add(getStartTime(cal.getTime()));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        ret.add(getEndTime(cal.getTime()));

        return ret;
    }

    public static List<Date> getBeginAndEndOfMonth(Date date) {
        List<Date> ret = new ArrayList<Date>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        // 所在月开始日期
        ret.add(getStartTime(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 所在月结束日期
        ret.add(getEndTime(cal.getTime()));

        return ret;
    }

    public static List<Date> getBeginAndEndOfYear(Date date) {
        List<Date> ret = new ArrayList<Date>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        // 所在月开始日期
        ret.add(getStartTime(cal.getTime()));
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        // 所在月结束日期
        ret.add(getEndTime(cal.getTime()));

        return ret;
    }

    // 获取当天的开始时间
    public static Date getDayBegin(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取当天的结束时间
    public static Date getDayEnd(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date getDayEndSecond(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    // 获取本周的开始时间
    public static Date getBeginDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getStartTime(cal.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getEndTime(weekEndSta);
    }

    // 获取本月的开始时间
    public static Date getBeginDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getStartTime(cal.getTime());
    }

    // 获取本月的结束时间
    public static Date getEndDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getEndTime(cal.getTime());
    }

    public static Date getBeingDayOfIntervalOneYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DATE, 1);

        return getStartTime(cal.getTime());
    }

    // 获取本年的开始时间
    public static Date getBeginDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getStartTime(cal.getTime());
    }

    // 获取本年的结束时间
    public static Date getEndDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getEndTime(cal.getTime());
    }

    private static Date getStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date getEndOfTimeIgnoreSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String getYMD(Date signUpTime) {
        if (signUpTime == null) {
            return "";
        }
        SimpleDateFormat sdf = getSdf(defaultPattern);
        String datetime = sdf.format(signUpTime);
        return getYMD(datetime);
    }

    public static String getYMD(String datetime) {
        return StringUtils.isNotBlank(datetime) ? datetime.substring(0, 10) : "";
    }

    public static String getHMS(Date signUpTime) {
        if (signUpTime == null) {
            return "";
        }
        SimpleDateFormat sdf = getSdf(defaultPattern);
        String datetime = sdf.format(signUpTime);
        return getHMS(datetime);
    }

    public static String getHMS(String datetime) {
        return StringUtils.isNotBlank(datetime) ? datetime.replaceAll(" +", "").substring(10) : "";
    }


    public static Date getYesterDay() {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,-24);
        return calendar.getTime();
    }

    public static Date getMaxTime(Date date) {
        if(date == null){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();
    }

    public static Date getMinTime(Date date) {
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
}
