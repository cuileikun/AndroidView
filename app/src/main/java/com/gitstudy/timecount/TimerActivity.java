package com.gitstudy.timecount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerActivity extends AppCompatActivity {

    @BindView(R.id.tv_guang_gao_time)
    TextView mTvGuangGaoTime;
    private int mRecLen = 5;
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        mTimer.schedule(task, 1000, 1000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    mRecLen--;
                    mTvGuangGaoTime.setText("跳过 " + mRecLen + "s");
                    if (mRecLen < 0) {
                        mTimer.cancel();
                        mTvGuangGaoTime.setVisibility(View.GONE);
                    }
                }
            });
        }
    };
}
