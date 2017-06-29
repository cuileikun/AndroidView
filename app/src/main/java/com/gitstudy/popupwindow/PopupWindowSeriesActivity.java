package com.gitstudy.popupwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;
import com.gitstudy.popupwindow.demo1.PopupWindowActivity;

public class PopupWindowSeriesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_demo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_series);
        initView();
        addListeners();
    }

    private void addListeners() {
        btn_demo1.setOnClickListener(PopupWindowSeriesActivity.this);
    }

    private void initView() {
        btn_demo1 = (Button) findViewById(R.id.btn_demo1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_demo1:
                startActivity(new Intent(PopupWindowSeriesActivity.this,PopupWindowActivity.class));
            break;
        }
    }


}
