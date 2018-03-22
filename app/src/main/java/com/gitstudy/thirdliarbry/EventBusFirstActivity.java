package com.gitstudy.thirdliarbry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.thirdliarbry.eventbus.EventBusSecondActivity;
import com.gitstudy.thirdliarbry.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 参考文章链接 https://www.jianshu.com/p/f9ae5691e1bb
 */
public class EventBusFirstActivity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button mButton;
    @BindView(R.id.tv1)
    TextView mText;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.btn3)
    Button mButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_first);
        ButterKnife.bind(this);
        mText.setText("今天是星期三");
        EventBus.getDefault().register(this);
        jumpActivity();
        testNianXing();
    }

    public void testNianXing() {
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().register(this);
            }
        });
    }

    private void jumpActivity() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventBusFirstActivity.this, EventBusSecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        mText.setText(messageEvent.getMessage());
    }

//    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
//    public void ononMoonStickyEvent(MessageEvent messageEvent) {
//        tv_message.setText(messageEvent.getMessage());
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
