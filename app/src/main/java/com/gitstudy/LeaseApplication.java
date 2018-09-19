package com.gitstudy;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by wbxmz15 on 2017/11/9.
 */

public class LeaseApplication extends Application {
    public static LeaseApplication mInstance;
    public Intent mPushIntent = null;
    private ArrayList<WeakReference<Activity>> mAllActivitys = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        //  BlockDetectByPrinter.start();
        initImageLoader();
    }

    public void addActivity(Activity activity) {
        mAllActivitys.add(new WeakReference<Activity>(activity));
        Log.e("chunhui", "-------add");
    }

    //杀死所有的activity
    public void killAllActivitys() {
        int size = mAllActivitys.size();
        mPushIntent = null;
        for (int i = 0; i < size; i++) {
            WeakReference<Activity> a = mAllActivitys.get(i);
            if (a != null) {
                Activity as = a.get();
                if (as != null) {
                    as.finish();
                }
            }
        }
        Log.e("chunhui", "-------delete");
        mAllActivitys.clear();
    }

    //移除某个activity
    public void removeActivity(Activity activity) {
        int size = mAllActivitys.size();
        for (int i = 0; i < size; i++) {
            WeakReference<Activity> a = mAllActivitys.get(i);
            if (a != null) {
                if (activity == a.get()) {
                    mAllActivitys.remove(i);
                    break;
                }
                ;
            }
        }
    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .showImageOnFail(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                LeaseApplication.mInstance).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
