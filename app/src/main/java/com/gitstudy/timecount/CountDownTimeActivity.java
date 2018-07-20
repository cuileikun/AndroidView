package com.gitstudy.timecount;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountDownTimeActivity extends AppCompatActivity {
    @BindView(R.id.tv_msg_num)
    TextView mTvMsgNum;
    @BindView(R.id.tv_msg_num_gray)
    TextView mTvMsgNumGray;
    @BindView(R.id.tv_msg_get_num_gray)
    TextView mTvMsgGetNumGray;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_time);
        ButterKnife.bind(this);
        time = new TimeCount(10000, 1000);
    }

    @OnClick({R.id.tv_msg_num})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_msg_num:
                mTvMsgNum.setVisibility(View.GONE);
                mTvMsgGetNumGray.setVisibility(View.GONE);
                mTvMsgNumGray.setVisibility(View.VISIBLE);
                time.start();
                break;

        }
    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvMsgNum.setClickable(false);
            mTvMsgNumGray.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            mTvMsgNum.setVisibility(View.VISIBLE);
            mTvMsgNumGray.setVisibility(View.GONE);
            mTvMsgGetNumGray.setVisibility(View.GONE);
            mTvMsgNum.setText("获取验证码");
            mTvMsgNum.setClickable(true);
        }
    }
}
