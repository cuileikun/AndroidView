package com.gitstudy.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.gitstudy.MainActivity;
import com.gitstudy.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    /**
     * 轮播图的背景图片
     */
    private int[] imageRes = {R.drawable.guide_1, R.drawable.guide_2,
            R.drawable.guide_3};
    private List<ImageView> mImageLists;
    private Button mButton;
    private LinearLayout mLlAllPoint;
    private View mPoint;
    //两个点之间的距离
    private int mWidth;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mButton = (Button) findViewById(R.id.button);
        mViewPager.setOnPageChangeListener(this);
        mButton.setOnClickListener(this);
        // 所有的点
        mLlAllPoint = (LinearLayout) findViewById(R.id.ll_all_point);
        // 选中的点
        mPoint = findViewById(R.id.point);
    }

    private void initData() {
        // 添加背景图片
        mImageLists = new ArrayList<ImageView>();
        for (int i = 0; i < imageRes.length; i++) {
            ImageView imageView = new ImageView(this);
            // 设置背景图片
            // 需要注意
            // 设置背景图片
            imageView.setBackgroundResource(imageRes[i]);
            // 设置原图
            // imageView.setImageResource(imageRes[i]);

            mImageLists.add(imageView);
            // 初始化点
            View view = new View(this);
            // 切记：如果通过代码设置大小，一定要添加当前类的父类
            // 如果通过代码进行设置当前的大小。那么宽和高全部全部都是像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(getApplicationContext(), 10),
                    DensityUtil.dip2px(getApplicationContext(), 10));
            // 如果当前的点不是第0个。那么就设置左右的间距
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(getApplicationContext(),
                        10);
            }

            // 设置点的宽和高
            view.setLayoutParams(params);

            // 设置默认的图片
            view.setBackgroundResource(R.drawable.dot_normal);
            // 把默认的点丢到线性布局里面
            mLlAllPoint.addView(view);

        }

        GuideAdapter adapter = new GuideAdapter();
        mViewPager.setAdapter(adapter);

        //        transformerList.add(DefaultTransformer.class.getSimpleName());
//        transformerList.add(AccordionTransformer.class.getSimpleName());
//        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
//        transformerList.add(CubeInTransformer.class.getSimpleName());
//        transformerList.add(CubeOutTransformer.class.getSimpleName());
//        transformerList.add(DepthPageTransformer.class.getSimpleName());
//        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
//        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
//        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
//        transformerList.add(RotateDownTransformer.class.getSimpleName());
//        transformerList.add(RotateUpTransformer.class.getSimpleName());
//        transformerList.add(StackTransformer.class.getSimpleName());
//        transformerList.add(ZoomInTransformer.class.getSimpleName());
//        transformerList.add(ZoomOutTranformer.class.getSimpleName());

//        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setPageTransformer(true, new CubeInTransformer());

        mPoint.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mWidth = mLlAllPoint.getChildAt(1).getLeft() - mLlAllPoint.getChildAt(0).getLeft();
            }
        }, 20);

    }


    /**
     * 当viewpager滑动的时候，时时调用
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        //公式 newPosition = width * (position + positionOffset)

        int newposition = (int) (mWidth * (position + positionOffset));
        //平移当前选中的点
        mPoint.setTranslationX(newposition);
    }

    @Override
    public void onPageSelected(int position) {
        // 判断当前的button是否在最后一页
        if (position == mImageLists.size() - 1) {
            // 按钮可见
            mButton.setVisibility(View.VISIBLE);
        } else {
            // 按钮不可见
            mButton.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            // mViewPager.removeViewChild(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // 获取图片添加到viewpager里面
            container.addView(mImageLists.get(position));
            // mViewPager.addViewChild(mImageLists.get(position),position);
            return mImageLists.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mImageLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

    }
}
