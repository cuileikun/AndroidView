package com.gitstudy.convenientbanner;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.gitstudy.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ConvenientBannerLocalActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnItemClickListener {
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner_local);
        initViews();
        init();
    }

    private void initViews() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

    }

    private void init() {
        loadTestDatas();
        initWay();
        //本地图片例子
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);

//        convenientBanner.setManualPageable(false);//设置不能手动影响


    }

    private void initWay() {

        //各种翻页效果
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


        String transforemerName = RotateUpTransformer.class.getSimpleName();
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true, transforemer);

            //部分3D特效需要调整滑动速度
            if (transforemerName.equals("StackTransformer")) {
                convenientBanner.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /*
    加入测试Views
    * */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
//        localImages.add(R.drawable.ic_test_0);
//        localImages.add(R.drawable.ic_test_1);
//        localImages.add(R.drawable.ic_test_2);
//        localImages.add(R.drawable.ic_test_3);
//        localImages.add(R.drawable.ic_test_4);
//        localImages.add(R.drawable.ic_test_5);
//        localImages.add(R.drawable.ic_test_6);
    }


    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "监听到翻到第" + position + "了", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

}
