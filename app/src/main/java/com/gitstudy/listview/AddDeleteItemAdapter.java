package com.gitstudy.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：popular cui
 * 时间：2017/6/24 13:09
 * 功能:listview适配器
 */
public class AddDeleteItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mCitys;

    public AddDeleteItemAdapter(Context context, ArrayList<String> cities) {
        this.mContext = context;
        this.mCitys = cities;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCitys.size();
    }

    @Override
    public Object getItem(int position) {
        return mCitys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = mCitys.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.add_delete_item, null);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        holder.tv_text.setText(name);
        return convertView;
    }

    class Holder {
        private TextView tv_text;
    }


}
