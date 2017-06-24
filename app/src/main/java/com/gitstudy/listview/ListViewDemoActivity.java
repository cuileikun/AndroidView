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


    @Override
    public void initViews() {
        super.initViews();
        mInstance = this;
        context = this;
        top_bar_view = (TopbarView) findViewById(R.id.top_bar_view);
        list_view_add_delete_rl = (RelativeLayout) findViewById(R.id.list_view_add_delete_rl);
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
        }
    }


}
