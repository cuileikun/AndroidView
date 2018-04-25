package com.gitstudy.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by mbcloud-cuilk on 2018/4/25.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private Toast mToast;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
//        mContext = context;
//        if (intent.getAction().equals(GlobalValues.TIMER_ACTION_REPEATING)) {
//            showToast("AlarmTimer-------->Repeating");
//        } else if (intent.getAction().equals(GlobalValues.TIMER_ACTION)) {
//            showToast("AlarmTimer-------->Timer");
//        }
    }

    private void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }
}
