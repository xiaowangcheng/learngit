package com.manage.rain.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wyf on 2018/1/15.
 */

public class DateUtil {
    /**
     * default date format pattern
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_FORMAT2 = "yyyy年MM月dd日";
    public final static String DATE_FORMAT3 = "yyyyMMdd";
    public final static String DATE_FORMAT4 = "yyyyMM";
    public final static String DATE_FORMAT5 = "yyyy年MM月";
    public final static String DATE_FORMAT6 = "yyyy-MM";
    public final static String DATE_FORMAT7 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public final static String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String TIME_FORMAT = "HH:mm";
    public final static String TIME_FORMAT1 = "HH:mm:ss";
    public final static String TIME_FORMAT2 = "HHmmss";
    public final static String MONTH_DAY_HOUR_MINUTE_FORMAT = "MM-dd HH:mm";
    public final static String FULL_DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public final static String FULL_DATE_TIME_FORMAT_2="yyyyMMddHHmmss";
    public final static String FULL_DATE_TIME_FORMAT_3 = "yyyy-MM-dd HH:mm";
    public final static String FULL_DATE_TIME_FORMAT_4 = "yyyyMMddHHmm";
    public final static String FULL_DATE_TIME_FORMAT_5 = "HHddmmss";

    private static final int DAYS_OF_A_WEEK = 7;

    private DateUtil() {
    }

    /**
     * parse date with the default pattern
     *
     * @param date string date
     * @return the parsed date
     */
    public static Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT3);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取增加小时后的 Date
     *
     * @param date
     * @param i
     * @return squall add 20100225
     */
    public static Date addHour(Date date, int i) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, i);
        return calendar.getTime();
    }

    /**
     * format date with the default pattern
     *
     * @param date the date that want to format to string
     * @return the formated date
     */
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }
    public static String formatDate3(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT3);
        return format.format(date);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }

    /**
     * 默认格式转化 把20120804格式化成2012-08-04格式
     * @param dateStr
     * @return
     */
    public static String formatDate(String dateStr) {
        try {
            return formatDate(parseDate(dateStr, DATE_FORMAT3), DATE_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 默认格式转化 把201208040101格式化成2012-08-04 01:01格式
     * @param dateStr
     * @return
     */
    public static String fomatDateTime(String dateStr) {
        try {
            return formatDate(parseDate(dateStr, FULL_DATE_TIME_FORMAT_4), DATE_TIME_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
        * 将时间戳转换为时间
        */
    public static String stampToDate(long s,int day){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(s);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);// 今天+1天

        Date tomorrow = c.getTime();


        res = simpleDateFormat.format(tomorrow);
        return res;
    }


    /**
     * 格式转化 把20120606162431格式化成2012年06月06日 16:24:31格式
     * @param dateStr
     * @return
     */
    public static String formatDateWithTime7(String dateStr){
        if (StringUtil.isBlank(dateStr)) {
            return "";
        }
        try {
            return formatDate(parseDate(dateStr,FULL_DATE_TIME_FORMAT_2),DATE_FORMAT7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式转化 把20120606162431格式化成2012-06-06 16:24:31格式
     * @param dateStr
     * @return
     */
    public static String formatDateWithTime(String dateStr){
        try {
            return formatDate(parseDate(dateStr,FULL_DATE_TIME_FORMAT_2),FULL_DATE_TIME_FORMAT_1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式转化 把20120606162431格式化成2012-06-06格式
     * @param dateStr
     * @return
     * @author wangcheng
     */
    public static String formatDateWithDate(String dateStr){
        try {
            return formatDate(parseDate(dateStr,FULL_DATE_TIME_FORMAT_2),DATE_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较两个日期的大小
     * 日期格式  yyyy-MM-dd
     * @param date1 比较值
     * @param date2 被比较值
     * @return  true 大于   false  小于      false 等于
     */
    public static boolean compareDate(String date1,String date2){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = sdf.parse(date1);
            Date dt2 = sdf.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 默认格式转化 把162431格式化成16:24:31格式
     * @param timeStr
     * @return
     */
    public static String fomatTime(String timeStr) {
        if(timeStr.length()==5) {
            timeStr = "0"+timeStr;
        }
        try {
            return formatDate(parseDate(timeStr, TIME_FORMAT2), TIME_FORMAT1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * format date with the given pattern
     *
     * @param date the date that want to format to string
     * @param pattern the formated pattern
     * @return the formated date
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 格式化，去掉年头两位，去掉日期，例：返回1208(表示2012年8月)
     * @param date
     * @return
     */
    public static String formatDateNoTop(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4);
            String formatStr = format.format(date);
            return formatStr.substring(2, formatStr.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化，去掉年头两位，去掉日期，例：返回201208(表示2012年8月)
     * @param date
     * @return
     */
    public static String formatDateWithTop(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化，把2012-08-04格式化成20120804格式
     * @param dateStr
     * @return
     */
    public static String formatDateNoSeparated(String dateStr) {
        try {
            Date date = parseDate(dateStr, DATE_FORMAT);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT3);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式化，把20120804格式化成格式2012-08-04
     * @param dateStr
     * @return
     */
    public static String formatDateForSeparated(String dateStr) {
        try {
            Date date = parseDate(dateStr, DATE_FORMAT3);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * get current date
     *
     * @return the string of current date
     */
    public static String getCurrentDateFormat() {
        return formatDate(new Date());
    }

    /**
     * get number of days between the two given date
     *
     * @param fromDate the begin date
     * @param endDate the end date
     * @return the number of days between the two date
     */
    public static int getDateNum(Date fromDate, Date endDate) {
        long days = (endDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24);
        return (int) days;
    }

    /**
     * add day to the date
     *
     * @param date the added date
     * @param number the number to add to the date
     * @return the added date
     */
    public static Date addDate(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, number);
        return calendar.getTime();
    }

    /**
     * add month to the date
     *
     * @param date the added date
     * @param number the number to add to the date
     * @return the added date
     */
    public static Date addMonth(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        return calendar.getTime();
    }

    /**
     * add year to the date
     *
     * @param date the added date
     * @param number the number to add to the date
     * @return the added date
     */
    public static Date addYear(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, number);
        return calendar.getTime();
    }

    /**
     * sub year to the date
     * number (Minus Sign)
     */
    public static Date subMonth(Date date, int number) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        return calendar.getTime();
    }

    /**
     * get the default calendar (start by MONDAY)
     *
     * @return the calendar instance
     */
    public static Calendar getDefaultCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar;
    }

    /**
     * format the date into string value
     *
     * @param calendar the formated calendar
     * @return the string date
     */
    public static String getStringDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + getNiceString(month) + "-" + getNiceString(day);
    }

    /**
     * according to the pattern yyyy-MM-dd
     *
     * @param value the value
     * @return the formated value
     */
    public static String getNiceString(int value) {
        String str = "00" + value;
        return str.substring(str.length() - 2, str.length());
    }

    /**
     * get calendar from date
     *
     * @param date the passing date
     * @return the calendar instance
     */
    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Interval->几时几分几秒
     *
     */
    public static String getInterval(Date startDate, Date endDate) {
        long intervalTime = endDate.getTime() - startDate.getTime();
        return getInterval(intervalTime);
    }

    /**
     * IntervalMinute
     *
     */
    public static int getIntervalMinute(Date startDate, Date endDate){
        long intervalTime = endDate.getTime() - startDate.getTime();
        return (int) (intervalTime / (1000 * 60));
    }

    /**
     * IntervalHour
     *
     */
    public static int getIntervalHour(Date startDate, Date endDate){
        long intervalTime = endDate.getTime() - startDate.getTime();
        return (int) ((intervalTime / (1000 * 60)) / 60);
    }

    /**
     * IntervalDay
     *
     */
    public static int getIntervalDay(String sDateStr, String eDateStr) {
        int day = 0;
        try {
            Date sDate = DateUtil.parseDate(sDateStr, DateUtil.DATE_FORMAT);
            Date eDate = DateUtil.parseDate(eDateStr, DateUtil.DATE_FORMAT);
            long intervalTime = eDate.getTime() - sDate.getTime();
            day = (int) (intervalTime / 1000 / 60 / 60 / 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * Interval->几时几分几秒
     *
     */
    public static String getInterval(long intervalTime) {
        int hour = (int) (intervalTime / (1000 * 60 * 60));
        int minute = (int) (intervalTime / (1000 * 60) - hour * 60);
        int second = (int) ((intervalTime / 1000) - hour * 60 * 60 - minute * 60);
        if (hour > 0) {
            return hour + "小时 " + minute + "分 " + second + "秒";
        } else if (minute > 0) {
            return minute + "分钟 " + second + "秒";
        } else {
            return second + "秒";
        }
    }

    /**
     * 年月日
     *
     */
    public static String getDateStr(Date date) {
        return getYear(date) + "年" + getMonth(date) + "月" + getDayOfMonth(date) + "日";
    }

    /**
     * 年
     *
     */
    public static int getYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 月
     *
     */
    public static int getMonth(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 日
     *
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时
     *
     */
    public static int getHour(Date now) {
        Calendar calendar = getCalendarFromDate(now);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 周
     *
     */
    public static int getWeekOfYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR) - 1;
    }

    /**
     * 时间Date
     *
     */
    public static Date getCurrentDate() {
        Calendar calendar = getCalendarFromDate(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得月份，格式为"yyyy-MM"
     *
     */
    public static String getFormatMonth(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT6, Locale.getDefault());
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下一天的Date
     *
     */
    public static Date getNextDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrentDate());
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 一周的日期
     * @param date
     * @return
     */
    public static List<Date> getWeekDayOfYear(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        List<Date> result = new ArrayList<Date>();
        result.add(getDateOfYearWeek(year, week, Calendar.MONDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.TUESDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.WEDNESDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.THURSDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.FRIDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.SATURDAY));
        result.add(getDateOfYearWeek(year, week, Calendar.SUNDAY));
        return result;
    }

    /**
     * 获取一年中某周,星期几的日期
     * @param yearNum
     * @param weekNum
     * @param dayOfWeek
     * @return
     */
    private static Date getDateOfYearWeek(int yearNum, int weekNum, int dayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        /*cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);*/
        return cal.getTime();
    }

    /**
     * 获取指定日期是一周的第几天,星期日是第一天
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = getCalendarFromDate(date);
        calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 计算是否在制定区间时间内 （天）
     *
     */
    public static boolean beyondDate(Date beginDate, Date endDate, int i){
        long inter = endDate.getTime() - beginDate.getTime();
        return inter - (i * 1000 * 60 * 60 * 24) >= 0;
    }

    /**
     * 计算是否在制定区间时间内 （分）
     *
     */
    public static boolean beyondMinute(Date beginDate, Date endDate, int i){
        long inter = endDate.getTime() - beginDate.getTime();
        return Math.abs(inter) - (i * 1000 * 60) >= 0;
    }

    /**
     * 计算是否在制定区间时间内 （秒）
     *
     */
    public static boolean beyondSecond(Date beginDate, Date endDate, int i){
        long inter = endDate.getTime() - beginDate.getTime();
        return Math.abs(inter) - (i * 1000) >= 0;
    }

    /**
     * 计算是否为开始时间与结束时间
     *
     */
    public static boolean isEndGtStart(Date StartDate, Date endDate) {
        long inter = endDate.getTime() - StartDate.getTime();
        return inter > 0;
    }

    /**
     * 判断是否同一天
     *
     */
    public static boolean isSameDay(Date dateOne, Date dateTwo) {
        boolean flag = true;
        if (getYear(dateOne) != getYear(dateTwo)) {
            flag = false;
        } else if (getMonth(dateOne) != getMonth(dateTwo)) {
            flag = false;
        } else if (getDayOfMonth(dateOne) != getDayOfMonth(dateTwo)) {
            flag = false;
        }
        return flag;
    }
}
