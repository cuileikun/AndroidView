package com.gitstudy.lockscreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gitstudy.R;
import com.gitstudy.lockscreen.util.BitmapUtil;
import com.gitstudy.lockscreen.util.cache.ACache;
import com.gitstudy.lockscreen.util.constant.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿支付宝手势密码解锁,具体使用请看github
 * github：https://github.com/cuileikunOrganization/LockPattern
 */
public class LockScreenActivity extends AppCompatActivity {
    @BindView(R.id.splash_iv)
    ImageView splashIv;

    private ACache aCache;
    private Bitmap splashBitmap;
    private int screenWidth, screenHeight;
    private Handler handler = new Handler(){};
    private static final String TAG = "LockScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        ButterKnife.bind(this);
        this.init();
    }

    private void init() {
        aCache = ACache.get(this);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        //Log.e(TAG, "screen width: " + screenWidth);
        //Log.e(TAG, "screen height: " + screenHeight);
        //Log.e(TAG, "imageview width: " + splashIv.getWidth());
        //Log.e(TAG, "imageview height: " + splashIv.getHeight());
        //Log.e(TAG, "status bar height: " + getStatusBarHeight());
        splashBitmap = BitmapUtil.resizeBitmap(screenWidth, screenHeight - getStatusBarHeight(),
                BitmapFactory.decodeResource(getResources(), R.mipmap.splash));
        splashIv.setImageBitmap(splashBitmap);
        this.doJump();
    }

    private void doJump() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String gesturePassword = aCache.getAsString(Constant.GESTURE_PASSWORD);
                if(gesturePassword == null || "".equals(gesturePassword)) {
                    Intent intent = new Intent(LockScreenActivity.this, CreateGestureActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LockScreenActivity.this, GestureLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
