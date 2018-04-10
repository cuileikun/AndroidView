package com.gitstudy.tablayout.verticaltablayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/4/10.
 */
public class DummyAdapter extends FragmentPagerAdapter {
    List<PlaceholderFragment> fragments = new ArrayList<>();

    public DummyAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0; i < 22; i++) {
            fragments.add(PlaceholderFragment.newInstance(i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 22;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return "PAGE 4";
        }
        return null;
    }
}
