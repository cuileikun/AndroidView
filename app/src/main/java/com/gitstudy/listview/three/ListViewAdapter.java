package com.gitstudy.listview.three;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.gitstudy.R;

import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/6/1.
 */
public class ListViewAdapter extends BaseSwipeAdapter {
    private Context mContext;
    private List<DeleteBean> mDataList;

    public ListViewAdapter(Context mContext, List<DeleteBean> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void fillValues(int position, View convertView) {
        DeleteBean bean = mDataList.get(position);
        // TODO Auto-generated method stub
        ImageView imageView = (ImageView) convertView.findViewById(R.id.message_center_item_iv);
        TextView message_center_item_sk_tv = (TextView) convertView.findViewById(R.id.message_center_item_sk_tv);
        TextView message_center_item_sksj_tv = (TextView) convertView.findViewById(R.id.message_center_item_sksj_tv);
        TextView message_center_item_skms_tv = (TextView) convertView.findViewById(R.id.message_center_item_skms_tv);
        message_center_item_sk_tv.setText(bean.time);
        message_center_item_skms_tv.setText(bean.dec);
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(mContext).inflate(R.layout.message_center_item, null);
        //SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));


        v.findViewById(R.id.message_center_item_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click delete"+position, Toast.LENGTH_SHORT).show();
                mDataList.remove(position);
                notifyDataSetChanged();
            }
        });
        return v;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        // TODO Auto-generated method stub
        return R.id.message_center_item_swipe;
    }
}
