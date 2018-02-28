package com.gitstudy.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gitstudy.MainActivity;
import com.gitstudy.R;

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("全部"));
        mTabLayout.addTab(mTabLayout.newTab().setText("营销"));
        mTabLayout.addTab(mTabLayout.newTab().setText("客户"));
        mTabLayout.addTab(mTabLayout.newTab().setText("项目"));
        mTabLayout.addTab(mTabLayout.newTab().setText("合同"));
        mTabLayout.addTab(mTabLayout.newTab().setText("资管"));
        mTabLayout.addTab(mTabLayout.newTab().setText("运营"));
        mTabLayout.addTab(mTabLayout.newTab().setText("办公"));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(TabLayoutActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
