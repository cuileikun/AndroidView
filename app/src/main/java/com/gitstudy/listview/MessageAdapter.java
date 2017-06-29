package com.gitstudy.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {

    private ArrayList<String> data;
    private LayoutInflater mInflater;
    private int saveposition;
    private DeleteCallback Callback;

    public MessageAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.message_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.tv_titel);
            holder.viewBtn = (Button) convertView.findViewById(R.id.bt_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(data.get(position));

        holder.viewBtn.setTag(position);// TODO 这里是关键

        holder.viewBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                saveposition = Integer.parseInt(v.getTag().toString());// TODO
                Callback.deletePosition(saveposition);
            }
        });

        return convertView;
    }

    // 提取出来方便点
    public final class ViewHolder {
        public TextView name;
        public Button viewBtn;
    }

    public interface DeleteCallback {

        void deletePosition(int saveposition);
      
    }
    public void setDeleteCallback(DeleteCallback  Callback ){
        this.Callback=Callback;
    }

    public void setData(ArrayList<String> data2) {
       this.data=data2;
    } 
}
