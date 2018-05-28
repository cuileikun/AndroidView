package com.gitstudy.pulltorefreshtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PullTestActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_test);
        mContext = PullTestActivity.this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_pull_refresh_scroll, R.id.btn_pull_refresh_webview})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_pull_refresh_scroll:
                startActivity(new Intent(mContext, ScrollPullToRefreshActivity.class));
                break;

        }
    }
}
