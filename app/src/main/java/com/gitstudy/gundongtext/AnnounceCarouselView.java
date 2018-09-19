package com.gitstudy.gundongtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhudong on 2018/9/13.
 * 通知公告轮播
 */

public class AnnounceCarouselView extends CarouselView {

    public AnnounceCarouselView(Context context) {
        super(context);
    }

    public AnnounceCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void setUPMarqueeView(ArrayList<View> views, List<TipsHolderBean> data) {

        if (null == data) {
//            KLog.e("the data type is not correct");
            return;
        }
        int i = 0;
        while (i < data.size()) {
            //设置滚动的单个布局
            View moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_announce_item, null, false);
            //初始化布局的控件
            final TextView tvTitle = (TextView) moreView.findViewById(R.id.tv_announce_title);
            ImageView ivThumb = (ImageView) moreView.findViewById(R.id.iv_announce_thumb);

            /**
             * 设置监听
             */
            (moreView.findViewById(R.id.ll_root_container)).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick((int)tvTitle.getTag(), v);
                }
            });

            //进行对控件赋值
            TipsHolderBean tipsHolderBean = data.get(i);
            if (tipsHolderBean instanceof TipsHolderBean.AnnounceHolderBean) {
                tvTitle.setText(((TipsHolderBean.AnnounceHolderBean) tipsHolderBean).getTipText());

                ImageLoader.getInstance().displayImage(Uri.parse(((TipsHolderBean.AnnounceHolderBean) tipsHolderBean).getImgUrl()).toString(), ivThumb, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        ((ImageView) view).setImageResource(R.drawable.app_icon);
                        Log.d("ImageLoader", "ImageLoader        onLoadingStarted    " + s);
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        if (failReason != null && failReason.getCause() != null && failReason.getCause().getMessage() != null) {
                            Log.d("ImageLoader", "ImageLoader        onLoadingFailed    " + failReason.getCause().getMessage());
                        }

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        Log.d("ImageLoader", "ImageLoader        onLoadingComplete    " + s);
                        ((ImageView) view).setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
                tvTitle.setTag(i);
                //添加到循环滚动数组里面去
                views.add(moreView);
                i = i + 1;
            }

        }
    }
}
