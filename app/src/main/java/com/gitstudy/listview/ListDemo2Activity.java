package com.gitstudy.listview;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;
public class ListDemo2Activity extends Activity implements
        Mydilog.IDialogOnclickInterface {

    private ListView listview;
    private List<String> list;
    private Mydilog myDialog;
    private View currentItemView;
    private int longClickPosition;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo2);
        listview = (ListView) findViewById(R.id.listview);

        myDialog = new Mydilog(this, R.style.MyDialogStyle);
        list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("测试数据" + i);
        }

        adapter = new ListAdapter();
        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int position, long id) {
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                // 获取当前view在屏幕中的绝对位置
                // ,location[0]表示view的x坐标值,location[1]表示view的坐标值
                // view.setBackgroundColor(getResources().getColor(R.color.blue));
                currentItemView = view;
                longClickPosition = position;
                // 获取手机的分辨率
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Display display = ListDemo2Activity.this.getWindowManager()
                        .getDefaultDisplay();
                // 将当前窗口的一些信息放在DisplayMetrics类中，
                display.getMetrics(displayMetrics);
                WindowManager.LayoutParams params = myDialog.getWindow()
                        .getAttributes();
                // myDialog当前的属性的设置
                params.gravity = Gravity.BOTTOM;
                params.y = display.getHeight() - location[1];
                myDialog.getWindow().setAttributes(params);
                myDialog.setCanceledOnTouchOutside(true);
                myDialog.show();
                return false;
            }
        });
        myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                currentItemView.setBackgroundColor(getResources().getColor(
                        android.R.color.white));
            }
        });
    }

    private class ListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public ListAdapter() {
            inflater = LayoutInflater.from(ListDemo2Activity.this);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
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
                convertView = inflater.inflate(R.layout.list2_item, null);
                holder.tv_item = (TextView) convertView
                        .findViewById(R.id.tv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_item.setText(list.get(position));
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_item;
        }
    }

    @Override
    public void leftOnclick() {
        myDialog.dismiss();
        currentItemView.setBackgroundColor(getResources().getColor(
                android.R.color.white));
        list.remove(longClickPosition);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void rightOnclick() {
        myDialog.dismiss();
        currentItemView.setBackgroundColor(getResources().getColor(
                android.R.color.white));
        list.remove(longClickPosition);
        adapter.notifyDataSetChanged();
    }


}



