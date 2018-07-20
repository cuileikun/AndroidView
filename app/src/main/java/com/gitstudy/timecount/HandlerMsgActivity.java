package com.gitstudy.timecount;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gitstudy.R;

public class HandlerMsgActivity extends AppCompatActivity {
    private int recLen = 11;
    private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_msg);
        txtView = (TextView)findViewById(R.id.txttime);

        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);
    }
    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    recLen--;
                    txtView.setText("" + recLen);

                    if(recLen > 0){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                    }else{
                        txtView.setVisibility(View.GONE);
                    }
            }

            super.handleMessage(msg);
        }
    };

}
