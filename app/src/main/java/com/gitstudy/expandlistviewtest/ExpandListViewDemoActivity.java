package com.gitstudy.expandlistviewtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * demo地址：http://blog.csdn.net/hehaiminginadth/article/details/48294203
 */
public class ExpandListViewDemoActivity extends AppCompatActivity {
    @BindView(R.id.btn_first)
    Button btn_first;
    @BindView(R.id.btn_second)
    Button btn_second;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view_demo);
        mContext=ExpandListViewDemoActivity.this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_first, R.id.btn_second,R.id.btn_multi_item})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_first:
                startActivity(new Intent(mContext,ExpandListViewFirstActivity.class));
                break;
            case R.id.btn_second:
                startActivity(new Intent(mContext,ExpandListViewSecondActivity.class));
                break;
            case R.id.btn_multi_item:
                startActivity(new Intent(mContext,MultiItemExpandListViewActivity.class));
            break;
        }
    }
}
