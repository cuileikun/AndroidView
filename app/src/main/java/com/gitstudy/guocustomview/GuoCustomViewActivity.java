package com.gitstudy.guocustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;
import com.gitstudy.guocustomview.zihuiview.ZiHuiViewActivity;
import com.gitstudy.guocustomview.zuheview.ZuHeViewActivity;

/**
 * gitmerge
 * csdn地址：http://blog.csdn.net/guolin_blog/article/details/17357967
 */
public class GuoCustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button zi_hui_view;
    private Button zu_he__view;
    private Button ji_cheng_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guo_custom_view);
        initView();
        addListener();
    }

    private void addListener() {
        zi_hui_view.setOnClickListener(GuoCustomViewActivity.this);
        zu_he__view.setOnClickListener(GuoCustomViewActivity.this);
        ji_cheng_view.setOnClickListener(GuoCustomViewActivity.this);

    }

    private void initView() {
        zi_hui_view = (Button) findViewById(R.id.zi_hui_view);
        zu_he__view = (Button) findViewById(R.id.zu_he__view);
        ji_cheng_view = (Button) findViewById(R.id.ji_cheng_view);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zi_hui_view:
                startActivity(new Intent(GuoCustomViewActivity.this,ZiHuiViewActivity.class));
                break;
            case R.id.zu_he__view:
                startActivity(new Intent(GuoCustomViewActivity.this,ZuHeViewActivity.class));
                break;
            case R.id.ji_cheng_view:
                startActivity(new Intent(GuoCustomViewActivity.this,JiChengViewActivity.class));
                break;
        }
    }


}
