package com.gitstudy.oneandallcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

public class AllCheckedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_checked);
        findViewById(R.id.btn_listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this,ListViewItemAllCheckedActivity.class));
            }
        });
        findViewById(R.id.btn_recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this,RecrcleViewItemAllCheckedActivity.class));
            }
        });



    }
}
