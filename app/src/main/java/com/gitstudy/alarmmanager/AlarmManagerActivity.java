package com.gitstudy.alarmmanager;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gitstudy.R;
import com.gitstudy.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmManagerActivity extends AppCompatActivity {
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.bt_setRepeatingAlarmTimer)
    Button mBtSetRepeatingAlarmTimer;

    @BindView(R.id.bt_setAlarmTimer)
    Button mBtSetAlarmTimer;
    @BindView(R.id.bt_cancelAlarmTimer)
    Button mBtCancelAlarmTimer;
    @BindView(R.id.bt_cancelRepeatingAlarmTimer)
    Button mBtCancelRepeatingAlarmTimer;
    @BindView(R.id.tv_current_time)
    TextView mTvCurrentTime;
    @BindView(R.id.tv_time2)
    TextView mTvTime2;

    //获取手势密码输错5次，锁屏总时长
    private int mRecLen = 60;//单位是秒
    Handler mHandler = new Handler();

    private AlarmReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalValues.TIMER_ACTION_REPEATING);
        filter.addAction(GlobalValues.TIMER_ACTION);
        mReceiver = new AlarmReceiver();
        this.registerReceiver(mReceiver, filter);

        mHandler.postDelayed(mRunnable, 1000);
        AlarmTimer.setRepeatingAlarmTimer(AlarmManagerActivity.this, System.currentTimeMillis() + 60 * 1000, 3 * 60 * 1000, GlobalValues.TIMER_ACTION_REPEATING, AlarmManager.RTC_WAKEUP);

        //时间显示on
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(l);
        mTvCurrentTime.setText("当前时间毫秒值" + l + "\r\n"+"年月日显示当前时间" + format.substring(0, 10) + "@@@@" + format.substring(11, format.length()));
        // 时间显示off
        showTime();
    }

    private void showTime() {
        String mRcvDate = "1514959999632";
        long mRcvDate2 = Long.parseLong("1514959999632");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(mRcvDate2);
        String mNianYueRi = format.substring(0, 10);
        String mShiFenMiao = format.substring(11, format.length());
        String t = getTimeDay2();
        if (t.equals(mNianYueRi)) {
            mTvTime2.setText("给定时间戳是" + mShiFenMiao.substring(0, mShiFenMiao.lastIndexOf(":")));
        } else {
            Calendar calendar = Calendar.getInstance();
            String[] ts = t.split("-");
            calendar.set(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]) - 1, Integer.parseInt(ts[2]), 0, 0, 0);
            long nt = calendar.getTimeInMillis();
            long dt = nt - Long.parseLong(mRcvDate);
            //   int st = (int)(dt/1000/60/60);
            float st2 = ((float) dt) / 1000 / 60 / 60;//单位小时
            if (st2 <= 168) {//一周
                int dd = (int) ((st2 + 24) / 24);
                mTvTime2.setText("给定时间戳是" + dd + "天前");
            } else if (st2 <= 720) {//一月
                int zz = ((int) (st2 + 168) / 168) - 1;
                mTvTime2.setText("给定时间戳是" + zz + "周前");
            } else if (st2 <= 8640) {//一年
                int yy = (int) ((st2 + 720) / 720) - 1;
                mTvTime2.setText("给定时间戳是" + yy + "月前");
            } else {
                int yy = (int) ((st2 + 8640) / 8640) - 1;
                mTvTime2.setText("给定时间戳是" + yy + "年前");
            }

        }
    }

    public static String getTimeDay2() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String time = dff.format(new Date());
        return time;
    }

    public class AlarmReceiver extends BroadcastReceiver {

        private Toast mToast;
        private Context mContext;

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            mContext = context;
            if (intent.getAction().equals(GlobalValues.TIMER_ACTION_REPEATING)) {
                showToast("AlarmTimer-------->Repeating");
                mRecLen = 0;
                mHandler.postDelayed(mRunnable, 1);
            } else if (intent.getAction().equals(GlobalValues.TIMER_ACTION)) {
                showToast("AlarmTimer-------->Timer");
                mRecLen = 0;
                mHandler.postDelayed(mRunnable, 1000);
            }
        }

        private void showToast(String text) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }


    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mRecLen--;
            String time = TimeUtils.getTime(mRecLen);
            mTvTime.setText("账号被锁定，请在" + time + "后尝试");
            mHandler.postDelayed(this, 1000);
            if (mRecLen <= 0) {
                mHandler.removeCallbacks(mRunnable);
                //把锁屏开始时间清零
//                SPUtil.putLong(LeaseBaseApplication.mInstance, StringUtil.CMB_LOCK_START_TIME, 0);
//                startActivity(new Intent(AlarmManagerActivity.this, GestureLoginActivity.class));
//                finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @OnClick({R.id.bt_setRepeatingAlarmTimer, R.id.bt_setAlarmTimer, R.id.bt_cancelRepeatingAlarmTimer, R.id.bt_cancelAlarmTimer})
    void click(View view) {
        switch (view.getId()) {
            case R.id.bt_setRepeatingAlarmTimer:
                AlarmTimer.setRepeatingAlarmTimer(AlarmManagerActivity.this, System.currentTimeMillis() + 2 * 60 * 1000, 2 * 60 * 1000, GlobalValues.TIMER_ACTION_REPEATING, AlarmManager.RTC_WAKEUP);
                break;
            case R.id.bt_setAlarmTimer:
                long triggerAtTime = SystemClock.elapsedRealtime() + 30 * 1000;
                AlarmTimer.setAlarmTimer(AlarmManagerActivity.this, triggerAtTime, GlobalValues.TIMER_ACTION, AlarmManager.RTC_WAKEUP);
                break;
            case R.id.bt_cancelRepeatingAlarmTimer:
                AlarmTimer.cancelAlarmTimer(AlarmManagerActivity.this, GlobalValues.TIMER_ACTION_REPEATING);
                break;
            case R.id.bt_cancelAlarmTimer:
                AlarmTimer.cancelAlarmTimer(AlarmManagerActivity.this, GlobalValues.TIMER_ACTION);
                break;
        }

    }

}
