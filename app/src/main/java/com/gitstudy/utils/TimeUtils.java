package com.gitstudy.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wbxmz19 on 2017/11/17.
 * 时间转换的工具类
 */
public class TimeUtils {
    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal<>();
    public static long mEndTime;
    public static final long TIME = 2 * 60 * 60 * 1000;

    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second <= 0) {
            return "00:00";
        }
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + ":" + minute + ":0" + second;
            }
            return "0" + hour + ":" + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + ":" + minute + ":0" + second;
        }
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 设置周期性闹钟
     *
     * @param context
     * @param firstTime
     * @param cycTime
     * @param action
     * @param AlarmManagerType 闹钟的类型，常用的有5个值：AlarmManager.ELAPSED_REALTIME、
     *                         AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、
     *                         AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP
     */
    public static void setRepeatingAlarmTimer(Context context, long firstTime,
                                              long cycTime, String action, int AlarmManagerType) {
        Intent myIntent = new Intent();
        myIntent.setAction(action);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, myIntent,
                0);
        AlarmManager alarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManagerType, firstTime, cycTime, sender);
    }

    /**
     * 设置定时闹钟
     *
     * @param context
     * @param cycTime
     * @param action
     * @param AlarmManagerType 闹钟的类型，常用的有5个值：AlarmManager.ELAPSED_REALTIME、
     *                         AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、
     *                         AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP
     */
    public static void setAlarmTimer(Context context, long cycTime,
                                     String action, int AlarmManagerType) {
        Intent myIntent = new Intent();
        myIntent.setAction(action);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, myIntent,
                0);
        AlarmManager alarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
//        alarm.set(AlarmManagerType, cycTime, sender);
        if (Build.VERSION.SDK_INT >= 19) {
            alarm.setExact(AlarmManagerType, cycTime, sender);
        } else {
            alarm.set(AlarmManagerType, cycTime, sender);
        }
    }

    /**
     * 取消闹钟
     *
     * @param context
     * @param action
     */
    public static void cancelAlarmTimer(Context context, String action) {
        Intent myIntent = new Intent();
        myIntent.setAction(action);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, myIntent,
                0);
        AlarmManager alarm = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(sender);
    }

    public static int getCurrentYear() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            String[] split = format.split("-");
            return Integer.parseInt(split[0]);
        } catch (Exception e) {
            return 1900;
        }
    }

    public static int getCurrentMonth() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            String[] split = format.split("-");
            return Integer.parseInt(split[1]);
        } catch (Exception e) {
            return 1;
        }
    }

    public static int getCurrentDay() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            String[] split = format.split("-");
            return Integer.parseInt(split[2]);
        } catch (Exception e) {
            return 1;
        }
    }

    public static String getCurrentTime() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            return format;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前日期的年月
     */
    public static String getCurrentYearMonth() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            String format = sdf.format(date);
            return format;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String format = sdf.format(date);
            return format;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前时间，格式yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime = format.format(date);
        return curTime;
    }

    /**
     * 获取上个月日期
     */
    public static String getBeforeMonthDate(int before) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DATE));
        //月份是从0开始的，所以11表示12月
        ca.add(Calendar.MONTH, -before); //月份减before
        Date lastMonth = ca.getTime(); //结果
        SimpleDateFormat sf;
        try {
            sf = new SimpleDateFormat("yyyyMMdd");
            return sf.format(lastMonth);
        } catch (Exception e) {
            return "";
        }
    }

    private static SimpleDateFormat getDefaultFormat() {
        SimpleDateFormat simpleDateFormat = SDF_THREAD_LOCAL.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            SDF_THREAD_LOCAL.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    /**
     * Date to the formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param date The date.
     * @return the formatted time string
     */
    public static String date2String(final Date date) {
        return date2String(date, getDefaultFormat());
    }

    /**
     * Milliseconds to the date.
     *
     * @param millis The milliseconds.
     * @return the date
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }


    /**
     * Date to the formatted time string.
     *
     * @param date   The date.
     * @param format The format.
     * @return the formatted time string
     */
    public static String date2String(final Date date, @NonNull final DateFormat format) {
        return format.format(date);
    }

    /**
     * 获取当前：时(24小时制）
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前：分
     *
     * @return
     */
    public static int getCurrentMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);
    }

    /**
     * 获取时间格式转换
     *
     * @param dateTime 年月日时分
     * @return
     */
    public static String getDateTimeString(Date dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        String dateTimeString = formatter.format(dateTime);
        return dateTimeString;
    }

    /**
     * 获取离当前日期最近的一个星期天
     *
     * @param
     * @return
     */

    public static String getLastSunday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        Calendar aCalendar = Calendar.getInstance();
        Date time = null;
        try {
            time = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aCalendar.setTime(time);


        //  设置当前日期
//        Calendar aCalendar = Calendar.getInstance();
//        aCalendar.setTime(new Date());


        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1) {
            week = 7;
        } else if (week == 0) {
            week = 6;
        } else {
            week -= 1;
        }

        // 取距离当前日期最近的周日与当前日期相差的天数（count：相差的天数。正数：之后的周日，负数：之前的周日）
        int count = 0;
        if (week <= 3) {
            count = -week;
        } else if (week >= 4) {
            count = 7 - week;
        }

        // 获取距离当前日期最近的周日日期
//        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//        Calendar c = Calendar.getInstance();

        aCalendar.add(Calendar.DAY_OF_WEEK, count);


        // 格式化并打印出距离当前日期最近的周日日期
        //当前日期
        String format = sdf.format(aCalendar.getTime());
        //最近周日
        String format1 = sdf.format(aCalendar.getTime());
        System.out.println("当前日期： " + sdf.format(aCalendar.getTime()) + '\n' + "最近周日： " + sdf.format(aCalendar.getTime()));
        return "最近周日： " + format1;
    }

    /**
     * 获取距离指定日期最近的一个星期天
     *
     * @param
     * @return
     */
    public static String getNearestSunday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        Date time = null;
        try {
            time = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(time);
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));
        System.out.println(cal.getFirstDayOfWeek() + "-" + day + "+6=" + (cal.getFirstDayOfWeek() - day + 6));

        cal.add(Calendar.DATE, 6);
        System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));
        return "所在周星期日的日期：" + sdf.format(cal.getTime());
    }

    /**
     * 获取指定日期的上个周末的周日
     *
     * @param
     * @return
     */

    public static String getSomeSunday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        Calendar aCalendar = Calendar.getInstance();
        Date time = null;
        try {
            time = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aCalendar.setTime(time);


        //  设置当前日期
//        Calendar aCalendar = Calendar.getInstance();
//        aCalendar.setTime(new Date());


        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1) {
            week = 7;
        } else if (week == 0) {
            week = 6;
        } else {
            week -= 1;
        }

        // 取距离当前日期最近的周日与当前日期相差的天数（count：相差的天数。正数：之后的周日，负数：之前的周日）
        int count = 0;
        if (week <= 3) {
            count = -week;
            aCalendar.add(Calendar.DAY_OF_WEEK, count);
        } else if (week >= 4) {
            count = 7 - week;
            aCalendar.add(Calendar.DAY_OF_WEEK, -week);
        }

        // 获取距离当前日期最近的周日日期
//        DateFormat df = new SimpleDateFormat("yyyyMMdd");
//        Calendar c = Calendar.getInstance();

//        aCalendar.add(Calendar.DAY_OF_WEEK, count);


        // 格式化并打印出距离当前日期最近的周日日期
        //当前日期
        String format = sdf.format(aCalendar.getTime());
        //最近周日
        String format1 = sdf.format(aCalendar.getTime());
        System.out.println("当前日期： " + sdf.format(aCalendar.getTime()) + '\n' + "最近周日： " + sdf.format(aCalendar.getTime()));
        return "最近周日： " + format1;
    }

    /**
     * 判断给定日期是否是周日
     *
     * @param
     * @return
     */
    public static int isSunday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //设置时间格式
        Calendar aCalendar = Calendar.getInstance();
        Date time = null;
        try {
            time = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aCalendar.setTime(time);

        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);

        return week;
    }


}
