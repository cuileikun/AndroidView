package com.gitstudy.convenientbanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;
import com.gitstudy.convenientbanner.recyclerviewdemo.ConvenientBannerRecycleActivity;

/**
 *通用的广告栏控件，让你轻松实现广告头效果
 * 项目github地址：https://github.com/saiwu-bigkoo/Android-ConvenientBanner
 */

public class ConvenientBannerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_local_convenient_banner;
    private Button btn_local_convenient_banner_page;
    private Button btn_net_convenient_banner;
    private Button btn_net_convenient_banner_page;
    private Button btn_recycle_view_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner);
        initView();
        addListener();
    }

    private void addListener() {
        btn_local_convenient_banner.setOnClickListener(ConvenientBannerActivity.this);
        btn_local_convenient_banner_page.setOnClickListener(ConvenientBannerActivity.this);
        btn_net_convenient_banner.setOnClickListener(ConvenientBannerActivity.this);
        btn_net_convenient_banner_page.setOnClickListener(ConvenientBannerActivity.this);
        btn_recycle_view_banner.setOnClickListener(ConvenientBannerActivity.this);


    }

    private void initView() {
        btn_local_convenient_banner = (Button) findViewById(R.id.btn_local_convenient_banner);
        btn_local_convenient_banner_page = (Button) findViewById(R.id.btn_local_convenient_banner_page);
        btn_net_convenient_banner = (Button) findViewById(R.id.btn_net_convenient_banner);
        btn_net_convenient_banner_page = (Button) findViewById(R.id.btn_net_convenient_banner_page);
        btn_recycle_view_banner = (Button) findViewById(R.id.btn_recycle_view_banner);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_local_convenient_banner:
                startActivity(new Intent(ConvenientBannerActivity.this,ConvenientBannerLocalActivity.class));
                break;
            case R.id.btn_local_convenient_banner_page:
                startActivity(new Intent(ConvenientBannerActivity.this,ConvenientBannerLocalPageActivity.class));
                break;
            case R.id.btn_net_convenient_banner:
                startActivity(new Intent(ConvenientBannerActivity.this,ConvenientBannerNetActivity.class));
                break;
            case R.id.btn_net_convenient_banner_page:
                startActivity(new Intent(ConvenientBannerActivity.this,ConvenientBannerNetPageActivity.class));
                break;
            case R.id.btn_recycle_view_banner:

                startActivity(new Intent(ConvenientBannerActivity.this,ConvenientBannerRecycleActivity.class));

                break;
        }

    }

}
