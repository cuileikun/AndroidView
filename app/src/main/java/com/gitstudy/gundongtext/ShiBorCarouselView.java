package com.gitstudy.gundongtext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhudong on 2018/9/13.
 * 通知公告轮播
 */

public class ShiBorCarouselView extends CarouselView {

    public ShiBorCarouselView(Context context) {
        super(context);
    }

    public ShiBorCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void setUPMarqueeView(ArrayList<View> views, List<TipsHolderBean> data) {

        if (null == data) {
//            Log.e("the data type is not correct");
            return;
        }
        int i = 0;
        while (i < data.size()) {
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_shibor_rxtextview_item, null);
            //初始化布局的控件
            final TextView tv1 = (TextView) moreView.findViewById(R.id.tv_shibor_rate);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv_shibor_content);

            /**
             * 设置监听
             */
            (moreView.findViewById(R.id.ll_root_container)).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick((int) tv1.getTag(), v);
                }
            });
            //进行对控件赋值
            TipsHolderBean tipsHolderBean = data.get(i);
            if (tipsHolderBean instanceof TipsHolderBean.ShiBorHolderBean) {
                tv1.setText(((TipsHolderBean.ShiBorHolderBean) tipsHolderBean).getUpTipText());
                tv2.setText(((TipsHolderBean.ShiBorHolderBean) tipsHolderBean).getDownTipText());
                tv1.setTag(i);
                tv2.setTag(i);
                //添加到循环滚动数组里面去
                views.add(moreView);
                i = i + 1;
            }

        }
    }
}
