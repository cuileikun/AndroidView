package com.gitstudy.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.listview.three.ExpandListViewDeleteActivity;
import com.gitstudy.listview.three.LinearLayoutDelDemoActivity;
import com.gitstudy.listview.three.ListViewDelDemoActivity;
import com.gitstudy.listview.three.RecycleDelDemoActivity;
import com.gitstudy.listview.three.ViewPagerActivity;

/**
 * 安卓侧滑删除：https://blog.csdn.net/zxt0601/article/details/52303781
 */
public class ListDemo3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo3);
        findViewById(R.id.rv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RecycleDelDemoActivity.class));
            }
        });

        findViewById(R.id.lv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ListViewDelDemoActivity.class));
            }
        });

        findViewById(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LinearLayoutDelDemoActivity.class));
            }
        });

        findViewById(R.id.viewPager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDemo3Activity.this, ViewPagerActivity.class));
            }
        });
        findViewById(R.id.expandableListView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDemo3Activity.this,ExpandListViewDeleteActivity.class));
            }
        });
    }


}
