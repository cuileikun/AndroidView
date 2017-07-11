package com.gitstudy;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.collapsingtoolbarlayout.CollapsingToolbarLayoutActivity;
import com.gitstudy.convenientbanner.ConvenientBannerActivity;
import com.gitstudy.datetimedialog.DateTimeDialogActivity;
import com.gitstudy.dynamicaddview.DynamicAddViewActivity;
import com.gitstudy.fitscreenend.FitScreenActivity;
import com.gitstudy.fragmenttabhostutils.FragmentTabhostActivity;
import com.gitstudy.guocustomview.GuoCustomViewActivity;
import com.gitstudy.horizontalscrollselectedview.HorizontalScrollSelectedViewActivity;
import com.gitstudy.listview.ListViewDemoActivity;
import com.gitstudy.lockscreen.LockScreenActivity;
import com.gitstudy.popupwindow.PopupWindowSeriesActivity;
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
    private RelativeLayout collapsing_toolbar_layout_rl;
    private RelativeLayout dynamic_add_view_rl;
    private RelativeLayout listview_add_delete_item_rl;
    private RelativeLayout popup_window_rl;
    private RelativeLayout date_time_dialog_rl;

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
        collapsing_toolbar_layout_rl = (RelativeLayout) findViewById(R.id.collapsing_toolbar_layout_rl);
        dynamic_add_view_rl = (RelativeLayout) findViewById(R.id.dynamic_add_view_rl);
        listview_add_delete_item_rl = (RelativeLayout) findViewById(R.id.listview_add_delete_item_rl);
        popup_window_rl = (RelativeLayout) findViewById(R.id.popup_window_rl);
        date_time_dialog_rl = (RelativeLayout) findViewById(R.id.date_time_dialog_rl);
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
        collapsing_toolbar_layout_rl.setOnClickListener(MainActivity.this);
        dynamic_add_view_rl.setOnClickListener(MainActivity.this);
        listview_add_delete_item_rl.setOnClickListener(MainActivity.this);
        popup_window_rl.setOnClickListener(MainActivity.this);
        date_time_dialog_rl.setOnClickListener(MainActivity.this);
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
                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                break;
            case R.id.horizontal_scroll_selected_view_rl:
                startActivity(new Intent(MainActivity.this, HorizontalScrollSelectedViewActivity.class));
                break;

            case R.id.collapsing_toolbar_layout_rl:
                startActivity(new Intent(MainActivity.this, CollapsingToolbarLayoutActivity.class));
                break;
            case R.id.dynamic_add_view_rl:
                startActivity(new Intent(MainActivity.this, DynamicAddViewActivity.class));
                break;
            case R.id.listview_add_delete_item_rl:
                startActivity(new Intent(MainActivity.this, ListViewDemoActivity.class));
                break;
            case R.id.popup_window_rl:
                startActivity(new Intent(MainActivity.this, PopupWindowSeriesActivity.class));
                break;
            case R.id.date_time_dialog_rl:
                startActivity(new Intent(MainActivity.this, DateTimeDialogActivity.class));
                break;

        }

    }


}
