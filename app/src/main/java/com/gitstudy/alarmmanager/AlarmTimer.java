package com.gitstudy.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mbcloud-cuilk on 2018/4/25.
 */
public class AlarmTimer {
    /**
     * 设置周期性闹钟
     *
     * @param context
     * @param firstTime
     * @param cycTime
     * @param action
     * @param AlarmManagerType
     *            闹钟的类型，常用的有5个值：AlarmManager.ELAPSED_REALTIME、
     *            AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、
     *            AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP
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
     * @param AlarmManagerType
     *            闹钟的类型，常用的有5个值：AlarmManager.ELAPSED_REALTIME、
     *            AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、
     *            AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP
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
        alarm.setExact(AlarmManagerType, cycTime, sender);
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
}
