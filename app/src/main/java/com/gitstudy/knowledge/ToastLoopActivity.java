package com.gitstudy.knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 1,http://blog.csdn.net/liu537192/article/details/41912609?locationNum=6
 * 2,http://blog.csdn.net/heng615975867/article/details/9194219
 */
public class ToastLoopActivity extends AppCompatActivity {
    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_loop);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(ToastLoopActivity.this, WebView1Activity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(ToastLoopActivity.this, WebView2Activity.class));
                break;


        }
    }

}
