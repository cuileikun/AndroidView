package com.gitstudy.rili;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.gitstudy.R;
import com.gitstudy.rili.base.activity.CvBaseActivity;

/**
 * 测试界面
 * Created by huanghaibin on 2018/8/7.
 */

public class TestActivity extends CvBaseActivity {

    private FrameLayout mLayout;

    public static void show(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mLayout = (FrameLayout) findViewById(R.id.customLayout);
    }

    @Override
    protected void initData() {
        mLayout.post(new Runnable() {
            @Override
            public void run() {
                View view = findViewById(R.id.emptyView);
                view.requestLayout();

            }
        });
    }
}
