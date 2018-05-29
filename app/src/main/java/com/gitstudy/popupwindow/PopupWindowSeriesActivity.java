package com.gitstudy.popupwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.popupwindow.custompop.CustomPopupActivity;
import com.gitstudy.popupwindow.multipop.MultiPopupActivity;
import com.gitstudy.popupwindow.popseries.PopupWindowActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupWindowSeriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_series);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_demo1,R.id.btn_demo2,R.id.btn_demo3})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_demo1:
                startActivity(new Intent(PopupWindowSeriesActivity.this, PopupWindowActivity.class));
                break;
            case R.id.btn_demo2:
                startActivity(new Intent(PopupWindowSeriesActivity.this, CustomPopupActivity.class));
                break;
            case R.id.btn_demo3:
                startActivity(new Intent(PopupWindowSeriesActivity.this, MultiPopupActivity.class));
                break;
        }
    }

}
