package com.gitstudy.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_first_apply_permission, R.id.btn_second_apply_permission})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_first_apply_permission:
                startActivity(new Intent(PerActivity.this,PermissionActivity.class));
                break;
            case R.id.btn_second_apply_permission:
                startActivity(new Intent(PerActivity.this,SecondPermissionActivity.class));
                break;
        }
    }
}
