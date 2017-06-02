package com.gitstudy;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.convenientbanner.ConvenientBannerActivity;
import com.gitstudy.fragmenttabhostutils.FragmentTabhostActivity;
import com.gitstudy.guocustomview.GuoCustomViewActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class MainActivity extends QkActivity implements View.OnClickListener {

    private RelativeLayout fragment_tabhost_utils_rl;
    private TopbarView top_bar_view;
    private RelativeLayout guo_custom_view_rl;
    private RelativeLayout convenient_banner_rl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        super.initViews();
        top_bar_view = (TopbarView) findViewById(R.id.top_bar_view);
        fragment_tabhost_utils_rl = (RelativeLayout) findViewById(R.id.fragment_tabhost_utils_rl);
        guo_custom_view_rl = (RelativeLayout) findViewById(R.id.guo_custom_view_rl);
        convenient_banner_rl = (RelativeLayout) findViewById(R.id.convenient_banner_rl);
    }

    @Override
    public void initData() {
        super.initData();
        top_bar_view.setTopbarTitle("######");
    }

    @Override
    public void addListeners() {
        super.addListeners();
        top_bar_view.setTopBarClickListener(topListener);
        fragment_tabhost_utils_rl.setOnClickListener(MainActivity.this);
        guo_custom_view_rl.setOnClickListener(MainActivity.this);
        convenient_banner_rl.setOnClickListener(MainActivity.this);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_tabhost_utils_rl:
                startActivity(new Intent(MainActivity.this,FragmentTabhostActivity.class));
                break;
            case R.id.guo_custom_view_rl:
                startActivity(new Intent(MainActivity.this,GuoCustomViewActivity.class));
                break;
            case R.id.convenient_banner_rl:
                startActivity(new Intent(MainActivity.this,ConvenientBannerActivity.class));
                break;


        }

    }




}
