package com.gitstudy.timecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://blog.csdn.net/lanxingfeifei/article/details/51769740
 * https://blog.csdn.net/chengxu_hou/article/details/54972502
 * https://www.jianshu.com/p/6e72527c03af
 * https://www.2cto.com/kf/201703/615763.html
 */
public class CountTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_time);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_count_down_time, R.id.btn_timer, R.id.btn_handler, R.id.btn_handler_msg,
            R.id.btn_handler_thread,R.id.btn_handler_runner})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_handler_runner:
                startActivity(new Intent(CountTimeActivity.this, HandlerRunnerActivity.class));
                break;
            case R.id.btn_handler_thread:
                startActivity(new Intent(CountTimeActivity.this, HandlerThreadActivity.class));
                break;
            case R.id.btn_handler_msg:
                startActivity(new Intent(CountTimeActivity.this, HandlerMsgActivity.class));
                break;
            case R.id.btn_handler:
                startActivity(new Intent(CountTimeActivity.this, TimerHandlerActivity.class));
                break;
            case R.id.btn_timer:
                startActivity(new Intent(CountTimeActivity.this, TimerActivity.class));
                break;
            case R.id.btn_count_down_time:
                startActivity(new Intent(CountTimeActivity.this, CountDownTimeActivity.class));
                break;
        }
    }
}
