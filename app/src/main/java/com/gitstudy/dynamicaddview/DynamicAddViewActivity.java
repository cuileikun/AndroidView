package com.gitstudy.dynamicaddview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;

public class DynamicAddViewActivity extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_view);
        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                createView();
                addToButton();
            }
        });


        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DynamicAddViewActivity.this,ActualEffectActivity.class));
            }
        });

    }

    public void createView() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout);
        TextView t = new TextView(this);
        t.setText("这是第一个添加的LinearLayout中添加的TextView！");
        ll.addView(t);
    }

    public void addToButton() {
        ViewGroup v = (ViewGroup) findViewById(R.id.newLayout);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setText("我是空LinearLayout里添加的第一个TextView！");
        tv2.setText("我是空LinearLayout里添加的第二个TextView！");
        v.addView(tv1);
        v.addView(tv2);
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        ll.setLayoutParams(params);
        TextView tv3 = new TextView(this);
        tv3.setText("我是添加的LinearLayout中的TextView！");
        ll.addView(tv3);
        v.addView(ll);
    }


}
