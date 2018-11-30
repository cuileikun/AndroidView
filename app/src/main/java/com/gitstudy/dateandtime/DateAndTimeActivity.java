package com.gitstudy.dateandtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateAndTimeActivity extends AppCompatActivity {
    @BindView(R.id.tv_get_near_sunday_from_now)
    TextView mTvGetNearSundayFromNow;
    @BindView(R.id.tv_get_near_sunday_from_someday)
    TextView mTvGetNearSundayFromSomeday;
    @BindView(R.id.tv_get_sunday_from_someday)
    TextView tv_get_sunday_from_someday;
    @BindView(R.id.ed_is_sunday)
    EditText editText;
    @BindView(R.id.tv_is_sunday)
    TextView tv_is_sunday;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get_near_sunday_from_now, R.id.btn_get_near_sunday_from_someday, R.id.btn_get_sunday_from_someday,
            R.id.btn_is_sunday})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_get_near_sunday_from_now:
                String lastSunday = TimeUtils.getLastSunday("20181126");
                mTvGetNearSundayFromNow.setText(lastSunday);
                break;
            case R.id.btn_get_near_sunday_from_someday:
                String nearSunday = TimeUtils.getNearestSunday("20181127");
                mTvGetNearSundayFromSomeday.setText(nearSunday);
                break;
            case R.id.btn_get_sunday_from_someday:
                String someSunday = TimeUtils.getSomeSunday("20181129");
                tv_get_sunday_from_someday.setText(someSunday);
                break;
            case R.id.btn_is_sunday:
                date = editText.getText().toString().trim();
                int week = TimeUtils.isSunday(date);
                show(week);
                break;
            default:
                break;
        }
    }

    private void show(int week) {
        switch (week) {
            case 1:
                tv_is_sunday.setText(date + "是周日");
                break;
            case 2:
                tv_is_sunday.setText(date + "是周一");
                break;
            case 3:
                tv_is_sunday.setText(date + "是周二");
                break;
            case 4:
                tv_is_sunday.setText(date + "是周三");
                break;
            case 5:
                tv_is_sunday.setText(date + "是周四");
                break;
            case 6:
                tv_is_sunday.setText(date + "是周五");
                break;
            case 7:
                tv_is_sunday.setText(date + "是周六");
                break;
            default:
                break;
        }
    }
}
