package com.gitstudy.listview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.R;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.widget.TopbarView;

public class ListViewDemoActivity extends QkActivity implements View.OnClickListener {
    public static ListViewDemoActivity mInstance = null;
    private Context context;
    private RelativeLayout list_view_add_delete_rl;
    private TopbarView top_bar_view;
    private RelativeLayout list_demo1_rl;
    private RelativeLayout list_demo2_rl;
    private RelativeLayout rl_cehua_delete;


    @Override
    public void initViews() {
        super.initViews();
        mInstance = this;
        context = this;
        top_bar_view = (TopbarView) findViewById(R.id.top_bar_view);
        list_view_add_delete_rl = (RelativeLayout) findViewById(R.id.list_view_add_delete_rl);
        list_demo1_rl = (RelativeLayout) findViewById(R.id.list_demo1_rl);
        list_demo2_rl = (RelativeLayout) findViewById(R.id.list_demo2_rl);
        rl_cehua_delete = (RelativeLayout) findViewById(R.id.rl_cehua_delete);
    }

    @Override
    public void initData() {
        super.initData();
        top_bar_view.setTopbarTitle("ListView系列");
    }

    @Override
    public void addListeners() {
        super.addListeners();
        top_bar_view.setTopBarClickListener(topListener);
        list_view_add_delete_rl.setOnClickListener(ListViewDemoActivity.this);
        list_demo1_rl.setOnClickListener(ListViewDemoActivity.this);
        list_demo2_rl.setOnClickListener(ListViewDemoActivity.this);
        rl_cehua_delete.setOnClickListener(ListViewDemoActivity.this);
    }

    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            finish();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view_demo;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_view_add_delete_rl:
                startActivity(new Intent(ListViewDemoActivity.this, ListViewAddDeleteItemActivity.class));
                break;

            case R.id.list_demo1_rl:
                startActivity(new Intent(ListViewDemoActivity.this, ListDemoActivity.class));
                break;
            case R.id.list_demo2_rl:
                startActivity(new Intent(ListViewDemoActivity.this, ListDemo2Activity.class));
                break;
            case R.id.rl_cehua_delete:
                startActivity(new Intent(ListViewDemoActivity.this, ListDemo3Activity.class));
                break;

        }
    }


}
