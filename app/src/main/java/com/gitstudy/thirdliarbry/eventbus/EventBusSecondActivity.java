package com.gitstudy.thirdliarbry.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitstudy.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventBusSecondActivity extends AppCompatActivity {
    @BindView(R.id.btn2)
    Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);
        ButterKnife.bind(this);
        jumpActivity();
//        testNianXing();
    }

    private void testNianXing() {
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件内容"));
                finish();
            }
        });
    }

    private void jumpActivity() {
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("I am testing  EventBus"));
                finish();
            }
        });
    }


}
