package com.gitstudy.lockscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿支付宝手势密码解锁,具体使用请看github
 * github：https://github.com/cuileikunOrganization/LockPattern
 */
public class SecondLockScreenActivity extends AppCompatActivity {
    @BindView(R.id.splash_iv)
    ImageView splashIv;
    @BindView(R.id.rl_splash)
    LinearLayout mRlSplash;

    private Handler handler = new Handler() {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        ButterKnife.bind(this);
        doJump();
    }


    private void doJump() {
        //展示2s启动页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SecondLockScreenActivity.this, GuangGaoActivity.class));
                finish();
            }
        }, 2000);
    }


}
