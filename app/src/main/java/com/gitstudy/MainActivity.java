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
import com.gitstudy.dateandtime.DateAndTimeActivity;
import com.gitstudy.datetimedialog.DateTimeDialogActivity;
import com.gitstudy.datetimedialog.sec.SecDateTimeDialogActivity;
import com.gitstudy.dialog.DialogActivity;
import com.gitstudy.dynamicaddview.DynamicAddViewActivity;
import com.gitstudy.expandlistviewtest.ExpandListViewDemoActivity;
import com.gitstudy.fitscreenend.FitScreenActivity;
import com.gitstudy.fragmenttabhostutils.FragmentTabhostActivity;
import com.gitstudy.guide.SecSplashActivity;
import com.gitstudy.gundongtext.GunDongActivity;
import com.gitstudy.guocustomview.GuoCustomViewActivity;
import com.gitstudy.history.SearchHistoryActivity;
import com.gitstudy.horizontalscrollselectedview.HorizontalScrollSelectedViewActivity;
import com.gitstudy.horizontalscrollview.HorizontalScrollViewActivity;
import com.gitstudy.html.HtmlTestActivity;
import com.gitstudy.json.JsonTestActivity;
import com.gitstudy.knowledge.KnowledgeActivity;
import com.gitstudy.listview.ListViewDemoActivity;
import com.gitstudy.localshare.LocalShareActivity;
import com.gitstudy.lockscreen.LockScreenActivity;
import com.gitstudy.lockscreen.SecondLockScreenActivity;
import com.gitstudy.oneandallcheck.AllCheckedActivity;
import com.gitstudy.order.OrderActivity;
import com.gitstudy.permission.PerActivity;
import com.gitstudy.photoview.PhotoViewActivity;
import com.gitstudy.popupwindow.PopupWindowSeriesActivity;
import com.gitstudy.pulltorefreshtest.PullTestActivity;
import com.gitstudy.recycleview.RecycleViewActivity;
import com.gitstudy.scrolledittxt.EditTextSwitchActivity;
import com.gitstudy.scrolledittxt.ScrollEditTextActivity;
import com.gitstudy.shape.ShapeActivity;
import com.gitstudy.showcastview.ShowCastViewActivity;
import com.gitstudy.tablayout.TabLayoutActivity;
import com.gitstudy.tablayout.verticaltablayout.VerticalTablayoutActivity;
import com.gitstudy.timecount.CountTimeActivity;
import com.gitstudy.watermark.ShuiYinActivity;
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
            R.id.lock_pattern_rl2, R.id.rl_search_history, R.id.date_time_dialog_rl2, R.id.rl_json, R.id.rl_html, R.id.rl_order,
            R.id.rl_guide, R.id.rl_shape, R.id.rl_count_time, R.id.rl_scroll_edit, R.id.rl_edit_switch, R.id.rl_ipc, R.id.rl_photo_view,
            R.id.rl_gundong_wenzi, R.id.rl_share,R.id.rl_rili,R.id.rl_time
    })
    void click(View view) {
        switch (view.getId()) {
            case R.id.rl_time:
                startActivity(new Intent(mContext, DateAndTimeActivity.class));
                break;
            case R.id.rl_rili:
//                startActivity(new Intent(mContext, RiLiStartActivity.class));
//                startActivity(new Intent(mContext, CustomActivity.class));
                break;
            case R.id.rl_share:
                startActivity(new Intent(mContext, LocalShareActivity.class));
                break;
            case R.id.rl_gundong_wenzi:
                startActivity(new Intent(mContext, GunDongActivity.class));
                break;
            case R.id.rl_photo_view:
                startActivity(new Intent(mContext, PhotoViewActivity.class));
                break;
            case R.id.rl_ipc:
//                startActivity(new Intent(mContext, IpcTestActivity.class));
                break;
            case R.id.rl_edit_switch:
                startActivity(new Intent(mContext, EditTextSwitchActivity.class));
                break;
            case R.id.rl_scroll_edit:
                startActivity(new Intent(mContext, ScrollEditTextActivity.class));
                break;
            case R.id.rl_count_time:
                startActivity(new Intent(mContext, CountTimeActivity.class));
                break;
            case R.id.rl_shape:
                startActivity(new Intent(mContext, ShapeActivity.class));
                break;
            case R.id.rl_guide:
                startActivity(new Intent(mContext, SecSplashActivity.class));
                break;
            case R.id.rl_order:
                startActivity(new Intent(mContext, OrderActivity.class));
                break;
            case R.id.rl_html:
                startActivity(new Intent(mContext, HtmlTestActivity.class));
                break;
            case R.id.rl_json:
                startActivity(new Intent(mContext, JsonTestActivity.class));
                break;
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
            case R.id.date_time_dialog_rl2:
                startActivity(new Intent(mContext, SecDateTimeDialogActivity.class));
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
                startActivity(new Intent(mContext, ShuiYinActivity.class));
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
