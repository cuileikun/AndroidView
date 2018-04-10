package com.gitstudy.tablayout.verticaltablayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class VerticalTablayoutActivity extends AppCompatActivity {
    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_tablayout);
        final VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        final VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout);
        tablayout.setTabAdapter(new MyTabAdapter(VerticalTablayoutActivity.this));
        tablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                verticalViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        verticalViewPager.setAdapter(new DummyAdapter(getFragmentManager()));
        verticalViewPager.setPageMargin(getResources(). getDimensionPixelSize(R.dimen.pagemargin));
        verticalViewPager.setPageMarginDrawable(new ColorDrawable( getResources().getColor(android.R.color.white)));
        verticalViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +(scaleFactor - MIN_SCALE) /(1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
    }

}
