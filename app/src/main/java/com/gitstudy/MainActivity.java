package com.gitstudy;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.convenientbanner.ConvenientBannerActivity;
import com.gitstudy.fitscreenend.FitScreenActivity;
import com.gitstudy.fragmenttabhostutils.FragmentTabhostActivity;
import com.gitstudy.guocustomview.GuoCustomViewActivity;
import com.gitstudy.horizontalscrollselectedview.HorizontalScrollSelectedViewActivity;
import com.gitstudy.lockscreen.LockScreenActivity;
import com.gitstudy.recycleview.RecycleViewActivity;
import com.gitstudy.showcastview.ShowCastViewActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class MainActivity extends QkActivity implements View.OnClickListener {

    private RelativeLayout fragment_tabhost_utils_rl;
    private TopbarView top_bar_view;
    private RelativeLayout guo_custom_view_rl;
    private RelativeLayout convenient_banner_rl;
    private RelativeLayout show_castview_rl;
    private RelativeLayout fit_screen_rl;
    private RelativeLayout lock_pattern_rl;
    private RelativeLayout recycle_view_rl;
    private RelativeLayout horizontal_scroll_selected_view_rl;

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
        show_castview_rl = (RelativeLayout) findViewById(R.id.show_castview_rl);
        fit_screen_rl = (RelativeLayout) findViewById(R.id.fit_screen_rl);
        lock_pattern_rl = (RelativeLayout) findViewById(R.id.lock_pattern_rl);
        recycle_view_rl = (RelativeLayout) findViewById(R.id.recycle_view_rl);
        horizontal_scroll_selected_view_rl = (RelativeLayout) findViewById(R.id.horizontal_scroll_selected_view_rl);
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
        show_castview_rl.setOnClickListener(MainActivity.this);
        fit_screen_rl.setOnClickListener(MainActivity.this);
        lock_pattern_rl.setOnClickListener(MainActivity.this);
        recycle_view_rl.setOnClickListener(MainActivity.this);
        horizontal_scroll_selected_view_rl.setOnClickListener(MainActivity.this);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_tabhost_utils_rl:
                startActivity(new Intent(MainActivity.this, FragmentTabhostActivity.class));
                break;
            case R.id.guo_custom_view_rl:
                startActivity(new Intent(MainActivity.this, GuoCustomViewActivity.class));
                break;
            case R.id.convenient_banner_rl:
                startActivity(new Intent(MainActivity.this, ConvenientBannerActivity.class));
                break;
            case R.id.show_castview_rl:
                startActivity(new Intent(MainActivity.this, ShowCastViewActivity.class));
                break;
            case R.id.fit_screen_rl:
                startActivity(new Intent(MainActivity.this, FitScreenActivity.class));
                break;

            case R.id.lock_pattern_rl:
                startActivity(new Intent(MainActivity.this, LockScreenActivity.class));
                break;
            case R.id.recycle_view_rl:
                startActivity(new Intent(MainActivity.this,RecycleViewActivity.class));
                break;
            case R.id.horizontal_scroll_selected_view_rl:
                startActivity(new Intent(MainActivity.this,HorizontalScrollSelectedViewActivity.class));
                break;

        }

    }


}
