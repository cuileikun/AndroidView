package com.gitstudy.watermark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gitstudy.R;

public class WaterMarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark);
    }

    protected void onStart() {
        addWaterMarkView();
        super.onStart();
    }

    /**
     * 添加水印
     */
    private void addWaterMarkView() {
        View waterMarkView = LayoutInflater.from(this)
                .inflate(R.layout.water_layout, null);
        getRootView().addView(waterMarkView, 0);
    }

    /**
     * 获取根布局DecorView
     *
     * @return
     */
    private ViewGroup getRootView() {
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        return rootView;
    }
}
