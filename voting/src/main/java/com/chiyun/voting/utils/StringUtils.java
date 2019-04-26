package com.chiyun.voting.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 判断目标字符串是否为空
     *
     * @param target
     * @return
     */
    public static Boolean isEmpty(String target) {
        if (target == null) {
            return true;
        }
        if (target.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断目标字符串是否不为空
     *
     * @param target
     * @return
     */
    public static Boolean isNotEmpty(String target) {
        if (target == null) {
            return false;
        }
        if (target.trim().length() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取给定时间前几小时的时间
     *
     * @param date 时间段的结束时间
     * @param hour 小时
     * @return String
     */
    public static String getbeginString(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, hour);// before  hour
        return format.format(cal.getTime());
    }

    public static Date getNewDate(Date old, Integer month) {
        if (old == null || old.before(new Date()))
            old = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(old);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取给定时间前几小时的时间
     *
     * @param date 时间段的结束时间
     * @param hour 小时
     * @return Date
     */
    public static Date getbeginDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, hour);// before  hour
        return cal.getTime();
    }

    public static String getnamelike(String str) {
        if (isEmpty(str))
            return "%%";
        return "%" + str + "%";
    }

    /**
     * 获取一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一天结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 比较所给时间与当前时间差是否小于给定分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static boolean datecompare(Date date, int minute) {
        if (date == null)
            return false;
        int dev = (int) ((date.getTime() - System.currentTimeMillis()) / (60 * 1000));
        return dev < minute && dev > 0;
    }
}
