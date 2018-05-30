package com.gitstudy.pulltorefreshtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.gitstudy.R;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullToRefreshLayout;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullableScrollView;

public class SecScrollViewActivity extends AppCompatActivity implements PullToRefreshLayout.OnRefreshListener{
    private PullableScrollView listView;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_scroll_view);
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
//        ptrl.setOnRefreshListener(new MyListener());
        ptrl.setOnRefreshListener(this);
        listView = (PullableScrollView) findViewById(R.id.content_view);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 第一次进入自动刷新
        if (isFirstIn) {
            ptrl.autoRefresh();
            isFirstIn = false;
        }
    }


    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        // 加载操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件加载完毕了哦！
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }


//    public class MyListener implements PullToRefreshLayout.OnRefreshListener {
//
//        @Override
//        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
//            // 下拉刷新操作
//            new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    // 千万别忘了告诉控件刷新完毕了哦！
//                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//                }
//            }.sendEmptyMessageDelayed(0, 5000);
//        }
//
//        @Override
//        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
//            // 加载操作
//            new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    // 千万别忘了告诉控件加载完毕了哦！
//                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//                }
//            }.sendEmptyMessageDelayed(0, 5000);
//        }
//
//    }
}
