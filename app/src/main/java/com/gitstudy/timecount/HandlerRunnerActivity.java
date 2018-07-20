package com.gitstudy.timecount;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gitstudy.R;

public class HandlerRunnerActivity extends AppCompatActivity {
    private int recLen = 0;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_runner);
        txtView = (TextView) findViewById(R.id.txttime);

        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen++;
            txtView.setText("" + recLen);
            handler.postDelayed(this, 1000);
        }
    };

}
