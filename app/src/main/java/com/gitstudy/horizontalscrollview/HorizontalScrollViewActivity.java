package com.gitstudy.horizontalscrollview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HorizontalScrollViewActivity extends AppCompatActivity {
    @BindView(R.id.btn_demo)
    Button btn_demo;
    @BindView(R.id.btn_horizontalscrollview)
    Button btn_horizontalscrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_demo, R.id.btn_horizontalscrollview})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_demo:
                startActivity(new Intent(HorizontalScrollViewActivity.this, FirstHorizontalScrollViewActivity.class));
                break;
            case R.id.btn_horizontalscrollview:
                startActivity(new Intent(HorizontalScrollViewActivity.this, SecondHorizontalScrollViewActivity.class));
                break;

        }
    }


}
