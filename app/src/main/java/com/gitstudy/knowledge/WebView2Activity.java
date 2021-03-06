package com.gitstudy.knowledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebView2Activity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        ButterKnife.bind(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSaveFormData(true);
        webview.loadUrl("http://blog.csdn.net/liu537192/article/details/41912609?locationNum=6");
    }
}
