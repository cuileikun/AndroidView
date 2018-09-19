package com.gitstudy.gundongtext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitstudy.utils.ToastUtils;

import java.util.List;

/**
 * Created by zhudong on 2018/9/14.
 */

public class CarouselHolder {
    private Context mContext;
    private CarouselView mRootView;
    private String toastTitle; //TODO 测试用 需要删除

    public enum TipsType {
        SINGLE, //单行轮播
        DOUBLE;//两行轮播
    }

    public CarouselHolder(Context context, TipsType type) {
        this.mContext = context;
        initView(type);
    }

    //TODO 测试用 需要删除
    public CarouselHolder(Context context, TipsType type, String title) {
        this.mContext = context;
        this.toastTitle = title;
        initView(type);
    }

    private void initView(TipsType type) {
        switch (type) {
            case SINGLE:
                mRootView = new AnnounceCarouselView(this.mContext);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mRootView.setLayoutParams(params);
                mRootView.setOnItemClickListener(new CarouselView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        ToastUtils.showShort(mContext, "点击第" + position + "条进入通知公告详情");
                    }
                });
                break;
            case DOUBLE:
                mRootView = new ShiBorCarouselView(this.mContext);
                ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mRootView.setLayoutParams(params1);
                mRootView.setOnItemClickListener(new CarouselView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        ToastUtils.showShort(mContext, "点击第" + position + "条进入" + CarouselHolder.this.toastTitle + "详情");
                    }
                });
                break;
            default:
                break;
        }
    }

    public void setHolderInterval(int interval) {
        if (mRootView != null) {
            mRootView.setInterval(interval);
        }
    }

    public void setHolderDurationIn(int animDurationIn) {
        if (mRootView != null) {
            mRootView.setAnimDurationIn(animDurationIn);
        }

    }

    /***
     * 设置轮播翻页动画时间
     *
     * @param animDurationOut
     */
    public void setDurationOut(int animDurationOut) {
        if (mRootView != null) {
            mRootView.setAnimDurationOut(animDurationOut);
        }
    }

    public View getRootView() {
        if (mRootView == null) {
            TextView textView = new TextView(mContext);
            textView.setText("加载轮播控件失败，请重试");
            return textView;
        }
        return mRootView;
    }

    public void setData(List<TipsHolderBean> list) {
        if (mRootView == null) {
//            KLog.e("mRootView must not be null");
            ToastUtils.showShort(mContext, "mRootView must not be null");
            return;
        }
        mRootView.setData(list);
    }

    public void release() {
        if (mRootView != null) {
            mRootView.release();
        }
        mRootView = null;
        if (mContext != null) {
            mContext = null;
        }
        mContext = null;
    }
}
