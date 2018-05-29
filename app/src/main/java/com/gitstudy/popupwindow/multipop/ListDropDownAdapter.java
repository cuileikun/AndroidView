package com.gitstudy.popupwindow.multipop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.List;


public class ListDropDownAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int checkItemPosition = 0;

//    public void setCheckItem(int position) {
//        checkItemPosition = position;
//        notifyDataSetChanged();
//    }

    public ListDropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView != null) {
//            viewHolder = (ViewHolder) convertView.getTag();
//        } else {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_default_drop_down, null);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//        viewHolder.mText.setText(list.get(position));
////        fillValue(position, viewHolder);
//        return convertView;


        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_default_drop_down, null);
            holder.choice_text = (TextView) convertView
                    .findViewById(R.id.choice_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.choice_text.setText(list.get(position));
        return convertView;

    }
    class ViewHolder {

        TextView choice_text;
    }
//    private void fillValue(int position, ViewHolder viewHolder) {
//        viewHolder.mText.setText(list.get(position));
//        if (checkItemPosition != -1) {
//            if (checkItemPosition == position) {
//                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
//                viewHolder.mText.setBackgroundResource(R.color.white);
//            } else {
//                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
//                viewHolder.mText.setBackgroundResource(R.color.white);
//            }
//        }
//    }

//    static class ViewHolder {
//        @InjectView(R.id.choice_text)
//        TextView mText;
//
//        ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
}
