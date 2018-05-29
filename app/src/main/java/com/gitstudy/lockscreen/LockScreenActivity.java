package com.gitstudy.lockscreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.lockscreen.util.cache.ACache;
import com.gitstudy.lockscreen.util.constant.Constant;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿支付宝手势密码解锁,具体使用请看github
 * github：https://github.com/cuileikunOrganization/LockPattern
 */
public class LockScreenActivity extends AppCompatActivity {
    @BindView(R.id.splash_iv)
    ImageView splashIv;
    @BindView(R.id.rl_splash)
    LinearLayout mRlSplash;
    private TextView mTvGuangGaoTime;
    private ImageView mIvGuangGao;
    private ACache aCache;
    private Bitmap splashBitmap;
    private int screenWidth, screenHeight;
    private Handler handler = new Handler() {
    };
    private static final String TAG = "LockScreenActivity";
    private int mRecLen = 5;
    Timer mTimer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        ButterKnife.bind(this);
        this.init();
    }

    private void init() {
        aCache = ACache.get(this);
//        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
//        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//        Log.e(TAG, "screen width: " + screenWidth);
//        Log.e(TAG, "screen height: " + screenHeight);
//        Log.e(TAG, "imageview width: " + splashIv.getWidth());
//        Log.e(TAG, "imageview height: " + splashIv.getHeight());
//        Log.e(TAG, "status bar height: " + getStatusBarHeight());
//        splashBitmap = BitmapUtil.resizeBitmap(screenWidth, screenHeight - getStatusBarHeight(),
//                BitmapFactory.decodeResource(getResources(), R.mipmap.splash));
//        splashIv.setImageBitmap(splashBitmap);
        this.doJump();
    }

    private void doJump() {
        //展示2s启动页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //展示5s广告页
                showGuangGaoView();
                handler.postDelayed(runnable12, 5000);
            }
        }, 2000);
    }

    private void showGuangGaoView() {
        mTimer.schedule(task, 1000, 1000);
        mRlSplash.setVisibility(View.INVISIBLE);
        final Dialog dialog = new Dialog(this, R.style.GuangGaoStyle);
        View mGuangGaoView = View.inflate(this, R.layout.guanggao_dialog, null);
        mIvGuangGao = (ImageView) mGuangGaoView.findViewById(R.id.iv_guang_gao);
        mTvGuangGaoTime = (TextView) mGuangGaoView.findViewById(R.id.tv_guang_gao_time);
        mTvGuangGaoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                handler.removeCallbacks(runnable12);
                jump12();
            }
        });
//        mIvGuangGao = (ImageView) view.findViewById(R.id.iv_guang_gao);
//        if (null != mPicPath && !TextUtils.isEmpty(mPicPath)) {
//            Glide.with(mContext).load(mPicPath).into(mIvGuangGao);
//        }

        dialog.setContentView(mGuangGaoView);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
//        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.75f);//设置对话框的宽度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;//设置对话框的宽度
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;//设置对话框的高度
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
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
            Intent intent = new Intent(LockScreenActivity.this, CreateGestureActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(LockScreenActivity.this, GestureLoginActivity.class);
            startActivity(intent);
            finish();
        }
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
