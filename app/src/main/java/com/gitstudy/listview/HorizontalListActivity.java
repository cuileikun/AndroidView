package com.gitstudy.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalListActivity extends AppCompatActivity {
    private HorizontalListView listview;
    private List<String> todoListData;
    private ListAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list);
        listview = (HorizontalListView) findViewById(R.id.listview);

        todoListData = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            todoListData.add("测试数据" + i);
        }

        todoAdapter = new ListAdapter();
        listview.setAdapter(todoAdapter);
    }

    private class ListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public ListAdapter() {
            inflater = LayoutInflater.from(HorizontalListActivity.this);
        }

        @Override
        public int getCount() {
            return todoListData.size();
        }

        @Override
        public Object getItem(int position) {
            return todoListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.horizonal_list_item, null);
                holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_item.setText(todoListData.get(position));
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_item;
        }
    }

}
