package com.gitstudy.localshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

public class LocalShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_share);
        findViewById(R.id.btn_share1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_TEXT, "分享测试");
                it.setType("text/plain");
                startActivity(Intent.createChooser(it, "分享内容到"));
            }
        });
        findViewById(R.id.btn_share2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("text/plain");
                it.putExtra(Intent.EXTRA_SUBJECT, "分享");
                it.putExtra(Intent.EXTRA_TEXT, "嗨 我是哈哈哈哈");
                startActivity(Intent.createChooser(it, "分享内容到"));
            }
        });
    }
}
