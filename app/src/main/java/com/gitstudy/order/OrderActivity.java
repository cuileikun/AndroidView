package com.gitstudy.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private ListView mListView;
    private WrHisBean wrHisBean;
    private MyAdpter adpter;
    private ArrayList<WrHisItmeBean> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ((TextView) findViewById(R.id.title)).setText("预警历史变更");
        findViewById(R.id.left_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mListView = (ListView) findViewById(R.id.listview);
        adpter = new MyAdpter();
        mListView.setAdapter(adpter);
        initData();

    }


    private void initData() {
         wrHisBean = new WrHisBean();
        mDataList = wrHisBean.HIS_LST;

        WrHisItmeBean bean5 = new WrHisItmeBean();
        bean5.APV_DTE ="2018-07-31 09:39:23";
        bean5.CRT_DTE = "2016-07-04";
        bean5.CRT_USR = "葛智平";
        bean5.WRN_LEV = "F";
        bean5.WRN_LEV_CHN = "红色预警";
        mDataList.add(bean5);

        WrHisItmeBean bean = new WrHisItmeBean();
        bean.APV_DTE = "2018-02-27 10:44:35";
        bean.CRT_DTE = "2018-02-01";
        bean.CRT_USR = "葛智平";
        bean.WRN_LEV = "D";
        bean.WRN_LEV_CHN = "黄色预警";
        mDataList.add(bean);

        WrHisItmeBean bean2 = new WrHisItmeBean();
        bean2.APV_DTE ="2018-01-31 14:45:59";
        bean2.CRT_DTE = "2018-01-23";
        bean2.CRT_USR = "葛智平";
        bean2.WRN_LEV = "D";
        bean2.WRN_LEV_CHN = "黄色预警";
        mDataList.add(bean2);

        WrHisItmeBean bean3 = new WrHisItmeBean();
        bean3.APV_DTE ="2017-12-20 16:35:22";
        bean3.CRT_DTE = "2018-01-23";
        bean3.CRT_USR = "葛智平";
        bean3.WRN_LEV = "D";
        bean3.WRN_LEV_CHN = "黄色预警";
        mDataList.add(bean3);

        WrHisItmeBean bean4 = new WrHisItmeBean();
        bean4.APV_DTE ="2017-07-31 09:39:23";
        bean4.CRT_DTE = "2017-07-04";
        bean4.CRT_USR = "葛智平";
        bean4.WRN_LEV = "C";
        bean4.WRN_LEV_CHN = "观察级";
        mDataList.add(bean4);



        adpter.notifyDataSetChanged();
    }


    private class MyAdpter extends BaseAdapter {
        @Override
        public int getCount() {
            return wrHisBean == null ? 0 : mDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return wrHisBean == null ? null : mDataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, android.view.View view, ViewGroup viewGroup) {
            WrHisItmeBean bean = mDataList.get(i);
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(OrderActivity.this).inflate(R.layout.order_item, null);
                viewHolder.top = view.findViewById(R.id.top);
                viewHolder.bline = view.findViewById(R.id.b_line);
                viewHolder.name = (TextView) view.findViewById(R.id.name);
                viewHolder.t2 = (TextView) view.findViewById(R.id.t2);
                viewHolder.t4 = (TextView) view.findViewById(R.id.t4);
                viewHolder.t6 = (TextView) view.findViewById(R.id.t6);
                viewHolder.img = (ImageView) view.findViewById(R.id.icon);
                // viewHolder.flg = (TextView) view.findViewById(R.id.status);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (i == 0) {
                viewHolder.top.setVisibility(View.VISIBLE);
            } else {
                viewHolder.top.setVisibility(View.GONE);
            }

            if (i ==mDataList.size() - 1) {
                viewHolder.bline.setVisibility(View.GONE);
            } else {
                viewHolder.bline.setVisibility(View.VISIBLE);
            }

            viewHolder.name.setText(bean.WRN_LEV_CHN);
            viewHolder.t2.setText(bean.APV_DTE);
            viewHolder.t4.setText(bean.CRT_USR);
            viewHolder.t6.setText(bean.CRT_DTE);

            if (bean.WRN_LEV.equals("F") || bean.WRN_LEV.equals("E")) {
                viewHolder.img.setImageResource(R.drawable.wr_his_bg_1);
            } else if (bean.WRN_LEV.equals("D")) {
                viewHolder.img.setImageResource(R.drawable.wr_his_bg_2);
            } else if (bean.WRN_LEV.equals("C") || bean.WRN_LEV.equals("B")) {
                viewHolder.img.setImageResource(R.drawable.wr_his_bg_3);
            } else {
                viewHolder.img.setImageResource(R.drawable.wr_his_bg_3);
            }
            //viewHolder.flg.setText(bean.APV_OPN);
            return view;
        }
    }

    private class ViewHolder {
        public TextView name;
        public TextView t2;
        public TextView t4;
        public TextView t6;
        public TextView flg;
        public ImageView img;
        public View top;
        public View bline;
    }

}
