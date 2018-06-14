package com.gitstudy.oneandallcheck.onlyonechoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

public class SecondSingleChoiceActivity extends AppCompatActivity {
    private ListView mLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_single_choice);
        mLv = (ListView) findViewById(R.id.lv);
        mLv.setAdapter(new ChoicePeopleAdapter(initDatas(), this, mLv));

    }
    public List<SecTestBean> initDatas() {
        List<SecTestBean> datas = new ArrayList<>();
        datas.add(new SecTestBean("","张安",false));
        datas.add(new SecTestBean("","张安",true));
        datas.add(new SecTestBean("","张安",false));
        datas.add(new SecTestBean("","张安",false));

        return datas;
    }
}
