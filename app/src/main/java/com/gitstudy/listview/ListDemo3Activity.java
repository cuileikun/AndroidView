package com.gitstudy.listview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gitstudy.R;
import com.gitstudy.listview.three.DeleteBean;
import com.gitstudy.listview.three.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListDemo3Activity extends AppCompatActivity {
    private ListView message_center_lv;
    private ListViewAdapter mAdapter;
    private List<DeleteBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo3);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 6; i++) {
            DeleteBean bean = new DeleteBean();
            bean.time = "上课时间" + i;
            bean.dec = "你的更衣柜将在七天后过期" + i;
            mDataList.add(bean);
        }
        // TODO Auto-generated method stub
        message_center_lv = (ListView) findViewById(R.id.message_center_lv);
        message_center_lv.setDivider(new ColorDrawable(Color.GRAY));
        message_center_lv.setDividerHeight(1);
        mAdapter = new ListViewAdapter(this,mDataList);

        message_center_lv.setAdapter(mAdapter);
    }
}
