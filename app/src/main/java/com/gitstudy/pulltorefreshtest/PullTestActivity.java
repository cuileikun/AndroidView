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

    @OnClick({R.id.btn_pull_refresh_scroll, R.id.btn_pull_refresh_webview, R.id.btn_listview, R.id.btn_scrollview,
            R.id.btn_textview, R.id.btn_imageview, R.id.btn_webview, R.id.btn_gridview, R.id.btn_expandview, R.id.btn_list_iew})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_list_iew:
                startActivity(new Intent(mContext,ListViewAddFooterActivity.class));
                break;
            case R.id.btn_pull_refresh_scroll:
                startActivity(new Intent(mContext, ScrollPullToRefreshActivity.class));
                break;
            case R.id.btn_listview:
                startActivity(new Intent(mContext, SecListViewActivity.class));
                break;
            case R.id.btn_scrollview:
                startActivity(new Intent(mContext, SecScrollViewActivity.class));
                break;
            case R.id.btn_textview:
                startActivity(new Intent(mContext, SecTextViewActivity.class));
                break;
            case R.id.btn_imageview:
                startActivity(new Intent(mContext, SecImageViewActivity.class));
                break;
            case R.id.btn_webview:
                startActivity(new Intent(mContext, SecWebViewActivity.class));
                break;
            case R.id.btn_gridview:
                startActivity(new Intent(mContext, SecGridViewActivity.class));
                break;
            case R.id.btn_expandview:
                startActivity(new Intent(mContext, SecExpandViewActivity.class));
                break;

        }
    }
}
