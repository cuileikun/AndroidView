package com.gitstudy.fitscreenend;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.WindowManager;

import com.gitstudy.R;
import com.gitstudy.fitscreenend.fragment.ListFragment;
import com.gitstudy.fitscreenend.fragment.PayFragment;
import com.gitstudy.fitscreenend.fragment.RecyclerViewFragment;
import com.gitstudy.fitscreenend.fragment.RecyclerViewGridFragment;
import com.gitstudy.fitscreenend.fragment.RegisterFragment;
import com.gitstudy.fitscreenend.fragment.TestFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * Android屏幕适配方案，直接填写设计图上的像素尺寸即可完成适配。详细使用请登录github查看
 * github:https://github.com/hongyangAndroid/AndroidAutoLayout
 */
public class FitScreenActivity extends AutoLayoutActivity {
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionStatus();
        setContentView(R.layout.activity_fit_screen);


        initView();
        initDatas();
    }

    private void setImmersionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initDatas() {
        ArrayList<Fragment> mList = new ArrayList<Fragment>();
        mList.add(new ListFragment());
        mList.add(new RegisterFragment());
        mList.add(new PayFragment());
        mList.add(new RecyclerViewFragment());
        mList.add(new RecyclerViewGridFragment());
        mList.add(new TestFragment());
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> tabs = null;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> tabs) {
            super(fm);
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int pos) {
            return tabs.get(pos);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }


}
