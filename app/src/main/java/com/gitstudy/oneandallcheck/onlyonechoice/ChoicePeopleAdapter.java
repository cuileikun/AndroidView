package com.gitstudy.oneandallcheck.onlyonechoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/6/12.
 */
public class ChoicePeopleAdapter extends BaseAdapter {
    private List<SecTestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    private int mSelectedPos = -1;//实现单选  方法二，变量保存当前选中的position
    private ListView mLv;//需要利用Lv获取某个postion的view

    public ChoicePeopleAdapter(List<SecTestBean> datas, Context context, ListView listView) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        //实现单选方法二： 设置数据集时，找到默认选中的pos
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
        mLv = listView;
    }

    @Override
    public int getCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final CouponVH couponVH;
        if (view == null) {
            view = mInflater.inflate(R.layout.sec_item_coupon, viewGroup, false);
            couponVH = new CouponVH();
            couponVH.ivSelect = (TextView) view.findViewById(R.id.ivSelect);
            couponVH.tvCoupon = (TextView) view.findViewById(R.id.tvCoupon);
            view.setTag(couponVH);
        } else {
            couponVH = (CouponVH) view.getTag();
        }
        couponVH.ivSelect.setSelected(mDatas.get(position).isSelected());
        couponVH.tvCoupon.setText(mDatas.get(position).getUSR_NAM());

        couponVH.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现单选，第一种方法，十分简单， Lv Rv通用,因为它们都有notifyDataSetChanged()方法
                // 每次点击时，先将所有的selected设为false，并且将当前点击的item 设为true， 刷新整个视图
                for (SecTestBean data : mDatas) {
                    data.setSelected(false);
                }
                mDatas.get(position).setSelected(true);

                notifyDataSetChanged();

            }
        });

        return view;
    }


    public static class CouponVH {
        private TextView ivSelect;
        private TextView tvCoupon;
    }
}
