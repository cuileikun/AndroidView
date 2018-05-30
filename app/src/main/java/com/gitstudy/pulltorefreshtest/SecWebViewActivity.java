package com.gitstudy.pulltorefreshtest;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gitstudy.R;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullToRefreshLayout;

public class SecWebViewActivity extends AppCompatActivity implements PullToRefreshLayout.OnRefreshListener {
    private WebView mWebView;
    private PullToRefreshLayout ptrl;
    private boolean isFirstIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_web_view);
        ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
//        ptrl.setOnRefreshListener(new MyListener());
        ptrl.setOnRefreshListener(this);
        mWebView = (WebView) findViewById(R.id.content_view);
        initWebViewSetting();
        mWebView.loadUrl("https://blog.csdn.net/zhongkejingwang/article/details/38868463");
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
        }.sendEmptyMessageDelayed(0, 3000);
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
        }.sendEmptyMessageDelayed(0, 3000);
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
    private void initWebViewSetting() {

        WebSettings webSetting = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSetting.setAllowFileAccessFromFileURLs(true);
            webSetting.setAllowUniversalAccessFromFileURLs(true);
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSetting.setMediaPlaybackRequiresUserGesture(false);
        }

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSetting.setAllowContentAccess(true);
        }
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);
        //   String dbPath = FileUtil.createFileInAppFiles(mContext, "database", "").getAbsolutePath();
        //  webSetting.setDatabasePath(dbPath);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);

        webSetting.setAppCacheEnabled(true);
        //   webSetting.setAppCacheMaxSize(20 * 1025 * 1025);
        //   webSetting.setAppCachePath(dbPath);
        webSetting.setTextSize(WebSettings.TextSize.NORMAL);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
//        mWebView.setWebViewClient(new MyWBClient(mWebView, mContext));

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                if(type && !TextUtils.isEmpty(title)){
//                    mTitleText.setText(title);
//                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
//                    mPrg.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
//                    mPrg.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
//                    mPrg.setProgress(newProgress);//设置进度值
                }

            }
        });

        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        mWebView.clearCache(true);
    }
}
