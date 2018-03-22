package com.gitstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.thirdliarbry.EventBusFirstActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.rl_first,R.id.rl_third_liarbry})
    void click(View view) {
        switch (view.getId()) {
            case R.id.rl_first:
             startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                break;
            case R.id.rl_third_liarbry:
                startActivity(new Intent(WelcomActivity.this, EventBusFirstActivity.class));
                break;

        }
    }
}
