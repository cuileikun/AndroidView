package com.gitstudy.fragmenttabhostutils.huihuituijian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gitstudy.R;

/**
 * Created by mbcloud-cuilk on 2018/6/29.
 */
public class TabThirdFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_third_fragment, container, false);
        return view;
    }
}
