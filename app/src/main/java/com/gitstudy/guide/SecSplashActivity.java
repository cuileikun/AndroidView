package com.gitstudy.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gitstudy.MainActivity;
import com.gitstudy.R;
import com.gitstudy.SPUtil;

public class SecSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_splash);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        // 暂停3秒钟
        // 判断用户是否是第一次进入当前的app,进入到引导界面
        // 如果用户不是第一次进入当前的app。那么进入到主界面
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // 获取用户是否是第一次打开当前的app
                boolean result = SPUtil.getBool(SecSplashActivity.this, "is_first_open", false);
                //测试
                result = false;

                if (result) {
                    // 进入到主界面
                    Intent intent = new Intent(SecSplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 第一次进来进入到引导界面
                    Intent intent = new Intent(SecSplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    //保存用户是否进来
                    SPUtil.putBool(SecSplashActivity.this, "is_first_open", true);
                    finish();
                }

            }
        }, 3000);

    }
}