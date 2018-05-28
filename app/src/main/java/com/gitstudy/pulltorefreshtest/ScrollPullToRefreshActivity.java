package com.gitstudy.pulltorefreshtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollPullToRefreshActivity extends AppCompatActivity {
    @BindView(R.id.scroll_pull_torefresh)
    PullToRefreshScrollView mScrollView;
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_pull_to_refresh);
        ButterKnife.bind(this);
        //第一种实现
//        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                //在这里可以进行下拉刷新的操作 比如调用联网的方法重新联网等
//                ToastUtils.showShort(ScrollPullToRefreshActivity.this, "ScrollView的下拉刷新操作");
//
//                mHander.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mScrollView.onRefreshComplete();
//                    }
//                }, 2000);
//
//            }
//        });
        //第二种实现
       mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
           @Override
           public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
               //在这里可以进行下拉刷新的操作 比如调用联网的方法重新联网等
               ToastUtils.showShort(ScrollPullToRefreshActivity.this, "ScrollView的下拉刷新操作");

               mHander.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mScrollView.onRefreshComplete();
                   }
               }, 2000);
           }

           @Override
           public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

           }
       });

    }
}
