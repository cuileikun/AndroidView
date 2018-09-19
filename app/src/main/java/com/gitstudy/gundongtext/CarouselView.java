package com.gitstudy.gundongtext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhudong on 2018/9/13.
 *
 * 轮播控件
 */

public class CarouselView extends ViewFlipper {

    protected Context mContext = null;
    //轮播总时间
    protected int interval = -1;


    /**
     * 动画时间
     */
    protected int animDurationIn = -1;
    protected int animDurationOut = -1;
    /**
     * 点击
     */
    protected OnItemClickListener onItemClickListener = null;
    // private MarqueenViewSetListener marqueenViewSetListener = null;

    public CarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CarouselView(Context context) {
        super(context);
        this.mContext = context;
    }

    /**
     * 设置监听接口
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public void setMarqueenViewSetListener(MarqueenViewSetListener marqueenViewSetListener) {
//        this.marqueenViewSetListener = marqueenViewSetListener;
//    }

    public void setInterval(int interval) {
        this.interval = interval;
        setFlipInterval(interval);

    }

    /***
     * 设置轮播进入动画时间
     * @param animDurationIn
     */
    public void setAnimDurationIn(int animDurationIn) {
        this.animDurationIn = animDurationIn;
        if (this.animDurationOut<0){
            setAnimation(animDurationIn, 300);
        }else {
            setAnimation(animDurationIn, this.animDurationOut);
        }


    }

    /***
     * 设置轮播翻页动画时间
     * @param animDurationOut
     */
    public void setAnimDurationOut(int animDurationOut) {
        this.animDurationOut = animDurationOut;
        if (this.animDurationIn<0){
            setAnimation(300, animDurationOut);
        }else {
            setAnimation(this.animDurationIn, animDurationOut);
        }
    }

    protected void setUPMarqueeView(ArrayList<View> views, List<TipsHolderBean> data) {
    }

    /***
     * 设置轮播进入动画
     */
    protected void setAnimation(int animDurationIn, int animDurationOut) {
        try {
            Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
            animIn.setDuration(animDurationIn);
            setInAnimation(animIn);

            Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
            animOut.setDuration(animDurationOut);
            setOutAnimation(animOut);

        } catch (Exception e) {
            ToastUtils.showShort(mContext,e.getMessage());
//            KLog.e(e.getMessage());
        }

    }

    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */

    protected void setViews(List<View> views) {
        if (views == null || views.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            final int position = i;
            //设置监听回调
            final View view = views.get(position);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, view);
                    }
                }
            });
            addView(view);
        }
        startFlipping();
    }


    /***
     *初始化轮播的时间参数
     */
    protected void init(int interval, int animDurationIn, int animDurationOut) {
        setFlipInterval(interval);
        setAnimation(animDurationIn, animDurationOut);
    }


    public void setData(List<TipsHolderBean> data) {
        //如果未设置轮播间隔时间  给一个默认值3秒
        if (this.interval < 0) {
            this.interval = 3000;
        }
        //如果未设置轮播进入时间  给一个默认值300毫秒
        if (this.animDurationIn < 0) {
            this.animDurationIn = 300;

        }
        //如果未设置轮播翻页时间  给一个默认值300毫秒
        if (this.animDurationOut < 0) {
            this.animDurationOut = 300;
        }
        init(this.interval, this.animDurationIn, this.animDurationOut);
        ArrayList<View> views = new ArrayList();
        setUPMarqueeView(views, data);
        setViews(views);
    }

    /**
     * item_view的接口
     */
    interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

//    public interface MarqueenViewSetListener<T> {
//           ArrayList<View> setUPMarqueeView(ArrayList<View> views, List<T> data);
//    }

    public void release() {
        removeAllViews();
        removeCallbacks(null);
        clearAnimation();
        mContext = null;
        onItemClickListener = null;

    }
}
