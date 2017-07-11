package com.gitstudy.datetimedialog;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.qk.applibrary.activity.QkActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeDialogActivity extends QkActivity {
    private Context context;
    private TextView tv_times;
    private RelativeLayout rl_select_time;

    @Override
    public void initViews() {
        context = DateTimeDialogActivity.this;
        tv_times = (TextView) findViewById(R.id.tv_times);
        rl_select_time = (RelativeLayout) findViewById(R.id.rl_select_time);
        rl_select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.qk.applibrary.widget.DateTimeDialog(context, 0, null, "带看时间不能选择当前时间之前的日期", new com.qk.applibrary.listener.DateTimeSetListener() {
                    @Override
                    public void onDateChanged(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        tv_times.setText(sdf.format(calendar.getTime()));
                    }
                }).show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_date_time_dialog;
    }


}
