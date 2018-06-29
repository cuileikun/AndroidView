package com.gitstudy.fragmenttabhostutils.huihuituijian;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gitstudy.R;
import com.gitstudy.fragmenttabhostutils.huihuituijian.eventbus.SpSaveEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mbcloud-cuilk on 2018/6/29.
 */
public class TabFirstFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_first_fragment, container, false);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCount(SpSaveEvent event) {
        //  AndroidUtil.sendToast(mContext,"asasasas");
        doSave();
    }

    private void doSave() {

    }
}
