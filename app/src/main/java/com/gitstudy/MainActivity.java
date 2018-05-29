package com.gitstudy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.OCRshibie.OCRActivity;
import com.gitstudy.alarmmanager.AlarmManagerActivity;
import com.gitstudy.androidpicker.AndroidPickerActivity;
import com.gitstudy.collapsingtoolbarlayout.CollapsingToolbarLayoutActivity;
import com.gitstudy.convenientbanner.ConvenientBannerActivity;
import com.gitstudy.datetimedialog.DateTimeDialogActivity;
import com.gitstudy.dialog.DialogActivity;
import com.gitstudy.dynamicaddview.DynamicAddViewActivity;
import com.gitstudy.expandlistviewtest.ExpandListViewDemoActivity;
import com.gitstudy.fitscreenend.FitScreenActivity;
import com.gitstudy.fragmenttabhostutils.FragmentTabhostActivity;
import com.gitstudy.guocustomview.GuoCustomViewActivity;
import com.gitstudy.history.SearchHistoryActivity;
import com.gitstudy.horizontalscrollselectedview.HorizontalScrollSelectedViewActivity;
import com.gitstudy.horizontalscrollview.HorizontalScrollViewActivity;
import com.gitstudy.knowledge.KnowledgeActivity;
import com.gitstudy.listview.ListViewDemoActivity;
import com.gitstudy.lockscreen.LockScreenActivity;
import com.gitstudy.lockscreen.SecondLockScreenActivity;
import com.gitstudy.oneandallcheck.AllCheckedActivity;
import com.gitstudy.permission.PerActivity;
import com.gitstudy.popupwindow.PopupWindowSeriesActivity;
import com.gitstudy.pulltorefreshtest.PullTestActivity;
import com.gitstudy.recycleview.RecycleViewActivity;
import com.gitstudy.showcastview.ShowCastViewActivity;
import com.gitstudy.tablayout.TabLayoutActivity;
import com.gitstudy.tablayout.verticaltablayout.VerticalTablayoutActivity;
import com.gitstudy.watermark.WaterMarkActivity;
import com.gitstudy.webviewvideo.MyActivity;
import com.gitstudy.xrcycleview.XRecycleViewDemoActivity;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends QkActivity {
    private Context mContext;
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
    @BindView(R.id.mianshi_rl)
    RelativeLayout mianshi_rl;
    @BindView(R.id.dialog_rl)
    RelativeLayout dialog_rl;
    @BindView(R.id.ocr_rl)
    RelativeLayout ocr_rl;
    @BindView(R.id.xrecycle_view_rl)
    RelativeLayout xrecycle_view_rl;
    @BindView(R.id.horizontal_scroll_view_rl)
    RelativeLayout horizontal_scroll_view_rl;
    @BindView(R.id.expand_list_view_rl)
    RelativeLayout expand_list_view_rl;
    @BindView(R.id.all_check_rl)
    RelativeLayout all_check_rl;
    @BindView(R.id.tablayout)
    RelativeLayout tablayout;

    @Override
    public int getLayoutId() {
        mContext = MainActivity.this;
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

    @OnClick({R.id.fragment_tabhost_utils_rl, R.id.guo_custom_view_rl, R.id.convenient_banner_rl, R.id.show_castview_rl,
            R.id.fit_screen_rl, R.id.lock_pattern_rl, R.id.recycle_view_rl, R.id.horizontal_scroll_selected_view_rl,
            R.id.collapsing_toolbar_layout_rl, R.id.dynamic_add_view_rl, R.id.listview_add_delete_item_rl, R.id.popup_window_rl,
            R.id.date_time_dialog_rl, R.id.android_picker_rl, R.id.webview_video_rl, R.id.mianshi_rl, R.id.dialog_rl, R.id.ocr_rl,
            R.id.xrecycle_view_rl, R.id.horizontal_scroll_view_rl, R.id.expand_list_view_rl, R.id.all_check_rl, R.id.tablayout,
            R.id.vertical_tablayout, R.id.rl_water_mark, R.id.rl_alarm_manager, R.id.rl_permission_request, R.id.rl_pull_refresh_test,
            R.id.lock_pattern_rl2, R.id.rl_search_history
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.fragment_tabhost_utils_rl:
                startActivity(new Intent(mContext, FragmentTabhostActivity.class));
                break;
            case R.id.guo_custom_view_rl:
                startActivity(new Intent(mContext, GuoCustomViewActivity.class));
                break;
            case R.id.convenient_banner_rl:
                startActivity(new Intent(mContext, ConvenientBannerActivity.class));
                break;
            case R.id.show_castview_rl:
                startActivity(new Intent(mContext, ShowCastViewActivity.class));
                break;
            case R.id.fit_screen_rl:
                startActivity(new Intent(mContext, FitScreenActivity.class));
                break;
            case R.id.lock_pattern_rl:
                startActivity(new Intent(mContext, LockScreenActivity.class));
                break;
            case R.id.lock_pattern_rl2:
                startActivity(new Intent(mContext, SecondLockScreenActivity.class));
                break;
            case R.id.recycle_view_rl:
                startActivity(new Intent(mContext, RecycleViewActivity.class));
                break;
            case R.id.horizontal_scroll_selected_view_rl:
                startActivity(new Intent(mContext, HorizontalScrollSelectedViewActivity.class));
                break;
            case R.id.collapsing_toolbar_layout_rl:
                startActivity(new Intent(mContext, CollapsingToolbarLayoutActivity.class));
                break;
            case R.id.dynamic_add_view_rl:
                startActivity(new Intent(mContext, DynamicAddViewActivity.class));
                break;
            case R.id.listview_add_delete_item_rl:
                startActivity(new Intent(mContext, ListViewDemoActivity.class));
                break;
            case R.id.popup_window_rl:
                startActivity(new Intent(mContext, PopupWindowSeriesActivity.class));
                break;
            case R.id.date_time_dialog_rl:
                startActivity(new Intent(mContext, DateTimeDialogActivity.class));
                break;
            case R.id.android_picker_rl:
                startActivity(new Intent(mContext, AndroidPickerActivity.class));
                break;
            case R.id.webview_video_rl://加载webview  点击图片或者视频 切换到全屏模式
//                startActivity(new Intent(mContext, WebViewVideoActivity.class));
                startActivity(new Intent(mContext, MyActivity.class));
                break;
            case R.id.mianshi_rl:
                startActivity(new Intent(mContext, KnowledgeActivity.class));
                break;
            case R.id.dialog_rl:
                startActivity(new Intent(mContext, DialogActivity.class));
                break;
            case R.id.ocr_rl:
                startActivity(new Intent(mContext, OCRActivity.class));
                break;
            case R.id.xrecycle_view_rl:
                startActivity(new Intent(mContext, XRecycleViewDemoActivity.class));
                break;
            case R.id.horizontal_scroll_view_rl:
                startActivity(new Intent(mContext, HorizontalScrollViewActivity.class));
                break;
            case R.id.expand_list_view_rl:
                startActivity(new Intent(mContext, ExpandListViewDemoActivity.class));
                break;
            case R.id.all_check_rl:
                startActivity(new Intent(mContext, AllCheckedActivity.class));
                break;
            case R.id.tablayout:
                startActivity(new Intent(mContext, TabLayoutActivity.class));
                break;
            case R.id.vertical_tablayout:
                startActivity(new Intent(mContext, VerticalTablayoutActivity.class));
                break;
            case R.id.rl_water_mark:
                startActivity(new Intent(mContext, WaterMarkActivity.class));
                break;
            case R.id.rl_alarm_manager:
                startActivity(new Intent(mContext, AlarmManagerActivity.class));
                break;
            case R.id.rl_permission_request:
                startActivity(new Intent(mContext, PerActivity.class));
                break;
            case R.id.rl_pull_refresh_test:
                startActivity(new Intent(mContext, PullTestActivity.class));
                break;
            case R.id.rl_search_history:
                startActivity(new Intent(mContext, SearchHistoryActivity.class));
                break;

        }
    }


}
