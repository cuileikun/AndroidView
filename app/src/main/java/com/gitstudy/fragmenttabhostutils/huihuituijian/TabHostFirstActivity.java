package com.gitstudy.fragmenttabhostutils.huihuituijian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.gitstudy.MainActivity;
import com.gitstudy.R;

public class TabHostFirstActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private int mIndex = 0;
    private View mViews[] = new View[5];
    private String[] mTabName = {"首页", "知识体系", "我的", "导航", "项目"};
    private Class[] mFragments = {TabFirstFragment.class, TabSecondFragment.class, TabThirdFragment.class, TabFourthFragment.class, TabFifthFragment.class};
    private int[] mUnImgIds = {R.drawable.ren_wu_unselected, R.drawable.xiao_xi_unselected, R.drawable.cai_ji, R.drawable.men_hu_unselected, R.drawable.bao_jiao_unselected};
    private int[] mImgIds = {R.drawable.ren_wu_selected, R.drawable.xiao_xi_selected, R.drawable.cai_ji, R.drawable.men_hu_selected, R.drawable.bao_jiao_selected};
    private String mUserTyp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host_first);
        initData();
    }

    private void initData() {
        //设置状态栏的颜色
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < 5; i++) {
            TabHost.TabSpec spec = mTabHost.newTabSpec(mTabName[i]).setIndicator(getTabItemView(i));
            switch (mUserTyp) {
                case "zcs":
                    mTabHost.addTab(spec, mFragments[i], null);
                    break;
                case "market":
                    mTabHost.addTab(spec, mFragments[i], null);
                    break;
                case "common":
                    mTabHost.addTab(spec, mFragments[i], null);
                    break;
                default:
                    mTabHost.addTab(spec, mFragments[i], null);
                    break;
            }
        }
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equals("我的")) {
                    mTabHost.setCurrentTab(mIndex);
                    return;
                }
                for (int i = 0; i < 5; i++) {
                    if (((String) mViews[i].getTag()).equals(s)) {
                        mIndex = i;
                        ((ImageView) mViews[i].findViewById(R.id.img)).setImageResource(mImgIds[i]);
                        ((TextView) mViews[i].findViewById(R.id.text)).setTextColor(0xff3c3c3c);
                    } else {
                        ((ImageView) mViews[i].findViewById(R.id.img)).setImageResource(mUnImgIds[i]);
                        ((TextView) mViews[i].findViewById(R.id.text)).setTextColor(0xffc2c2c2);
                    }
                }
            }
        });
        mTabHost.setCurrentTab(0);

        findViewById(R.id.cai_ji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TabHostFirstActivity.this, MainActivity.class));
            }
        });
    }
    private View getTabItemView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main_tab_itme, null);
        if (i == 0) {
            ((ImageView) view.findViewById(R.id.img)).setImageResource(mImgIds[i]);
            ((TextView) view.findViewById(R.id.text)).setTextColor(0xff3c3c3c);
        } else {
            if (i != 2) {
                ((ImageView) view.findViewById(R.id.img)).setImageResource(mUnImgIds[i]);
                ((TextView) view.findViewById(R.id.text)).setTextColor(0xffc2c2c2);
            } else {
                ((TextView) view.findViewById(R.id.text)).setTextColor(0xffc2c2c2);
            }
        }
        if (i == 1) {
            TextView mMsgTip = (TextView) view.findViewById(R.id.num);
        } else if (i == 0) {
            TextView mTaskMsgTip = (TextView) view.findViewById(R.id.task_num);
        }

        view.setTag(mTabName[i]);
        mViews[i] = view;
        ((TextView) view.findViewById(R.id.text)).setText(mTabName[i]);
        return view;
    }
}
