package com.gitstudy.watermark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.watermark.first.WaterMarkFirstActivity;
import com.gitstudy.watermark.second.WaterMarkSecondActivity;

/**
 * 给背景添加水印  共有两种方式
 */
public class ShuiYinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui_yin);
        findViewById(R.id.btn_first_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShuiYinActivity.this,WaterMarkFirstActivity.class));
            }
        });
        findViewById(R.id.btn_second_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShuiYinActivity.this,WaterMarkSecondActivity.class));
            }
        });


    }
}
