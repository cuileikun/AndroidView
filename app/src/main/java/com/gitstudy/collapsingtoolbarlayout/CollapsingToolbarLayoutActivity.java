package com.gitstudy.collapsingtoolbarlayout;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gitstudy.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {
    /**
     * ScrollView上半部分
     */
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 头像
     */
    @Bind(R.id.head_iv)
    ImageView headIv;
    /**
     * CollapsingToolbarLayout内部显示内容部分
     */
    @Bind(R.id.head_layout)
    LinearLayout headLayout;
    /**
     * tab分类条目
     */
    @Bind(R.id.toolbar_tab)
    TabLayout toolbarTab;

    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    /**
     * 折叠部分
     */
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    /**
     * ViewPager
     */
    @Bind(R.id.main_vp_container)
    ViewPager mViewPager;
    /**
     * ScrollView
     */
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    /**
     * 整个布局
     */
    @Bind(R.id.coordinator_Layout)
    CoordinatorLayout coordinatorLayout;

    private ViewPagerAdapter myPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        ButterKnife.bind(this);

        //用toolBar替换ActionBar
        setToolBarReplaceActionBar();

        //把title设置到CollapsingToolbarLayout上
        setTitleToCollapsingToolbarLayout();

        // 给viewpager设置适配器
        setViewPagerAdapter();

        //tablayout和viewpager建立联系
        setTabBindViewPager();

        //设置毛玻璃效果和沉浸状态栏
        loadBlurAndSetStatusBar();

        //设置头像
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new RoundedCornersTransformation(this,
                90, 0)).into(headIv);
    }

    /**
     * 用toolBar替换ActionBar
     */
    private void setToolBarReplaceActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CollapsingToolbarLayoutActivity.this, "返回", Toast.LENGTH_LONG).show();
                // onBackPressed();//结束程序
            }
        });
    }

    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -headLayout.getHeight() / 2) {
                    collapsingToolbarLayout.setTitle("黄晓果");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }

    /**
     * 给viewpager设置适配器
     */
    private void setViewPagerAdapter() {
        myPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(myPagerAdapter);
    }

    /**
     * tablayout和viewpager建立联系
     */
    private void setTabBindViewPager() {
        //tablayout和viewpager建立联系方式一：tab与viewpager之间的相互绑定
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (toolbarTab));
        toolbarTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (mViewPager));

        //tablayout和viewpager建立联系方式二： 使用此方法Tablayout中的TabItem设置icon无效
        // （android:icon="@drawable/tab_selector" ）只能使用 android:text="分享"
        //  toolbarTab.setupWithViewPager(mViewPager);
    }


    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        //目的是让状态栏半透明
//         StatusBarUtil.setTranslucent(MainActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        //目的是让状态栏全透明
        StatusBarUtil.setTransparent(CollapsingToolbarLayoutActivity.this);

        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        headLayout.setBackground(resource);
                        coordinatorLayout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }

    //在ActionBar设置条目
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.webview:
                msg += "博客跳转";
                break;
            case R.id.weibo:
                msg += "微博跳转";
                break;
            case R.id.action_settings:
                msg += "设置";
                break;
        }
        if (!msg.equals("")) {
            Toast.makeText(CollapsingToolbarLayoutActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }





}
