package com.gitstudy.lockscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.lockscreen.util.cache.ACache;
import com.gitstudy.lockscreen.util.constant.Constant;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuangGaoActivity extends AppCompatActivity {
    @BindView(R.id.tv_guang_gao_time)
    TextView mTvGuangGaoTime;
    @BindView(R.id.iv_guang_gao)
    ImageView mIvGuangGao;


    private ACache aCache;
    private Handler handler = new Handler() {
    };
    private static final String TAG = "LockScreenActivity";
    private int mRecLen = 5;
    Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guang_gao);
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        //展示5s广告页
        showGuangGaoView();
        handler.postDelayed(runnable12, 5000);

    }

    private void showGuangGaoView() {
        mTimer.schedule(task, 1000, 1000);
        mTvGuangGaoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                handler.removeCallbacks(runnable12);
                jump12();
            }
        });
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
    Runnable runnable12 = new Runnable() {
        @Override
        public void run() {
            jump12();
        }
    };

    private void jump12() {
        String gesturePassword = aCache.getAsString(Constant.GESTURE_PASSWORD);
        if (gesturePassword == null || "".equals(gesturePassword)) {
            Intent intent = new Intent(GuangGaoActivity.this, CreateGestureActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(GuangGaoActivity.this, GestureLoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
