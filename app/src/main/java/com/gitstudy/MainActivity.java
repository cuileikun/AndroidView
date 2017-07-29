package com.gitstudy;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.androidpicker.AndroidPickerActivity;
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
import com.gitstudy.webviewvideo.MyActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends QkActivity {
    @BindView(R.id.fragment_tabhost_utils_rl)
    RelativeLayout fragment_tabhost_utils_rl;
    @BindView(R.id.top_bar_view)
    TopbarView top_bar_view;
    @BindView(R.id.guo_custom_view_rl)
    RelativeLayout guo_custom_view_rl;
    @BindView(R.id.convenient_banner_rl)
    RelativeLayout convenient_banner_rl;
    @BindView(R.id.show_castview_rl)
    RelativeLayout show_castview_rl;
    @BindView(R.id.fit_screen_rl)
    RelativeLayout fit_screen_rl;
    @BindView(R.id.lock_pattern_rl)
    RelativeLayout lock_pattern_rl;
    @BindView(R.id.recycle_view_rl)
    RelativeLayout recycle_view_rl;
    @BindView(R.id.horizontal_scroll_selected_view_rl)
    RelativeLayout horizontal_scroll_selected_view_rl;
    @BindView(R.id.collapsing_toolbar_layout_rl)
    RelativeLayout collapsing_toolbar_layout_rl;
    @BindView(R.id.dynamic_add_view_rl)
    RelativeLayout dynamic_add_view_rl;
    @BindView(R.id.listview_add_delete_item_rl)
    RelativeLayout listview_add_delete_item_rl;
    @BindView(R.id.popup_window_rl)
    RelativeLayout popup_window_rl;
    @BindView(R.id.date_time_dialog_rl)
    RelativeLayout date_time_dialog_rl;
    @BindView(R.id.android_picker_rl)
    RelativeLayout android_picker_rl;
    @BindView(R.id.webview_video_rl)
    RelativeLayout webview_video_rl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);

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

    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @OnClick({R.id.fragment_tabhost_utils_rl, R.id.guo_custom_view_rl,
            R.id.convenient_banner_rl, R.id.show_castview_rl,
            R.id.fit_screen_rl, R.id.lock_pattern_rl, R.id.recycle_view_rl,
            R.id.horizontal_scroll_selected_view_rl, R.id.collapsing_toolbar_layout_rl, R.id.dynamic_add_view_rl,
            R.id.listview_add_delete_item_rl, R.id.popup_window_rl, R.id.date_time_dialog_rl,
            R.id.android_picker_rl, R.id.webview_video_rl})
    void click(View view) {
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
            case R.id.android_picker_rl:
                startActivity(new Intent(MainActivity.this, AndroidPickerActivity.class));
                break;
            case R.id.webview_video_rl://加载webview  点击图片或者视频 切换到全屏模式
//                startActivity(new Intent(MainActivity.this, WebViewVideoActivity.class));
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                break;

        }
    }


}
