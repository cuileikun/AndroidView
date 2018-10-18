package com.gitstudy.watermark.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 第二种定位方式的实现 + ArrayAdapter
 */
public class WaterMarkSecondActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mDatas;
    private ArrayAdapter adapter;
    private LinearLayout llWaterContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark_second);
        initListView();
        initWaterMark();
    }

    private void initWaterMark() {
        llWaterContainer = (LinearLayout) findViewById(R.id.ll_water_back_container);
        //给页面添加水印
        ImageUtil.centerTextBackGround(WaterMarkSecondActivity.this, llWaterContainer);
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.listview);
        //准备数据源
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add(i + "demodemodemodemoodemo" + i);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(adapter);

        //设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort(WaterMarkSecondActivity.this, "你点击的是" + position);
            }
        });
        //设置长按点击事件
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mDatas.remove(position);
                adapter.notifyDataSetChanged();
                ToastUtils.showShort(WaterMarkSecondActivity.this, "你删除的条目是" + position);
                return true;
            }
        });
    }
}
