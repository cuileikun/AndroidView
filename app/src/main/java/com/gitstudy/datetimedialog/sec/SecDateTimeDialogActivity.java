package com.gitstudy.datetimedialog.sec;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.datetimedialog.DateTimeDialogActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecDateTimeDialogActivity extends AppCompatActivity {
    private Context context;
    private TextView tv_times;
    private RelativeLayout rl_select_time;
    private Button datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_date_time_dialog);
        initData();
    }

    private void initData() {
        context = SecDateTimeDialogActivity.this;
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
        datePickerDialog = (Button) findViewById(R.id.btn_date_picker_dialog);
        datePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(year);
                        if ((month + 1)
                                < 10) {
                            buffer.append("-0" + (month + 1));
                        } else {
                            buffer.append("-" + (month + 1));
                        }
                        if (dayOfMonth < 10) {
                            buffer.append("-0" + dayOfMonth);
                        } else {
                            buffer.append("-" + dayOfMonth);
                        }
                        String date = buffer.toString();
                        tv_times.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
