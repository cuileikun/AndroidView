package com.gitstudy.oneandallcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.oneandallcheck.onlyonechoice.JustSingleChoiceActivity;
import com.gitstudy.oneandallcheck.onlyonechoice.SecondSingleChoiceActivity;
import com.gitstudy.oneandallcheck.program.SingleAndAllSelectActivity;

public class AllCheckedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_checked);
        findViewById(R.id.btn_listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this, ListViewItemAllCheckedActivity.class));
            }
        });
        findViewById(R.id.btn_recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this, RecrcleViewItemAllCheckedActivity.class));
            }
        });
        findViewById(R.id.btn_listview2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this, SingleAndAllSelectActivity.class));
            }
        });
        findViewById(R.id.btn_listview_single_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this, JustSingleChoiceActivity.class));
            }
        });
        findViewById(R.id.btn_single_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCheckedActivity.this, SecondSingleChoiceActivity.class));
            }
        });


    }
}
