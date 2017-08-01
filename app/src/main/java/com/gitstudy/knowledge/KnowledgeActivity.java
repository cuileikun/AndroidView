package com.gitstudy.knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class KnowledgeActivity extends AppCompatActivity {
    @BindView(R.id.toast_loop_rl)
    RelativeLayout toast_loop_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toast_loop_rl})
    void click(View view) {
        switch (view.getId()) {
            case R.id.toast_loop_rl:
                startActivity(new Intent(KnowledgeActivity.this, ToastLoopActivity.class));
                break;


        }
    }


}
