package com.gitstudy.fragmenttabhostutils.huihuituijian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.fragmenttabhostutils.huihuituijian.eventbus.SpSaveEvent;

import org.greenrobot.eventbus.EventBus;

public class TabHostThirdActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private View mViews[] = new View[3];
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_host_third);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        TabHost.TabSpec spec = null;
        Class[] classes = {TabFirstFragment.class, TabSecondFragment.class, TabThirdFragment.class};
        String[] names = {"申请表", "审批", "审批历史"};
        for (int i = 0; i < 3; i++) {
            spec = mTabHost.newTabSpec(names[i]).setIndicator(getTabItemView(i, names[i]));
            mTabHost.addTab(spec, classes[i], null);
        }
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (((String) mViews[mIndex].getTag()).equals(s)) {
                    return;
                }
                for (int i = 0; i < 3; i++) {


                    if (((String) mViews[i].getTag()).equals(s)) {
                        if (i == 1) {
                            EventBus.getDefault().post(new SpSaveEvent());
                        }
                        mIndex = i;
                        mViews[i].findViewById(R.id.line).setVisibility(View.VISIBLE);
                        ((TextView) mViews[i].findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.bar_red));
                    } else {
                        mViews[i].findViewById(R.id.line).setVisibility(View.GONE);
                        ((TextView) mViews[i].findViewById(R.id.text)).setTextColor(0xff9B9B9B);
                    }
                }
            }
        });
    }

    private View getTabItemView(int i, String name) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_approval_tab_itme, null);
        if (i == 0) {
            view.findViewById(R.id.line).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.bar_red));
        } else {
            view.findViewById(R.id.line).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text)).setTextColor(0xff9B9B9B);
        }
        view.setTag(name);
        mViews[i] = view;
        ((TextView) view.findViewById(R.id.text)).setText(name);
        return view;
    }


}
