package com.qk.applibrary.widget;/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import com.qk.applibrary.R;
import com.qk.applibrary.listener.DateTimeSetListener;
import com.qk.applibrary.util.CommonUtil;

/**
 * 日期时间控件
 */
public class DateTimeDialog extends AlertDialog implements OnDateChangedListener,OnTimeChangedListener {

	private DatePicker datePicker;
	private TimePicker timePicker;
	private TextView dateTv;
	private String dateTime;
	private String initDateTime;
	private Activity mActivity;
	private Calendar currentCalendar;
	private String toastErrorInfo;//弹出的错误




	/**
	 * 显示日期时间框
	 * @param context 界面对应的activity
	 * @param initDateTime 初始化日期时间
	  * @param listener 把设置好的日期时间回调给前端
	 * @param errorInfo 弹出的错误提醒
	 */
	public DateTimeDialog(Context context, int theme ,String initDateTime,String errorInfo,final DateTimeSetListener listener) {
		super(context, theme);
		mActivity = (Activity)context;
		toastErrorInfo = errorInfo;
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.common_datetime, null);
		setView(view);
		Button setBt = (Button)view.findViewById(R.id.set_bt);
		Button cancelBt = (Button)view.findViewById(R.id.cancel_bt);
		setBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(listener != null) {
					Calendar calendar = Calendar.getInstance();
					/**
					 * 检查选择的时间是否小于当前的时间
					 */
					if(calendar.getTimeInMillis() >=currentCalendar.getTimeInMillis()) {
						CommonUtil.sendToast(mActivity,toastErrorInfo);
						/**
						 * 把选择的时间改为当前的时间
						 */
						currentCalendar = calendar;
					}

					listener.onDateChanged(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
							currentCalendar.get(Calendar.DAY_OF_MONTH), currentCalendar.get(Calendar.HOUR_OF_DAY),
							currentCalendar.get(Calendar.MINUTE));
				}
				dismiss();
			}
		});
		cancelBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		dateTv = (TextView)view.findViewById(R.id.date_tv);
		this.initDateTime = initDateTime;
		datePicker = (DatePicker)view.findViewById(R.id.datepicker);
		timePicker = (TimePicker) view.findViewById(R.id.timepicker);
		resizePikcer(datePicker);
		resizePikcer(timePicker);
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);
		onDateChanged(null, 0, 0, 0);

	}

	/**
	 * 调整FrameLayout大小
	 * @param tp
	 */
	private void resizePikcer(FrameLayout tp){
		Display display = mActivity.getWindowManager().getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);
		int spaceing = 5*((int)getContext().getResources().getDimension(R.dimen.dimen_5_dip))+
				(int)getContext().getResources().getDimension(R.dimen.dimen_60_dip);
		int columnWidth = (display.getWidth()-spaceing)/5;
		List<NumberPicker> npList = findNumberPicker(tp);
		for(NumberPicker np:npList){
			resizeNumberPicker(np,columnWidth);
		}
	}

	/**
	 * 得到viewGroup里面的numberpicker组件
	 * @param viewGroup
	 * @return
	 */
	private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
		List<NumberPicker> npList = new ArrayList<NumberPicker>();
		View child = null;
		if(null != viewGroup){
			for(int i = 0;i<viewGroup.getChildCount();i++){
				child = viewGroup.getChildAt(i);
				if(child instanceof NumberPicker){
					NumberPicker picker = (NumberPicker) child;
//					try {
//						EditText et = (EditText)(picker.getChildAt(0));
//						et.setTextSize(13);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					npList.add(picker);
				}
				else if(child instanceof LinearLayout){
					List<NumberPicker> result = findNumberPicker((ViewGroup)child);
					if(result.size()>0){
						return result;
					}
				}
			}
		}
		return npList;
	}

	/**
	 * 调整numberpicker大小
	 * @param np
	 * @param columnWidth 宽度
	 */
	private void resizeNumberPicker(NumberPicker np,int columnWidth){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columnWidth,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins((int) getContext().getResources().getDimension(R.dimen.dimen_5_dip), 0, 0, 0);
		np.setLayoutParams(params);
	}


	public void init(DatePicker datePicker, TimePicker timePicker) {
		currentCalendar = Calendar.getInstance();
		if (!(null == initDateTime || "".equals(initDateTime))) {
			currentCalendar = this.getCalendarByInintData(initDateTime);
		} else {
			initDateTime = currentCalendar.get(Calendar.YEAR) + "年"
					+ currentCalendar.get(Calendar.MONTH) + "月"
					+ currentCalendar.get(Calendar.DAY_OF_MONTH) + "日 "
					+ currentCalendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ currentCalendar.get(Calendar.MINUTE);
		}
		datePicker.init(currentCalendar.get(Calendar.YEAR),
				currentCalendar.get(Calendar.MONTH),
				currentCalendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(currentCalendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(currentCalendar.get(Calendar.MINUTE));
	}



	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

	public void onDateChanged(DatePicker view, int year, int monthOfYear,
							  int dayOfMonth) {
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(),
				datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
				timePicker.getCurrentMinute());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		dateTime = sdf.format(calendar.getTime());
		dateTv.setText(dateTime);
		currentCalendar = calendar;

	}

	/**
	 * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
	 *
	 * @param initDateTime
	 *            初始日期时间值 字符串型
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
		String date = spliteString(initDateTime, "日", "index", "front"); // 日期
		String time = spliteString(initDateTime, "日", "index", "back"); // 时间

		String yearStr = spliteString(date, "年", "index", "front"); // 年份
		String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

		String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
		String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

		String hourStr = spliteString(time, ":", "index", "front"); // 时
		String minuteStr = spliteString(time, ":", "index", "back"); // 分

		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

		calendar.set(currentYear, currentMonth, currentDay, currentHour,
				currentMinute);
		return calendar;
	}

	/**
	 * 截取子串
	 *
	 * @param srcStr
	 *            源串
	 * @param pattern
	 *            匹配模式
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern,
									  String indexOrLast, String frontOrBack) {
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index")) {
			loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
		} else {
			loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
		}
		if (frontOrBack.equalsIgnoreCase("front")) {
			if (loc != -1)
				result = srcStr.substring(0, loc); // 截取子串
		} else {
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
		}
		return result;
	}

}
