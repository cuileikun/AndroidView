package com.gitstudy.timecount;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHandlerActivity extends AppCompatActivity {
    private int recLen = 11;
    private TextView txtView;
    Timer timer = new Timer();

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    txtView.setText("还剩" + recLen+"s");
                    if (recLen < 0) {
                        timer.cancel();
                        txtView.setVisibility(View.GONE);
                    }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_handler);
        txtView = (TextView) findViewById(R.id.txttime);

        timer.schedule(task, 1000, 1000);       // timeTask
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
}
