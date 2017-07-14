package com.gitstudy.androidpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.gitstudy.R;

import java.util.Calendar;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;

public class AndroidPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_picker);
    }
    /**
     *1选择器视图内嵌
     */

    /**
     * 2窗口动画(基于XML)
     */

    /**
     * 3窗口动画（基于Java）
     */

    /**
     * 4年月日选择
     */
    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(2016, 8, 29);
        picker.setSelectedItem(2050, 10, 14);
//        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                showToast(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 5年月日时间选择
     */
    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setDateRangeStart(2017, 1, 1);
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }
    /**
     * 6年月选择
     */
    public void onYearMonthPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.6));
        picker.setRangeStart(2016, 10, 14);
        picker.setRangeEnd(2020, 11, 11);
        picker.setSelectedItem(2017, 9);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                showToast(year + "-" + month);
            }
        });
        picker.show();
    }
    /**
     * 7月日选择
     */
    public void onMonthDayPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.MONTH_DAY);
        picker.setGravity(Gravity.CENTER);//弹框居中
        picker.setRangeStart(5, 1);
        picker.setRangeEnd(12, 31);
        picker.setSelectedItem(10, 14);
        picker.setOnDatePickListener(new DatePicker.OnMonthDayPickListener() {
            @Override
            public void onDatePicked(String month, String day) {
                showToast(month + "-" + day);
            }
        });
        picker.show();
    }
    /**
     * 8时间选择
     */
    public void onTimePicker(View view) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                showToast(hour + ":" + minute);
            }
        });
        picker.show();
    }
    /**
     * 9单项选择
     */

    /**
     * 10双项选择
     */

    /**
     * 11多项选择
     */

    /**
     * 12二三级联动选择
     */

    /**
     * 13星座选择
     */

    /**
     * 14数字选择（如身高、体重、年龄）
     */

    /**
     * 15地址选择（包括省级、地级、县级）
     */

    /**
     * 16地址选择（只包括地级、县级）
     */

    /**
     * 17地址选择（只包括省级、地级）
     */

    /**
     * 18颜色选择
     */

    /**
     * 19文件选择
     */

    /**
     * 20目录选择
     */
    /**
     * 21建议收集
     */

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }





}
