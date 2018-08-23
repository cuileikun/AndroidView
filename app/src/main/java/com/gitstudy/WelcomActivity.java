package com.gitstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gitstudy.thirdliarbry.EventBusFirstActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);
        test();
    }

    private void test() {
        String mAccZidValue = "123456789";
        if (null != mAccZidValue && !TextUtils.isEmpty(mAccZidValue)) {
            try {
                String leftVaule = mAccZidValue.substring(0, mAccZidValue.length() - 4);
                String mNewLeftVaule = "";
                for (int i = 0; i < leftVaule.length(); i++) {
                    mNewLeftVaule += "*";
                }

                String rightVaule = mAccZidValue.substring(mAccZidValue.length() - 4, mAccZidValue.length());
//                String replace = rightVaule.replace(rightVaule, "****");
                mTvText.setText(mNewLeftVaule + rightVaule);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @OnClick({R.id.rl_first, R.id.rl_third_liarbry,R.id.android_test})
    void click(View view) {
        switch (view.getId()) {
            case R.id.android_test:
                startActivity(new Intent(WelcomActivity.this, TestActivity.class));
                break;
            case R.id.rl_first:
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                break;
            case R.id.rl_third_liarbry:
                startActivity(new Intent(WelcomActivity.this, EventBusFirstActivity.class));
                break;

        }
    }
}
