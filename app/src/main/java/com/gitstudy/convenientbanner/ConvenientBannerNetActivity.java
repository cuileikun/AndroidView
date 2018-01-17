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
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Arrays;
import java.util.List;

public class ConvenientBannerNetActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnItemClickListener {
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private List<String> networkImages;
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
//    private String[] images = {
//            "https://office.cmb-leasing.com/app/s/image/thumbnil/07c19ec5-9dc0-4e45-83c2-1153bc690042?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/thumbnil/50946a6b-ccd0-4c90-8179-941f8834d7d0?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/thumbnil/623c76e5-c3d5-4700-ac1e-a466174eff8a?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/thumbnil/9e509cda-0ed3-4b99-8802-da4689005d10?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//    };
//    private String[] images = {
//            "https://office.cmb-leasing.com/app/s/image/07c19ec5-9dc0-4e45-83c2-1153bc690042?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/50946a6b-ccd0-4c90-8179-941f8834d7d0?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/623c76e5-c3d5-4700-ac1e-a466174eff8a?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//            "https://office.cmb-leasing.com/app/s/image/9e509cda-0ed3-4b99-8802-da4689005d10?token=JWid5Geu2W6UQqkXOZm+Koa6ywGUqF5gXp+Y1Xx9HatUwqe/+5n7lVF19oNXLlQjkwobF5lI4HNtZRTLehFvYAFmEDVJcSIlBFV3tKGikMNctvKK40W8KswI+AWIJ1hnAaIEao3vKvptLdeGB9OGEUYW0fWnv/FK3RK8mrd/0veolJCqfL1jCwhi6q+VgSZ1EADFc/i9raB5tbhmDMltRQqdyNtwF+LswoHWn+lxMgrlLE/XokTmISz6HnjmB0laSGyVa/rjOhEjDJdgRF5kJw==&deviceId=865376030207730",
//
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenient_banner_net);
        initViews();
        init();
    }

    private void init() {
        initWay();
        initImageLoader();
        //网络加载例子
        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);

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
    private void initViews() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
    }



    //初始化网络图片缓存库
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
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
